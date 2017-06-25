package com.liang.graduationProject.utils;

import java.util.Random;

/**
 * Created by Administrator on 2017/5/17/017.
 */
public class RandomUtils {
    private static Random random;
    static {
        random = new Random();
    }

    /**
     * 生成6位随机数
     * @return
     */
    public static String getAuthCode() {
        int authCode = random.nextInt(899999);
        authCode += 100000;
        return authCode+"";
    }

}
