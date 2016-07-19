package com.test.rabbitma.test.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.test.rabbitma.test.util.ConnectionUtil;

public class Recv
{
	private final static String QUEUE_NAME = "test_queue";

	public static void main(String[] argv) throws Exception
	{

		// 获取到连接以及mq通道
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();

		// 声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		// 定义队列的消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		// 监听队列 // true 自动确认消费 只要获取到消息了 就算作消费成功
		channel.basicConsume(QUEUE_NAME, true, consumer);

		// 获取消息
		while (true)
		{
			// nextDelivery 方法 会一直等待新的消息 后续执行暂停
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println(" [x] Received '" + message + "'");
		}
	}
}
