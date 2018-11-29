package servlets;
import Game.*;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import com.google.gson.Gson;

public class ExternalDataGetter {
	class Wrapper{
		String name;
		String lastName;
		String email;
		int score;
	}
	private String ActiveUserURL= "https://security-dot-training-project-lab.appspot.com/activeusers";//the link of security API
	
	public void loadFromDatabase() {//load from MS-sql (call first)
		
	}
	public void SaveToDatabase() {//Save to Ms-sql	(call third)
		
	}
	public void UpdateActiveUsers()throws IOException {//Request Security Api for User list and update the instance(Call second)
		URL obj = new URL(ActiveUserURL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			Type listType = new TypeToken<ArrayList<Wrapper>>(){}.getType();
			List<Wrapper> ActiveUserList = new Gson().fromJson(response.toString(), listType);
			
		
			System.out.println("number of my Wrappers"+ ActiveUserList.size());
			
			for(int i=0;i<ActiveUserList.size();i++) {
				ActiveUsers mine= new ActiveUsers();
				//check if the user is in any game
				//if() {}
				
			}
			
			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("GET request not worked");
		}
		
	}
	
}
