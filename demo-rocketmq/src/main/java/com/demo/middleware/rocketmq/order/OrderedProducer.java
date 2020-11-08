package com.demo.middleware.rocketmq.order;

import com.demo.middleware.rocketmq.RocketMqCommon;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 有序消息
 * @author jj
 * @since 2020/3/29 22:29
 */
public class OrderedProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException, UnsupportedEncodingException {
        DefaultMQProducer producer = RocketMqCommon.getDefaultProducer();
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);
        // 定义消息选择器
        MessageQueueSelector selector = new MessageQueueSelector() {
            /**
             * 消息队列选择器，保证同一条业务数据的消息在同一个队列
             * @param mqs topic中所有队列的集合
             * @param msg 发送的消息
             * @param arg 此参数是本示例中producer.send的第三个参数
             * @return
             */
            @Override
            public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                Integer id = (Integer) arg;
                int index = id % mqs.size();
                /* 通过使用broke中指定queue来达到消息有序 */

                // 分区顺序
                return mqs.get(index);

                // 全局顺序
//                return mqs.get(mqs.size()-1);
            }
        };
        String[] tags = new String[]{"TagA", "TagB", "TagC"};

        List<Map> bizDatas = getBizDatas();

        // 5：循环发送消息
        for (int i = 0; i < bizDatas.size(); i++) {
            Map bizData = bizDatas.get(i);
            // keys：业务数据的ID，比如用户ID、订单编号等等
            Message msg = new Message(RocketMqCommon.DEFAULT_TOPIC_NAME, tags[i % tags.length], "" + bizData.get("msgType"), bizData.toString().getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 发送有序消息
            SendResult sendResult = producer.send(msg, selector, bizData.get("msgType"));

            System.out.printf("%s， body:%s%n", sendResult, bizData);
        }

        // 6：关闭生产者
        producer.shutdown();
    }
    public static List<Map> getBizDatas() {
        List<Map> orders = new ArrayList<Map>();

        HashMap orderData = new HashMap();
        orderData.put("msgType", 1001);
        orderData.put("userId", "tony");
        orderData.put("desc", "1");
        orders.add(orderData);

        orderData = new HashMap();
        orderData.put("msgType", 1001);
        orderData.put("userId", "tony");
        orderData.put("desc", "2");
        orders.add(orderData);

        orderData = new HashMap();
        orderData.put("msgType", 1001);
        orderData.put("userId", "tony");
        orderData.put("desc", "3");
        orders.add(orderData);

        orderData = new HashMap();
        orderData.put("msgType", 1001);
        orderData.put("userId", "tony");
        orderData.put("desc", "4");
        orders.add(orderData);

        orderData = new HashMap();
        orderData.put("msgType", 1001);
        orderData.put("userId", "tony");
        orderData.put("desc", "5");
        orders.add(orderData);

        orderData = new HashMap();
        orderData.put("msgType", 1001);
        orderData.put("userId", "tony");
        orderData.put("desc", "6");
        orders.add(orderData);

        orderData = new HashMap();
        orderData.put("msgType", 1001);
        orderData.put("userId", "tony");
        orderData.put("desc", "7");
        orders.add(orderData);


        orderData = new HashMap();
        orderData.put("msgType", 1001);
        orderData.put("userId", "tony");
        orderData.put("desc", "8");
        orders.add(orderData);

        orderData = new HashMap();
        orderData.put("msgType", 1001);
        orderData.put("userId", "tony");
        orderData.put("desc", "9");
        orders.add(orderData);

        return orders;
    }
}
