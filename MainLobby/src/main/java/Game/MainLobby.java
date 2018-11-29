package Game;
import java.util.ArrayList;

import java.util.Random;

import com.google.gson.Gson;


public class MainLobby {
	
 private static MainLobby instance = null;
	
private ArrayList<GameLobby> ListGameLobby=new ArrayList<GameLobby>() ;

private ArrayList<ActiveUsers> ListUsers= new ArrayList<ActiveUsers>() ;


public MainLobby() {
}

public static MainLobby getInstance() {
    if (instance == null) {
    	
    	
        instance = new MainLobby();
    }
    return instance;
}
//MainLobby.getInstance();

public String FinishGame(int GameID) {	//will be given to Game Engine delete the game lobby from the list
	if(MainLobby.getInstance().GameIDCheck(GameID)) {
		return "Game with given ID does not exist";
		
	}else {
		for (GameLobby temp : MainLobby.getInstance().ListGameLobby) {
			
			if(temp.getGameID()==GameID) 
			{MainLobby.getInstance().ListGameLobby.remove(temp);
			return "Action completed successfully";	
			}
			}
		return "ListgameLobby list loop fail";
			
	}
}
public String NewGameLobby(int playerNumber,String username) {//add new Game lobby
	if(playerNumber>4||playerNumber<0) {
		return "Wrong number of Players";
	}
	if(MainLobby.getInstance().CheckUserGameLobby(username,0) == false) {
		return "User already in another Game";
	}
	int newGameID= GenarateGameID();
	GameLobby newone=new GameLobby(newGameID, playerNumber);
	newone.JoinGameLobby(GetActiveUser(username));
	MainLobby.getInstance().GetActiveUser(username).setGameLobby(newGameID);
	MainLobby.getInstance().ListGameLobby.add(newone);
	return newone.ToJSon();
}

public String JoinGameLobby(String username,int GameID) {// add user to game lobby (join game lobby)
	boolean flag1= false;
	
			if(!MainLobby.getInstance().GameIDCheck(GameID)) {
				return "INvalid GameID";
			}else {
				flag1=true;
			}
			//the specific game ID exists otherwise INvalid GameID
		
			if(MainLobby.getInstance().CheckUserGameLobby(username,0) == false) {
				return "user already in game";
			}
	if(MainLobby.getInstance().CheckUserGameLobby(username,0) == true) {//confirm user not in any game
		
		if(flag1==true) {
			ActiveUsers one = MainLobby.getInstance().GetActiveUser(username);
			for (GameLobby temp : MainLobby.getInstance().ListGameLobby) {
				
				if(temp.getGameID()==GameID) {
					one.setGameLobby(GameID);//Changing the gameID of user 
					temp.JoinGameLobby(one);//telling gameLobbby to add the user to its list
					return "Success";
				}}
		}
	}
	return "Fail";
}
private boolean GameIDCheck(int GameIDtest) {//checks if GameID exists
	for (GameLobby temp : MainLobby.getInstance().ListGameLobby) {
		
		if(temp.getGameID()==GameIDtest) {
			return true;
		}
}
	return false;
	}

private int GenarateGameID() {

Random rand = new Random();
int number;
number=rand.nextInt();
for (GameLobby temp : MainLobby.getInstance().ListGameLobby) {
	
	if(temp.getGameID()==number || number==0) {
		number = GenarateGameID();
	}	
}
	
	return number;
}
private ActiveUsers GetActiveUser(String username) {
	for (ActiveUsers temp : MainLobby.getInstance().ListUsers) {
		if(temp.getUsername()==username) {
			return temp;
		}}
	return null;
}

public boolean CheckUserGameLobby(String username, int GameID) {
	
	for (ActiveUsers temp : MainLobby.getInstance().ListUsers) {
		if(temp.getUsername()==username) {
			if(temp.getGameLobby()==GameID)
			{
				return true;
			}
		}
	}
	return false;
}
public boolean CheckUserInActivelist (String username) {
	for (ActiveUsers temp : ListUsers) {
		if(temp.getUsername()==username) {
			return true;
		}
	}
	return false;
}
public String toJson() {
	
	Gson gson = new Gson();
	String json = gson.toJson(MainLobby.getInstance());  
	return json; 
}
public void loadFromJson(String json) throws Throwable {
	Gson gson = new Gson();
	MainLobby new1=gson.fromJson(json, MainLobby.class);
	instance.ListGameLobby=new1.ListGameLobby;
	instance.ListUsers=new1.ListUsers;
	new1.finalize();
}
public GameLobby getGameLobbyfromUsername(String username) {
	if(GetActiveUser(username)==null) {
		
	}
	int gameId=GetActiveUser(username).getGameLobby();
	for (GameLobby temp : MainLobby.getInstance().ListGameLobby) {
		if(temp.getGameID()==gameId) {
			return temp;
		}
	}
	return null;
}

public String PopulateActiveUsersDemo(String username,int win,int lose) {
	ActiveUsers user= new ActiveUsers();
	user.setUsername(username);
	user.setGameLobby(0);
	user.setReady(false);
	user.setWin(win);
	MainLobby.getInstance().ListUsers.add(user);
	
	return MainLobby.getInstance().toJson();
}

public void PopulateActiveUsers(String json) {//get the users from khloud in a request and put them here
	
}

}
