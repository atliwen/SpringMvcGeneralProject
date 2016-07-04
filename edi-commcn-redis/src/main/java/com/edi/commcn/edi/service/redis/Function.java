package com.edi.commcn.edi.service.redis;

/**
 * 回调方法
* <p>Title: Function </p>
* <p>@Description: </p>
* <p>Company:  </p>
* @author 李文
* @date   2016年6月21日 上午10:03:49
 */
public interface Function<T, E>
{
	public T callback(E e);
}