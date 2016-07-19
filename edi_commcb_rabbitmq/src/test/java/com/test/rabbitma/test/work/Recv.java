package com.test.rabbitma.test.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.test.rabbitma.test.util.ConnectionUtil;

public class Recv
{

	private final static String QUEUE_NAME = "test_queue_work";

	public static void main(String[] argv) throws Exception
	{

		// 获取到连接以及mq通道
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();

		// 声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		// 同一时刻服务器只会发一条消息给消费者 // 不设置 会均匀分配， 会照成 消费端消费快的等待中
		channel.basicQos(1);

		// 定义队列的消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		// 监听队列，手动返回完成 // false 不自动确认消费完成 需要手动确认
		channel.basicConsume(QUEUE_NAME, false, consumer);

		// 获取消息
		while (true)
		{
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println(" [x] Received '" + message + "'");
			// 休眠
			Thread.sleep(10);
			// 返回确认状态
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}
	}
}