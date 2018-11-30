package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Game.MainLobby;



@WebServlet(
name = "setseed",
urlPatterns = {"/SetSeed"}
)
public class SetSeed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String seed=request.getParameter("seed");
		String userName=request.getParameter("username");
		//Here should be Kholoud Active user list request and updating my own list
		ExternalDataGetter mine= new ExternalDataGetter();
		try {
			mine.UpdateActiveUsers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int seed1=Integer.parseInt(seed);
		
		String gameLobby=MainLobby.getInstance().getGameLobbyfromUsername(userName).SetSeed(userName, seed1);
		
		 PrintWriter out = response.getWriter();
	     out.print(gameLobby);
	}

}
