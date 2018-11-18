package Game;

import java.util.List;
import java.util.Random;

import com.google.gson.Gson;


public class GameLobby {
private int GameID;
private String Name;
private List<ActiveUsers> users;
private int mode;
private int Seed;
private List<ActiveUsers> Readyusers;

public String JoinGameLobby(ActiveUsers s) {//add users to game lobby
	if(Check(s.getUsername())==true ) {
		return "user already in Gamelobby";//user already in Gamelobby
	}
	if(users.size()+1>mode) {
		return "size full";//size full
	}
	users.add(s);
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
		return "Success";
	}else {	return "user not in game";}
	

}
public String Ready( String username) {	//user ready or unready
	
	if(Check(username)) {
		if(CheckReadyuser(username)) {
			Readyusers.remove(getUser(username));
			return "user unready";
		}else {
		Readyusers.add(getUser(username)); 
		return "user ready";
		}
	}else return "user not in game";
}
public String RenameLobby(String username ,  String Newname) //rename game lobby
{
	if(Check(username)) {
		this.Name=Newname;
		return "Success";
	}
	return "user not ingame";
}
public String SetSeed( String username, int NewSeed) { 	// set seed
	if(Check(username)) {
		this.Seed=NewSeed;
		return "Success";
	}
	return "user not ingame";
}
private boolean CheckReadyuser (String username) {
	for (ActiveUsers temp : Readyusers) {
		if(temp.getUsername()==username) {
			return true;
		}
	}
	return false;
}
private boolean Check (String username) {
	for (ActiveUsers temp : users) {
		if(temp.getUsername()==username) {
			return true;
		}
	}
	return false;
}

/*
public JSONObject GetGameLobbyData() {
	JSONObject   value =  	new JSONObject();
try {
	value.put("GameID",this.GameID);
	value.put("Name",this.Name);
	JSONArray Users=new JSONArray();
	for(ActiveUsers temp : users) {
	Users.put(temp.getJsonData());
	}
	value.put("Users",Users);
	value.put( "Mode", this.mode);
	value.put("Seed", this.Seed);
} catch (JSONException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

	return value;
}*/

public boolean allReady() {		// check if all users are ready
	boolean flag;
	if(Readyusers.size()==4)
		{flag=true;}
	else {
		flag=false;
	}
	return flag;
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
