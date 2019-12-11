package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2019/11/27.
 */
public class JsonData implements Serializable{
    private static final long serialVersion = 1L;

    // 成员变量
    boolean success;
    String msg;
    long counts;     // 记录数
    private List result = new ArrayList();      // 存放结果集

    // 构造函数
    public JsonData(){
        super();
    }
    public JsonData(boolean success, String msg){
        super();
        this.success = success;
        this.msg = msg;
    }
    public JsonData(boolean success, String msg, long count, List result){
        super();
        this.success = success;
        this.msg = msg;
        this.counts = count;
        this.result.addAll(result);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCounts() {
        return counts;
    }

    public void setCounts(long counts) {
        this.counts = counts;
    }

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "JsonData { success: "+success+", "+"msg: "+msg+", "+"counts: "+counts+", "+"result: "+result+" }";
    }
}
