package com.ecms.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.jackrabbit.JcrConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springmodules.jcr.JcrCallback;
import org.springmodules.jcr.JcrTemplate;

import com.ecms.util.TranscodeConstant;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

@RestController
public class ContentController {/*

	@Autowired
	private JcrTemplate jcrTemplate;

	@Autowired
	private FFmpeg ffmpeg;

	@Autowired
	private FFprobe ffprobe;

	@Autowired
	private FFmpegBuilder builder;

	*//**
	 * Adds node if it doesn't exist.
	 *//*

	@GetMapping("/addnode")
	public String addNodeRepository() {

		String nodeName = "Cmsrepository";

		jcrTemplate.execute(new JcrCallback() {

			@Override
			public Object doInJcr(Session session) throws IOException, RepositoryException {
				// TODO Auto-generated method stub

				Node root = session.getRootNode();
				String user = session.getUserID();
				System.out.println(user);
				Node node = null;

				if (root.hasNode(nodeName)) {
					node = root.getNode(nodeName);
					System.out.println(node.getName() + " exist" + node.getPath());

				} else {
					node = root.addNode(nodeName);
					System.out.println(node.getName() + " added");

					session.save();

				}
				return node;

			}
		});
		return "Done";

	}

	@GetMapping("/addfile")
	public void addFileToNode() {

		String nodeName = "cmsdemo";

		jcrTemplate.execute(new JcrCallback() {
			@SuppressWarnings("deprecation")
			public Object doInJcr(Session session) throws RepositoryException, IOException {
				Node resultNode = null;

				Node root = session.getRootNode();

				// should have been created in previous test
				Node folderNode = root.getNode(nodeName);

				String videoFileFolder = "videonode";
				// String fileName = "sampled.avi";

				if (folderNode.hasNode(videoFileFolder)) {
				} else {

					FileInputStream filed = new FileInputStream(
							"D:\\Users\\kandalakar.r\\Documents\\InputforTranscoding\\sample.avi");

					Node fileNode = folderNode.addNode(videoFileFolder, JcrConstants.NT_FILE);

					// create the mandatory child node - jcr:content
					resultNode = fileNode.addNode(JcrConstants.JCR_CONTENT, JcrConstants.NT_RESOURCE);

					resultNode.setProperty(JcrConstants.JCR_MIMETYPE, "video/x-msvideo");
					resultNode.setProperty(JcrConstants.JCR_DATA, filed);
					Calendar lastModified = Calendar.getInstance();
					lastModified.setTimeInMillis(System.currentTimeMillis());
					resultNode.setProperty(JcrConstants.JCR_LASTMODIFIED, lastModified);

					session.save();

				}

				return resultNode;
			}
		});
	}

	@GetMapping("/file")
	public void getFile() {

		String nodeName = "cmsdemo";

		jcrTemplate.execute(new JcrCallback() {

			public Object doInJcr(Session session) throws RepositoryException, IOException {
				Node resultNode = null;

				Node root = session.getRootNode();
				// should have been created in previous test
				Node folderNode = root.getNode(nodeName);

				String fileName = "sampled.avi";

				Node contentNode = folderNode.getNode(fileName).getNode(JcrConstants.JCR_CONTENT);

				Property dataProperty = contentNode.getProperty(JcrConstants.JCR_DATA);

				Node nodepath = root.getNode("cmsdemo/sampled.avi");

				final String pathoffile = dataProperty.getPath();

				builder.setInput(nodepath.getPath()).overrideOutputFiles(true)
						.addOutput(TranscodeConstant.OutputPath + "\\" + pathoffile + ".mp4").setVideoFrameRate(25)
						.setVideoCodec("libx264").setStrict(FFmpegBuilder.Strict.EXPERIMENTAL).setAudioCodec("copy")
						.done();

				FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
				executor.createJob(builder).run();

				return resultNode;
			}
		});

	}
*/}
