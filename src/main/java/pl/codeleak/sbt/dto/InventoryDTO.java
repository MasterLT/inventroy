package pl.codeleak.sbt.dto;

import pl.codeleak.sbt.entity.Inventory;
import pl.codeleak.sbt.enums.InventoryEnum;
import pl.codeleak.sbt.utils.DateUtiles;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 2017/11/3.
 */
public class InventoryDTO {

    private Integer id;
    private Integer departmentId;
    private String deptName;
    private String deptUserName;
    private String startTime;
    private String endTime;
    private Integer assetNum;
    private Integer completeNum;
    private Integer inventoryNum;
    private Integer noNum;
    private Integer days;
    private String state;

    public InventoryDTO() {
    }

    public InventoryDTO(Inventory inventory) {
        this.id = inventory.getId();
        this.departmentId = inventory.getDepartmentId();
        this.startTime = DateUtiles.formatDateTime(inventory.getStartTime());
        this.endTime = DateUtiles.formatDateTime(inventory.getEndTime());
        this.assetNum = inventory.getAssetNum();
//        this.completeNum = inventory.getCompleteNum();
//        this.noNum = inventory.getNoNum();
//        this.inventoryNum = assetNum - completeNum - noNum;
        this.state = InventoryEnum.getNameByType(inventory.getState());
        this.days = DateUtiles.differentDaysByMillisecond(inventory.getStartTime(), inventory.getEndTime() == null ? new Date() : inventory.getEndTime());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptUserName() {
        return deptUserName;
    }

    public void setDeptUserName(String deptUserName) {
        this.deptUserName = deptUserName;
    }

    public Integer getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(Integer inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getAssetNum() {
        return assetNum;
    }

    public void setAssetNum(Integer assetNum) {
        this.assetNum = assetNum;
    }

    public Integer getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(Integer completeNum) {
        this.completeNum = completeNum;
    }

    public Integer getNoNum() {
        return noNum;
    }

    public void setNoNum(Integer noNum) {
        this.noNum = noNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
