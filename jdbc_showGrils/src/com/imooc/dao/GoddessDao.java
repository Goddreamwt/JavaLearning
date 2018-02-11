package com.imooc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.imooc.db.DBUtil;
import com.imooc.model.Goddess;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class GoddessDao {

	// 添加女神信息
	public void addGoddess(Goddess g) throws Exception {
		Connection conn = DBUtil.getConnection();// 获取数据库连接

		String sql = "" + "INSERT INTO imooc_goddess" + "(user_name,sex,age,birthday,email,mobile,"
				+ "create_user,create_date,update_user,update_date,isdel)" + "values("
				+ "?,?,?,?,?,?,?,current_date(),?,current_date(),?)";
		PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);// 预编译sql语句

		ptmt.setString(1, g.getUser_name());
		ptmt.setInt(2, g.getSex());
		ptmt.setInt(3, g.getAge());
		ptmt.setDate(4, new Date(g.getBirthday().getTime()));
		ptmt.setString(5, g.getEmail());
		ptmt.setString(6, g.getMobile());
		ptmt.setString(7, g.getCreate_user());
		ptmt.setDate(8, (Date) g.getUpdate_date());
		ptmt.setInt(9, g.getIsdel());
		ptmt.execute();
	}

	//更新女神信息
	public void updateGoddess(Goddess g) throws SQLException {
		Connection conn = DBUtil.getConnection();// 获取数据库连接

		String sql = "" + 
		             " UPDATE imooc_goddess" + 
		             " SET user_name=?,sex=?,age=?,birthday=?,email=?,mobile=?,"+ 
				     " update_user=?,update_date=current_date(),isdel=?" + 
		             " WHERE id=?";
		PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);// 预编译sql语句

		ptmt.setString(1, g.getUser_name());
		ptmt.setInt(2, g.getSex());
		ptmt.setInt(3, g.getAge());
		ptmt.setDate(4, new Date(g.getBirthday().getTime()));
		ptmt.setString(5, g.getEmail());
		ptmt.setString(6, g.getMobile());
		ptmt.setString(7, g.getUpdate_user());
		ptmt.setInt(8, g.getIsdel());
		ptmt.setInt(9, g.getId());
		ptmt.execute();
	}

	//删除
	public void delGoddess(Integer id) throws SQLException {
		Connection conn = DBUtil.getConnection();// 获取数据库连接

		String sql = "" + 
		             " DELETE FROM imooc_goddess" + 
		             " WHERE id=?";
		PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);// 预编译sql语句

		ptmt.setInt(1, id);
		ptmt.execute();
	}

	//查找全部1
	public List<Goddess> query() throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement stmt = (Statement) conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT id,user_name,age FROM imooc_goddess");

		List<Goddess> gs = new ArrayList<Goddess>();

		Goddess g = null;

		while (rs.next()) {
			g = new Goddess();
			g.setId(rs.getInt("id"));
			g.setUser_name(rs.getString("user_name"));
			g.setAge(rs.getInt("age"));

			gs.add(g);
		}
		return gs;
	}
	//根据参数查找1
	public List<Goddess> query(String name,String mobile,String email) throws Exception {
		List<Goddess> result = new ArrayList<Goddess>();
		
		Connection conn = DBUtil.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM imooc_goddess ");
		
		sb.append(" WHERE user_name LIKE ? AND mobile LIKE ? AND email LIKE ?");
		
		PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sb.toString());
		ptmt.setString(1,"%"+ name+"%");
		ptmt.setString(2, "%"+mobile+"%");
		ptmt.setString(3, "%"+email+"%");
		System.out.println(sb.toString());
		
		ResultSet rs =ptmt.executeQuery();
		
		Goddess g = null;

		while (rs.next()) {
			g = new Goddess();
			g.setId(rs.getInt("id"));
			g.setUser_name(rs.getString("user_name"));
			g.setAge(rs.getInt("age"));
			g.setSex(rs.getInt("sex"));
			g.setBirthday(rs.getDate("birthday"));
			g.setEmail(rs.getString("email"));
			g.setMobile(rs.getString("mobile"));
			g.setCreate_date(rs.getDate("create_date"));
			g.setCreate_user(rs.getString("create_user"));
			g.setUpdate_date(rs.getDate("update_date"));
			g.setUpdate_user(rs.getString("update_user"));
			g.setIsdel(rs.getInt("isdel"));

			result.add(g);
		}
		return result;
	}
	
	//根据参数查找2
	public List<Goddess> query(List<Map<String, Object>> params) throws Exception {
		List<Goddess> result = new ArrayList<Goddess>();
		
		Connection conn = DBUtil.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM imooc_goddess WHERE 1=1 ");
		
		if(params !=null && params.size()>0) {
			for (int i = 0; i < params.size(); i++) {
				Map<String, Object> map=params.get(i);
				sb.append(" AND  " +map.get("name")+
						" " + map.get("rela")+
						" "+map.get("value")+
						" ");
			}
		}
		
		PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sb.toString());
		System.out.println(sb.toString());
		
		ResultSet rs =ptmt.executeQuery();
		
		Goddess g = null;

		while (rs.next()) {
			g = new Goddess();
			g.setId(rs.getInt("id"));
			g.setUser_name(rs.getString("user_name"));
			g.setAge(rs.getInt("age"));
			g.setSex(rs.getInt("sex"));
			g.setBirthday(rs.getDate("birthday"));
			g.setEmail(rs.getString("email"));
			g.setMobile(rs.getString("mobile"));
			g.setCreate_date(rs.getDate("create_date"));
			g.setCreate_user(rs.getString("create_user"));
			g.setUpdate_date(rs.getDate("update_date"));
			g.setUpdate_user(rs.getString("update_user"));
			g.setIsdel(rs.getInt("isdel"));

			result.add(g);
		}
		return result;
	}
	
	public Goddess get(Integer id) throws SQLException {
		Goddess g =null;
		Connection conn = DBUtil.getConnection();// 获取数据库连接

		String sql = "" + 
		             " SELECT * FROM imooc_goddess" + 
		             " WHERE id=?";
		PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);// 预编译sql语句

		ptmt.setInt(1, id);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			g = new Goddess();
			g.setId(rs.getInt("id"));
			g.setUser_name(rs.getString("user_name"));
			g.setAge(rs.getInt("age"));
			g.setSex(rs.getInt("sex"));
			g.setBirthday(rs.getDate("birthday"));
			g.setEmail(rs.getString("email"));
			g.setMobile(rs.getString("mobile"));
			g.setCreate_date(rs.getDate("create_date"));
			g.setCreate_user(rs.getString("create_user"));
			g.setUpdate_date(rs.getDate("update_date"));
			g.setUpdate_user(rs.getString("update_user"));
			g.setIsdel(rs.getInt("isdel"));
		}
		return g;
	}
}
