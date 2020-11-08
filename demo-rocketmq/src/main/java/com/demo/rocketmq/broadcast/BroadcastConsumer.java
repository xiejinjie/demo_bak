package com.demo.rocketmq.broadcast;

import com.demo.rocketmq.RocketMqCommon;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * 发布订阅模式
 * @author jj
 * @since 2020/3/30 10:41
 */
public class BroadcastConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = RocketMqCommon.getDefaultConsumer("Broadcast");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        /**
         * 设置消息模式，默认是CLUSTERING
         * MessageModel.BROADCASTING 广播消费模式
         * MessageModel.CLUSTERING   集群消费模式
         */
        consumer.setMessageModel(MessageModel.BROADCASTING);

        consumer.registerMessageListener(RocketMqCommon.getDefaultConsumerListener());
        consumer.start();
        System.out.println("已启动消费者");
    }
}
