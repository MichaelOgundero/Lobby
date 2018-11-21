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
name = "Useradd",
urlPatterns = {"/useradd"}
)
public class useradd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		 PrintWriter out = response.getWriter();
		 
		String username=request.getParameter("username");
		 out.print("Entered username\t"+username+"\n");
	MainLobby.getInstance().PopulateActiveUsersDemo(username, 0, 0);
	String mainlobby =MainLobby.getInstance().toJson();
	
	
	     out.print(mainlobby);
		
	}

}
