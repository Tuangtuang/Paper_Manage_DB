package com.example.demo.tool;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;


/**
 * @program: message
 * @description: 阿里短信服务接口
 * @author: 0GGmr0
 * @create: 2019-03-26 18:06
 */
public class TextCheck {

    private static final String product = "Dysmsapi";

    private static final String domain = "dysmsapi.aliyuncs.com";

    private static final String accessKeyId = "LTAIAlpJwaj1JQJY";

    private static final String accessKeySecret = "4a7k2tQ8SVGYbJLTFesaNqmzJxAHkl";

    /* 短信发送 */
    public static SendSmsResponse sendSms(String phone,String checkCode) throws ClientException {

        /* 超时时间，可自主调整 */
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        /* 初始化acsClient,暂不支持region化 */
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        /* 组装请求对象-具体描述见控制台-文档部分内容 */
        SendSmsRequest request = new SendSmsRequest();
        /* 必填:待发送手机号 */
        request.setPhoneNumbers(phone);
        /* 必填:短信签名-可在短信控制台中找到 */
        request.setSignName("觊翼留学");
        /* 必填:短信模板code-可在短信控制台中找到 */
        request.setTemplateCode("SMS_164276376");
        /* 可选:模板中的变量替换JSON串,如模板内容为"亲爱的用户,您的验证码为${code}"时,此处的值为 */
        request.setTemplateParam("{\"code\":\"" + checkCode + "\"}");

        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode()!= null && sendSmsResponse.getCode().equals("OK")){
            System.out.println("短信发送成功！验证码：" + checkCode);
        }else {
            System.out.println("短信发送失败！");
        }
        return sendSmsResponse;
    }
}