package ServiceDAO.indexData.SongHits;

import POJO.indexData.SongHits;
import java.util.ArrayList;

public interface SongHitsServiceDAO {
    public ArrayList<SongHits> select(SongHits SongHits);
    public ArrayList<SongHits> selectRan(SongHits SongHits);
    public int insert(SongHits SongHits);
    public int delete(SongHits SongHits);
    public int update(SongHits SongHits);
    public int count(SongHits SongHits);


}
