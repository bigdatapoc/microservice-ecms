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
@RequestMapping(value = "/transcode/audio")
public class TranscodeAudioController {

	@Autowired
	private TranscodService transcodService;

	/* api to change audio format */
	@GetMapping("/audioformat/{format}")
	public ResponseEntity<?> transcodeAudioFile(@PathVariable String format) throws IOException, InterruptedException {

		if (transcodService.changeAudioFormat(format)) {

			return ResponseEntity.ok("Format of Audio file changed successfully");

		} else {
			return ResponseEntity.ok("Format of Audio file Not changed");

		}

	}

}
