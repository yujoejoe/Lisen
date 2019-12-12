package DAO.indexData.EuropeAmerica;

import POJO.indexData.EuropeAmerica;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EuropeAmericaDAO {
    public ArrayList<EuropeAmerica> select(EuropeAmerica EuropeAmerica) throws SQLException;
    public int count(EuropeAmerica EuropeAmerica) throws SQLException;

}
