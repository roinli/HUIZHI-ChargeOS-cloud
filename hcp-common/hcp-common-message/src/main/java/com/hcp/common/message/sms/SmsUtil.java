package com.hcp.common.message.sms;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class SmsUtil {

    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.signName}")
    private String signName;

    @Value("${aliyun.templateCode}")
    private String templateCode;

    @Value("${aliyun.endpoint}")
    private String endpoint;

    public void send(String tel, String code) throws ClientException {
        DefaultProfile profile= DefaultProfile.getProfile(endpoint,accessKeyId,accessKeySecret);
        IAcsClient client=new DefaultAcsClient(profile);
        SendSmsRequest request=new SendSmsRequest();
        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        request.setPhoneNumbers(tel);//设置手机号
        Map<String,String> param=new HashMap<>();
        param.put("password",code);
        Gson gson=new Gson();
        String json = gson.toJson(param);
        request.setTemplateParam(json); //设置随机验证码
        //发送过程
        SendSmsResponse response= client.getAcsResponse(request);
        log.info(response.getMessage());
    }

}
