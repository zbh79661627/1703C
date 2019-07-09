package com.bw.DpService;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bw.bean.Brand;
import com.bw.mapper.BrandDao;
@Service
public class DpServiceImp implements DpService {

	@Resource
	BrandDao dao;
	
	@Override
	public List<Brand> findAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findAll(map);
	}

	@Override
	public List<Brand> select() {
		// TODO Auto-generated method stub
		return dao.select();
	}

	@Override
	public Integer add(Brand br) {
		// TODO Auto-generated method stub
		return dao.add(br);
	}

	@Override
	public Integer del(String did) {
		// TODO Auto-generated method stub
		return dao.del(did);
	}

	@Override
	public List<Brand> finddid(String did) {
		// TODO Auto-generated method stub
		return dao.finddid(did);
	}

}
