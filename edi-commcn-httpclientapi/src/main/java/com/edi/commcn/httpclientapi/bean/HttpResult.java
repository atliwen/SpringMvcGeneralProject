package com.edi.commcn.httpclientapi.bean;

/**
 * 发起httpclient请求，返回的对象
 *
 */
public class HttpResult
{

	private Integer code;

	private String body;

	public HttpResult()
	{
		super();
	}

	public HttpResult(Integer code, String body)
	{
		super();
		this.code = code;
		this.body = body;
	}

	public Integer getCode()
	{
		return code;
	}

	public void setCode(Integer code)
	{
		this.code = code;
	}

	public String getBody()
	{
		return body;
	}

	public void setBody(String body)
	{
		this.body = body;
	}

}
