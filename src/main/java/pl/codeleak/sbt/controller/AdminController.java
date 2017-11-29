package pl.codeleak.sbt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.codeleak.sbt.dto.UserDTO;
import pl.codeleak.sbt.entity.Authority;
import pl.codeleak.sbt.entity.SysUser;
import pl.codeleak.sbt.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by admin on 2017/2/4.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @RequestMapping("")
    public String home(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("user") != null)
            return "redirect:/admin/index";
        return "login";
    }

    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request, SysUser user, ModelAndView mv) {
        UserDTO admin=userService.login(user);
        if (user!=null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", admin);
            mv.addObject("user", admin);
            mv.setViewName("redirect:/admin/index");
        }else
            mv.setViewName("redirect:/admin");
        return mv;
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "login";
    }

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, ModelAndView mv) {
        Object user=request.getSession().getAttribute("user");
        mv.setViewName("manage/index");
        if (user!=null)
            mv.addObject("user", user);
        else
            mv.setViewName("login");
        return mv;
    }
}
