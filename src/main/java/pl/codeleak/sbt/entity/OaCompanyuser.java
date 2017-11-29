package pl.codeleak.sbt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class OaCompanyuser {

    @Id
    @GeneratedValue
    private String workcode;

    private String lastname;

    private String supname;

    private String subcompanyname;

    private String manager;

    public String getWorkcode() {
        return workcode;
    }

    public void setWorkcode(String workcode) {
        this.workcode = workcode == null ? null : workcode.trim();
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname == null ? null : lastname.trim();
    }

    public String getSupname() {
        return supname;
    }

    public void setSupname(String supname) {
        this.supname = supname == null ? null : supname.trim();
    }

    public String getSubcompanyname() {
        return subcompanyname;
    }

    public void setSubcompanyname(String subcompanyname) {
        this.subcompanyname = subcompanyname == null ? null : subcompanyname.trim();
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OaCompanyuser that = (OaCompanyuser) o;

        if (workcode != null ? !workcode.equals(that.workcode) : that.workcode != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (supname != null ? !supname.equals(that.supname) : that.supname != null) return false;
        if (subcompanyname != null ? !subcompanyname.equals(that.subcompanyname) : that.subcompanyname != null)
            return false;
        return manager != null ? manager.equals(that.manager) : that.manager == null;

    }

    @Override
    public int hashCode() {
        int result = workcode != null ? workcode.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (supname != null ? supname.hashCode() : 0);
        result = 31 * result + (subcompanyname != null ? subcompanyname.hashCode() : 0);
        result = 31 * result + (manager != null ? manager.hashCode() : 0);
        return result;
    }
}