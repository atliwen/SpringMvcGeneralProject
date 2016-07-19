package com.test.rabbitma.test.ps;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.test.rabbitma.test.util.ConnectionUtil;

public class Send
{

	private final static String EXCHANGE_NAME = "test_exchange_fanout";

	public static void main(String[] argv) throws Exception
	{
		// 获取到连接以及mq通道
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();

		// 声明exchange 交换机
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

		// 消息内容
		String message = "Hello World!";
		channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");

		channel.close();
		connection.close();

		/**
		 * PS 
		 *   如果将消息 发送到没有绑定队列的 交换器中， 该消息将丢失！
		 * 
		 *   交换机模式中 ，同一个消息将被多个 消费端接收！
		 * 
		 */
	}
}