package com.uilangage.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	
	public final static String FILE_UPLOAD_PATH = "C:\\information1278\\snsproject\\upload\\sns";
	
	public static String saveFile(int userId, MultipartFile file) {
		
		String directoryName = "/" + userId + "_" + System.currentTimeMillis();
		
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(directoryPath);
		
		if(!directory.mkdir()) {
			//디렉토리 생성 실패
			return null;
		}
		
		// 파일 저장
		String filePath = directoryPath + "/" + file.getOriginalFilename();
		
		try {
			byte[] bytes = file.getBytes();
			
			Path path = Paths.get(filePath);
			Files.write(path, bytes);
		} catch (IOException e) {
			
			e.printStackTrace();
			return null;
		}
		
		return "/images" + directoryName + "/" + file.getOriginalFilename();
		
	}
	
	
	
}
