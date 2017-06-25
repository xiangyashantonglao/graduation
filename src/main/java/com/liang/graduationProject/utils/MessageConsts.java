package com.liang.graduationProject.utils;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;

/**
 * Created by admin on 2017/4/28.
 */
public class MessageConsts {
    //阿里云信息服务主题名称
    public static String SMS_TOPIC = "sms.topic-cn-hangzhou";
    //公网Endpoint
    public static String PUBLIC_ENDPOINT = "http://1590192410746582.mns.cn-hangzhou.aliyuncs.com/";
    //阿里云access key id
    public static String ACCESS_KEY_ID = "LTAIJS9Ehmit7ASe";
    //阿里云access key secret
    public static String ACCESS_KEY_SECRET = "Wg5GlH3rWrfAb880TwGpYIu1eXp2qt";
    //短信模板code
    public static String SMS_TEMPLATE_CODE = "SMS_63910162";
    public static String SIGN_NAME = "莘十课程";
    //SMS消息体
    public static String MESSAGE_BODY = "sms-message";
}
