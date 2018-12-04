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
name = "unready",
urlPatterns = {"/Unready"}
)

public class Unready extends HttpServlet {
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
		String gameLobby=MainLobby.getInstance().getGameLobbyfromUsername(userName).UnReady(userName);
		
		if((MainLobby.getInstance().GetActiveUser(userName).getGameLobby()==0)||
				(MainLobby.getInstance().getGameLobbyfromUsername(userName).getGameID()
				!=MainLobby.getInstance().GetActiveUser(userName).getGameLobby())) {
			response.sendError(400, "User not in GameLobby");
		}else {
		 PrintWriter out = response.getWriter();
	     out.print(gameLobby);
	     response.setStatus(response.SC_OK, "Action completed successfully");
	}}

}
