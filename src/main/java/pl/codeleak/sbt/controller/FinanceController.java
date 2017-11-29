package pl.codeleak.sbt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.codeleak.sbt.entity.Department;
import pl.codeleak.sbt.entity.Inventory;
import pl.codeleak.sbt.service.InventoryService;
import pl.codeleak.sbt.service.UserService;

import java.util.List;

/**
 * Created by admin on 2017/2/4.
 */
@Controller
@RequestMapping("/finance")
public class FinanceController {

    @Autowired
    InventoryService inventoryService;

    @RequestMapping("inventory")
    public ModelAndView inventory(ModelAndView mv) {
        List<Department> departments = inventoryService.getAllDept();
        mv.addObject("dept", departments);
        mv.setViewName("manage/inventory");
        return mv;
    }

    @RequestMapping("see")
    public ModelAndView see(ModelAndView mv) {
        List<Department> departments = inventoryService.getAllDept();
        mv.addObject("dept", departments);
        mv.setViewName("manage/see");
        return mv;
    }

    @RequestMapping("getInventoryDate")
    @ResponseBody
    public Object getInventoryDate(Integer start, Integer length, Integer extra_search) {
        return inventoryService.getData(start, length, extra_search);
    }

    @RequestMapping("getSeeDate")
    @ResponseBody
    public Object getSeeDate(Integer start, Integer length, String extra_search) {
        return inventoryService.getSeeDate(start, length, Integer.valueOf(extra_search.split("@")[0]), Integer.valueOf(extra_search.split("@")[1]));
    }

    @RequestMapping("getAssetData")
    @ResponseBody
    public Object getAssetData(Integer start, Integer length, String extra_search) {
        return inventoryService.getAssetData(start, length, extra_search);
    }

    @RequestMapping("detail")
    @ResponseBody
    public Object detail(Integer id) {
        return inventoryService.detail(id);
    }

    @RequestMapping("addInventory")
    @ResponseBody
    public Object addInventory(Inventory inventory) {
        return inventoryService.addInventory(inventory);
    }

}
