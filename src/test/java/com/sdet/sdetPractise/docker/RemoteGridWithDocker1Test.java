package com.sdet.sdetPractise.docker;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class RemoteGridWithDocker1Test extends DockerBaseTest {
	

	@Test
	public  void test1() throws MalformedURLException {
		// TODO Auto-generated method stub

		ChromeOptions options = new ChromeOptions();
	    DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome"); 	//To specify the browser

		capabilities.setCapability(ChromeOptions.CAPABILITY, options);


		//FirefoxOptions options = new FirefoxOptions();

		URL url = new URL("http://localhost:4444/wd/hub");
		RemoteWebDriver driver = new RemoteWebDriver(url,options);
		driver.get("https://facebook.com");
		System.out.println(driver.getTitle());
	}

}
