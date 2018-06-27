package com.ecms.service.Impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.jcr.Binary;
import javax.jcr.LoginException;
import javax.jcr.NoSuchWorkspaceException;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.commons.io.FileUtils;
import org.modeshape.jcr.api.JcrTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecms.entity.Event;
import com.ecms.producer.Producer;
import com.ecms.service.FileSyetemService;
import com.ecms.util.FileSystemConstants;

@Service
public class FileSystemServiceImpl implements FileSyetemService {

	@Autowired
	private Repository repository;

	@Autowired
	private Producer producer;

	private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemServiceImpl.class);

	/**
	 * add File to Modeshape repository
	 *
	 * @param mediaFile
	 * 
	 * @return boolean
	 * @throws RepositoryException
	 *             if a repository failed to add new file .
	 */
	public boolean add(MultipartFile mediaFile) throws RepositoryException {

		Session session = null;
		InputStream mediaFileStream;
		String tempFilename = mediaFile.getOriginalFilename();
		String mediaFileNodeName = tempFilename.substring(0, tempFilename.indexOf("."));

		// mimeType of file
		String mediaFileMimeType = mediaFile.getContentType();

		try {
			session = this.repository.login(FileSystemConstants.ECMS_WORKSPACE);
			Node rootNode = session.getRootNode();

			// All nodes present in rootnode
			NodeIterator iterator = rootNode.getNodes();

			while (iterator.hasNext()) {

				Node tempNode = (Node) iterator.next();

				if (tempNode.getName().equals(FileSystemConstants.ECMS_NODE)) {

					LOGGER.info("In Folder :- " + FileSystemConstants.ECMS_NODE);

					// crated new file node to save file in repository
					Node mediaFileNode = tempNode.addNode(mediaFileNodeName, "nt:file");

					// crated new content node which containes actual data of file in repository
					Node contentNode = mediaFileNode.addNode("jcr:content", "nt:resource");
					mediaFileStream = new BufferedInputStream(mediaFile.getInputStream());

					// File data stored in repository in Binary form
					Binary binary = session.getValueFactory().createBinary(mediaFileStream);

					// File data stored in repository in Binary form
					contentNode.setProperty("jcr:data", binary);

					// Set additional information of file
					contentNode.setProperty("jcr:mimeType", mediaFileMimeType);
					session.save();
					session.logout();
					producer.produce(new Event("abc"));
					LOGGER.info("File Directory Folder :- " + FileSystemConstants.ECMS_NODE + "  File Name :- "
							+ tempFilename + " Node name :- " + mediaFileNodeName);
					return true;
				}

			}

			// crated new Folder to store data repository
			Node ecmsNode = rootNode.addNode(FileSystemConstants.ECMS_NODE, "nt:folder");
			LOGGER.info("New File Directory Folder :- " + FileSystemConstants.ECMS_NODE + " created");

			// crated new file node to save file in repository
			Node mediaFileNode = ecmsNode.addNode(mediaFileNodeName, "nt:file");

			// crated new content node which containes actual data of file in repository
			Node contentNode = mediaFileNode.addNode("jcr:content", "nt:resource");
			mediaFileStream = new BufferedInputStream(mediaFile.getInputStream());

			// File data stored in repository in Binary form
			Binary binary = session.getValueFactory().createBinary(mediaFileStream);
			contentNode.setProperty("jcr:data", binary);

			// Set additional information of file
			contentNode.setProperty("jcr:mimeType", mediaFileMimeType);
			session.save();
			session.logout();
			LOGGER.info("File Directory Folder :- " + FileSystemConstants.ECMS_NODE + "  File Name :- " + tempFilename
					+ " Node name :- " + mediaFileNodeName);
			return true;

		} catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		}

	}

	/**
	 * Get Requested File from repository and write to local file system
	 *
	 * @param filename
	 * 
	 * @return
	 * @throws RepositoryException,IOException
	 *             if a repository failed to get file list or login or get session
	 *             or not find node path file .
	 */

	public void getFile(String mediafilename) throws RepositoryException, IOException {

		String mediafileNodeName = mediafilename.substring(0, mediafilename.indexOf("."));
		Session session = null;
		Node contentNode = null;
		InputStream mediaFileStream = null;

		try {
			session = this.repository.login(FileSystemConstants.ECMS_WORKSPACE);

			// mediafile node from repository
			Node mediafileNode = session.getNode(FileSystemConstants.ECMS_NODE_PATH + "" + mediafileNodeName);

			// Actual Node which contains actual data
			contentNode = mediafileNode.getNode("jcr:content");

			// Get stream from repository
			mediaFileStream = contentNode.getProperty("jcr:data").getBinary().getStream();

		} catch (Exception e) {

			throw new RepositoryException(e.getMessage());
		}

		// Get additional Information of repository
		String mimetype = contentNode.getProperty("jcr:mimeType").getString();
		LOGGER.info(
				mediafilename + " File size in bytes :--" + mediaFileStream.available() + "File Type :- " + mimetype);

		// write stream to local system
		FileUtils.copyInputStreamToFile(mediaFileStream,
				Files.createFile(Paths.get(FileSystemConstants.OUTPUT_PATH + "" + mediafilename)).toFile());
		session.save();
		session.logout();

	}

	/**
	 * get all Files list from Modeshape repository
	 *
	 * @param
	 * 
	 * @return
	 * @throws RepositoryException
	 *             if a repository failed to get file list or login or get session
	 *             or not find node path file .
	 */

	public List<String> getallFiles() throws RepositoryException {

		Session session = null;
		Node ecmsNode;
		ArrayList<String> fileNameList;

		try {

			session = this.repository.login(FileSystemConstants.ECMS_WORKSPACE);
			ecmsNode = session.getNode(FileSystemConstants.ECMS_NODE_PATH);

		} catch (Exception e) {

			throw new RepositoryException(e.getMessage());

		}

		fileNameList = new ArrayList<String>();
		// List of Filenames from Repository
		NodeIterator iterator = ecmsNode.getNodes();

		while (iterator.hasNext()) {

			Node tempNode = (Node) iterator.next();
			// Extract data from node if you need
			fileNameList.add(tempNode.getName());
			LOGGER.info("File name is :- " + tempNode.getName());

		}

		session.save();
		session.logout();

		return fileNameList;
	}

	public void deleteAllNodes() throws LoginException, NoSuchWorkspaceException, RepositoryException {
		Session session = this.repository.login(FileSystemConstants.ECMS_WORKSPACE);

		try {
			JcrTools jcrTools = new JcrTools(false);
			jcrTools.printSubgraph(session.getRootNode());
			NodeIterator iter = session.getNode("/" + FileSystemConstants.ECMS_NODE).getNodes();
			while (iter.hasNext()) {
				Node child = iter.nextNode();
				if (!child.getName().contains("jcr:system")) {
					jcrTools.removeAllChildren(child);
					child.remove();

				}
			}
			session.save();
			session.logout();
		} catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		}
	}

	public void addVersionService(MultipartFile mediaFile)
			throws LoginException, NoSuchWorkspaceException, RepositoryException, IOException {

		String tempFilename = mediaFile.getOriginalFilename();
		String mediaFileNodeName = tempFilename.substring(0, tempFilename.indexOf("."));
		String mediaFileMimeType = mediaFile.getContentType();

		Session session = this.repository.login(FileSystemConstants.ECMS_WORKSPACE);

		Node rootnode = session.getRootNode();
		Node VideodemoNode = rootnode.addNode("Videodemo", "nt:folder");

		Node mediaFileNode = VideodemoNode.addNode(mediaFileNodeName, "nt:file");
		Node cotentNode = mediaFileNode.addNode("jcr:content", "nt:resource");
		InputStream mediaFileStream = new BufferedInputStream(mediaFile.getInputStream());

		// File data stored in repository in Binary form
		Binary binary = session.getValueFactory().createBinary(mediaFileStream);
		cotentNode.setProperty("jcr:data", binary);

		// Set additional information of file
		cotentNode.setProperty("jcr:mimeType", mediaFileMimeType);
		cotentNode.addMixin("mix:versionable");
		session.save();
		session.logout();

	}
}
