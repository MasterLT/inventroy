package pl.codeleak.sbt.service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.codeleak.sbt.dto.MenuDTO;
import pl.codeleak.sbt.dto.UserDTO;
import pl.codeleak.sbt.entity.*;
import pl.codeleak.sbt.repository.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2017/2/9.
 */
@Repository
@Transactional
public class UserService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    SysuserRoleRepository sysuserRoleRepository;
    @Autowired
    RoleAuthorityRepository roleAuthorityRepository;
    @Autowired
    SysUserRepository sysUserRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserDTO login(SysUser user) {
        UserDTO admin = null;
        List<SysUser> real = sysUserRepository.findByCodeAndPassword(user.getCode(), user.getPassword());
        if (real.size() > 0) {
            //根据用户菜单
            admin = new UserDTO(real.get(0));
            //获取其权限
            admin.setRoles(getRolesByUserId(admin));
            //获取菜单信息
            admin.setMenus(getMenusByUserId(admin));
            logger.info("登录成功:"+admin);
        }
        return admin;
    }

    private List<Integer> getRolesByUserId(UserDTO admin) {
        List<SysuserRole> sysuserRoles = sysuserRoleRepository.findBySysuserId(admin.getId());
        List<Integer> lists = Lists.transform(sysuserRoles, new Function<SysuserRole, Integer>() {
            @Override
            public Integer apply(SysuserRole s) {
                return s.getRoleId();
            }
        });
        return lists;
    }

    private List<MenuDTO> getMenusByUserId(UserDTO admin) {
        //根据角色获取对应菜单id
        List<RoleAuthority> roleAuthoritys = roleAuthorityRepository.findByRoleIdIn(admin.getRoles());
        List<Integer> menusIds = Lists.transform(roleAuthoritys, new Function<RoleAuthority, Integer>() {
            @Override
            public Integer apply(RoleAuthority input) {
                return input.getMenuId();
            }
        });
        List<Authority> authortys = authorityRepository.findByIdInAndParentMenuidOrderBySequenceDesc(menusIds, null);
        return returnMenuDto(authortys);
    }

    private List<MenuDTO> returnMenuDto(List<Authority> authortys) {
        List<MenuDTO> menus = Lists.transform(authortys, new Function<Authority, MenuDTO>() {
            @Override
            public MenuDTO apply(Authority input) {
                MenuDTO menu = new MenuDTO(input);
                List<Authority> as = authorityRepository.findByParentMenuidOrderBySequenceDesc(menu.getId());
                if (as.size() > 0) {
                    menu.setMenus(returnMenuDto(as));
                }
                return menu;
            }
        });
        return menus;
    }

    public void delete(Integer id) {
        sysUserRepository.delete(id);
    }

    public SysUser save(SysUser user) {
        if (user.getId() == null) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    public SysUser getOne(Integer id) {
        return sysUserRepository.findOne(id);
    }

    public void test(){
    }

//    public DatatablesViewPage<SysUser> getData(Integer start, Integer length) {
//        int page = start == null ? 0 : start / length;
//        DatatablesViewPage<SysUser> data = new DatatablesViewPage<SysUser>();
//        Pageable pageable = new PageRequest(page, length);
//        Page<SysUser> list = userRepository.findAll(pageable);
//        data.setAaData(list.getContent());
//        data.setiTotalDisplayRecords(list.getTotalPages());
//        data.setiTotalRecords(list.getTotalPages());
//        return data;
//    }

}
