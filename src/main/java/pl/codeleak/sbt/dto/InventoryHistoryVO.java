package pl.codeleak.sbt.dto;

import pl.codeleak.sbt.entity.Asset;
import pl.codeleak.sbt.enums.AssetEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 2017/11/3.
 */
public class InventoryHistoryVO {

    private Integer id;
    private Integer inventoryId;
    private String assetcode;
    private String deptname;
    private String place;
    private String address;
    private Date inventorydate;
    private String inventorystate;
    private String photo;
    private Double longitude;
    private Double latitude;
    private String name;
    private String inventoryRemark;
    private String inventoryUser;

    public InventoryHistoryVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getAssetcode() {
        return assetcode;
    }

    public void setAssetcode(String assetcode) {
        this.assetcode = assetcode;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getInventorydate() {
        return inventorydate;
    }

    public void setInventorydate(Date inventorydate) {
        this.inventorydate = inventorydate;
    }

    public String getInventorystate() {
        return inventorystate;
    }

    public void setInventorystate(String inventorystate) {
        this.inventorystate = inventorystate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInventoryRemark() {
        return inventoryRemark;
    }

    public void setInventoryRemark(String inventoryRemark) {
        this.inventoryRemark = inventoryRemark;
    }

    public String getInventoryUser() {
        return inventoryUser;
    }

    public void setInventoryUser(String inventoryUser) {
        this.inventoryUser = inventoryUser;
    }
}
