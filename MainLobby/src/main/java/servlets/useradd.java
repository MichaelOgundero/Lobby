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
		 String paramName = "username";

		String username=request.getParameter(paramName);
	
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		 PrintWriter out = response.getWriter();
	//	 out.print("Entered username\t"+username+"\n");
	MainLobby.getInstance().PopulateActiveUsersDemo(username, 0, 0);
	String firstuseradded =MainLobby.getInstance().toJson();
	   //out.println(request.getQueryString());
	
	//  out.print(firstuseradded);						//first user
		
	
	//  out.println(MainLobby.getInstance().PopulateActiveUsersDemo("default",20,10));		//adding default user
	
	MainLobby.getInstance().NewGameLobby(2, username);
	MainLobby.getInstance().CheckUserInActivelist(username);
	
	int ID =MainLobby.getInstance().getGameLobbyfromUsername(username).getGameID();
	
	MainLobby.getInstance().CheckUserGameLobby(username,ID);
	 String mainlobby1 = MainLobby.getInstance().toJson();
	 MainLobby.getInstance().PopulateActiveUsersDemo("default", 0, 0);
	// out.print(mainlobby1);							//creating a gamelobby from user
	MainLobby.getInstance().JoinGameLobby("default", ID);
	mainlobby1 = MainLobby.getInstance().toJson();
	
//	out.print(mainlobby1);							//adding user default to that lobby
	MainLobby.getInstance().getGameLobbyfromUsername(username).LeaveGameLobby(username);
	
	String mainlobby =MainLobby.getInstance().toJson();
	   out.println(request.getQueryString());
	
	    out.print(mainlobby);				//removing first user from the lobby
		
	}

}
