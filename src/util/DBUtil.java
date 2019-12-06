package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by user on 2019/11/26.
 */
public class DBUtil {

    private static Properties prop = new Properties();
    private static String driverClass;
    private static String url;
    private static String userName;
    private static String password;

    /**
     * 将读取 Properties定义为静态代码块
     * 让JVM加载类时自动执行这些它，且只被执行一次
     */
    static {
        InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("config.properties");
        try {
            prop.load(is);
            driverClass = prop.getProperty("driverClass");
            url = prop.getProperty("url");
            url = url.replaceAll("%20", " ");//路径中的空格编码为%20
            userName = prop.getProperty("userName");
            password = prop.getProperty("password");
            Class.forName(driverClass);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(is!=null){
                try {is.close();}
                catch (IOException e){e.printStackTrace();}
            }
        }
    }

    /**
     * 将读取 getConnection定义为静态方法
     * 以保证同一事务使用的是同一个连接
     */
    public static Connection getConnection(){
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(url,userName,password);
            conn.setAutoCommit(false);//注意：为防止事务自动提交，我们习惯在这里将事务的提交方式改为手动提交
            System.out.println("DBUtil: 连接数据库成功！");
//            System.out.println("connectID: " + conn.toString());

        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("连接失败");
            return null;
        }
        return conn;
    }

    /**
     * 将 closeConnection也定义为静态方法
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {//关闭连接
                conn.close();
                System.out.println("关闭连接");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }
}
