package com.demo.rocketmq.order;

import com.demo.rocketmq.RocketMqCommon;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author jj
 * @since 2020/3/29 22:29
 */
public class OrderedConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = RocketMqCommon.getDefaultConsumer("TagA || TagB || TagC");
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);


        // 4. 注册消息接收到Broker消息后的处理接口
//        注1：普通消息消费不能保证消息处理顺序
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//                doBiz(list.get(0));
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });


        // consumer
        consumer.setMaxReconsumeTimes(-1);
        // 延时  level  3
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs,
                                                       ConsumeOrderlyContext context) {
                context.setAutoCommit(true);
                doBiz(msgs.get(0));

                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        // 5. 启动消费者(必须在注册完消息监听器后启动，否则会报错)
        consumer.start();

        System.out.println("已启动消费者");
    }


    /**
     * 模拟处理业务
     *
     * @param message
     */
    public static void doBiz(Message message) {
        try {
            System.out.printf("线程：%-25s 接收到新消息 %s --- %s %n", Thread.currentThread().getName(), message.getTags(), new String(message.getBody(), RemotingHelper.DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
