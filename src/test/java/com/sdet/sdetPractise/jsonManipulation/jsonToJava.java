package com.sdet.sdetPractise.jsonManipulation;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jsonToJava {
	
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException, StreamWriteException, DatabindException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		CustomerExtended customer = mapper.readValue(new File("/Users/ashishk/eclipse-workspace/sdetPractise/src/test/java/resources/CustomerDetailsExtended.json"), CustomerExtended.class);
		
		System.out.println(customer.getCustomerName());
		
	}

}
