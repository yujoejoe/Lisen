package ServiceDAO.indexData.Japan;

import POJO.indexData.Japan;

import java.util.ArrayList;

public interface JapanServiceDAO {
    public ArrayList<Japan> select(Japan Japan);
    public ArrayList<Japan> selectRan(Japan Japan);
    public int insert(Japan Japan);
    public int delete(Japan Japan);
    public int update(Japan Japan);
    public int count(Japan Japan);


}
