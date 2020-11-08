package com.demo.rocketmq.broadcast;

import com.demo.rocketmq.RocketMqCommon;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * 发布订阅模式
 * @author jj
 * @since 2020/3/30 10:41
 */
public class BroadcastProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = RocketMqCommon.getDefaultProducer();
        producer.start();
        RocketMqCommon.sendDefaultMessage(producer, "Broadcast");
        producer.shutdown();
    }
}
