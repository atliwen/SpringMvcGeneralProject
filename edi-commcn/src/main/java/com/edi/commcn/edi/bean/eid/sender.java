package com.edi.commcn.edi.bean.eid;

public class sender
{
	/**
	 * 详细地址(丌要包含省市区\县) 
	 */
	public String address;

	/**
	 * 寄件或收件市（北京市、宜昌市）
	 */
	public String city;

	/**
	 * 寄件或收件区\县(如东城区、延庆县)
	 */
	public String district;

	/**
	 * 移劢电话，最多 2 个用/隔开
	 */
	public String mobile;

	/**
	 * 寄件人或收件人姓名(最多 15 个汉字)
	 */
	public String name;

	/**
	 * 固定电话，包括区号、电话号码及分机号
	 */
	public String phone;

	/**
	 * 寄件人或收件人邮编
	 */
	public String postCode;

	/**
	 * 寄件或收件省（北京市、湖北省）
	 */
	public String prov;

	/**  
	 * 获取详细地址(丌要包含省市区县)  
	 * @return address 详细地址(丌要包含省市区\县)  
	 */
	public String getAddress()
	{
		return address;
	}

	/**  
	 * 设置详细地址(丌要包含省市区县)  
	 * @param address 详细地址(丌要包含省市区\县)  
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**  
	 * 获取寄件或收件市（北京市、宜昌市）  
	 * @return city 寄件或收件市（北京市、宜昌市）  
	 */
	public String getCity()
	{
		return city;
	}

	/**  
	 * 设置寄件或收件市（北京市、宜昌市）  
	 * @param city 寄件或收件市（北京市、宜昌市）  
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**  
	 * 获取寄件或收件区县(如东城区、延庆县)  
	 * @return district 寄件或收件区\县(如东城区、延庆县)  
	 */
	public String getDistrict()
	{
		return district;
	}

	/**  
	 * 设置寄件或收件区县(如东城区、延庆县)  
	 * @param district 寄件或收件区\县(如东城区、延庆县)  
	 */
	public void setDistrict(String district)
	{
		this.district = district;
	}

	/**  
	 * 获取移劢电话，最多2个用隔开  
	 * @return mobile 移劢电话，最多2个用隔开  
	 */
	public String getMobile()
	{
		return mobile;
	}

	/**  
	 * 设置移劢电话，最多2个用隔开  
	 * @param mobile 移劢电话，最多2个用隔开  
	 */
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	/**  
	 * 获取寄件人或收件人姓名(最多15个汉字)  
	 * @return name 寄件人或收件人姓名(最多15个汉字)  
	 */
	public String getName()
	{
		return name;
	}

	/**  
	 * 设置寄件人或收件人姓名(最多15个汉字)  
	 * @param name 寄件人或收件人姓名(最多15个汉字)  
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**  
	 * 获取固定电话，包括区号、电话号码及分机号  
	 * @return phone 固定电话，包括区号、电话号码及分机号  
	 */
	public String getPhone()
	{
		return phone;
	}

	/**  
	 * 设置固定电话，包括区号、电话号码及分机号  
	 * @param phone 固定电话，包括区号、电话号码及分机号  
	 */
	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	/**  
	 * 获取寄件人或收件人邮编  
	 * @return postCode 寄件人或收件人邮编  
	 */
	public String getPostCode()
	{
		return postCode;
	}

	/**  
	 * 设置寄件人或收件人邮编  
	 * @param postCode 寄件人或收件人邮编  
	 */
	public void setPostCode(String postCode)
	{
		this.postCode = postCode;
	}

	/**  
	 * 获取寄件或收件省（北京市、湖北省）  
	 * @return prov 寄件或收件省（北京市、湖北省）  
	 */
	public String getProv()
	{
		return prov;
	}

	/**  
	 * 设置寄件或收件省（北京市、湖北省）  
	 * @param prov 寄件或收件省（北京市、湖北省）  
	 */
	public void setProv(String prov)
	{
		this.prov = prov;
	}

}
