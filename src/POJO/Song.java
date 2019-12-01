package POJO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by user on 2019/12/1.
 */
public class Song {
    private static final String prefix = "http://192.168.1.125:8080/music/song/music/";

    private String path = null;

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {

        try {
            if(path == null){
                System.out.println("路径为空！");
                return null;
            }
            // 对路径按utf-8格式编码    此处空格会被编码成 ‘+’
            path = URLEncoder.encode(path, "utf-8");
            // 替换所有‘+’为‘%20’
            path = path.replaceAll("\\+","%20");
            // System.out.println(path);
            // 加上路径前缀
            path = prefix + path;
//        System.out.println(path);


            return path;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("资源不存在！");
            return null;
        }
    }
}
