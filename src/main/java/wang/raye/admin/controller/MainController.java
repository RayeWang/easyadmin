package wang.raye.admin.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wang.raye.admin.model.AdminUser;
import wang.raye.admin.model.Menu;
import wang.raye.admin.service.AdminUserService;
import wang.raye.admin.service.MenuService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 后台管理主控制器
 * Created by Raye on 2017/3/16.
 */
@Controller
@RequestMapping("admin")
@Log4j
public class MainController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private AdminUserService adminUserService;

    /**
     * 进入系统管理首页
     * @param map
     * @return
     */
    @RequestMapping("index")
    public String index(ModelMap map, HttpSession session){
        AdminUser user = (AdminUser) session.getAttribute("loginUser");
        List<Menu> menus = menuService.selectByUser(user.getId());

        map.put("treeMenu",menus);
        return "index";
    }

    /**
     * 进入登录页面
     * @return
     */
    @GetMapping(value = "login")
    public String login(@RequestParam(defaultValue = "0") int type){
        log.info("MainController to login");
        if(type == 1){
            log.info("nologin");
            return "nologin";
        }
        return "login";
    }


    @PostMapping(value = "login")
    public String login(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "")  String pass,
                        ModelMap map, HttpSession session){
        AdminUser user = adminUserService.login(name,pass);
        if(user == null){
            map.put("error","用户名或密码错误");
            return "login";
        }
        session.setAttribute("loginUser",user);
        session.setAttribute("authorities",menuService.selectAuthorities(user.getId()));
        return "redirect:/admin/index";
    }

    /**
     * 注销登录
     * @param session
     * @return
     */
    @RequestMapping("loginout")
    public String loginOut(HttpSession session){
        session.setAttribute("loginUser",null);
        session.setAttribute("authorities",null);
        return "login";
    }
}
