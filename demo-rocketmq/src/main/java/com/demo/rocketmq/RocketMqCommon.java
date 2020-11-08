package com.demo.rocketmq;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author jj
 * @since 2020/3/29 22:31
 */
public class RocketMqCommon {
    public static final String NAME_SERVER_ADDR = "127.0.0.1:9876";
    public static final String DEFAULT_GROUP_PRODUCER_NAME = "Rocket_Demo_Group";
    public static final String DEFAULT_GROUP_CONSUMER_NAME = "Rocket_Demo_Group";
    public static final String DEFAULT_TOPIC_NAME = "Rocket_Demo";
    public static DefaultMQProducer getDefaultProducer(){
        // 1.创建消费者对象
        DefaultMQProducer producer = new DefaultMQProducer(DEFAULT_GROUP_PRODUCER_NAME);
        // 2.设置NameServer的地址，如果设置了环境变量NAMESRV_ADDR，可以省略此步
        producer.setNamesrvAddr(NAME_SERVER_ADDR);
        return producer;
    }
    public static DefaultMQPushConsumer getDefaultConsumer(String subExpression) throws MQClientException {
        // 1.创建消费者对象
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(DEFAULT_GROUP_CONSUMER_NAME);
        // 2.设置NameServer地址，如果设置环境变量NAME_SERVER_ADDR可省略此步骤
        consumer.setNamesrvAddr(NAME_SERVER_ADDR);
        // 3.订阅对应的主题和tag
        consumer.subscribe(RocketMqCommon.DEFAULT_TOPIC_NAME, subExpression);
        return consumer;
    }
    public static void sendDefaultMessage(DefaultMQProducer producer, String tags) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {
        for (int i = 0; i < 10; i++) {
            String text = String.format("MQ Message:%d %s",i, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            Message mssage = new Message(RocketMqCommon.DEFAULT_TOPIC_NAME, tags, text.getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult result = producer.send(mssage);
            System.out.printf("发送结果：%s%n", result);
        }
    }
    public static MessageListenerConcurrently getDefaultConsumerListener(){
        return new DefaultConsumerListener();
    }


    public static class DefaultConsumerListener implements MessageListenerConcurrently {
        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
            try {
                MessageExt messageExt = list.get(0);
                System.out.printf("线程%-25s 接收到新消息%s ------ %s %n", Thread.currentThread().getName(), messageExt.getTags(), new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
    }

}
