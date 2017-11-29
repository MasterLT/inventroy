package pl.codeleak.sbt.dto;

import pl.codeleak.sbt.entity.Authority;

import java.util.List;

/**
 * Created by Administrator on 2017/11/3.
 */
public class MenuDTO {
    private Integer id;
    private String menuCode;
    private String menuName;
    private String menuClass;
    private String dateUrl;
    private Integer sequence;
    private List<MenuDTO> menus;

    public MenuDTO(){}

    public MenuDTO(Authority authority){
        this.id=authority.getId();
        this.menuCode=authority.getMenuCode();
        this.menuName=authority.getMenuName();
        this.menuClass=authority.getMenuClass();
        this.dateUrl=authority.getDateUrl();
        this.sequence=authority.getSequence();
    }

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

    public List<MenuDTO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDTO> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "menus=" + menus +
                ", id=" + id +
                ", menuName='" + menuName + '\'' +
                '}';
    }
}
