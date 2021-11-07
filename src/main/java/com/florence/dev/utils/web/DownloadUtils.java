package com.florence.dev.utils.web;


import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author wuyanzhen
 */
public class DownloadUtils {


    public static void exportToDownload(HttpServletResponse response, String filename, String path) throws IOException {

        response.setContentType("application/dowload");
        response.setHeader("Content-disposition", "attachment;filename=\""
                + new String((java.net.URLEncoder.encode(filename, "UTF-8")).getBytes("UTF-8"), "GB2312") + "\"");

        DataInputStream in = null;
        OutputStream out = null;

        //输入流：本地文件路径
        in = new DataInputStream(
                new FileInputStream(new File(path)));
        //输出流
        out = response.getOutputStream();
        //输出文件
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
    }

    public static void exportToDownload(HttpServletResponse response, File file) throws IOException {

        response.setContentType("application/dowload");
        response.setHeader("Content-disposition", "attachment;filename=\""
                + new String((java.net.URLEncoder.encode(file.getName(), "UTF-8")).getBytes("UTF-8"), "GB2312") + "\"");

        DataInputStream in = null;
        OutputStream out = null;

        //输入流：本地文件路径
        in = new DataInputStream(
                new FileInputStream(file));
        //输出流
        out = response.getOutputStream();
        //输出文件
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
    }

}
