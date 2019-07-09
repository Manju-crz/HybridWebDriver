package com.mks.directorizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.nio.file.InvalidPathException;
import java.util.List;

import org.openqa.selenium.InvalidArgumentException;

import com.mks.utilizer.MksArray;

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

	private void checkFolderPathFormat(String folderPath) {
		String[] directories = null;
		
		String fileName = null;
		if(folderPath.contains("/")) {
			directories = folderPath.split("/");
			fileName = directories[directories.length-1];
		}
		else if(folderPath.contains("\\")) {
			int chLastIndx = folderPath.lastIndexOf("\\");
			fileName = folderPath.substring(chLastIndx + 1, folderPath.length());
		}
		else
			throw new InvalidArgumentException(String.format("Given folder path %s is not a valid folder path.", folderPath));
		if(fileName.startsWith("."))
			fileName = fileName.substring(1);
		if(fileName.contains("."))
			throw new InvalidArgumentException(String.format("Given folder path %s is not a valid folder path.", folderPath));
	}
	
	
	public boolean findFile(String filePath) {
		
		checkFileFormatExt(filePath);
		
		File f = new File(filePath);
		return f.exists();
		/*if(!f.exists())
			throw new InvalidArgumentException(String.format("Given path and file %s is not a valid file format.", filePath));
		return true;*/
	}
	
	public boolean findFile(String folderPath, String fileName) {
		
		checkFolderPathFormat(folderPath);
		File f = new File(folderPath);
		if(!f.exists())
			throw new InvalidPathException(folderPath, String.format("Given input folder path %s is not existing in the system.", folderPath));
		String[] files = f.list();
		
		MksArray array = new MksArray();
		if(array.isStringExistsInArray(fileName, files))
			return true;
		else if(array.isStringExistsInArrayIgnoringCase(fileName, files)) {
			System.out.println(String.format("One of the file found in the given folder %s with the file name %s, but file name character cases not matching!\nAll found files are : %s", folderPath, fileName, array.getArrayListFromArray(files).toString()));
			return false;
		}
		else {
			System.out.println(String.format("There is no file found with the file name %s in the given folder %s, the found file names are : %s", fileName, folderPath, array.getArrayListFromArray(files).toString()));
			return false;
		}
	}
	
	public String getFilePath(String ancestorFolderPath, String fileName) {
		
		
		
		return null;
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
