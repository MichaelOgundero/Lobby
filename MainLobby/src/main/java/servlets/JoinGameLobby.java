package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Game.MainLobby;
import util.ExternalDataGetter;



@WebServlet(
name = "joingamelobby",
urlPatterns = {"/JoinGameLobby"}
)
public class JoinGameLobby extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String gameID=request.getParameter("GameID");
		String userName=request.getParameter("userName");
		//Here should be Kholoud Active user list request and updating my own list
		ExternalDataGetter mine= new ExternalDataGetter();
		try {
			mine.UpdateActiveUsers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int gameId=Integer.parseInt(gameID);
		
		if(MainLobby.getInstance().GetActiveUser(userName) == null) {
			response.sendError(400, "Wrong username");
		}else if(MainLobby.getInstance().GetActiveUser(userName).getGameLobby()!=0)
		{
			response.sendError(400, "User already in Game");
		}
			else if(MainLobby.getInstance().GameIDCheck(gameId)==false){
			response.sendError(400, "Wrong GameID");
		}else if(MainLobby.getInstance().GetGameLobby(gameId).isInGame()==true){
			response.sendError(400, "GameLobby already InGame");
		}else if(MainLobby.getInstance().GetGameLobby(gameId).getMode()==
				MainLobby.getInstance().GetGameLobby(gameId).getNumberofActiveUsers()){
			response.sendError(400, "GameLobby Full");
		}else {
		MainLobby.getInstance().JoinGameLobby(userName, gameId);
		
		//printing
		String mainlobby =MainLobby.getInstance().toJson();
		PrintWriter out = response.getWriter();
	    out.print(mainlobby);
	    response.setStatus(response.SC_OK, "Action completed successfully");
		}
	}


}
