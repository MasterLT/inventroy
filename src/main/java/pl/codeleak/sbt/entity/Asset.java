package pl.codeleak.sbt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

/**
 * Created by Administrator on 2017/11/3.
 */
@Entity
@Table
public class Asset {

    @Id
    @GeneratedValue
    private String assetcode;
    private String assetsortname;
    private String name;
    private String norms;
    private Integer unit;
    private Integer numbers;
    private Integer price;
    private Integer state;
    private String deptname;
    private String place;
    private String manufacturer;
    private Date buydate;
    private String projectid;
    private String projectname;
    private Integer valid;
    private String remark;
    private String detailedlocation;
    private Integer guarantee;
    private Integer lifeperiods;
    private Integer periodsused;
    private String address;
    private Date inventorydate;
    private Integer inventorystate;
    private String photo;
    private Double longitude;
    private Double latitude;
    private String inventoryRemark;
    private String inventoryUser;

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

    public String getAssetcode() {
        return assetcode;
    }

    public void setAssetcode(String assetcode) {
        this.assetcode = assetcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNorms() {
        return norms;
    }

    public void setNorms(String norms) {
        this.norms = norms;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getBuydate() {
        return buydate;
    }

    public void setBuydate(Date buydate) {
        this.buydate = buydate;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDetailedlocation() {
        return detailedlocation;
    }

    public void setDetailedlocation(String detailedlocation) {
        this.detailedlocation = detailedlocation;
    }

    public Integer getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(Integer guarantee) {
        this.guarantee = guarantee;
    }

    public Integer getLifeperiods() {
        return lifeperiods;
    }

    public void setLifeperiods(Integer lifeperiods) {
        this.lifeperiods = lifeperiods;
    }

    public Integer getPeriodsused() {
        return periodsused;
    }

    public void setPeriodsused(Integer periodsused) {
        this.periodsused = periodsused;
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

    public Integer getInventorystate() {
        return inventorystate;
    }

    public void setInventorystate(Integer inventorystate) {
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

    public String getAssetsortname() {
        return assetsortname;
    }

    public void setAssetsortname(String assetsortname) {
        this.assetsortname = assetsortname;
    }
}
