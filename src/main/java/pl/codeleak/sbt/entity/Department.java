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
public class Department {

    @Id
    @GeneratedValue
    private Integer id;
    private String departmentKey;
    private String departmentValue;
    private String description;
    private String parentDepartmentkey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentKey() {
        return departmentKey;
    }

    public void setDepartmentKey(String departmentKey) {
        this.departmentKey = departmentKey;
    }

    public String getDepartmentValue() {
        return departmentValue;
    }

    public void setDepartmentValue(String departmentValue) {
        this.departmentValue = departmentValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentDepartmentkey() {
        return parentDepartmentkey;
    }

    public void setParentDepartmentkey(String parentDepartmentkey) {
        this.parentDepartmentkey = parentDepartmentkey;
    }
}
