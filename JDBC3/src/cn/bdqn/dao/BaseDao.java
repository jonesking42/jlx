package cn.bdqn.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bdqn.utils.JDBCUtils;

public class BaseDao {
	public void update(String sql,Object...args){
		Connection conn=null;
		PreparedStatement pstm=null;
		
		try {
			conn=JDBCUtils.getConn();
			pstm=conn.prepareStatement(sql);
			for(int i=0;i<args.length;i++){
				pstm.setObject(i+1, args[i]);
			}
			pstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(null, pstm, conn);
		}
	}
	
	public <T>T queryOne(Class<T> clazz,String sql,Object...args){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			conn=JDBCUtils.getConn();
			pstm=conn.prepareStatement(sql);
			for(int i=0;i<args.length;i++){
				pstm.setObject(i+1, args[i]);
			}
			rs= pstm.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			T t = clazz.newInstance();
			if(rs.next()){
				for(int i=0;i<count;i++){
					Object value = rs.getObject(i+1);
					String label = rsmd.getColumnLabel(i+1);
					Field field = clazz.getDeclaredField(label);
					field.setAccessible(true);
					field.set(t, value);
				}
				return t;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(rs, pstm, conn);
		}
		return null;
	}
	
	public <T> List<T> queryMore(Class<T> clazz,String sql,Object...args){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		List<T> list=new ArrayList<T>();
		
		try {
			conn=JDBCUtils.getConn();
			pstm=conn.prepareStatement(sql);
			for(int i=0;i<args.length;i++){
				pstm.setObject(i+1, args[i]);
			}
			rs= pstm.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			
			while(rs.next()){
				T t = clazz.newInstance();
				for(int i=0;i<count;i++){
					Object value = rs.getObject(i+1);
					String label = rsmd.getColumnLabel(i+1);
					Field field = clazz.getDeclaredField(label);
					field.setAccessible(true);
					field.set(t, value);
				}
				list.add(t);
			}
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(rs, pstm, conn);
		}
		return null;
	}

}
