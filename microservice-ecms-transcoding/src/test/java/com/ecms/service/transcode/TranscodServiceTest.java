package com.ecms.service.transcode;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ecms.util.TranscodeConstant;
import com.google.common.base.Joiner;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TranscodServiceTest {

	@Autowired
	private FFmpeg ffmpeg;

	@Autowired
	private FFmpegBuilder builder;

	@Test
	public void testchangeVideoFormate() throws IOException {

		String format = "avi";

		this.builder.setInput(TranscodeConstant.InputPath + "\\Samplevideo\\sample_video." + format)
				.overrideOutputFiles(true).addOutput(TranscodeConstant.OutputPath + "\\Output_video_" + format + ".mp4")
				.setFormat(format).setVideoFrameRate(25).setVideoCodec("libx264")
				.setStrict(FFmpegBuilder.Strict.EXPERIMENTAL).setAudioCodec("copy").done();

		String ffmpegcommand = "D:\\Users\\kandalakar.r\\Documents\\Softwares\\ffmpeg-20180529-ea0010b-win64-static\\ffmpeg-20180529-ea0010b-win64-static\\bin\\ffmpeg -y -v error"
				+ " -i D:\\Users\\kandalakar.r\\Documents\\InputforTranscoding\\Samplevideo\\sample_video." + format
				+ " -strict experimental" + " -f avi -vcodec libx264 -r 25/1 -acodec copy"
				+ " D:\\Users\\kandalakar.r\\Documents\\OutputofTranscoding\\Output_video_" + format + ".mp4";

		String actual = Joiner.on(" ").join(ffmpeg.path(builder.build()));
		assertEquals(ffmpegcommand, actual);

	}

	@Test
	public void testchangeVideoResolution() throws IOException {

		String filename = "file_example_MP4_640_3MG";

		this.builder.setInput(TranscodeConstant.InputPath + "\\Resolution\\" + filename + ".mp4")
				.overrideOutputFiles(true)
				.addOutput(TranscodeConstant.OutputPath + "\\Resolution_sample_" + filename + ".mp4")
				.setVideoResolution(640, 360).setAudioCodec("copy").setVideoBitRate(1000000).done();

		String ffmpegcommand = "D:\\Users\\kandalakar.r\\Documents\\Softwares\\ffmpeg-20180529-ea0010b-win64-static\\ffmpeg-20180529-ea0010b-win64-static\\bin\\ffmpeg -y -v error"
				+ " -i D:\\Users\\kandalakar.r\\Documents\\InputforTranscoding\\Resolution\\" + filename + ".mp4"
				+ " -s 640x360 -b:v 1000000 -acodec copy"
				+ " D:\\Users\\kandalakar.r\\Documents\\OutputofTranscoding\\Resolution_sample_" + filename + ".mp4";

		String actual = Joiner.on(" ").join(ffmpeg.path(builder.build()));
		assertEquals(ffmpegcommand, actual);

	}

	@Test
	public void testchangeAudioFormat() throws IOException {

		String format = "wav";
		this.builder.setInput(TranscodeConstant.InputPath + "\\Audioformat\\sample_audio." + format)
				.overrideOutputFiles(true)
				.addOutput(TranscodeConstant.OutputPath + "\\Audio_format\\Output_audio_" + format + ".mp3")
				.setAudioCodec("libmp3lame").addExtraArgs("-q:a", "2").done();

		String ffmpegcommand = "D:\\Users\\kandalakar.r\\Documents\\Softwares\\ffmpeg-20180529-ea0010b-win64-static\\ffmpeg-20180529-ea0010b-win64-static\\bin\\ffmpeg -y -v error"
				+ " -i D:\\Users\\kandalakar.r\\Documents\\InputforTranscoding\\Audioformat\\sample_audio." + format
				+ " -acodec libmp3lame -q:a 2"
				+ " D:\\Users\\kandalakar.r\\Documents\\OutputofTranscoding\\Audio_format\\Output_audio_" + format
				+ ".mp3";

		String actual = Joiner.on(" ").join(ffmpeg.path(builder.build()));
		assertEquals(ffmpegcommand, actual);

	}

	@Test
	public void testconvertVideoToAudio() throws IOException {

		String format = "flv";

		this.builder.setInput(TranscodeConstant.InputPath + "\\Samplevideo\\sample_video." + format)
				.overrideOutputFiles(true)
				.addOutput(TranscodeConstant.OutputPath + "\\Convertedvideo\\Convertes_audio_" + format + ".mp3")
				.setAudioCodec("libmp3lame").addExtraArgs("-q:a", "2").done();

		String ffmpegcommand = "D:\\Users\\kandalakar.r\\Documents\\Softwares\\ffmpeg-20180529-ea0010b-win64-static\\ffmpeg-20180529-ea0010b-win64-static\\bin\\ffmpeg -y -v error"
				+ " -i D:\\Users\\kandalakar.r\\Documents\\InputforTranscoding\\Samplevideo\\sample_video." + format
				+ " -acodec libmp3lame -q:a 2"
				+ " D:\\Users\\kandalakar.r\\Documents\\OutputofTranscoding\\Convertedvideo\\Convertes_audio_" + format
				+ ".mp3";

		String actual = Joiner.on(" ").join(ffmpeg.path(builder.build()));
		assertEquals(ffmpegcommand, actual);

	}

}
