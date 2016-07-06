package edi.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.http.ResponseEntity;

import com.edi.commcn.edi.bean.EasyUIResult;
import com.edi.commcn.httpclientapi.bean.HttpResult;
import com.edi.web.bean.EdiUser;
import com.edi.web.controller.UserTableController;

public class WebTest
{
	UserTableController user;

	@Before
	public void setUp() throws Exception
	{
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.setValidating(false);
		context.load("classpath:spring/applicationContext*.xml");
		context.refresh();
		user = context.getBean(UserTableController.class);

	}

	/**
	 * ID 查询
	 */
	@Test
	public void Test_queryMByid()
	{

		ResponseEntity<EdiUser> userEntity = user.queryMByid(1L);
		EdiUser ediUser = userEntity.getBody();
		System.out.println(ediUser.getName());
	}

	/**
	 * ID  条件查询
	 */
	@Test
	public void Test_queryMByClass()
	{
		EdiUser eUser = new EdiUser();
		eUser.setName("111");

		ResponseEntity<List<EdiUser>> userEntity = user.queryMByClass(eUser);
		List<EdiUser> list = userEntity.getBody();
		System.out.println(list.size());
	}

	/**
	 * 查询所有
	 */
	@Test
	public void Test_queryMList()
	{
		ResponseEntity<List<EdiUser>> userEntity = user.queryMList();
		List<EdiUser> list = userEntity.getBody();
		System.out.println(list.size());
	}

	/**
	 * ID  分页查询
	 */
	@Test
	public void Test_queryItemList()
	{
		ResponseEntity<EasyUIResult> easyUIResult = user.queryPageList(2, 10, null);
		EasyUIResult e = easyUIResult.getBody();
		System.out.println(e.getRows().size());
	}

	/**
	 * 删除
	 */
	@Test
	public void Test_deleteContentCategory()
	{
		ResponseEntity<HttpResult> reseEntity = user.deleteContentCategory(2L);
		HttpResult e = reseEntity.getBody();
		System.out.println(e.getCode());
	}

	/**
	 * 更新 
	 */
	@Test
	public void Test_update()
	{
		EdiUser ediUser = new EdiUser();
		ediUser.setId(3L);
		ediUser.setIp("aaaa");
		ResponseEntity<HttpResult> reseEntity = user.update(ediUser);
		HttpResult e = reseEntity.getBody();
		System.out.println(e.getCode());
	}

}
