import org.json.*;
public class ActiveUsers {
private String username;
private int GameLobby;
private int win;
private int Loses;



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

public int getLoses() {
	return Loses;
}

public void setLoses(int loses) {
	Loses = loses;
}
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

}
