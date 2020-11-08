package com.demo.rocketmq.batch;

import com.demo.rocketmq.RocketMqCommon;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;

/**
 * 批量
 * @author jj
 * @since 2020/3/30 11:29
 */
public class BatchMessageConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = RocketMqCommon.getDefaultConsumer("batch");
        consumer.setConsumeMessageBatchMaxSize(10);
        consumer.registerMessageListener(
                (MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
                    try {
                        // 设置消息批处理数量后，list中才会有多条，否则每次只会有一条
                        for (MessageExt messageExt : list) {
                            System.out.printf("线程：%-25s 接收到新消息 --- %s %n", Thread.currentThread().getName(), new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET));
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                });

        consumer.start();
        System.out.println("已启动消费者");

    }

}
