package com.edi.manage.comtroller.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.edi.commcn.edi.bean.EasyUIResult;
import com.edi.manage.pojo.BasePojo;
import com.edi.manage.service.BaseService;
import com.github.pagehelper.PageInfo;

public class BaseComtroller<T extends BaseService, M extends BasePojo>
{

	@Autowired
	private T MService;

	/**
	 * 通过 ID 查询用户
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<M> queryMByid(@PathVariable("id") Long id)
	{
		try
		{
			return ResponseEntity.ok((M) MService.queryByID(id));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	/**
	 * 获取所有的用户
	 * @return
	 */
	@RequestMapping(value = "all", method = RequestMethod.GET)
	public ResponseEntity<List<M>> queryMList()
	{
		return ResponseEntity.ok(MService.queryAll());
	}

	/**
	 * 提供rest接口，分页查询商品信息 倒序
	 * 
	 * @param page 当前页
	 * @param rows 每页几条
	 * @return 返回 http 状态为 200 时  fanh
	 */

	@RequestMapping(value = "page", method = RequestMethod.GET)
	// 将 EasyUIResult 序列号为JSON
	public ResponseEntity<EasyUIResult> queryItemList(@RequestParam("page") Integer page,
			@RequestParam("rows") Integer rows)
	{
		try
		{
			PageInfo<M> pageInfo = this.MService.queryListByPageAndOrder(null, page, rows, null);
			EasyUIResult easyUIResult = new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());

			return ResponseEntity.ok(easyUIResult);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	/**
	 * 删除  通过主键ID 
	 * 
	 * @param TbContentCategory
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteContentCategory(@RequestParam("id") Long id)
	{
		try
		{
			this.MService.deleteById(id);
			return ResponseEntity.ok(null);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	/**
	 * 更新
	 * 
	 * @param TbContentCategory
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> update(M M)
	{

		try
		{
			this.MService.updateByIdSelective(M);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(null);

	}

	/**
	 * 保存一个子节点到数据  
	 * @param TbContentCategory
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<M> saveContentCategory(M M)
	{
		try
		{
			this.MService.saveSelective(M);
			return ResponseEntity.ok(M);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
