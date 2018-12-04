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
name = "finishgame",
urlPatterns = {"/FinishGame"}
		)
public class FinishGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String GameID=request.getParameter("GameID");
		int gameID=Integer.parseInt(GameID);
		//load from my database
		
		//Here should be Kholoud Active user list request and updating my own list
		ExternalDataGetter mine= new ExternalDataGetter();
		try {
			mine.UpdateActiveUsers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(MainLobby.getInstance().GameIDCheck(gameID)==false) {
			response.sendError(400, "Game with given ID does not exist");
		}else {
		MainLobby.getInstance().FinishGame(gameID);
		//printing
		 String mainlobby =MainLobby.getInstance().toJson();
		 PrintWriter out = response.getWriter();
	     out.print(mainlobby);
	     response.setStatus(response.SC_OK, "Action completed successfully");
	     //save to my database
	
		}}


}
