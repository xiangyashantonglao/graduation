package com.liang.graduationProject.utils;

import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by admin on 2017/5/3.
 */
public class HeadUtils {
    private static String HEAD_PATH = "";
    public static Boolean checkSize(String head,int maxSize) {
        String[] strings = head.split(",");
        if (strings[1].length() >maxSize) {
            return false;
        }else {
            return true;
        }
    }
    public static Boolean saveHeadForBase64(String path ,String head) {
        BASE64Decoder decoder = new BASE64Decoder();
        String[] strings = head.split(",");
        try {
            FileOutputStream writer = new FileOutputStream(new File(path),false);
            byte[] decoderBytes = decoder.decodeBuffer(strings[1]);
            writer.write(decoderBytes);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
