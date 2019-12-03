package ServiceDAO.users;

import DAO.users.UsersDAOimpl;
import POJO.Users;
import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersServiceDAOimpl implements UsersServiceDAO{
  /**
   * 账号验证业务
   */
  public ArrayList<Users> checkUser(Users users) {
    Connection conn = DBUtil.getConnection();
    UsersDAOimpl UserD = new UsersDAOimpl(conn);
    try{
      ArrayList<Users> UserList = new ArrayList<Users>();
      UserList = UserD.select(users);//返回记录集
      conn.commit();
      return UserList;
    }catch(Exception e){
      try {
        conn.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
      return null;
    }finally{
      if(conn != null){
        DBUtil.closeConnection(conn);
      }
    }
  }

  /**
   * 查询业务
   */
  public ArrayList<Users> select(Users users) {
    Connection conn = DBUtil.getConnection();
    UsersDAOimpl studentD = new UsersDAOimpl(conn);
    try{
      ArrayList<Users> usersList = new ArrayList<Users>();
      usersList = studentD.select(users);//返回记录集
      conn.commit();
      return usersList;
    }catch(Exception e){
      try {
        conn.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
      return null;
    }finally{
      if(conn != null){
        DBUtil.closeConnection(conn);
      }
    }
  }

  /**
   * 统计业务
   */
  public int count(Users users) {
    Connection conn = DBUtil.getConnection();
    UsersDAOimpl usersD = new UsersDAOimpl(conn);
    int cnt;
    try{
      cnt = usersD.count(users);//返回记录集
      conn.commit();
      return cnt;
    }catch(Exception e){
      try {
        conn.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
      return -1;
    }finally{
      if(conn != null){
        DBUtil.closeConnection(conn);
      }
    }
  }
  
  /**
   * 添加/注册业务
   */
  public int insert(Users users){
	Connection conn = DBUtil.getConnection();
	UsersDAOimpl UserD = new UsersDAOimpl(conn);
    try{
      int i = UserD.insert(users);
      conn.commit();
      return i;
    }catch(Exception e){
      try {
        conn.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
      return -1;
    }finally{
      if(conn != null){
    	DBUtil.closeConnection(conn);
      }
    }
  }

  /**
   * 更新业务(密码、相片、昵称等)
   */
  public int updatePassword(Users users){
	Connection conn = DBUtil.getConnection();
	UsersDAOimpl UserD = new UsersDAOimpl(conn);
    try{
      int i = UserD.updatePart(users);
      conn.commit();
      return i;
    }catch(Exception e){
      try {
        conn.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
      return -1;
    }finally{
      if(conn != null){
    	DBUtil.closeConnection(conn);
      }
    }
  }
  
  /**
   * 删除业务
   */
  public int delete(Users users){
	Connection conn = DBUtil.getConnection();
	UsersDAOimpl UserD = new UsersDAOimpl(conn);
    try{
      int i = UserD.delete(users);
      conn.commit();
      return i;
    }catch(Exception e){
      try {
        conn.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
      return -1;
    }finally{
      if(conn != null){
    	DBUtil.closeConnection(conn);
      }
    }
  }
}