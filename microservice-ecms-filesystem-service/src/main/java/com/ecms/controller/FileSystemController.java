package com.ecms.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.jcr.LoginException;
import javax.jcr.NoSuchWorkspaceException;
import javax.jcr.RepositoryException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecms.service.Impl.FileSystemServiceImpl;

@RestController
public class FileSystemController {

	@Autowired
	private FileSystemServiceImpl repositoryService;

	@PostMapping("/file")
	public ResponseEntity<?> addFile(@RequestParam("file") MultipartFile mediaFile)
			throws LoginException, NoSuchWorkspaceException, FileNotFoundException, RepositoryException {

		
		this.repositoryService.add(mediaFile);
		return new ResponseEntity<>("added file to Repository", HttpStatus.OK);
	}

	@GetMapping("/file/{filename:.+}")
	public ResponseEntity<?> getFile(@PathVariable("filename") String filename)
			throws LoginException, NoSuchWorkspaceException, RepositoryException, IOException {

		this.repositoryService.getFile(filename);
		return new ResponseEntity<>("got file from Repository", HttpStatus.OK);

	}

	@GetMapping("/files")
	public ResponseEntity<?> getAllFiles()
			throws LoginException, NoSuchWorkspaceException, RepositoryException, IOException {

		List<String> fileNameList = this.repositoryService.getallFiles();
		return new ResponseEntity<>(fileNameList,HttpStatus.OK);

	}
	
	@GetMapping("/delete")
	public ResponseEntity<?> deleteNode()
			throws LoginException, NoSuchWorkspaceException, RepositoryException, IOException {

		this.repositoryService.deleteAllNodes();
		return new ResponseEntity<>("deleted all data",HttpStatus.OK);

	}
	
	@GetMapping("/version")
	public ResponseEntity<?> addVersion(@RequestParam("file") MultipartFile mediaFile)
			throws LoginException, NoSuchWorkspaceException, RepositoryException, IOException {

		this.repositoryService.addVersionService(mediaFile);
		return new ResponseEntity<>("deleted all data",HttpStatus.OK);

	}

}
