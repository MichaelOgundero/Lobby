package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Game.MainLobby;

@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
	  response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	  /*
	    ExternalDataGetter mine= new ExternalDataGetter();
		String[] users= {"houssam","nesq1","rick"};
		try {
			mine.CallStartGame(users, 15);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
    response.getWriter().print("Hello App Engine Rovshan Shirinli!\r\n");
    
  }
public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
	/*
	BufferedReader in = new BufferedReader(new InputStreamReader(
			request.getInputStream()));
	String inputLine;
	StringBuffer res = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
		res.append(inputLine);
	}
	in.close();
	
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    
	    PrintWriter out = response.getWriter();
	     out.print(res.toString());
	     */
	     response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		    
	     response.getWriter().print("Hello App Engine Rovshan Shirinli!\r\n");
	  }
}