package com.utilities;

import java.io.FileReader;
import java.util.Properties;

public class ReadPropertiesFile {

	public  void readpropertyfile() throws Exception {
		
		FileReader filereader = new FileReader("./configfiles/config.properties");
		Properties prop= new Properties();
		prop.load(filereader);
		
		System.out.println(prop.getProperty("browser"));
		System.out.println(prop.getProperty("testurl"));
		System.out.println(prop.getProperty("implicitwait"));
		System.out.println(prop.getProperty("explicitwait"));
	}

}
