package DAO.user;

import POJO.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO {
  public ArrayList<User> select(User user) throws SQLException; //查询记录
  public int count(User user, int type) throws SQLException;    // 统计用户
  public int insert(User user) throws SQLException;//添加记录
  public int updatePart(User user) throws SQLException;//更新部分记录
  public int update(User user) throws SQLException;//更新部分记录
  public int delete(User user) throws SQLException;//删除记录(按id)
}