package com.demo.rocketmq.simple2;

import com.demo.rocketmq.RocketMqCommon;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

/**
 * 发送异步消息
 * 一般用来对方法调用响应时间有较严格要求的情况下，异步调用，立即返回
 * 不同于同步的唯一在于： send方法调用的时候多携带一个回调接口参数，用来异步处理消息发送结果
 * @author jj
 */
public class ASyncProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException, UnsupportedEncodingException {
        DefaultMQProducer producer = RocketMqCommon.getDefaultProducer();

        // 设置异步发送失败重试次数，默认为2
        producer.setRetryTimesWhenSendAsyncFailed(0);

        // 发送消息
        int count = 10;
        CountDownLatch cd = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            final int index = i;
            Message mssage = new Message("RocketMQDemo", "ASync", ("Hello MQ:" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(mssage, new SendCallback(){
                /**
                 * 发送成功的回调函数
                 * 但会结果有多种状态，在SendStatus枚举中定义
                 * @param sendResult
                 */
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%-10d OK MSG_ID:%s %n", index, sendResult.getMsgId());
                    cd.countDown();
                }

                /**
                 * 发送失败的回调函数
                 * @param e
                 */
                @Override
                public void onException(Throwable e) {
                    System.out.printf("%-10d Exception %s %n", index, e);
                    e.printStackTrace();
                    cd.countDown();
                }
            });
        }
        // 确保消息都发送完成
        cd.await();
        // 停止生产者
        producer.shutdown();

    }
}
