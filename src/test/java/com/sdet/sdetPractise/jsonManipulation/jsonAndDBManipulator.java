package com.sdet.sdetPractise.jsonManipulation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class jsonAndDBManipulator {
	
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException, StreamWriteException, DatabindException, IOException {
		
		Class.forName("com.mysql.cj.jdbc.Driver"); // it is present in the mysql-jdbc maven dependency. This gives DriverManager service.

		
		Connection connection = null;
		
		JSONArray jsonArray = new JSONArray();
		ObjectMapper mapper = new ObjectMapper();

		
		List<Customer> customers = new ArrayList<Customer>();
		
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business?useSSL=false","root","admin@123");

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("Select * from CustomerInfo where location = 'Asia';");
		
		while(resultSet.next()) {
			
			Customer customer = new Customer();
			customer.setCourseName(resultSet.getString(1));
			customer.setPurchasedDate(resultSet.getString(2));
			customer.setAmount(resultSet.getInt(3));
			customer.setLocation(resultSet.getString(4));
			customers.add(customer);
				
		}
		
		for(int i =0; i<customers.size();i++) {
			mapper.writeValue(new File("//Users//ashishk//eclipse-workspace//sdetPractise//src//test//java//resources//CustomerDetails"+i+".json"),customers.get(i));
			System.out.println(mapper.writeValueAsString(customers.get(i))); // Converting JsonObject to JsonString using jackson library ---1
			//Student student = new ObjectMapper().readValue(jsonString, Student.class);  ----> Converting JsonString to JsonObject using Jackson Library --4
			
			
			Gson gSon = new Gson(); // Library to convert Json object to JsonString. Could have been done by mapper.writeValueAsString method as well

			String jsonString = gSon.toJson(customers.get(i)); // Converting JsonObject to JsonString using GSon Library --2
			//Student s = gSon.fromJson(jsonString, Student.class)  ---> Converting JsonString to JsonObject using GSon library ---5

			jsonArray.add(jsonString);
			
		}
		
		JSONObject obj = new JSONObject(); // For the nested Json , with data as the key and array of Json objects as value.
		obj.put("data",jsonArray);
		

		
		System.out.println(obj.toJSONString()); // Converting jsonObject to JsonString using Json Simple Library ---3
		
		String newString = StringEscapeUtils.unescapeJava(obj.toJSONString()); //removing the extra escaped characters
		String finalString = newString.replace("\"{", "{").replace("}\"", "}"); //more formatting
		System.out.println(finalString);
		
		
		//***************** Print to file using the File Writer class in java.io which takes the Json String************
		
		try(FileWriter file = new FileWriter("//Users//ashishk//eclipse-workspace//sdetPractise//src//test//java//resources//Final.json")) {
			file.write(finalString);
		}catch(Exception e) {
			System.out.println("Exception in worting to file");
		}

		
		//*****************Print to file using the Jackson Library to write to file which takes a Json Object*******************
		
		JSONObject jsonObjectFromJsonSimple = new JSONObject();
		JSONParser parser = new JSONParser();  
		try {
			jsonObjectFromJsonSimple = (JSONObject) parser.parse(finalString); //Converting jsonString to JsonObject using Json Simple Library --6
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		mapper.writeValue(new File("//Users//ashishk//eclipse-workspace//sdetPractise//src//test//java//resources//Final3.json"),jsonObjectFromJsonSimple);

		
		connection.close();
		  
		
	}

}
