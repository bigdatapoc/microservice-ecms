package com.ecms.service.transcode.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ecms.exception.ExecuteCommandFailedException;
import com.ecms.service.transcode.TranscodService;
import com.ecms.util.TranscodeConstant;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;


/**
 * Service to transcode files uploaded by user
 * Implimented Business logic using FFmpeg library.
 * 
 */

@Service
public class TranscodServiceImpl implements TranscodService{
	
	
	@Autowired
	private FFmpeg ffmpeg;

	@Autowired
	private FFprobe ffprobe;

	@Autowired
	private FFmpegBuilder builder;

	
	
	/**
	 * Change video format of uploaded file into Different format
	 *
	 * @param mediaFile and format
	 * @return
	 * @throws ExecuteCommandFailedException
	 *             if a FFmpeg failes to execute command created using FFmpegBuilder .
	 */	
	@Override
	public void changeVideoFormat(MultipartFile mediaFile,String format) throws ExecuteCommandFailedException {
		
		String tempFilename = mediaFile.getOriginalFilename();
		String fileName = tempFilename.substring(0,tempFilename.indexOf(".")); 

		
		this.builder.setInput(TranscodeConstant.InputPath+"\\"+mediaFile.getOriginalFilename())
				.overrideOutputFiles(true)
				.addOutput(TranscodeConstant.OutputPath+"\\"+fileName+"."+format)
				.setFormat("mp3").setVideoFrameRate(25).setVideoCodec("libx264")
				.setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)
				.setAudioCodec("copy")
				.done();

	
		try {
			FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
			executor.createJob(builder).run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ExecuteCommandFailedException(e.getMessage());
		}
		

	}

	
	/**
	 * Change video resolution into 640*360  
	 *
	 * @param mediaFile
	 * @return
	 * @throws ExecuteCommandFailedException
	 *             if a FFmpeg failes to execute command created using FFmpegBuilder .
	 */	
	@Override
	public void changeVideoResolution(MultipartFile mediaFile) throws ExecuteCommandFailedException {
		// TODO Auto-generated method stub

		String tempFileName = mediaFile.getOriginalFilename();
		String fileName = tempFileName.substring(0,tempFileName.indexOf(".")); 

		
		this.builder.setInput(TranscodeConstant.InputPath+"\\Resolution\\"+mediaFile.getOriginalFilename())
				.overrideOutputFiles(true)
				.addOutput(TranscodeConstant.OutputPath+"\\"+fileName+".mp4")
				.setVideoResolution(640,360)
				.setAudioCodec("copy")
				.setVideoBitRate(1000000)
				.done();

		try {
			FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
			executor.createJob(builder).run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ExecuteCommandFailedException(e.getMessage());
		}

	}

	
	
	
	/**
	 * Covert video file into Audio(mp3) format
	 *
	 * @param mediaFile and format
	 * @return
	 * @throws ExecuteCommandFailedException
	 *             if a FFmpeg failes to execute command created using FFmpegBuilder .
	 */	
	@Override
	public void convertVideoToAudio(MultipartFile mediaFile) throws ExecuteCommandFailedException {
		
		String tempFileName = mediaFile.getOriginalFilename();
		String fileName = tempFileName.substring(0,tempFileName.indexOf(".")); 


		this.builder.setInput(TranscodeConstant.InputPath+"\\Samplevideo\\"+mediaFile.getOriginalFilename())
				.overrideOutputFiles(true)
				.addOutput(TranscodeConstant.OutputPath+"\\Convertedvideo\\"+fileName+".mp3")
				.setAudioCodec("libmp3lame")
				.addExtraArgs("-q:a","2")
				.done();
		try {
			FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
			executor.createJob(builder).run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ExecuteCommandFailedException(e.getMessage());
		}



	}
	
	/**
	 * Change audio format of uploaded file into mp3 format
	 *
	 * @param mediaFile 
	 * @return
	 * @throws ExecuteCommandFailedException
	 *             if a FFmpeg failes to execute command created using FFmpegBuilder .
	 */	
	@Override
	public void changeAudioFormat(MultipartFile mediaFile) throws ExecuteCommandFailedException {
		// TODO Auto-generated method stub
		
		String tempFileName = mediaFile.getOriginalFilename();
		String fileName = tempFileName.substring(0,tempFileName.indexOf(".")); 

		this.builder.setInput(TranscodeConstant.InputPath+"\\Audioformat\\"+mediaFile.getOriginalFilename())
				.overrideOutputFiles(true)
				.addOutput(TranscodeConstant.OutputPath+"\\Audio_format\\" + fileName + ".mp3")
				.setAudioCodec("libmp3lame")
				.addExtraArgs("-q:a","2")
				.done();

		try {
			FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
			executor.createJob(builder).run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ExecuteCommandFailedException(e.getMessage());
		}


	}
	
	}
