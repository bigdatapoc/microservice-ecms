package com.ecms.service;

import java.io.IOException;
import java.util.List;

import javax.jcr.LoginException;
import javax.jcr.NoSuchWorkspaceException;
import javax.jcr.RepositoryException;

import org.springframework.web.multipart.MultipartFile;

public interface FileSyetemService {
	
	boolean add(MultipartFile mediaFile) throws RepositoryException;
	void getFile(String mediafilename) throws LoginException, NoSuchWorkspaceException, RepositoryException, IOException;
	List<String> getallFiles()	throws LoginException, NoSuchWorkspaceException, RepositoryException, IOException;
	void deleteAllNodes() throws LoginException, NoSuchWorkspaceException, RepositoryException;
}
