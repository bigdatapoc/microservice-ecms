package com.ecms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecms.exception.ExecuteCommandFailedException;
import com.ecms.service.transcode.Impl.TranscodServiceImpl;

@RestController
@RequestMapping(value = "/transcode/video")
public class TranscodeVideoController {

	@Autowired
	private TranscodServiceImpl transcodService;

	/* API to change video format */
	@PostMapping("/format/{resformat}")
	public ResponseEntity<?> transcodeVideoFormat(@RequestParam("file") MultipartFile mediaFile,
			@PathVariable String resformat) throws ExecuteCommandFailedException {

		if (mediaFile.getContentType().contains("video")) {

			if (mediaFile.isEmpty() && mediaFile.getSize() == 0) {

				return new ResponseEntity<>("please select a file!", HttpStatus.OK);
			}

			this.transcodService.changeVideoFormat(mediaFile, resformat);
			return new ResponseEntity<>("Uploded and transcoded " + mediaFile.getOriginalFilename() + " File",
					HttpStatus.OK);

		} else {

			return new ResponseEntity<>("please select video file! This is " + mediaFile.getContentType() + " file",
					HttpStatus.OK);
		}
	}

	/* API to change video resolution */
	@PostMapping("/resolution")
	public ResponseEntity<?> transcodeVideResolution(@RequestParam("file") MultipartFile mediaFile)
			throws ExecuteCommandFailedException {

		if (mediaFile.getContentType().contains("video")) {

			if (mediaFile.isEmpty() && mediaFile.getSize() == 0) {

				return new ResponseEntity<>("please select a file!", HttpStatus.OK);

			}

			this.transcodService.changeVideoResolution(mediaFile);

			return new ResponseEntity<>("Uploded and transcoded " + mediaFile.getOriginalFilename() + " File",
					HttpStatus.OK);

		} else {

			return new ResponseEntity<>("please select video file! This is " + mediaFile.getContentType() + " file",
					HttpStatus.OK);

		}
	}

	/* API to change video to audio format */
	@PostMapping("/audio")
	public ResponseEntity<?> transcodeVideoToAudio(@RequestParam("file") MultipartFile mediaFile)
			throws ExecuteCommandFailedException {

		if (mediaFile.getContentType().contains("video")) {

			if (mediaFile.isEmpty() && mediaFile.getSize() == 0) {

				return new ResponseEntity<>("please select a file!", HttpStatus.OK);

			}

			this.transcodService.convertVideoToAudio(mediaFile);

			return new ResponseEntity<>("Uploded and transcoded " + mediaFile.getOriginalFilename() + " File",
					HttpStatus.OK);
		} else {

			return new ResponseEntity<>("please select video file! This is " + mediaFile.getContentType() + " file",
					HttpStatus.OK);

		}

	}

}
