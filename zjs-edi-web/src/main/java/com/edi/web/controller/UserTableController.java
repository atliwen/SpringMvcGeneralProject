package com.edi.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edi.web.bean.EdiUser;
import com.edi.web.controller.base.BaseController;

@RestController
@RequestMapping("table")
public class UserTableController extends BaseController<EdiUser>
{

}
