package ServiceDAO.album;
import POJO.Album;

import java.util.ArrayList;


public interface AlbumServiceDAO {
    public ArrayList<Album> select(Album album);
    public ArrayList<Album> selectRan(Album album);
    public int insert(Album album);
    public int delete(Album album);
    public int update(Album album);
    public int count(Album album);



}
