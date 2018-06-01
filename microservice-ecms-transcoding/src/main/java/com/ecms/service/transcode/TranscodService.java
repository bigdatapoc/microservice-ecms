package com.ecms.service.transcode;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

@Service
public class TranscodService {

	@Autowired
	private FFmpeg ffmpeg;

	@Autowired
	private FFprobe ffprobe;

	@Autowired
	private FFmpegBuilder builder;

	public boolean changeVideoFormate(String format) {

		this.builder.setInput("D:\\Users\\kandalakar.r\\Documents\\InputforTranscoding\\sample_video." + format)
				.overrideOutputFiles(true)
				.addOutput("D:\\Users\\kandalakar.r\\Documents\\OutputofTranscoding\\Output_video.mp4")
				.setFormat(format).setVideoFrameRate(25).setVideoCodec("libx264")
				.setStrict(FFmpegBuilder.Strict.EXPERIMENTAL).done();

		FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

		executor.createJob(builder).run();

		return true;

	}

	public boolean changeVideoResolution(String filename) {
		// TODO Auto-generated method stub

		this.builder.setInput("D:\\Users\\kandalakar.r\\Documents\\InputforTranscoding\\Resolution\\"+filename+".mp4")
				.overrideOutputFiles(true)
				.addOutput("D:\\Users\\kandalakar.r\\Documents\\OutputofTranscoding\\Resolution_sammple"+filename+".mp4")
				.setVideoResolution(1920,1200)
				.setVideoFilter("scale=1920:1200")
				.done();

		FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

		executor.createJob(builder).run();

		return true;
	}

	public boolean changeAudioFormat(String format) {
		// TODO Auto-generated method stub

		this.builder.setInput("D:\\Users\\kandalakar.r\\Documents\\InputforTranscoding\\sample_audio." + format)
				.overrideOutputFiles(true)
				.addOutput("D:\\Users\\kandalakar.r\\Documents\\OutputofTranscoding\\Output_audio_" + format + ".mp3")
				.setAudioBitRate(192000).setFormat(format).done();

		FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

		executor.createJob(builder).run();

		return true;

	}

	public boolean convertVideoToAudio(String format) throws IOException {

		this.builder.setInput("D:\\Users\\kandalakar.r\\Documents\\InputforTranscoding\\sample_video."+format)
				.overrideOutputFiles(true)
				.addOutput("D:\\Users\\kandalakar.r\\Documents\\OutputofTranscoding\\Convertes_audio_"+format+".mp3")
				.setAudioBitRate(192000).setFormat("mp3").done();

		FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

		executor.createJob(builder).run();

		return true;

	}

	public boolean convertImgesToVideo() throws IOException {

		this.builder.setInput("D:\\Users\\kandalakar.r\\Documents\\InputforTranscoding\\image-%2d.png")
				.overrideOutputFiles(true)
				.addOutput("D:\\Users\\kandalakar.r\\Documents\\OutputofTranscoding\\Output_vid.mp4")
				.setVideoFrameRate(25).setVideoCodec("").done();

		FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

		executor.createJob(builder).run();

		return true;

	}

	public boolean convertOneToMultipalMp3() throws IOException {

		this.builder.setInput("D:\\Users\\kandalakar.r\\Documents\\InputforTranscoding\\Sample_audio.mp3")
				.overrideOutputFiles(true)
				.addOutput("D:\\Users\\kandalakar.r\\Documents\\OutputofTranscoding\\Output_audio_1.mp3")
				.addExtraArgs("-map_channel", "0.0.0") // destination
				.done().addOutput("D:\\Users\\kandalakar.r\\Documents\\OutputofTranscoding\\Output_audio_2.mp3")
				.addExtraArgs("-map_channel", "0.0.1").done();

		FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

		executor.createJob(builder).run();

		return true;

	}

}
