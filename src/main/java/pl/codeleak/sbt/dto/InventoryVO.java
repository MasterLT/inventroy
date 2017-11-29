package pl.codeleak.sbt.dto;

/**
 * Created by Administrator on 2017/11/6.
 */
public class InventoryVO {
    private Integer id;
    private String deptName;
    private Long deptNum;
    private String deptUser;
    private String lastTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getDeptNum() {
        return deptNum;
    }

    public void setDeptNum(Long deptNum) {
        this.deptNum = deptNum;
    }

    public String getDeptUser() {
        return deptUser;
    }

    public void setDeptUser(String deptUser) {
        this.deptUser = deptUser;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }
}
