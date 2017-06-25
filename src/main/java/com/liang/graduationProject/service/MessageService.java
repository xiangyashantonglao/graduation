package com.liang.graduationProject.service;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;
import com.liang.graduationProject.model.User;
import com.liang.graduationProject.response.DataResult;
import com.liang.graduationProject.utils.MessageConsts;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/4/28.
 */
@Service("messageService")
public class MessageService extends ServiceFacade{
    public static CloudTopic cloudTopic;
    public static RawTopicMessage msg;
    static {
        //获取主题引用
        CloudAccount account = new CloudAccount(MessageConsts.ACCESS_KEY_ID,MessageConsts.ACCESS_KEY_SECRET,MessageConsts.PUBLIC_ENDPOINT);
        MNSClient client = account.getMNSClient();
        cloudTopic = client.getTopicRef(MessageConsts.SMS_TOPIC);
        //设置消息体，必需
        msg = new RawTopicMessage();
        msg.setMessageBody(MessageConsts.MESSAGE_BODY);
    }

    /**
     * 发送验证码到入参的电话
     * @param
     */
    public DataResult sendMessage(String phone, String authCode) {

        DataResult data = new DataResult();

        User user = this.getDaoFacade().getUserRepository().findByPhone(phone);

        MessageAttributes messageAttributes = new MessageAttributes();
        BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
        //设置发送信息的签名
        batchSmsAttributes.setFreeSignName(MessageConsts.SIGN_NAME);
        //设置发送短信使用的模板
        batchSmsAttributes.setTemplateCode(MessageConsts.SMS_TEMPLATE_CODE);

        //设置发送短信所使用的模板中参数对应的值（短信模板中设置的，没有可以不用设置）
        BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
        smsReceiverParams.setParam("authCode", authCode);
        //添加目标手机号码
        if ( user != null ) {
            if ( user.getPhone().equals(phone)) {
                batchSmsAttributes.addSmsReceiver(user.getPhone(), smsReceiverParams);
                messageAttributes.setBatchSmsAttributes(batchSmsAttributes);
                //发送信息
                try {
                    TopicMessage ret = cloudTopic.publishMessage(msg, messageAttributes);
                    data.setErrorCode("200");
                    data.setReason("发送成功");
                    data.setResult(authCode);
                    user.setAuthCode(authCode);
                    this.getDaoFacade().getUserRepository().save(user);
                } catch (Exception e) {
                    e.printStackTrace();
                    data.setReason("发送失败");
                    data.setErrorCode("400");
                }
            }else {
                data.setErrorCode("400");
                data.setReason("手机号码出错");
            }
        }else {
            data.setReason("该用户不存在");
            data.setErrorCode("400");
        }
        return data;
    }
}
