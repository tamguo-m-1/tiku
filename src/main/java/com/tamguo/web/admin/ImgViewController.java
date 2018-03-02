package com.tamguo.web.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ImgViewController {
    Logger logger = Logger.getLogger(ImgViewController.class);

    @Value("${editorImg}")
    private String dormImgUrl;
    
    @RequestMapping("dormImgView")
    public void imgView(String imgPath, HttpServletResponse response, HttpServletRequest request) {
        try {
            if (StringUtils.isEmpty(imgPath)) {
                return;
            }
            String fileName = imgPath;
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache,no-store,max-age=0");
            response.setDateHeader("Expires", 0);
            FileInputStream fis = new FileInputStream(dormImgUrl+ fileName);
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            response.getOutputStream();
            byte[] buff = new byte[2048];
            int length = 0;
            while ((length = fis.read(buff, 0, buff.length)) != -1) {
                bos.write(buff, 0, length);
            }
            fis.close();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping("downEditorFile")
    public void download(String path,HttpServletRequest request,HttpServletResponse response) throws IOException {
        try {
            path = dormImgUrl+path;
            File file = new File(path);
            // 取得文件名。
            // 以流的形式下载文件。
            String fileName = file.getName();
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            String agent = request.getHeader("USER-AGENT");
            if (agent != null && agent.indexOf("MSIE") == -1) {
                fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
            } else { // IE
                fileName = java.net.URLEncoder.encode(fileName, "utf-8");
            }
            response.setContentLength((int) file.length());
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            response.getWriter().println("<script>alert('文件不存在');</script>");
            return;
        }
    }
}
