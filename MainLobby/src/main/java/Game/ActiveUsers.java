package Game;

import com.google.gson.Gson;

public class ActiveUsers {
private String username= new String();
private int GameLobby;
private int win; // this indicates the Score parameter that will be parsed from the Security
private boolean ready=false;


public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public int getGameLobby() {
	return GameLobby;
}

public void setGameLobby(int gameLobby) {
	GameLobby = gameLobby;
}

public int getWin() {
	return win;
}

public void setWin(int win) {
	this.win = win;
}


public String ToJSon() {
	Gson gson = new Gson();
	String json = gson.toJson(this);  
	return json; 
}
public void fromJson(String json) {
	Gson gson = new Gson();
	ActiveUsers new1=gson.fromJson(json, ActiveUsers.class);
	this.username=new1.username;
	this.GameLobby=new1.GameLobby;
	this.win=new1.win;

}
/*
public JSONObject getJsonData() {
	JSONObject test= new JSONObject();
	try {
		test.put("username", this.username);
		test.put("GameLobby",this.GameLobby);
		test.put("win", this.win);
		test.put("Loses", this.Loses);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return test;
}
*/

public boolean isReady() {
	return ready;
}

public void setReady(boolean ready) {
	this.ready = ready;
}

}
