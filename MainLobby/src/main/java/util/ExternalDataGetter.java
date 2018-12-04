package util;
import Game.*;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


import javax.servlet.ServletException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 

import com.google.gson.Gson;

public class ExternalDataGetter {
	class Wrapper{
		String name;
		String lastName;
		String email;
		int score;
	}
	class Wrapper2{
		 ArrayList<String> usernames=new ArrayList<>() ;
		 int seed;
		public Wrapper2(ArrayList<String> username, int seed1) {
			usernames=username;
			seed=seed1;
		}
		public String ToJSon() {
			Gson gson = new Gson();
			String json = gson.toJson(this,this.getClass());  
			return json; 
		}
	}
	private String ActiveUserURL= "https://security-dot-training-project-lab.appspot.com/activeusers";//the link of security API
	private String StartGameURL="https://gameengine-dot-training-project-lab.appspot.com/start";
	
	public void loadFromDatabase() {//load from MS-sql (call first)
		
		//DB connection
		 Connection connection = null;
	        try {
	            connection = dbConnection.conn();
	        }
	        catch(ServletException ex) {
	            System.out.println("SQL Error: " + ex.getMessage());
	        }
	        PreparedStatement preparedStatement=null;
	       
	        try {
		preparedStatement = connection.prepareStatement("SELECT * FROM `activeusers`");
	
		 ResultSet resultSet = preparedStatement.executeQuery();
		 while (resultSet.next())
         {
			 ActiveUsers temp=new ActiveUsers();
			 temp.setUsername(resultSet.getString("username"));
			 temp.setGameLobby(resultSet.getInt("gamelobby"));
			 temp.setWin( resultSet.getInt("win"));
			 temp.setReady(resultSet.getBoolean("ready"));
			 
			 MainLobby.getInstance().loadUsersFromDB(temp);
         }
		 preparedStatement = connection.prepareStatement("SELECT * FROM `lobbydata`");
		 ResultSet resultSet2 = preparedStatement.executeQuery();
		 while (resultSet2.next())
         {
			 GameLobby temp=new GameLobby();
			 temp.setGameID(resultSet2.getInt("gameID"));//name,  mode, seed, inGame
			 temp.setName(resultSet2.getString("name"));
			 temp.setSeed(resultSet2.getInt("seed"));
			 temp.setMode(resultSet2.getInt("mode"));
			 temp.setInGame(resultSet2.getBoolean("inGame"));
			 temp.AddUsersToLobbyList(); //adding users with the same GameID to the gameID
			 
			 MainLobby.getInstance().loadLobbiesFromDB(temp);
         }
	        } catch (SQLException ex) {
	            System.out.println("SQL Error: " + ex.getMessage());
	        }
	}
	
	public void SaveToDatabase() {//Save to Ms-sql	(call third)
		//DB connection
        //load userslist and GameLobbyList into database
        Connection connection = null;
        try {

            connection = dbConnection.conn();

        }
        catch(ServletException ex) {
            System.out.println("SQL Error: " + ex.getMessage());
        }
        PreparedStatement preparedStatement=null;
       
        try {
        	//emptying the tables
        	 preparedStatement=connection.prepareStatement("TRUNCATE [TABLE] activeusers");
             preparedStatement.executeUpdate();
             preparedStatement=connection.prepareStatement("TRUNCATE [TABLE] lobbydata");
             preparedStatement.executeUpdate();
        	
             //filling the rows with data
        	for(ActiveUsers user: MainLobby.getInstance().getActiveUsersList()) {
            preparedStatement=connection.prepareStatement("INSERT INTO activeusers(username, gamelobby, win, ready)VALUES(?,?,?,?)");

            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setInt(2, user.getGameLobby());
            preparedStatement.setInt(3, user.getWin());
            preparedStatement.setBoolean(4, user.isReady());
            
            preparedStatement.executeUpdate();
        	}
        	
        	for(GameLobby temp:MainLobby.getInstance().getGameLobbyList()) {
            preparedStatement = connection.prepareStatement("INSERT INTO lobbydata(gameID, name, mode, seed, inGame) VALUES(?,?,?,?,?)");

            preparedStatement.setInt(1,temp.getGameID());
            preparedStatement.setString(2, temp.getName());
            preparedStatement.setInt(3,temp.getMode());
            preparedStatement.setInt(4,temp.getSeed());
            preparedStatement.setBoolean(5,temp.isInGame());
            
            preparedStatement.executeUpdate();
        	}
            connection.close();
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex.getMessage());
        }
	}
	public void UpdateActiveUsers()throws IOException {//Request Security Api for User list and update the instance(Call second)
		URL obj = new URL(ActiveUserURL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			Type listType = new TypeToken<ArrayList<Wrapper>>(){}.getType();
			ArrayList<Wrapper> ActiveUserList = new Gson().fromJson(response.toString(), listType);
			
		
			System.out.println("number of my Wrappers \t"+ ActiveUserList.size());
			
			for(int i=0;i<ActiveUserList.size();i++) {
				//check if the user is in any game
				if(MainLobby.getInstance().CheckUserInActivelist(ActiveUserList.get(i).email)) {
					System.out.println("user already in the list");
				}else {
					ActiveUsers new1=new ActiveUsers();
					new1.setUsername(ActiveUserList.get(i).email);
					new1.setWin(ActiveUserList.get(i).score);
					MainLobby.getInstance().PopulateActiveUsers(new1);
				}
				
			}
			
			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("GET request not worked");
		}
		
	}
	public int CallStartGame(ArrayList<String> usernames,int seed) throws IOException{
		Wrapper2 mine=new Wrapper2(usernames,seed);
		String json = mine.ToJSon();
		 
		URL obj = new URL(StartGameURL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.setDoInput(true);
		//setting up connection
		con.setRequestProperty("Content-Type", "json");
		con.setRequestProperty("Content-Length", String.valueOf(json));
		
		OutputStream os = con.getOutputStream();
		BufferedWriter writer = new BufferedWriter(
		        new OutputStreamWriter(os, "UTF-8"));
		writer.write(json);
		writer.flush();
		writer.close();
		os.close();
		con.connect();
		
		System.out.println(json);
		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);
	/*	
	 
	 if(responseCode==HttpURLConnection.HTTP_OK) {//the game has started the responce code recieved
			MainLobby.getInstance().getGameLobbyfromUsername(usernames.get(0)).setInGame(true);
		}
		
	*/
		MainLobby.getInstance().getGameLobbyfromUsername(usernames.get(0)).setInGame(true);
		return responseCode;
		 
	}
}
