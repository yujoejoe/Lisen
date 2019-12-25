package ServiceDAO.indexData.timeDown;

import POJO.indexData.TimeDown;

import java.util.ArrayList;

public interface TimeDownServiceDAO {
    public ArrayList<TimeDown> select(TimeDown song);
    public ArrayList<TimeDown> selectRan(TimeDown song);
    public int insert(TimeDown song);
    public int delete(TimeDown song);
    public int update(TimeDown song);
    public int count(TimeDown song);


}
