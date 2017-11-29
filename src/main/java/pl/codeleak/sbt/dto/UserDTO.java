package pl.codeleak.sbt.dto;

import pl.codeleak.sbt.entity.Role;
import pl.codeleak.sbt.entity.SysUser;

import java.util.List;

/**
 * Created by Administrator on 2017/11/3.
 */
public class UserDTO {
    private Integer id;
    private String departmentKey;
    private List<Integer> roles;
    private List<MenuDTO> menus;

    public UserDTO(){}

    public UserDTO(SysUser user){
        this.id=user.getId();
        this.departmentKey= user.getDepartmentKey();
    }

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

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }

    public List<MenuDTO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDTO> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", departmentKey='" + departmentKey + '\'' +
                ", roles=" + roles +
                ", menus=" + menus +
                '}';
    }
}
