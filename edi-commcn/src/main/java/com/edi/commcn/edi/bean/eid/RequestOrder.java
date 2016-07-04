package com.edi.commcn.edi.bean.eid;

public class RequestOrder
{
	/**
	 * 代收金额（代收订单时，金额必须>0） 
	 */
	public String codAmount;

	/**
	 * 数据标识(用于分仓发货,C2C 类客户省略) 
	 */
	public String dataFlag;

	/**
	 * 订单标识
	 */
	public String flag;

	/**
	 * 保险费(暂丌使用)
	 */
	public String insuranceValue;

	/**
	 * 商品信息
	 */
	public item[] items;

	/**
	 * 商品总品名：品名*数量|…|品名*数量 
	 */
	public String itemsName;

	/**
	 * 商品总数量。根据您的发货单位统计数量
	 */
	public String itemsNumber;

	/**
	 * 商品尺寸：长*宽*高*件数,
	 */
	public String itemsVolume;

	/**
	 * 总重量（KG 精度.00） 
	 */
	public String itemsWeight;

	/**
	 * 客户标识(宅急送提供)
	 */
	public String logisticProviderID;

	/**
	 * 快递单号(宅急送物流单号) 
	 */
	public String mailNo;

	/**
	 * 客户单号（物流订单号）
	 */
	public String orderNo;

	/**
	 * 收件人信息
	 */
	public receiver receiver;

	/**
	 * 重要提示
	 */
	public String remark;

	/**
	 * 寄件人信息
	 */
	public sender sender;

	/**
	 * 物流子订单号，多个子订单号逗号隔开
	 */
	public String subOrderNo;

	/**
	 * 业务交易号（财务对账使用）
	 */
	public String tradeNo;

	/**
	 * 代收订单 0、普通正向 1、退货订单 2 
	 */
	public String type;
}
