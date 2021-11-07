package com.florence.dev.utils.web;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Slf4j
public class ExcelExportUtil {

    private static final int COLUMN_WIDTH = 16;

    /**
     * 导出sheet到http
     *
     * @param response
     * @param filename 文件名
     * @param sheet    sheet名
     * @param titles   列名
     * @param dataList 数据
     * @throws IOException
     */
    public static void exportToDownload(HttpServletResponse response, String filename, String sheet,
                                        List<String> titles, List<List<String>> dataList)
            throws IOException {

        OutputStream os = response.getOutputStream();
        response.setContentType("application/dowload");
        response.setHeader("Content-disposition", "attachment;filename=\""
                + new String((java.net.URLEncoder.encode(filename, "UTF-8")).getBytes("UTF-8"), "GB2312") + "\"");
        export(os, sheet, titles, dataList);
    }

    /**
     * 导出
     *
     * @param os
     * @param sheet
     * @param titles
     * @param dataList
     * @throws IOException
     */
    public static void export(OutputStream os, String sheet,
                              List<String> titles, List<List<String>> dataList)
            throws IOException {
        int maxRowCount = 60000;//不能够超过Excel的最大容量
        int currentSheetId = 0;
        WritableWorkbook workBookDownload = Workbook.createWorkbook(os);
//        WritableSheet sheetDownload = workBookDownload.createSheet(sheet, 0);
//        WritableSheet currentSheet = workBookDownload.createSheet(sheet, currentSheetId);
        WritableSheet currentSheet;

        WritableCellFormat wc = new WritableCellFormat();
        // 设置居中
        try {
            wc.setAlignment(Alignment.CENTRE);
            for (int i = 0; i < dataList.size(); i++) {
                if (i % maxRowCount == 0) {
                    //                currentSheetId++;
                    currentSheet = workBookDownload.createSheet(sheet, currentSheetId++);
                    if (titles != null) {
                        for (int titleIndex = 0; titleIndex < titles.size(); titleIndex++) {
                            currentSheet.addCell(new Label(titleIndex, 0, titles.get(titleIndex), new WritableCellFormat(wc)));
                            currentSheet.setColumnView(i, COLUMN_WIDTH);
                        }
                    }
                }
                currentSheet = workBookDownload.getSheet(currentSheetId - 1);
                List<String> data = dataList.get(i);
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) != null) {
                        currentSheet.addCell(new Label(j, i % maxRowCount + 1, data.get(j), new WritableCellFormat(wc)));
                    }
                }

            }
            workBookDownload.write();
            workBookDownload.close();
            os.close();
        } catch (WriteException e) {
            log.error("WebExcel导出失败", e);
        }
    }


    public static void export(HttpServletResponse response, String filename, String fullFilePath)
            throws IOException {

        OutputStream os = response.getOutputStream();
        response.setContentType("application/dowload");
        response.setHeader("Content-disposition", "attachment;filename=\""
                + new String((java.net.URLEncoder.encode(filename, "UTF-8")).getBytes("UTF-8"), "GB2312") + "\"");
        InputStream is = new FileInputStream(new File(fullFilePath));
        IOUtils.copy(is, os);
        is.close();
        os.close();
    }
}