package ServiceDAO.users;

import POJO.Users;
import java.util.ArrayList;

public interface UsersServiceDAO {
  public ArrayList<Users> checkUser(Users users);//账号验证业务
  public ArrayList<Users> select(Users users);//查询业务
  public int count(Users users);//统计业务
  public int insert(Users users);//添加业务
  public int delete(Users users);//删除业务
  public int updatePassword(Users users);//修改业务
}