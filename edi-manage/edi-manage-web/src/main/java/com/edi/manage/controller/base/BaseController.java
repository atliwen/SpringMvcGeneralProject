package com.edi.manage.controller.base;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

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
import com.edi.manage.service.base.BaseService;
import com.github.pagehelper.PageInfo;

/**
* <p>Title: BaseComtroller </p>
* <p>@Description: Comtroller </p>
* <p>Company:  </p>
* @author 李文
* @date   2016年7月4日 上午9:39:48 
*/
public class BaseController<T extends BaseService, M extends BasePojo>
{

	@Autowired
	private T MService;

	/**
	 * 通过 ID 查询
	 * @return
	 */
	@ApiOperation(value = "通过 ID 查询", notes = "通用模板")
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
	 * 获取所有的 数据
	 * @return
	 */
	@ApiOperation(value = "获取所有列表", notes = "通用模板")
	@RequestMapping(value = "all", method = RequestMethod.GET)
	public ResponseEntity<List<M>> queryMList()
	{
		return ResponseEntity.ok(MService.queryAll());
	}

	/**
	 * 获取更具条件查询    M 如果是个 空类型  会 查询所有 表数据 
	 * @return
	 */
	@ApiOperation(value = "更具条件查询  ", notes = " 如果是个 空类型  会 查询所有 表数据   通用模板")
	@RequestMapping(value = "where", method = RequestMethod.GET)
	public ResponseEntity<List<M>> queryMListWhere(M m)
	{
		return ResponseEntity.ok(MService.queryListByWhere(m));
	}

	/**
	 * 提供rest接口，分页查询商品信息 倒序 按照ID 
	 * 
	 * @param page 当前页
	 * @param rows 每页几条
	 * @return 返回 http 状态为 200 时  fanh
	 */
	@ApiOperation(value = "分页查询", notes = "通用模板")
	@RequestMapping(value = "pagedesc", method = RequestMethod.GET)
	@ApiImplicitParams(
	{
			@ApiImplicitParam(name = "page", value = "当前页", required = true, dataType = "Integer"),
			@ApiImplicitParam(name = "rows", value = "每页几条", required = true, dataType = "Integer"),
			@ApiImplicitParam(name = "order", value = "排序默认 按照ID  ", required = false, dataType = "String") }

	)
	// 将 EasyUIResult 序列号为JSON
	public ResponseEntity<EasyUIResult> queryPageList(@RequestParam("page") Integer page,
			@RequestParam("rows") Integer rows,
			@RequestParam(value = "order", defaultValue = "") String order)
	{
		try
		{
			System.out.println();
			PageInfo<M> pageInfo = this.MService.queryListByPageAndOrder(null, page, rows, order);
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
	@ApiOperation(value = "通过主键ID 删除", notes = "通用模板")
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
	@ApiOperation(value = "更新", notes = "通用模板")
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
	@ApiOperation(value = "新增", notes = "通用模板")
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