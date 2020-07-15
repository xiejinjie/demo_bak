package com.demo.middleware.rocketmq.batch;

import com.demo.middleware.rocketmq.RocketMqCommon;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 批量
 * @author jj
 * @since 2020/3/30 11:29
 */
public class BatchMessageProducer {
    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {
        DefaultMQProducer producer = RocketMqCommon.getDefaultProducer();
        producer.start();
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            String content = "Hello batch message " + i;
            Message message = new Message(RocketMqCommon.DEFAULT_TOPIC_NAME, "batch", content.getBytes(RemotingHelper.DEFAULT_CHARSET));

            messages.add(message);
        }
        SendResult result = producer.send(messages);
        System.out.println("消息已发送：" + result);
        producer.shutdown();
    }
}
