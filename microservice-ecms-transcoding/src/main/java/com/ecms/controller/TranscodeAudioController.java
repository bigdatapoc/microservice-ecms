package com.ecms.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.ecms.exception.ExecuteCommandFailedException;
import com.ecms.service.transcode.Impl.TranscodServiceImpl;

@RestController
@RequestMapping(value = "/transcode/audio")
public class TranscodeAudioController {

	@Autowired
	private TranscodServiceImpl transcodService;
	

	/*
	 * api to change audio format
	 * 
	 */
	@PostMapping("/format")
	public ResponseEntity<?> transcodeAudioFile(@RequestParam("file") MultipartFile mediaFile)
			throws ExecuteCommandFailedException {

		if (mediaFile.getContentType().contains("audio")) {

			if (mediaFile.isEmpty() && mediaFile.getSize() == 0) {

				return new ResponseEntity<>("please select a file!", HttpStatus.OK);
			}

			this.transcodService.changeAudioFormat(mediaFile);

			return new ResponseEntity<>("Uploded and transcoded "+ mediaFile.getOriginalFilename() +" File",
					HttpStatus.OK);
		} else {

			return new ResponseEntity<>("please select Audio file! This is " + mediaFile.getContentType() + " file",
					HttpStatus.OK);
		}
	}



}
