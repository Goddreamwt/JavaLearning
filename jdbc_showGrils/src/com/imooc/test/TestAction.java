package com.imooc.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.imooc.action.GoddessAction;
import com.imooc.model.Goddess;

public class TestAction {

	public static void main(String[] args) throws Exception {
		GoddessAction action = new GoddessAction();
		
		Goddess g = new Goddess();
		g.setUser_name("小白");
		g.setSex(1);
		g.setAge(18);
		g.setBirthday(new Date());
		g.setEmail("xiaoqing@imooc.com");
		g.setMobile("18500468829");
		g.setIsdel(0);
		g.setId(6);
//		action.add(g);
		action.edit(g);
		/*
		 * 查询
		 */
		List<Map<String, Object>> params = new ArrayList<Map<String,Object>>();
		Map<String, Object> map =  new HashMap<String,Object>();
		
		map.put("name", "user_name");
		map.put("rela", "=");
		map.put("value", "'小美'");
		
		params.add(map);
		
		List<Goddess> result = action.query(params);

		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).getId() + ":" + result.get(i).getUser_name());
		}
	}
}
