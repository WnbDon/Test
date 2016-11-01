package cn.edu.jxnu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.jxnu.model.User;
import cn.edu.jxnu.util.DbUtil;

public class UserDao {
	public static User getUser(String username,String pwd){
		User u = null;
		Connection conn = null;
		try {  
			conn =DbUtil.getConnection();
			String sql="select * from user_t where  username=?  and pwd=?";
			PreparedStatement pst =conn.prepareStatement(sql);
			
			pst.setString(1, username);
			pst.setString(2, pwd); 
			 
			ResultSet rs = pst.executeQuery();
			while(rs.next()){ 
				u = new User();
				u.setId(rs.getInt("Id"));
				u.setPwd(pwd);
				u.setUname(username);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		} finally{
			try { 
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return u;
	}
}
