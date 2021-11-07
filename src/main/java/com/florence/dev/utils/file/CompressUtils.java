package com.florence.dev.utils.file;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import com.florence.dev.utils.constants.FileSuffixesStants;
import com.florence.dev.utils.string.StrUtils;
import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wuyanzhen
 */
@Slf4j
public class CompressUtils {
    /**
     * 压缩
     *
     * @param text
     * @return
     */
    public static String compressText(String text) {

        try {
            byte[] stream = text.getBytes(StandardCharsets.UTF_8);
            ByteArrayOutputStream out = new ByteArrayOutputStream(stream.length);
            try (GZIPOutputStream gzip = new GZIPOutputStream(out)) {
                gzip.write(stream);
            }
            return new sun.misc.BASE64Encoder().encode(out.toByteArray());
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 解压缩
     *
     * @param text
     * @return
     */
    public static String decompressText(String text) {
        try {
            byte[] stream = new sun.misc.BASE64Decoder().decodeBuffer(text);
            ByteArrayInputStream in = new ByteArrayInputStream(stream);
            GZIPInputStream gzip = new GZIPInputStream(in);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                byte[] buffer = new byte[4 * 1024];
                int len;
                while ((len = gzip.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
            } finally {
                gzip.close();
            }
            return out.toString("utf-8");
        } catch (IOException e) {
            return null;
        }
    }


    /**
     * @param filePath 源文件目录
     */
    public static String unzip(String filePath) {
        String destFilePath = "";

        File zipFile = new File(filePath);
        if (zipFile.isFile() && zipFile.exists()) {
            destFilePath = zipFile.getParent() + File.separator
                    + System.currentTimeMillis();    //以当前时间戳为目录名

            File dir = new File(destFilePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            ZipInputStream zais;

            try {
                zais = new ZipInputStream(new FileInputStream(zipFile));
                ZipEntry zipEntry;
                while ((zipEntry = zais.getNextEntry()) != null) {
                    // 获取文件名
                    String entryFileName = zipEntry.getName();
                    // 构造解压出来的文件存放路径
                    String entryFilePath = destFilePath + File.separator + entryFileName;
                    OutputStream os = null;

                    try {
                        // 把解压出来的文件写到指定路径
                        File entryFile = new File(entryFilePath);
                        if (entryFileName.endsWith("/")) {
                            entryFile.mkdirs();
                        } else {
                            os = new BufferedOutputStream(new FileOutputStream(new File(entryFilePath)));
                            byte[] buffer = new byte[2048];
                            int len = -1;
                            while ((len = zais.read(buffer)) != -1) {
                                os.write(buffer, 0, len);
                            }
                        }
                    } finally {
                        if (os != null) {
                            os.flush();
                            os.close();
                        }
                    }
                }
                zais.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return destFilePath;
    }

    /**
     * @param filePath 源文件目录
     * @return 返回解压后的文件目录
     */
    public static String unrar(String filePath) {
        String destFilePath = "";

        File rarFile = new File(filePath);
        if (rarFile.isFile() && rarFile.exists()) {
            destFilePath = rarFile.getParent() + File.separator
                    + System.currentTimeMillis();    //以当前时间戳为目录名

            File dir = new File(destFilePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            Archive archive = null;
            try {
                archive = new Archive(rarFile);
                FileHeader fh = archive.nextFileHeader();
                while (fh != null) {
                    //文件夹
                    if (fh.isDirectory()) {
                        File outDir = new File(destFilePath + File.separator
                                + fh.getFileNameString());
                        outDir.mkdirs();
                    } else {
                        //文件
                        String tempPath = destFilePath + File.separator
                                + fh.getFileNameString().trim();
                        //对每一个解压后的文件重命名
                        tempPath = renameFile(tempPath);
                        File outFile = new File(tempPath);
                        if (!outFile.exists()) {
                            // 相对路径可能多级，可能需要创建父目录.
                            if (!outFile.getParentFile().exists()) {
                                outFile.getParentFile().mkdirs();
                            }
                            outFile.createNewFile();
                        }
                        FileOutputStream os = new FileOutputStream(outFile);
                        archive.extractFile(fh, os);
                        os.close();
                    }

                    fh = archive.nextFileHeader();
                }
                archive.close();
            } catch (RarException | IOException e) {
                log.error(e.getMessage());
            }
        }

        return destFilePath;
    }


    public static String uncompressFile(String filePath) throws Exception {

        if (filePath.endsWith(FileSuffixesStants.ZIP_SUFFIXES.getSuffixes())) {
            return unzip(filePath);
        } else if (filePath.endsWith(FileSuffixesStants.RAR_SUFFIXES.getSuffixes())) {
            return unrar(filePath);
        } else {
            throw new Exception("This type of file is not supported!");
        }
    }


    /**
     * Rename the file name as format of fileName+yyyyMMddHHmmss
     * For example: icon.jpg would rename as randomStr_20160527172531.jpg
     *
     * @param fileName
     * @return newFileName
     */
    public static String renameFile(String fileName) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String formattedTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));


        String[] fileNameParts = fileName.split("\\.");
        int fileNamePartsLength = fileNameParts.length;
        StringBuffer sb = new StringBuffer();

        String randomStr = StrUtils.getRandomString(6);
        sb.append(randomStr)
                .append(formattedTime)
                .append(".")
                .append(fileNameParts[fileNamePartsLength - 1]);

        return sb.toString();
    }
}
