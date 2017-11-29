package pl.codeleak.sbt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.codeleak.sbt.dto.UserDTO;
import pl.codeleak.sbt.entity.Department;
import pl.codeleak.sbt.entity.Inventory;
import pl.codeleak.sbt.entity.SysUser;
import pl.codeleak.sbt.entity.SysuserRole;
import pl.codeleak.sbt.service.DeptService;
import pl.codeleak.sbt.service.InventoryService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by admin on 2017/2/4.
 */
@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    DeptService deptService;

    @RequestMapping("assets")
    public ModelAndView assets(ModelAndView mv, HttpSession session) {
        List<SysUser> users = deptService.getUsersByDeptId(((UserDTO) session.getAttribute("user")).getDepartmentKey());
        mv.addObject("userList", users);
        mv.setViewName("manage/assets");
        return mv;
    }

    @RequestMapping("inventory")
    public ModelAndView inventory(ModelAndView mv, HttpSession session) {
        List<SysUser> users = deptService.getUsersByDeptId(((UserDTO) session.getAttribute("user")).getDepartmentKey());
        mv.addObject("userList", users);
        mv.setViewName("manage/inventory2");
        return mv;
    }

    @RequestMapping("getAssetDate")
    @ResponseBody
    public Object getAssetDate(Integer start, Integer length, String extra_search, HttpSession session) {
        return deptService.getAssetDate(start, length, extra_search, ((UserDTO) session.getAttribute("user")).getDepartmentKey());
    }

    @RequestMapping("getSeeDate")
    @ResponseBody
    public Object getSeeDate(Integer start, Integer length, String extra_search, HttpSession session) {
        return deptService.getSeeDate(start, length, ((UserDTO) session.getAttribute("user")).getDepartmentKey(), Integer.valueOf(extra_search));
    }

    @RequestMapping("getInventoryDate")
    @ResponseBody
    public Object getInventoryDate(Integer start, Integer length, String extra_search, HttpSession session) {
        return deptService.getInventoryDate(start, length, extra_search, ((UserDTO) session.getAttribute("user")).getDepartmentKey());
    }

    @RequestMapping("detail")
    @ResponseBody
    public Object detail(String assetcode) {
        return deptService.detail(assetcode);
    }

    @RequestMapping("noInventory")
    @ResponseBody
    public Object noInventory(Integer inventoryId, String assetcode, String remark) {
        return deptService.noInventory(inventoryId, assetcode, remark);
    }

    @RequestMapping("moveInventory")
    @ResponseBody
    public Object moveInventory(Integer inventoryId, String assetcode, String user) {
        return deptService.moveInventory(inventoryId, assetcode, user);
    }

    @RequestMapping("inventoryFinish")
    @ResponseBody
    public Object inventoryFinish(Integer id) {
        return deptService.inventoryFinish(id);
    }

    @RequestMapping("outExl")
    public void outExl(Integer id, HttpServletResponse response) {
        deptService.outExl(id,response);
    }

    @RequestMapping("noInventoryBach")
    @ResponseBody
    public Object noInventoryBach(Integer[] ids, String remark) {
        return deptService.noInventoryBach(ids, remark);
    }

    @RequestMapping("moveInventoryBach")
    @ResponseBody
    public Object moveInventoryBach(Integer[] ids, String user) {
        return deptService.moveInventoryBach(ids, user);
    }

}
