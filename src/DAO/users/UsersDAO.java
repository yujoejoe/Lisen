package DAO.users;

import POJO.Users;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UsersDAO {
  public ArrayList<Users> select(Users users) throws SQLException;//查询记录
  public int count(Users users) throws SQLException;//统计记录
  public int insert(Users users) throws SQLException;//添加记录
  public int updatePart(Users users) throws SQLException;//更新部分记录
  public int delete(Users users) throws SQLException;//删除记录(按id)
}