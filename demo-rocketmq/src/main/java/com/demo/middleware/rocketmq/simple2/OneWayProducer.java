package com.demo.middleware.rocketmq.simple2;

import com.demo.middleware.rocketmq.RocketMqCommon;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * 单向模式
 * 一般用来对可靠性有一定要求的消息发送，例如日志系统
 * 不同于同步的唯一之处在于：调用的是sendOneway方法，且方法不返回任何值，即调用者不需要关心成功或失败
 * @author jj
 * @since 2020/3/29 16:37
 */
public class OneWayProducer {
    public static void main(String[] args) throws RemotingException, MQClientException, InterruptedException, UnsupportedEncodingException {

        DefaultMQProducer producer = RocketMqCommon.getDefaultProducer();
        // 启动生产者
        producer.start();

        // 发送消息
        for (int i = 0; i < 10; i++) {
            Message mssage = new Message("RocketMQDemo", "OneWay", ("Hello MQ:" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.sendOneway(mssage);
        }
        System.out.println("消息已发送");

        // 停止生产者
        producer.shutdown();
    }
}
