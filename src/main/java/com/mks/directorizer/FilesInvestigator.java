package com.mks.directorizer;

import java.io.File;
import java.util.List;

import org.openqa.selenium.InvalidArgumentException;

public class FilesInvestigator {
	
	/*
	public FilesInvestigator() {
		
	}

	public FilesInvestigator(String filePath) {
		
	}
	*/
	
	
	private void checkFileFormatExt(String filePath) {
		String[] directories = null;
		
		String fileName = null;
		if(filePath.contains("/")) {
			directories = filePath.split("/");
			fileName = directories[directories.length-1];
		}
		else if(filePath.contains("\\")) {
			int chLastIndx = filePath.lastIndexOf("\\");
			fileName = filePath.substring(chLastIndx, filePath.length());
			
		}
		else
			throw new InvalidArgumentException(String.format("Given path and file %s is not a valid file path/format.", filePath));
		if(!fileName.contains("."))
			throw new InvalidArgumentException(String.format("Given path and file %s is not having a file format defined.", filePath));
	}
	
	
	public boolean findFile(String filePath) {
		
		checkFileFormatExt(filePath);
		
		File f = new File(filePath);
		return f.exists();
		/*if(!f.exists())
			throw new InvalidArgumentException(String.format("Given path and file %s is not a valid file format.", filePath));
		return true;*/
	}
	
	public String findFile(String folderPath, String fileName) {
		
		return null;
	}
	
	public void getFilePath(String ancestorFolderPath) {
		
	}
	
	public void renameFile(String filePath) {
		
	}
	
	public void renameFile(String folderPath, String fileName) {
		
	}

	public void deleteFile(String filePath) {
		
	}
	
	public void deleteFile(String folderPath, String fileName) {
		
	}
	
	public void deleteFiles(String... filePath) {
		
	}
	
	public void deleteFiles(String folderPath, String... fileNames) {
		
	}
	
	public List<String> getAllFilesOfFolder(String folderPath) {
		
		
		return null;
	}
	
}
