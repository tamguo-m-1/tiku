package com.tamguo.web.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tamguo.util.DateUtils;
import com.tamguo.util.Status;
import com.tamguo.util.UploaderMessage;

/**
 * 文件上传
 */
@RestController
@RequestMapping("/admin")
public class FileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	@Value("${file.storage.path}")
	private String fileStoragePath;
	
	/**
	 * 上传当个文件
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = "application/json;charset=utf8")
	@ResponseBody
	public UploaderMessage uploadFileHandler(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws IOException {

		if (!file.isEmpty()) {
			InputStream in = null;
			OutputStream out = null;
			
			try {
				String path = fileStoragePath + DateUtils.format(new Date(), "yyyyMMdd");
				File dir = new File(path);
				if (!dir.exists())
					dir.mkdirs();
				File serverFile = new File(dir + File.separator + file.getOriginalFilename());
				in = file.getInputStream();
				out = new FileOutputStream(serverFile);
				byte[] b = new byte[1024];
				int len = 0;
				while ((len = in.read(b)) > 0) {
					out.write(b, 0, len);
				}
				out.close();
				in.close();
				logger.info("Server File Location=" + serverFile.getAbsolutePath());

				UploaderMessage msg = new UploaderMessage();
				msg.setStatus(Status.SUCCESS);
				msg.setStatusMsg("File upload success");
				return msg;
			} catch (Exception e) {
				UploaderMessage msg = new UploaderMessage();
				msg.setStatus(Status.ERROR);
				msg.setError("File upload file");
				return msg;
			} finally {
				if (out != null) {
					out.close();
					out = null;
				}

				if (in != null) {
					in.close();
					in = null;
				}
			}
		} else {
			UploaderMessage msg = new UploaderMessage();
			msg.setStatus(Status.ERROR);
			msg.setError("File is empty");
			return msg;
		}
	}

	/**
	 * 上传多个文件
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST, produces = "application/json;charset=utf8")
	@ResponseBody
	public UploaderMessage uploadMultipleFileHandler(@RequestParam("file") MultipartFile[] files,HttpServletRequest request) throws IOException {
		ArrayList<Integer> arr = new ArrayList<>();
		UploaderMessage msg = new UploaderMessage();
		String filePath = "";
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				InputStream in = null;
				OutputStream out = null;
				
				try {
					String path = fileStoragePath + DateUtils.format(new Date(), "yyyyMMdd") ;
					File dir = new File(path);
					if (!dir.exists())
						dir.mkdirs();
					File serverFile = new File(dir + File.separator + file.getOriginalFilename());
					in = file.getInputStream();
					out = new FileOutputStream(serverFile);
					byte[] b = new byte[1024];
					int len = 0;
					while ((len = in.read(b)) > 0) {
						out.write(b, 0, len);
					}
					out.close();
					in.close();
					logger.info("Server File Location=" + serverFile.getAbsolutePath());

					msg.setStatus(Status.SUCCESS);
					msg.setStatusMsg("File upload success");
					msg.setFilePath("files" + "/" + DateUtils.format(new Date(), "yyyyMMdd") + "/" +  file.getOriginalFilename());
					return msg;
				} catch (Exception e) {
					msg.setStatus(Status.ERROR);
					msg.setError("File upload file");
					return msg;
				} finally {
					if (out != null) {
						out.close();
						out = null;
					}

					if (in != null) {
						in.close();
						in = null;
					}
				}
			} else {
				msg.setStatus(Status.ERROR);
				msg.setError("File is empty");
				return msg;
			}
		}
		if (arr.size() > 0) {
			msg.setStatus(Status.ERROR);
			msg.setError("Files upload fail");
			msg.setErrorKys(arr);
		} else {
			msg.setStatus(Status.SUCCESS);
			msg.setStatusMsg("Files upload success");
			msg.setFilePath(filePath);
		}
		return msg;
	}
}
