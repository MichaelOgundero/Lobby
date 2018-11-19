package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Game.MainLobby;

//this is a temporary servlet to test adding users on my own
@WebServlet("/useradd")
public class useradd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String username=request.getParameter("username");
		
	MainLobby.getInstance().PopulateActiveUsersDemo(username, 0, 0);
	String mainlobby =MainLobby.getInstance().toJson();
	
		 PrintWriter out = response.getWriter();
	     out.print(mainlobby);
		
	}

}
