package Game;

import java.util.ArrayList;
import java.util.Random;

import com.google.gson.Gson;


public class GameLobby {
private int GameID;
private String Name;
private ArrayList<ActiveUsers> users;
private int mode;
private int Seed;


public String JoinGameLobby(ActiveUsers s) {//add users to game lobby
	if(Check(s.getUsername())==true ) {
		return "user already in Gamelobby";//user already in Gamelobby
	}
	if(users.size()+1>mode) {
		return "size full";//size full
	}
	users.add(s);
	s.setGameLobby(this.GameID);
	return "Success";
}
public GameLobby(int gameID, int mode) {//game lobby constructor
	super();
	this.setSeed(0);
	this.setName(GameNameGenarator());
	GameID = gameID;
	this.mode = mode;
}
private String GameNameGenarator() {
	Random one =new Random();
	String set="game"+one.nextInt();
	return set;
}

public String LeaveGameLobby(String username) {//user leave game lobby
	if(Check(username)) {
		users.remove(getUser(username));
		getUser(username).setGameLobby(0);
		return this.ToJSon();
	}else {	return "user not in game";}
	

}
public String Ready( String username) {	//user ready or unready
	
	if(Check(username)) {
		getUser(username).setReady(true);
	return this.ToJSon();
	}
	else return "user not in game";

}
public String UnReady( String username) {	//user ready or unready
	
	if(Check(username)) {
		getUser(username).setReady(false);
	return this.ToJSon();
	}
	else return "user not in game";

}
public String RenameLobby(String username ,  String Newname) //rename game lobby
{
	if(Check(username)) {
		this.Name=Newname;
		return this.ToJSon();
	}
	return "user not ingame";
}
public String SetSeed( String username, int NewSeed) { 	// set seed
	if(Check(username)) {
		this.Seed=NewSeed;
		return this.ToJSon();
	}
	return "user not ingame";
}

private boolean Check (String username) {
	for (ActiveUsers temp : users) {
		if(temp.getUsername()==username) {
			return true;
		}
	}
	return false;
}


public boolean allReady() {		// check if all users are ready
	int flag = 0;
	for (ActiveUsers temp : users) {
		if(temp.isReady()==true)
			flag++;
	}
	if(flag==4)
		{return true;}
	else
		return false;
	 
}

private ActiveUsers getUser(String username) {
	for (ActiveUsers temp : users) {
		if(temp.getUsername()==username) {
			return temp;
		}
	}
	return null;
}
public String ToJSon() {
	Gson gson = new Gson();
	String json = gson.toJson(this);  
	return json; 
}
public int getGameID() {
	return GameID;
}
public void setGameID(int gameID) {
	GameID = gameID;
}
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public int getSeed() {
	return Seed;
}
public void setSeed(int seed) {
	Seed = seed;
}
}
