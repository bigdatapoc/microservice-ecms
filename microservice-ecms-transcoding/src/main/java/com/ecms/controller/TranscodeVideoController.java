package com.ecms.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecms.service.transcode.TranscodService;


@RestController
@RequestMapping(value = "/transcode/video")
public class TranscodeVideoController {

	@Autowired
	private TranscodService transcodService;

	/* api to change video format */
	@GetMapping("/videoformat/{format}")
	public ResponseEntity<?> transcodeVideoFormat(@PathVariable String format)
			throws IOException, InterruptedException {

		if (transcodService.changeVideoFormate(format)) {

			return ResponseEntity.ok("Format of Video file changed successfully");

		} else {
			return ResponseEntity.ok("Format of Video file Not changed");

		}

	}

	/* api to change video resolution */
	@GetMapping("/resolution/{filename}")
	public ResponseEntity<?> transcodeVideResolution(@PathVariable String filename ) throws IOException, InterruptedException {

		if (transcodService.changeVideoResolution(filename)) {

			return ResponseEntity.ok("Resolution of Video file changed successfully");

		} else {
			return ResponseEntity.ok("Resolution of Video file Not changed");

		}
	}

	/* api to change video to audio format */
	@GetMapping("/audio/{format}")
	public ResponseEntity<?> transcodeVideoToAudio(@PathVariable String format) throws IOException, InterruptedException {

		if (transcodService.convertVideoToAudio(format)) {

			return ResponseEntity.ok("Convert Video file to audio successfully");

		} else {
			return ResponseEntity.ok("Convert Video file to audio is failed");

		}

	}

	/* api to create video from images */
	@GetMapping("/image")
	public ResponseEntity<?> transcodeImagesToVideo() throws IOException, InterruptedException {

		if (transcodService.convertImgesToVideo()) {

			return ResponseEntity.ok("Convert images to video file successfully");

		} else {
			return ResponseEntity.ok("Convert images to video file is failed");

		}

	}
}
