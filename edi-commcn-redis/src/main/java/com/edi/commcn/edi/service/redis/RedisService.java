package com.edi.commcn.edi.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
* <p>Title: RedisService </p>
* <p>@Description: Resdis API  </p>
* <p>Company:  </p>
* @author 李文
* @date   2016年7月01日 上午10:56:06 
*/
@Service
public class RedisService
{

	// 如果容器中存在，就注入，否则就不注入
	@Autowired(required = false)
	private ShardedJedisPool shardedJedisPool;

	/**
	 * 回调方法
	 * @param fun
	 * @return
	 */
	private <T> T execute(Function<T, ShardedJedis> fun)
	{
		ShardedJedis shardedJedis = null;
		try
		{
			// 从连接池中获取到jedis分片对象
			shardedJedis = shardedJedisPool.getResource();
			// 从redis中获取数据
			return fun.callback(shardedJedis);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (null != shardedJedis)
			{
				// 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
				shardedJedis.close();
			}
		}
		return null;
	}

	/**
	 * 保存数据到redis中
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(final String key, final String value)
	{
		return execute(shardedJedis -> {
			return shardedJedis.set(key, value);
		});
	}

	/**
	 * 从redis中获取数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String get(final String key)
	{
		return execute(shardedJedis -> {
			return shardedJedis.get(key);
		});
	}

	/**
	 * 从redis中获取数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long del(final String key)
	{
		return execute(shardedJedis -> {
			return shardedJedis.del(key);
		});
	}

	/**
	 * 保存数据到redis中,并设置生存时间
	 * 
	 * @param key
	 * @param value
	 * @param time 生存时间，单位是秒
	 * @return
	 */
	public Long set(final String key, final String value, final Integer time)
	{
		return execute(shardedJedis -> {
			shardedJedis.set(key, value);
			return shardedJedis.expire(key, time);
		});
	}

	/**
	 * 根据key，设置数据的生存时间
	 * 
	 * @param key
	 * @param value
	 * @param time
	 * @return
	 */
	public Long set(final String key, final Integer time)
	{
		return execute(shardedJedis -> {
			return shardedJedis.expire(key, time);
		});
	}

}
