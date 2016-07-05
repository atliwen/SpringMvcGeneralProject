package com.edi.web.controller.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.edi.commcn.edi.bean.EasyUIResult;
import com.edi.commcn.httpclientapi.bean.HttpResult;
import com.edi.web.service.base.BaseService;

/**
* <p>Title: BaseComtroller </p>
* <p>@Description: Comtroller </p>
* <p>Company:  </p>
* @author 李文
* @date   2016年7月4日 上午9:39:48 
*/
public class BaseController<M>
{

	@Autowired
	private BaseService<M> MService;

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
	 * 通过 条件 查询用户    M 如果是个 空类型  会 查询所有 表数据 
	 * @return
	 */
	@RequestMapping(value = "where", method = RequestMethod.GET)
	public ResponseEntity<List<M>> queryMByClass(M m)
	{
		try
		{
			return ResponseEntity.ok(MService.queryListByWhere(m));
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
		try
		{
			return ResponseEntity.ok(MService.queryAll());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
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
	public ResponseEntity<EasyUIResult> queryPageList(@RequestParam("page") Integer page,
			@RequestParam("rows") Integer rows, @RequestParam("order") String order)
	{
		try
		{
			EasyUIResult easyUIResult = this.MService.queryListByPageAndOrder(null, page, rows,
					order);
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
	public ResponseEntity<HttpResult> deleteContentCategory(@RequestParam("id") Long id)
	{
		try
		{
			return ResponseEntity.ok(this.MService.deleteById(id));
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
	public ResponseEntity<HttpResult> update(M M)
	{

		try
		{
			return ResponseEntity.ok(this.MService.updateByIdSelective(M));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	/**
	 * 保存一个子节点到数据  
	 * @param TbContentCategory
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<HttpResult> saveContentCategory(M M)
	{
		try
		{
			return ResponseEntity.ok(this.MService.saveSelective(M));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}