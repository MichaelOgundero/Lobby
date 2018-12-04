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
name = "getgamelist",
urlPatterns = {"/GetGameList"}
)
public class GetGameList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		ExternalDataGetter mine= new ExternalDataGetter();
		try {
			mine.UpdateActiveUsers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		String mainlobby =MainLobby.getInstance().toJson();
		
		 PrintWriter out = response.getWriter();
	     out.print(mainlobby);
	     response.setStatus(response.SC_OK, "Action completed successfully");
	}

	

}
