package ServiceDAO.album;

import POJO.AlbumS;

import java.util.ArrayList;

public interface AlbumSServiceDAO {
    public ArrayList<AlbumS> select(AlbumS album);
    public ArrayList<AlbumS> selectRan(AlbumS album);
    public int insert(AlbumS album);
    public int delete(AlbumS album);
    public int update(AlbumS album);
    public int count(AlbumS album);


}
