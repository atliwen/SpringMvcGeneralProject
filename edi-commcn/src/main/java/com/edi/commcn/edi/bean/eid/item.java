package com.edi.commcn.edi.bean.eid;

public class item
{
	/**
	 * 商品总品名：品名*数量|…|品名*数量 
	 */
	public String itemName;

	/**
	 * 商品数量
	 */
	public String itemNumber;

	/**
	 * 商品价值
	 */
	public String itemValue;

	/**
	 * 商品体积
	 */
	public String itemVolume;

	/**
	 * 总重量（KG 精度.00） 
	 */
	public String itemWeight;

	/**  
	 * 获取商品总品名：品名数量|…|品名数量  
	 * @return itemName 商品总品名：品名数量|…|品名数量  
	 */
	public String getItemName()
	{
		return itemName;
	}

	/**  
	 * 设置商品总品名：品名数量|…|品名数量  
	 * @param itemName 商品总品名：品名数量|…|品名数量  
	 */
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}

	/**  
	 * 获取商品数量  
	 * @return itemNumber 商品数量  
	 */
	public String getItemNumber()
	{
		return itemNumber;
	}

	/**  
	 * 设置商品数量  
	 * @param itemNumber 商品数量  
	 */
	public void setItemNumber(String itemNumber)
	{
		this.itemNumber = itemNumber;
	}

	/**  
	 * 获取商品价值  
	 * @return itemValue 商品价值  
	 */
	public String getItemValue()
	{
		return itemValue;
	}

	/**  
	 * 设置商品价值  
	 * @param itemValue 商品价值  
	 */
	public void setItemValue(String itemValue)
	{
		this.itemValue = itemValue;
	}

	/**  
	 * 获取商品体积  
	 * @return itemVolume 商品体积  
	 */
	public String getItemVolume()
	{
		return itemVolume;
	}

	/**  
	 * 设置商品体积  
	 * @param itemVolume 商品体积  
	 */
	public void setItemVolume(String itemVolume)
	{
		this.itemVolume = itemVolume;
	}

	/**  
	 * 获取总重量（KG精度.00）  
	 * @return itemWeight 总重量（KG精度.00）  
	 */
	public String getItemWeight()
	{
		return itemWeight;
	}

	/**  
	 * 设置总重量（KG精度.00）  
	 * @param itemWeight 总重量（KG精度.00）  
	 */
	public void setItemWeight(String itemWeight)
	{
		this.itemWeight = itemWeight;
	}

}
