package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Game.MainLobby;

public class main {

	public static void main(String[] args) {
/*
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println("Enter user 1 username");
		String user1=input.next();
		
		System.out.println(MainLobby.getInstance().PopulateActiveUsersDemo(user1,2,1));
		String user2=input.next();
		System.out.println("Enter user 2 username");
		System.out.println(MainLobby.getInstance().PopulateActiveUsersDemo(user2,20,10));
		
		
		MainLobby.getInstance().getGameLobbyfromUsername("user1").LeaveGameLobby("user1");
		String mainlobby =MainLobby.getInstance().toJson();
		System.out.println(mainlobby);
		
		MainLobby.getInstance().NewGameLobby(2, user1);
		MainLobby.getInstance().CheckUserInActivelist(user1);
		
		int ID =MainLobby.getInstance().getGameLobbyfromUsername(user1).getGameID();
		MainLobby.getInstance().CheckUserGameLobby(user1,ID);
		 String mainlobby1 = MainLobby.getInstance().toJson();
		System.out.println(mainlobby1);

		MainLobby.getInstance().JoinGameLobby(user2, ID);
		String mainlobby2 =MainLobby.getInstance().toJson();
		System.out.println(mainlobby2);
		
		System.out.println(MainLobby.getInstance().getGameLobbyfromUsername(user2).ToJSon());
		System.out.println(MainLobby.getInstance().getGameLobbyfromUsername(user1).ToJSon());
		
		System.out.println(	MainLobby.getInstance().getGameLobbyfromUsername(user1).LeaveGameLobby(user1));
		mainlobby2 =MainLobby.getInstance().toJson();
		System.out.println(mainlobby2);
		input.close();
		*/
		ExternalDataGetter mine= new ExternalDataGetter();
		try {
			mine.UpdateActiveUsers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//trying the khouloud call
		MainLobby.getInstance().NewGameLobby(2, "Houssam_mahdi");
		System.out.println(MainLobby.getInstance().getGameLobbyfromUsername("Houssam_mahdi").ToJSon());
		String mainlobby2 =MainLobby.getInstance().toJson();
		System.out.println(mainlobby2);
		
		mine.SaveToDatabase();
		
		
	/*
		ArrayList<String> users1=new ArrayList<>();
		users1.add("houssam");
		users1.add("test1");
		users1.add("user3");
		try {
			mine.CallStartGame(users1, 15);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}

}