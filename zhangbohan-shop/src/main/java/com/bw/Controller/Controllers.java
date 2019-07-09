package com.bw.Controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bw.DpService.DpService;
import com.bw.bean.Brand;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class Controllers {

	@Resource
	DpService se;
	
	@RequestMapping("list.do")
	public String findAll(ModelMap ma,@RequestParam(required=false,defaultValue="1")Integer pageNum,String dname,String bid){
		Map<String,Object> map = new HashMap<String,Object>();
		Page<Object> pa = PageHelper.startPage(pageNum, 2);
		map.put("dname",dname);
		map.put("bid",dname);
		List<Brand> list = se.findAll(map);
		List<Brand> find = se.select();
		PageInfo<Brand> page = new PageInfo<Brand>(list);
        ma.put("list",list);
        ma.put("find",find);
        ma.put("page",page);
		return "list";
	}
	
	@RequestMapping("add.do")
	public String add(ModelMap map){
		List<Brand> find = se.select();
		 map.put("find",find);
		return "add";
	}
	
	@RequestMapping("addbrand.do")
	public String addbrand(Brand br){
		Integer integer = se.add(br);
		if(integer > 0){
			return "redirect:list.do";
		}else{
			return "redirect:add.jsp";
		}
		
		
	}
	
	@RequestMapping("del.do")
	public String del(String did){
		  Integer integer = se.del(did);
		  if(integer > 0){
				return "redirect:list.do";
			}else{
				return "redirect:list.do";
			}
	}
	
	@RequestMapping("finddid.do")
	public String finddid(String did,ModelMap ma){
             List<Brand> list = se.finddid(did);
            List<Brand> find = se.select();
            ma.put("br",list);
            ma.put("find",find);
		return "xq";
	}
}
