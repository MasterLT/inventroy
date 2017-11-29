package pl.codeleak.sbt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/11/3.
 */
@Entity
@Table
public class Authority {

    @Id
    @GeneratedValue
    private Integer id;
    private String menuCode;
    private String menuName;
    private String menuClass;
    private String dateUrl;
    private Integer sequence;
    private Integer parentMenuid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuClass() {
        return menuClass;
    }

    public void setMenuClass(String menuClass) {
        this.menuClass = menuClass;
    }

    public String getDateUrl() {
        return dateUrl;
    }

    public void setDateUrl(String dateUrl) {
        this.dateUrl = dateUrl;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getParentMenuid() {
        return parentMenuid;
    }

    public void setParentMenuid(Integer parentMenuid) {
        this.parentMenuid = parentMenuid;
    }
}
