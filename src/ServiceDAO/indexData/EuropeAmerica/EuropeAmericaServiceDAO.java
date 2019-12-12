package ServiceDAO.indexData.EuropeAmerica;

import POJO.indexData.EuropeAmerica;
import java.util.ArrayList;

public interface EuropeAmericaServiceDAO {
    public ArrayList<EuropeAmerica> select(EuropeAmerica EuropeAmerica);
    public ArrayList<EuropeAmerica> selectRan(EuropeAmerica EuropeAmerica);
    public int insert(EuropeAmerica EuropeAmerica);
    public int delete(EuropeAmerica EuropeAmerica);
    public int update(EuropeAmerica EuropeAmerica);
    public int count(EuropeAmerica EuropeAmerica);


}
