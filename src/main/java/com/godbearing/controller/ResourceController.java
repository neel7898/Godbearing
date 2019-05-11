package com.godbearing.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.godbearing.service.ZipService;

@RestController
public class ResourceController {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	ZipService zipService;
		
	@GetMapping("/listImageDirectory")
	public List<Set<String>> listFilesUsingDirectoryStream(@RequestParam("dir") String dir) throws IOException {
		
		List<Set<String>> list = new ArrayList<Set<String>>();
		
	    Set<String> fileList = new HashSet<>();
	    try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(context.getRealPath("/"+dir)))) {
	        for (Path path : stream) {
	            if (!Files.isDirectory(path)) {
	                fileList.add(path.getFileName()
	                    .toString());
	            }
	        }
	    }
	    
	    Set<String> folderList = new HashSet<>();
	    try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(context.getRealPath("/"+dir)))) {
	        for (Path path : stream) {
	            if (Files.isDirectory(path)) {
	            	folderList.add(path.getFileName()
	                    .toString());
	            }
	        }
	    }
	    list.add(folderList);
	    list.add(fileList);
	    return list;
	}
	
	@PostMapping("/deleteFolder")
	public String deleteFile(@RequestParam("dir") String dir) throws IOException {
		
		    Path fileToDeletePath = Paths.get(context.getRealPath(dir));
		    this.deleteDirectoryStream(fileToDeletePath);
	    return "{\"msg\":\"deleted successfully\"}";
	}
	
	@PostMapping("/createFolder")
	public String createFolder(@RequestBody String folderName) {
		Path dirPathObj = Paths.get(context.getRealPath("/"+folderName));
		String msg="Folder Created successfully";
        boolean dirExists = Files.exists(dirPathObj);
        if(dirExists) {
           // System.out.println("! Directory Already Exists !");
        } else {
            try {
                // Creating The New Directory Structure
                Files.createDirectories(dirPathObj);
                msg = "! New Directory Successfully Created !";
            } catch (IOException ioExceptionObj) {
                System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
            }
        }
		return "{\"msg\":\""+msg+"\"}";
	}
	
	@PostMapping("/uploadFile")
	public String uploadFile(@RequestParam("uploadedFile") MultipartFile[] uploadedFile, @RequestParam("data") String currDir) {
		String msg="Folder Uploaded successfully";
		
		for(MultipartFile file:uploadedFile) {
			 if (file.isEmpty()) {
		            msg  = "Please select a file to upload";   
		        }else {
		        	 try {
		        		 String fileName = currDir.replace("\"", "")+"/"+file.getOriginalFilename();
		        		 Path dirPathObj = Paths.get(context.getRealPath("/"+fileName));
		        		
		        	        boolean dirExists = Files.exists(dirPathObj);
		                 // Get the file and save it somewhere
		        	        if(!dirExists) {
		                 byte[] bytes = file.getBytes();
		                 Files.write(dirPathObj, bytes);
		             }} catch (IOException e) {
		                 e.printStackTrace();
		             }
		        }
		}
		
		return "{\"msg\":\""+msg+"\"}";
	}
	
	void deleteDirectoryStream(Path path) throws IOException {
		  Files.walk(path)
		    .sorted(Comparator.reverseOrder())
		    .map(Path::toFile)
		    .forEach(File::delete);
		}
	
	@GetMapping(value = "/zip/{path}", produces = "application/zip")
	public void zipImagesAndDownload(HttpServletResponse response, @PathVariable(name="path") String path ) {
		response.setHeader("Content-Disposition", "attachment; filename=download.zip");
		try (ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream())) {
	       this.zipService.zipFile(new File(context.getRealPath("/"+path)), zipOut);
	    } catch (Exception e) {
	        // Do something with Exception
	    }        
		
	}
	
}
