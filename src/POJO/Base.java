package POJO;

/**
 * Created by user on 2019/11/26.
 */
public class Base {

    String condition;   // 查询条件
    String limit;   // 分页
    String orderBy; // 排序

    public Base(){

    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
