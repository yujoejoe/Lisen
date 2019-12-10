package ServiceDAO.onesinger;


import POJO.Single;

import java.util.ArrayList;

public interface SingleServiceDAO {
    public ArrayList<Single> select(Single single);

    public int count(Single single);
}
