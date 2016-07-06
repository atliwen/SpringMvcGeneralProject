package com.edi.commcn.httpclientapi.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edi.commcn.httpclientapi.bean.HttpResult;

@Service
public class ApiService
{

	// 如果容器中存在，就注入，否则就不注入
	@Autowired(required = false)
	private CloseableHttpClient httpClient;

	// 如果容器中存在，就注入，否则就不注入
	@Autowired(required = false)
	private RequestConfig config;

	/**
	 * 带参数的get请求,如果返回状态码为200，则返回body，如果不为200，则返回null
	 * @param url  
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public String doGet(String url, Object t) throws Exception
	{
		return doGet(url, getClassMap(t, new HashMap<String, Object>()));
	}

	/**
	 * 带参数的get请求,如果返回状态码为200，则返回body，如果不为200，则返回null
	 * @param url  
	 * @param t
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String doGet(String url, Object t, Map<String, Object> map) throws Exception
	{
		return doGet(url, getClassMap(t, map));
	}

	/**
	 * 带参数的get请求,如果返回状态码为200，则返回body，如果不为200，则返回null
	 * 
	 * @param url
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String doGet(String url, Map<String, Object> map) throws Exception
	{
		// 声明URIBuilder，准备进行请求属性的拼接
		URIBuilder uriBuilder = new URIBuilder(url);
		if (map != null)
		{
			// 遍历map，拼接请求属性
			for (Map.Entry<String, Object> entry : map.entrySet())
			{
				if (entry.getValue() == null) uriBuilder.setParameter(entry.getKey(), "");
				else uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
			}
		}
		// 声明http get请求
		HttpGet httpGet = new HttpGet(uriBuilder.build());
		// 加载配置信息
		httpGet.setConfig(config);
		// 发出请求
		CloseableHttpResponse response = this.httpClient.execute(httpGet);
		// 对响应进行判断，如果响应码为200，则返回响应体的内容
		if (response.getStatusLine().getStatusCode() == 200) { return EntityUtils.toString(
				response.getEntity(), "UTF-8"); }
		// 如果响应码不为200，则返回null
		return null;
	}

	/**
	 * 不带参数的get请求,如果返回状态码为200，则返回body，如果不为200，则返回null
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String doGet(String url) throws Exception
	{
		return this.doGet(url, null);
	}

	/**
	 * 带参数的post请求
	 * 
	 * @param url
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public HttpResult doPost(String url, Object t) throws Exception
	{
		return doPost(url, getClassMap(t, new HashMap<String, Object>()));
	}

	/**
	 * 带参数的post请求
	 * 
	 * @param url
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public HttpResult doPost(String url, Object t, Map<String, Object> map) throws Exception
	{
		return doPost(url, getClassMap(t, map));
	}

	/**
	 * 带参数的post请求
	 * 
	 * @param url
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HttpResult doPost(String url, Map<String, Object> map) throws Exception
	{

		HttpPost httpPost = new HttpPost(url);
		// 加载配置信息
		httpPost.setConfig(config);

		if (map != null)
		{
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			// 遍历map，组装成form表单
			for (Map.Entry<String, Object> entry : map.entrySet())
			{
				if (entry.getValue() == null) list.add(new BasicNameValuePair(entry.getKey(), ""));
				else list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
			}
			// 构造一个form表单式的实体
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");
			// 将请求实体设置到httpPost对象中
			httpPost.setEntity(urlEncodedFormEntity);
		}
		// 发出请求
		CloseableHttpResponse response = this.httpClient.execute(httpPost);
		// 对响应进行判断，如果响应码为200，则返回响应体的内容
		return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
				response.getEntity(), "UTF-8"));
	}

	/**
	 * 带参数的post请求,body是json格式
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public HttpResult doPost(String url) throws Exception
	{
		return this.doPost(url, null);
	}

	public HttpResult doPostJson(String url, String json) throws Exception
	{
		// 声明http post
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(config);

		// 把json封装到StringEntity
		StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);

		// 把StringEntity放到httpPost
		httpPost.setEntity(stringEntity);

		// 执行请求
		CloseableHttpResponse response = this.httpClient.execute(httpPost);

		return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
				response.getEntity(), "UTF-8"));

	}

	/**
	 * 带参数的http Put请求
	 * 
	 * @param url
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public HttpResult doPut(String url, Object t) throws Exception
	{
		return doPut(url, getClassMap(t, new HashMap<String, Object>()));
	}

	/**
	 * 带参数的http Put请求
	 * 
	 * @param url
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public HttpResult doPut(String url, Object t, Map<String, Object> map) throws Exception
	{
		return doPut(url, getClassMap(t, map));
	}

	/**
	 * 带参数的http Put请求
	 * 
	 * @param url
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public HttpResult doPut(String url, Map<String, Object> map) throws Exception
	{
		// 声明http put方法
		HttpPut httpPut = new HttpPut(url);
		httpPut.setConfig(config);

		if (map != null)
		{
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			for (Map.Entry<String, Object> entry : map.entrySet())
			{
				if (entry.getValue() == null) list.add(new BasicNameValuePair(entry.getKey(), ""));
				else list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
			}
			// 创建from表单
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");
			httpPut.setEntity(urlEncodedFormEntity);
		}

		// 发出请求
		CloseableHttpResponse response = this.httpClient.execute(httpPut);

		return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
				response.getEntity(), "UTF-8"));
	}

	/**
	 * 不带参数的put提交
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public HttpResult doPut(String url) throws Exception
	{
		return this.doPut(url, null);
	}

	public HttpResult doDelete(String url) throws Exception
	{
		// 声明http Delete方法
		HttpDelete httpDelete = new HttpDelete(url);
		httpDelete.setConfig(config);

		// 发出请求
		CloseableHttpResponse response = this.httpClient.execute(httpDelete);

		return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
				response.getEntity(), "UTF-8"));
	}

	// ----辅助方法
	public Map<String, Object> getClassMap(Object t, Map<String, Object> map) throws Exception
	{

		if (t == null) return map;
		// 获取t的字段
		Field[] fields = t.getClass().getDeclaredFields();
		for (Field field : fields)
		{
			// 设置为true，可以获取声明的私有字段的值
			field.setAccessible(true);
			if (field.get(t) != null)
			{
				// 非空的字段的值，加入到条件中
				map.put(field.getName(), field.get(t));
			}
		}
		return map;
	}
}
