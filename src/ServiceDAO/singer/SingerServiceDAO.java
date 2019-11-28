package ServiceDAO.singer;

import POJO.Singer;

import java.util.ArrayList;

/**
 * Created by user on 2019/11/28.
 */
public interface SingerServiceDAO {
    public ArrayList<Singer> select(Singer singer);
    public ArrayList<Singer> selectRan(Singer singer);
    public int insert(Singer singer);
    public int delete(Singer singer);
    public int update(Singer singer);
    public int count(Singer singer);
}
