import java.util.List;
import java.util.Random;
import org.json.*;

public class GameLobby {
private int GameID;
private String Name;
private List<ActiveUsers> users;
private int mode;
private int Seed;

public boolean JoinGameLobby(ActiveUsers s) {
	if(Check(s.getUsername())==true ) {
		return false;//user already in Gamelobby
	}
	if(users.size()+1>mode) {
		return false;//size full
	}
	users.add(s);
	return true;
}
public GameLobby(int gameID, int mode) {
	super();
	this.Seed=0;
	this.Name=GameNameGenarator();
	GameID = gameID;
	this.mode = mode;
}
private String GameNameGenarator() {
	Random one =new Random();
	String set="game"+one.nextInt();
	return set;
}

public boolean LeaveGameLobby(String username) {
	return false;
}
public boolean Ready( String username) {
	return false;
}
public boolean RenameLobby(String username ,  String Newname)
{
	return false;
}
public void SetSeed( String username, int NewSeed) {
	
	
}
private boolean Check (String username) {
	for (ActiveUsers temp : users) {
		if(temp.getUsername()==username) {
			return true;
		}
	}
	return false;
}
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
}
public int getGameID() {
	return GameID;
}
public void setGameID(int gameID) {
	GameID = gameID;
}
}
