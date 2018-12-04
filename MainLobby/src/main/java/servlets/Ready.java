package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Game.ActiveUsers;
import Game.MainLobby;



@WebServlet(
name = "ready",
urlPatterns = {"/Ready"}
)

public class Ready extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String userName=request.getParameter("username");
		
		//Here should be Kholoud Active user list request and updating my own list
		ExternalDataGetter mine= new ExternalDataGetter();
		try {
			mine.UpdateActiveUsers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if((MainLobby.getInstance().GetActiveUser(userName).getGameLobby()==0)||
				(MainLobby.getInstance().getGameLobbyfromUsername(userName).getGameID()
				!=MainLobby.getInstance().GetActiveUser(userName).getGameLobby())) {
			response.sendError(400, "User not in GameLobby");
		}else {
			
		
		String gameLobby=MainLobby.getInstance().getGameLobbyfromUsername(userName).Ready(userName);
		 PrintWriter out = response.getWriter();
	     out.print(gameLobby);
	     response.setStatus(response.SC_OK, "Action completed successfully");
		}
		//calling start game f all users are ready
		if(MainLobby.getInstance().getGameLobbyfromUsername(userName).allReady()==true) {
			ArrayList<String> userstoPass=MainLobby.getInstance().getGameLobbyfromUsername(userName).getActiveUsers();
			int seed=MainLobby.getInstance().getGameLobbyfromUsername(userName).getSeed();
			ExternalDataGetter passer=new ExternalDataGetter();
			int responseCode=passer.CallStartGame(userstoPass, seed);
			if(responseCode==201) {
				System.out.println("The responseCode recieved succesfully : 201");
			}
		}
		}

	

}
