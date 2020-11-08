package com.demo.rocketmq.simple2;

import com.demo.rocketmq.RocketMqCommon;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * 发送同步消息
 * 可靠的同步传输用于广泛的场景，如重要的通知消息，短信通知，短信营销系统等。
 * @author jj
 */
public class SyncProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException, UnsupportedEncodingException {
        DefaultMQProducer producer = RocketMqCommon.getDefaultProducer();
        producer.start();
        RocketMqCommon.sendDefaultMessage(producer, "sync");
        producer.shutdown();

    }
}
