package myServlets;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "GetGameList",
    urlPatterns = {"/getgamelist"}
)
public class LobbyEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    

   /* jdbc:mysql://google/<DATABASE_NAME>
    	?cloudSqlInstance=
    	<INSTANCE_CONNECTION_NAME>
    &socketFactory=com.google.cloud.sql.mysql.SocketFactory
    &user=<MYSQL_USER_NAME>
    &password=<MYSQL_USER_PASSWORD>
    &useSSL=false
  */
  }
  public void doPost(HttpServletRequest request, HttpServletResponse response)     throws IOException {
	  
  }
}