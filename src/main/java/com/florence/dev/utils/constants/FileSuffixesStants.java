package com.florence.dev.utils.constants;

public enum FileSuffixesStants {

    /**
     * 文件后缀
     */
    JPG_SUFFIXES(".jpg"),
    JPEG_SUFFIXES(".jpeg"),
    BMP_SUFFIXES(".bmp"),
    PNG_SUFFIXES(".png"),
    GIF_SUFFIXES(".gif"),
    ZIP_SUFFIXES(".zip"),
    RAR_SUFFIXES(".rar"),
    TXT_SUFFIXES(".txt"),
    APK_SUFFIXES(".apk"),
    FLV_SUFFIXES(".flv"),
    MP4_SUFFIXES(".mp4"),
    MKV_SUFFIXES(".mkv"),
    GP_SUFFIXES(".3gp"),
    RMVB_SUFFIXES(".rmvb");

    private String suffixes;

    private FileSuffixesStants(String suffixes) {
        this.suffixes = suffixes;
    }

    public String getSuffixes() {
        return suffixes;
    }

    public void setSuffixes(String suffixes) {
        this.suffixes = suffixes;
    }

}
