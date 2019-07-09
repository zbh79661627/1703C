package com.bw.DpService;

import java.util.List;
import java.util.Map;

import com.bw.bean.Brand;

public interface DpService {

	public List<Brand> findAll(Map<String, Object> map);
	
	public List<Brand> select();

	public Integer add(Brand br);

	public Integer del(String did);

	public List<Brand> finddid(String did);
}
