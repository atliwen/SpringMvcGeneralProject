package com.edi.web.service.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.edi.commcn.edi.bean.EasyUIResult;
import com.edi.commcn.httpclientapi.bean.HttpResult;
import com.edi.commcn.httpclientapi.service.ApiService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseService<T>
{

	private static final ObjectMapper ObjectMapper = new ObjectMapper();

	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public BaseService()
	{
		super();
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType ptype = (ParameterizedType) type;
		this.clazz = (Class<T>) ptype.getActualTypeArguments()[0];
		ObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Value("${restful.url}")
	private String url;

	@Autowired
	private ApiService aipService;

	/**
	 * 具体功能地址
	 */
	public String function;

	/**  
	 * 设置具体功能地址  
	 * 
	 */
	public abstract String getFunction();

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public T queryByID(Long id) throws Exception
	{
		String user = aipService.doGet(url + "/" + this.getFunction() + "/" + id);
		return (T) ObjectMapper.readValue(user, clazz);
	}

	/**
	 * 查询所有数据
	 * 
	 * @return
	 * @throws Exception 
	 */
	public List<T> queryAll() throws Exception
	{
		String user = aipService.doGet(url + "/" + this.getFunction() + "/all");
		JavaType javaType = ObjectMapper.getTypeFactory().constructParametricType(ArrayList.class,
				clazz);
		return (List<T>) ObjectMapper.readValue(user, javaType);

	}

	/**
	 * 根据条件查询
	 * 
	 * @param t
	 * @return
	 * @throws Exception 
	 */
	public List<T> queryListByWhere(T t) throws Exception
	{
		String user = aipService.doGet(url + "/" + this.getFunction() + "/where", t);
		JavaType javaType = ObjectMapper.getTypeFactory().constructParametricType(ArrayList.class,
				clazz);
		return (List<T>) ObjectMapper.readValue(user, javaType);
	}

	/**
	 * 根据条件分页查询
	 * 
	 * @param t
	 * @param page
	 * @param rows 
	 * @return
	 * @throws Exception 
	 */
	public EasyUIResult queryPageByWhere(T t, Integer page, Integer rows) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("rows", rows);
		String user = aipService.doGet(url + "/" + this.getFunction() + "/pagedesc", t, map);
		return (EasyUIResult) ObjectMapper.readValue(user, EasyUIResult.class);

	}

	/**
	 * 分页    倒叙条件
	 * @param t
	 * @param page
	 * @param rows
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public EasyUIResult queryListByPageAndOrder(T t, Integer page, Integer rows, String order)
			throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("rows", rows);
		map.put("order", order);
		String user = aipService.doGet(url + "/" + this.getFunction() + "/pagedesc", t, map);
		return (EasyUIResult) ObjectMapper.readValue(user, EasyUIResult.class);

	}

	/**
	 * 保存
	 * 
	 * @param t
	 * @throws Exception 
	 */
	public HttpResult saveSelective(T t) throws Exception
	{
		return aipService.doPost(url + "/" + this.getFunction(), t);
	}

	/**
	 * 更新
	 * 
	 * @param t
	 * @throws Exception 
	 */
	public HttpResult updateByIdSelective(T t) throws Exception
	{
		return aipService.doPut(url + "/" + this.getFunction(), t);
	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @throws Exception 
	 */
	public HttpResult deleteById(Long id) throws Exception
	{
		return aipService.doDelete(url + "/" + this.getFunction() + "?id=" + id);
	}

}