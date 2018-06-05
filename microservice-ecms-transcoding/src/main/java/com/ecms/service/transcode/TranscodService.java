package com.ecms.service.transcode;


import org.springframework.web.multipart.MultipartFile;

import com.ecms.exception.ExecuteCommandFailedException;

public interface TranscodService {

	void changeVideoFormat(MultipartFile mediaFile,String resformat) throws ExecuteCommandFailedException;
	void changeVideoResolution(MultipartFile mediaFile) throws ExecuteCommandFailedException;
	void changeAudioFormat(MultipartFile mediaFile) throws ExecuteCommandFailedException;
	void convertVideoToAudio(MultipartFile mediaFile) throws ExecuteCommandFailedException;

}
