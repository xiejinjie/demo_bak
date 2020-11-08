package com.demo.rocketmq.simple2;

import com.demo.rocketmq.RocketMqCommon;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * @author jj
 * @since 2020/3/29 11:29
 */
public class PushConsumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = RocketMqCommon.getDefaultConsumer("*");
        consumer.registerMessageListener(RocketMqCommon.getDefaultConsumerListener());
        consumer.start();
        System.out.println("已启动消费者");
    }
}
