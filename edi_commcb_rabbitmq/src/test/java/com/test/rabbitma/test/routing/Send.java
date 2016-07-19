package com.test.rabbitma.test.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.test.rabbitma.test.util.ConnectionUtil;

public class Send
{

	private final static String EXCHANGE_NAME = "test_exchange_direct";

	public static void main(String[] argv) throws Exception
	{
		// 获取到连接以及mq通道
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();

		// 声明exchange
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");

		// 消息内容
		String message = "Hello World!";
		// Key 指定 那个消费者 消费
		channel.basicPublish(EXCHANGE_NAME, "key2", null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");

		channel.close();
		connection.close();
	}
}