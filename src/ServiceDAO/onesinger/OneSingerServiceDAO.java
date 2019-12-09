package ServiceDAO.onesinger;


import POJO.Singer;

import java.util.ArrayList;

public interface OneSingerServiceDAO {

    public ArrayList<Singer> select(Singer singer);

    public int count(Singer singer);
}
