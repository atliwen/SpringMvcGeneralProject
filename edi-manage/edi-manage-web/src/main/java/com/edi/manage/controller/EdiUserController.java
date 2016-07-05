package com.edi.manage.controller;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edi.manage.controller.base.BaseController;
import com.edi.manage.pojo.EdiUser;
import com.edi.manage.service.EdiUserService;

@RestController
@RequestMapping("eiduser")
public class EdiUserController extends BaseController<EdiUserService, EdiUser>
{

	// 通用方法 满足不了需求的时候 可直接从写 改方法

	/**
	 * 通过 ID 查询用户
	 * @return
	 */
	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<EdiUser> queryMByid(@PathVariable("id") Long id)
	{
		EdiUser user = new EdiUser();
		user.setClientflag("111");
		user.setDataflag("111");
		user.setDatatype("111");
		user.setId(111L);
		user.setName("111");
		user.setOrdertype(1);
		user.setUpdatetime(new Date());
		user.setRecordtime(new Date());
		user.setVerifyseed("1");
		user.setXid(11);
		user.setIp("100000");
		return ResponseEntity.ok(user);
	}

}
