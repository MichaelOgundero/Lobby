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
       

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String playernum=request.getParameter("playernumber");
		String userName=request.getParameter("username");
		//Here should be Kholoud Active user list request and updating my own list
		
		int playernumber=Integer.parseInt(playernum);
		
		String gameLobby=MainLobby.getInstance().NewGameLobby(playernumber, userName);
		
		 PrintWriter out = response.getWriter();
	     out.print(gameLobby);
		
	}

}
