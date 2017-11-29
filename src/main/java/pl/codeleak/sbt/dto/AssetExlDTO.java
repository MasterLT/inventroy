package pl.codeleak.sbt.dto;

import java.util.Date;

/**
 * Created by Administrator on 2017/11/16.
 */
public class AssetExlDTO {
    private Integer index;
    private String assetcode;
    private String name;
    private String assetsortname;
    private Integer numbers;
    private String place;
    private String detailedlocation;
    private String inventorystate;
    private String inventoryRemark;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
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

    public String getAssetsortname() {
        return assetsortname;
    }

    public void setAssetsortname(String assetsortname) {
        this.assetsortname = assetsortname;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDetailedlocation() {
        return detailedlocation;
    }

    public void setDetailedlocation(String detailedlocation) {
        this.detailedlocation = detailedlocation;
    }

    public String getInventorystate() {
        return inventorystate;
    }

    public void setInventorystate(String inventorystate) {
        this.inventorystate = inventorystate;
    }

    public String getInventoryRemark() {
        return inventoryRemark;
    }

    public void setInventoryRemark(String inventoryRemark) {
        this.inventoryRemark = inventoryRemark;
    }
}
