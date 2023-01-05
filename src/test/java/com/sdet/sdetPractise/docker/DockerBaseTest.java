package com.sdet.sdetPractise.docker;

import java.io.*;
import java.util.Calendar;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DockerBaseTest {
	
//	@Test
//	public void initializeDockerServer() throws IOException, InterruptedException {
//
//		final String[] cmd = new String[] {"/Users/ashishk/eclipse-workspace/sdetPractise/docker-start.sh"};
//		ProcessBuilder cb = new ProcessBuilder(cmd);
//
//		try {
//
//		Process p = cb.start();
//		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
//		String outputLine= null;
//		while((outputLine= reader.readLine())!=null) {
//			System.out.println(outputLine);
//		}
//
//		}catch(Exception e) {
//
//		}
//	}

		@BeforeTest
		public void initializeDockerServer() throws IOException, InterruptedException {

		File outFile = new File("/Users/ashishk/eclipse-workspace/sdetPractise/output.txt");

		if(outFile.delete()){
			System.out.println("File deleted successfully)");
		}

		Calendar c = Calendar.getInstance();
		c.add(Calendar.SECOND,45);
		Long stopTime = c.getTimeInMillis();

		String outputFile = "output.txt";
		boolean found = false;

		final String[] cmd = new String[] {"/Users/ashishk/eclipse-workspace/sdetPractise/docker-start.sh"};
		ProcessBuilder cb = new ProcessBuilder(cmd);
		Process p = cb.start();
		Thread.sleep(5000);// deliberate time to allow for the file creation else line 52 will break

			while(System.currentTimeMillis()<stopTime && !found){

			BufferedReader reader = new BufferedReader( new FileReader(outputFile));
			String readLine = reader.readLine();

			while(readLine != null && !found)
			{
				if(readLine.contains("Node has been added"))
				{
					System.out.println("Node added to hub Text Found");
					found=true;
					break;
				}

				readLine=reader.readLine(); // to iterate to next Line

			}

			reader.close();

		}
	}

	@AfterTest
	public void destroyDockerServer() throws IOException, InterruptedException {

		Calendar c = Calendar.getInstance();
		c.add(Calendar.SECOND,45);
		Long stopTime = c.getTimeInMillis();

		String outputFile = "output.txt";
		boolean found = false;

		final String[] cmd = new String[] {"/Users/ashishk/eclipse-workspace/sdetPractise/docker-stop.sh"};
		ProcessBuilder cb = new ProcessBuilder(cmd);
		Process p = cb.start();
		Thread.sleep(5000);// deliberate time to allow for the file creation else line 52 will break

		while(System.currentTimeMillis()<stopTime && !found){

			BufferedReader reader = new BufferedReader( new FileReader(outputFile));
			String readLine = reader.readLine();

			while(readLine != null && !found)
			{
				if(readLine.contains("Shutdown complete"))
				{
					System.out.println("Shutdown Complete Text Found");
					found=true;
					break;
				}
				readLine=reader.readLine(); // to iterate to next Line
			}

			reader.close();

		}
	}
}
