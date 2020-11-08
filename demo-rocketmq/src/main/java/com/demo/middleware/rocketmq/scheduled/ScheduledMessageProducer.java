package com.demo.middleware.rocketmq.scheduled;

import com.demo.middleware.rocketmq.RocketMqCommon;
import org.apache.commons.lang3.RandomUtils;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 延时
 * @author jj
 * @since 2020/3/30 11:02
 */
public class ScheduledMessageProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = RocketMqCommon.getDefaultProducer();
        producer.start();

        for (int i = 0; i < 10; i++) {
            String content = "Hello scheduled message " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").format(new Date());
            Message message = new Message(RocketMqCommon.DEFAULT_TOPIC_NAME,"Scheduled", content.getBytes(RemotingHelper.DEFAULT_CHARSET));

            // 设置延时等级，此消息将在10秒后传递给消费者
            // 可以在broker服务器端自行配置messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
            message.setDelayTimeLevel(3);

            SendResult result = producer.send(message);
            System.out.printf("发送结果：%s%n", result);
            TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(300, 800));
        }
        producer.shutdown();
    }
}
