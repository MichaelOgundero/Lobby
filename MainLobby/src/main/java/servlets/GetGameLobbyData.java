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
name = "getgamelobbydata",
urlPatterns = {"/GetGameLobbyData"}
)
public class GetGameLobbyData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String userName=request.getParameter("userName");
	
		//Here should be Kholoud Active user list request and updating my own list
		ExternalDataGetter mine= new ExternalDataGetter();
		try {
			mine.UpdateActiveUsers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(MainLobby.getInstance().GetActiveUser(userName) == null) {
			response.sendError(400, "Wrong username");
		}else if((MainLobby.getInstance().GetActiveUser(userName).getGameLobby()==0)||
				(MainLobby.getInstance().getGameLobbyfromUsername(userName).getGameID()
				!=MainLobby.getInstance().GetActiveUser(userName).getGameLobby())){
			response.sendError(400, "No Access");
		}
		else {
		String gameLobby=MainLobby.getInstance().getGameLobbyfromUsername(userName).ToJSon();
		 PrintWriter out = response.getWriter();
	     out.print(gameLobby);
	     response.setStatus(response.SC_OK, "Action completed successfully");
		}
	}


}
