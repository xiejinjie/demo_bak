package com.demo.middleware.rocketmq.transaction;

import com.demo.middleware.rocketmq.RocketMqCommon;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * @author jj
 * @since 2020/3/30 14:31
 */
public class TransactionMessageConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = RocketMqCommon.getDefaultConsumer("transaction");
        consumer.registerMessageListener(RocketMqCommon.getDefaultConsumerListener());
        consumer.start();
        System.out.println("已启动消费者");
    }
}
