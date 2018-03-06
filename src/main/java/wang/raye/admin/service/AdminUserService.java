package wang.raye.admin.service;


import wang.raye.admin.model.AdminUser;
import wang.raye.admin.model.Role;
import wang.raye.admin.model.WebResult;

import java.util.List;

/**
 * 系统用户相关控制器
 * Created by Raye on 2017/3/21.
 */
public interface AdminUserService {

    /**
     * 查询所有系统用户
     * @return
     */
    public List<AdminUser> select(int page, int pageSize, String query);

    /**
     * 查询数据总数
     * @return
     */
    public int selectCount(String query);

    /**
     * 后台用户登录
     * @param name 用户名
     * @param pwd 密码
     * @return
     */
    public AdminUser login(String name, String pwd);
    /**
     * 根据id查询系统用户
     * @param id
     * @return
     */
    public AdminUser selectById(int id);

    /**
     * 添加一个系统用户
     * @param user
     * @return
     */
    public boolean insert(AdminUser user);

    /**
     * 更新一个系统用户
     * @param user
     * @return
     */
    public boolean update(AdminUser user);

    /**
     * 删除指定id的系统用户
     * @param id
     * @return
     */
    public boolean delete(int id);

    /**
     * 查询用户的角色信息，会查询出所有所有角色
     * @param id
     * @return
     */
    public List<Role> selectUserRole(int id);

    /**
     * 更新用户角色
     * @param ids
     * @return
     */
    boolean updateRoleMenu(String ids, int userid, int creater);

    /**
     * 更新用户密码
     * @param userId
     * @param psw
     * @param oldPsw
     * @return
     */
    WebResult updatePass(int userId, String psw, String oldPsw);
}
