package org.launchcode.flagshipphonewebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlagshipPhoneWebsiteApplication {

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(FlagshipPhoneWebsiteApplication.class, args);
	}

}
