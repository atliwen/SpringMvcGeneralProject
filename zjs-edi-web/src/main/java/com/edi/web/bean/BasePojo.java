package com.edi.web.bean;

import java.util.Date;

public class BasePojo
{
	/**
	 * 更新时间
	 */

	private Date updatetime;

	/**
	 * 创建时间
	 */

	private Date recordtime;

	/**  
	 * 获取更新时间  
	 * @return updatetime 更新时间  
	 */
	public Date getUpdatetime()
	{
		return updatetime;
	}

	/**  
	 * 设置更新时间  
	 * @param updatetime 更新时间  
	 */
	public void setUpdatetime(Date updatetime)
	{
		this.updatetime = updatetime;
	}

	/**  
	 * 获取创建时间  
	 * @return recordtime 创建时间  
	 */
	public Date getRecordtime()
	{
		return recordtime;
	}

	/**  
	 * 设置创建时间  
	 * @param recordtime 创建时间  
	 */
	public void setRecordtime(Date recordtime)
	{
		this.recordtime = recordtime;
	}

}
