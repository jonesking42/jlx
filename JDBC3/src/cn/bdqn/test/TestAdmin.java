package cn.bdqn.test;

import java.util.List;
import java.util.Scanner;

import cn.bdqn.dao.BaseDao;
import cn.bdqn.entity.Admin;

public class TestAdmin {
	
	public  boolean select(String username,Integer password){
		//boolean flag=false;
		Admin a=null;
		BaseDao bd=new BaseDao();
		String sql="select username,password from admin where username=? and password=?";
		a= bd.queryOne(Admin.class, sql ,username,password);
		if(a==null){
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("用户名");
		String username=sc.next();
		System.out.print("密码");
		Integer password=sc.nextInt();
		/*BaseDao bd=new BaseDao();
		String sql="select username,password from admin";
		//Admin a= bd.queryOne(Admin.class, sql,"lishi");
		List<Admin> list = bd.queryMore(Admin.class, sql);
		
		for(int i=0;i<list.size();i++){
			if(list.get(i).getUsername().equals(username)&&list.get(i).getPassword().equals(password)){
				System.out.println("��½�ɹ���");
				break;
			}
		}*/
		TestAdmin t=new TestAdmin();
		Boolean flag=t.select(username,password);
		if(flag){
			System.out.println("成功");
		}else{
			System.out.println("失败");
		}
		
		
		
	}

	
	

}
