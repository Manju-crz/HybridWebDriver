package com.mks.directorizer;

import java.net.URL;
import java.nio.file.InvalidPathException;

public class SystemDirectoryPath {
	
	private static ClassLoader classLoader = SystemDirectoryPath.class.getClassLoader();
	
	
	
	public static String findAbsolutePath(String filePath) {
		URL url = classLoader.getResource(filePath);
		if(url == null) {
	        throw new InvalidPathException("Unable to find path: %s", filePath);
	    }
		return url.getPath();
	}
	
	
	public String constructAbsoluteFolderPath(String filePath) {
		
	    String path = findAbsolutePath(filePath);
	    String OS = System.getProperty("os.name").toLowerCase();       
        if (OS.contains("windows"))
    	    path = findAbsolutePath(filePath).substring(1);
        return path;
	}
	
	
	public String getCurrentProjectFolder() {
		return System.getProperty("user.dir");
	}
	
	public String constructProjectFolderPath(String filePath) {
		
	    String path = null;
	    String OS = System.getProperty("os.name").toLowerCase();       
	    
        if (OS.contains("windows")) {

        	ClassLoader classLoader = SystemDirectoryPath.class.getClassLoader();
    		URL url = classLoader.getResource(filePath);
    	    if(url == null) {
    	        throw new InvalidPathException("Unable to find path: %s", filePath);
    	    }
    	    path = url.getPath().substring(1);
        }
        return path;
	}
	
}
