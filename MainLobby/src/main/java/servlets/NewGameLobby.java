package servlets;
import Game.MainLobby;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
name = "newgamelobby",
urlPatterns = {"/NewGameLobby"}
)

public class NewGameLobby extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String playernum=request.getParameter("playerNumber");
		String userName=request.getParameter("userName");
		//Here should be Kholoud Active user list request and updating my own list
		ExternalDataGetter mine= new ExternalDataGetter();
		try {
			mine.UpdateActiveUsers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int playernumber=Integer.parseInt(playernum);
		
		if(playernumber>4) {
			response.sendError(400,"Wrong number of Players");
		}else if(MainLobby.getInstance().GetActiveUser(userName).getGameLobby()!=0) {
			response.sendError(400, "User already in Game");
		}else {
		String gameLobby=MainLobby.getInstance().NewGameLobby(playernumber, userName);
		
		 PrintWriter out = response.getWriter();
	     out.print(gameLobby);
		response.setStatus(201, "Action completed successfully");
		}
	}

}
