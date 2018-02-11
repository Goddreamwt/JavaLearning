package com.imooc.view;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.imooc.action.GoddessAction;
import com.imooc.model.Goddess;

public class View {

	private static final String CONTEXT = "欢迎来到女神禁区：\n" + "下面是女神禁区的功能列表：\n" + "[MAIN/M]:主菜单 \n"
			+ "[QUERY/Q]:查看女神的全部信息 \n" + "[GET/G]:查看某位女神的详细信息 \n" + "[ADD/A]:添加女神信息 \n" + "[UPDATE/U]:更新女神信息 \n"
			+ "[DELETE/D]:删除女神 \n" + "[SEARCH/S]:查询女神信息（根据姓名，手机号来查询） \n" + "[EXIT/E]:退出女神禁区 \n"
			+ "[BREAK/B]:退出当前功能，返回主菜单";

	private static final String OPERATION_MAIN = "MAIN";// 主菜单
	private static final String OPERATION_QUERY = "QUERY";// 查询
	private static final String OPERATION_GET = "GET";// 获得某位女生信息
	private static final String OPERATION_ADD = "ADD";// 添加
	private static final String OPERATION_UPDATE = "UPDATE";// 更新
	private static final String OPERATION_DELETE = "DELETE";// 删除
	private static final String OPERATION_SEARCH = "SEARCH";// 根据姓名手机号查询
	private static final String OPERATION_EXIT = "EXIT";// 退出
	private static final String OPERATION_BREAK = "BREAK";// 返回主菜单

	private static Scanner scan;

	public static void main(String[] args) {

		System.out.println(CONTEXT);

		// 怎么保持程序一直运行

		scan = new Scanner(System.in);

		Goddess goddess = new Goddess();
		GoddessAction action = new GoddessAction();
		String prenious = null;
		Integer step = 1;
		while (scan.hasNext()) {
			String in = scan.next().toString();
			if (OPERATION_EXIT.equals(in.toUpperCase()) || OPERATION_EXIT.substring(0, 1).equals(in.toUpperCase())) {
				System.out.println("您已成功退出女神禁区。");
				break;
			} else if (OPERATION_QUERY.equals(in.toUpperCase())
					|| OPERATION_QUERY.substring(0, 1).equals(in.toUpperCase())) {
				try {
					List<Goddess> list = action.query();
					for (Goddess go : list) {
						System.out.println(go.getId() + ",姓名：" + go.getUser_name());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (OPERATION_MAIN.equals(in.toUpperCase())
					|| OPERATION_MAIN.substring(0, 1).equals(in.toUpperCase())) {
				System.out.println(CONTEXT);
				prenious = null;
				step = 1;
			} else if (OPERATION_GET.equals(in.toUpperCase())
					|| OPERATION_GET.substring(0, 1).equals(in.toUpperCase())) {
				prenious = OPERATION_GET;

				System.out.println("请输入女神【Id】:");
				Goddess go;
				try {
					go = action.get(Integer.valueOf(scan.next().toString()));
					System.out.println(go.getId() + ",姓名：" + go.getUser_name());
					System.out.println(CONTEXT);
					prenious = null;
					step = 1;
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (OPERATION_GET.equals(prenious)) {
					step++;
				}
			} else if (OPERATION_ADD.equals(in.toUpperCase()) || OPERATION_ADD.substring(0, 1).equals(in.toUpperCase())
					|| OPERATION_ADD.equals(prenious)) {
				prenious = OPERATION_ADD;
				// 新增女神
				if (1 == step) {
					System.out.println("请输入女神【姓名】:");
				} else if (2 == step) {
					goddess.setUser_name(in);
					System.out.println("请输入女神【年龄】:");
				} else if (3 == step) {
					goddess.setAge(Integer.valueOf(in));
					System.out.println("请输入女神【生日】，格式如：yyyy-MM-dd :");
				} else if (4 == step) {
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					Date birthday = null;
					try {
						birthday = sf.parse(in);
						goddess.setBirthday(birthday);
						System.out.println("请输入女神【邮箱】:");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("您输入的格式有误，请重新输入");
						step = 3;
					}
				} else if (5 == step) {
					goddess.setEmail(in);
					System.out.println("请输入女神【性别】:");
				} else if (6 == step) {
					goddess.setSex(Integer.valueOf(in));
					System.out.println("请输入女神【是否删除】:");
				} else if (7 == step) {
					goddess.setIsdel(Integer.valueOf(in));
					System.out.println("请输入女神【手机号】:");
				} else if (8 == step) {
					goddess.setMobile(in);
					try {
						action.add(goddess);
						System.out.println("新增女神成功！");
						step = 1;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("新增女神失败！");
						step = 1;
					}
				}
				if (OPERATION_ADD.equals(prenious)) {
					step++;
				}

			} else {
				System.out.println(prenious);
				System.out.println("您输入的值为：" + in.toString());
			}
		}
	}
}
