package com.demo.rocketmq.simple;

import com.demo.rocketmq.RocketMqCommon;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author jj
 * @since 2020/3/29 11:29
 */
public class Consumer {

    public static void main(String[] args) throws MQClientException {
        // 1.创建消费者对象
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("Rocket_Demo_Group");
        // 2.设置NameServer地址，如果设置环境变量NAME_SERVER_ADDR可省略此步骤
        consumer.setNamesrvAddr(RocketMqCommon.NAME_SERVER_ADDR);
        // 3.订阅对应的主题和tag
        consumer.subscribe("RocketMQDemo", "*");
        // 4.注册消息接收后处理接口
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                try {
                    MessageExt messageExt = list.get(0);
                    System.out.printf("线程%-25s 接收到新消息%s ------ %s %n", Thread.currentThread().getName(), messageExt.getTags(), new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.out.println("已启动消费者");



    }
}
