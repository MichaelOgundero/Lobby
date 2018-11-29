package servlets;
import Game.*;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import com.google.gson.Gson;

public class ExternalDataGetter {
	class Wrapper{
		String name;
		String lastName;
		String email;
		int score;
	}
	private String ActiveUserURL= "https://security-dot-training-project-lab.appspot.com/activeusers";//the link of security API
	private String StartGameURL="";
	public void loadFromDatabase() {//load from MS-sql (call first)
		
	}
	public void SaveToDatabase() {//Save to Ms-sql	(call third)
		
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
			
		
			System.out.println("number of my Wrappers"+ ActiveUserList.size());
			
			for(int i=0;i<ActiveUserList.size();i++) {
				ActiveUsers mine= new ActiveUsers();
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

	public void CallStartGame(String[] useranmes,int seed) throws IOException{
		URL obj = new URL(StartGameURL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);
		
		
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			
			
		} else {
			System.out.println("GET request not worked");
		}
	}
}
