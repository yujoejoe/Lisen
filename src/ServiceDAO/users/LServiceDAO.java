package ServiceDAO.users;

import POJO.Collection;

import java.util.ArrayList;

public interface LServiceDAO {
    public ArrayList<Collection> select(Collection collection);
    public int count(Collection collection);
    public int insert(Collection collection);
    public int delete(Collection collection);
}
