package Game;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(MainLobby.getInstance().PopulateActiveUsersDemo("user1",2,1));

		System.out.println(MainLobby.getInstance().PopulateActiveUsersDemo("user2",20,10));

		
	/*	MainLobby.getInstance().getGameLobbyfromUsername("user1").LeaveGameLobby("user1");
		String mainlobby =MainLobby.getInstance().toJson();
		System.out.println(mainlobby);*/

		MainLobby.getInstance().NewGameLobby(2, "user1");
		MainLobby.getInstance().CheckUserInActivelist("user1");
		int ID =MainLobby.getInstance().getGameLobbyfromUsername("user1").getGameID();
		 String mainlobby1 = MainLobby.getInstance().toJson();
		System.out.println(mainlobby1);

		MainLobby.getInstance().JoinGameLobby("user2", ID);
		String mainlobby2 =MainLobby.getInstance().toJson();
		System.out.println(mainlobby2);
	}

}
