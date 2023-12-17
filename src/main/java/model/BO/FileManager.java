package model.BO;

import java.util.UUID;

public class FileManager {
	public static String basePath = "D:\\d5thSemesterUniversity\\Web\\Source code Eclipse\\ITNews\\src\\main\\webapp";
	
	public String extractFileNameWithoutExtension(String filePath) {
	    // Remove the file extension
	    int dotIndex = filePath.lastIndexOf('.');
	    String fileName = "default";
	    if (dotIndex > 0) {
	    	fileName = filePath.substring(0, dotIndex);
	    }

	    return fileName;
	}
	
	public String extractFileNameExtension(String filePath) {
	    // Remove the file extension
	    int dotIndex = filePath.lastIndexOf('.');
	    String fileExtension = "png";
	    if (dotIndex > 0) {
	    	fileExtension = filePath.substring(dotIndex + 1);
	    }

	    return fileExtension;
	}
	
	public String generateUniqueId() {
		UUID id = UUID.randomUUID();
		return id.toString();
	}
}
