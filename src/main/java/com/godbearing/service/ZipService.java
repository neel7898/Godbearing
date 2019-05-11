package com.godbearing.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Service;

@Service
public class ZipService {
	
	
	public void zipFile(File fileToZip, ZipOutputStream zipOut) throws IOException {
			Path sourceFolderPath = Paths.get(fileToZip.getAbsolutePath());
		  Files.walkFileTree(sourceFolderPath, new SimpleFileVisitor<Path>() { public
		  FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws
		  IOException { zipOut.putNextEntry(new
		  ZipEntry(sourceFolderPath.relativize(file).toString())); Files.copy(file,
		  zipOut); zipOut.closeEntry(); return FileVisitResult.CONTINUE; } });
		  zipOut.close();
		 
		
		/*
		 * String fileName = fileToZip.getName(); if (fileToZip.isHidden()) { return; }
		 * if (fileToZip.isDirectory()) { if (fileName.endsWith("/")) {
		 * zipOut.putNextEntry(new ZipEntry(fileName)); zipOut.closeEntry(); } else {
		 * zipOut.putNextEntry(new ZipEntry(fileName + "/")); zipOut.closeEntry(); }
		 * File[] children = fileToZip.listFiles(); for (File childFile : children) {
		 * this.zipFile(childFile, zipOut); } return; } FileInputStream fis = new
		 * FileInputStream(fileToZip); ZipEntry zipEntry = new ZipEntry(fileName);
		 * zipOut.putNextEntry(zipEntry); byte[] bytes = new byte[1024]; int length;
		 * while ((length = fis.read(bytes)) >= 0) { zipOut.write(bytes, 0, length); }
		 * fis.close();
		 */
	    }
 
}

