package pl.codeleak.sbt.dingding;

import com.dingtalk.open.client.api.model.corp.MessageBody;
import com.dingtalk.open.client.api.model.corp.MessageType;
import org.apache.log4j.Logger;
import pl.codeleak.sbt.dingding.message.LightAppMessageDelivery;
import pl.codeleak.sbt.dingding.message.MessageHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/11/17.
 */
public class DingMessageUtiles {

    private static Logger logger = Logger.getLogger(DingMessageUtiles.class);

    public static void send(String toUsers) {
        try {
            // 获取access token
            String accessToken = AuthHelper.getAccessToken();
            logger.info("成功获取access token: " + accessToken);

            MessageBody.TextBody textBody = new MessageBody.TextBody();
            textBody.setContent("TextMessage");

            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            MessageBody.LinkBody linkBody = new MessageBody.LinkBody();
            linkBody.setMessageUrl("http://125.210.115.11:8082/treasure");
            linkBody.setPicUrl("@lALPBY0V4rPfxTggIA");
            linkBody.setTitle("盘点系统提醒");
            linkBody.setText("你有新的盘点任务！" + formatter.format(new Date()));

            // 发送微应用消息
            String agentId = Env.AGENT_ID;
            LightAppMessageDelivery lightAppMessageDelivery = new LightAppMessageDelivery(toUsers, "", agentId);

            lightAppMessageDelivery.withMessage(MessageType.LINK, linkBody);
            MessageHelper.send(accessToken, lightAppMessageDelivery);
            logger.info("发送消息："+toUsers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
