package ServiceDAO.indexData.timeDown;

import POJO.indexData.timeDown;

import java.util.ArrayList;

public interface timeDownServiceDAO {
    public ArrayList<timeDown> select(timeDown song);
    public ArrayList<timeDown> selectRan(timeDown song);
    public int insert(timeDown song);
    public int delete(timeDown song);
    public int update(timeDown song);
    public int count(timeDown song);


}
