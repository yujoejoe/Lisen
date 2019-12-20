package ServiceDAO.users;

import POJO.User;

import java.util.ArrayList;

public interface UserServiceDAO {
  public ArrayList<User> checkUser(User user);//账号验证业务
  public ArrayList<User> select(User user);//查询业务
  public int count(User user, int type);//统计业务
  public int insert(User user);//添加业务
  public int delete(User user);//删除业务
  public int updatePassword(User user);//修改业务
  public int update(User user);//更新业务
}