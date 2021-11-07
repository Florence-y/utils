package com.florence.dev.utils.web;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author: xiongpengfei
 * @datetime: 2017/12/5 18:04
 * @description:
 */
public class TxtExportUtils {


    /**
     * 导出txt到http
     *
     * @param response
     * @param filename 文件名
     * @param titles   列名
     * @param dataList 数据
     */
    public static void exportToDownload(HttpServletResponse response, String filename,
                                        List<String> titles, List<List<String>> dataList)
            throws IOException {

        OutputStream os = response.getOutputStream();
        response.setContentType("application/download");
        response.setHeader("Content-disposition", "attachment;filename=\""
                + new String((java.net.URLEncoder.encode(filename, "UTF-8")).getBytes(
                StandardCharsets.UTF_8), "GB2312")
                + "\"");
        export(os, titles, dataList);
    }

    /**
     * 导出txt到http
     *
     * @param response
     * @param filename 文件名
     */
    public static void exportToDownload(HttpServletResponse response, String filename, String content)
            throws IOException {

        OutputStream os = response.getOutputStream();
        response.setContentType("application/dowload");
        response.setHeader("Content-disposition", "attachment;filename=\""
                + new String((java.net.URLEncoder.encode(filename, "UTF-8")).getBytes(
                StandardCharsets.UTF_8), "GB2312")
                + "\"");
        IOUtils.write(content.getBytes(), os);
        os.close();
    }

    public static void export(OutputStream os, List<String> titles, List<List<String>> dataList)
            throws IOException {
        StringBuffer sb = new StringBuffer();
        if (titles != null) {
            titles.forEach(t -> {
                sb.append(t);
                sb.append("\t");
            });
            sb.append("\r\n");
        }
        dataList.forEach(list -> {
            list.forEach(s -> {
                if (s != null) {
                    sb.append(s);
                    sb.append("\t");
                }
            });
            sb.append("\r\n");
        });
        IOUtils.write(sb.toString().getBytes(), os);
        os.close();
    }

    public static void export(HttpServletResponse response, String filename, URL url)
            throws IOException {

        OutputStream os = response.getOutputStream();
        response.setContentType("application/dowload");
        response.setHeader("Content-disposition", "attachment;filename=\""
                + new String((java.net.URLEncoder.encode(filename, "UTF-8")).getBytes(
                StandardCharsets.UTF_8), "GB2312")
                + "\"");
        InputStream is = url.openStream();
        IOUtils.copy(is, os);
        is.close();
        os.close();
    }
}