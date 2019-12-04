package ServiceDAO.mv;

import POJO.MV;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by user on 2019/12/3.
 */
public interface mvServiceDAO {
    public ArrayList<MV> select(MV mv);
    public ArrayList<MV> selectRan(MV mv);
    public int insert(MV mv);
    public int delete(MV mv);
    public int update(MV mv);
    public int count(MV mv);
}
