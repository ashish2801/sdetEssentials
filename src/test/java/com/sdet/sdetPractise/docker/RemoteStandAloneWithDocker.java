package com.sdet.sdetPractise.docker;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteStandAloneWithDocker {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub

		//ChromeOptions options = new ChromeOptions();
		
		FirefoxOptions options = new FirefoxOptions();
	    //DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		URL url = new URL("http://localhost:4444/wd/hub");
		RemoteWebDriver driver = new RemoteWebDriver(url,options);
		driver.get("https://facebook.com");
		System.out.println(driver.getTitle());
	}

}
