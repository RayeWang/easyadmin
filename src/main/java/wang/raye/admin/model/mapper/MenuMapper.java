package wang.raye.admin.model.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.common.Mapper;
import wang.raye.admin.model.Menu;

import java.util.HashMap;
import java.util.List;

public interface MenuMapper extends Mapper<Menu> {

    @Select({"SELECT m.id, m.name, m.url, m.icon, m.menutype, m.display, m.parentid, m.flag,",
            "r.roleid FROM menu m LEFT JOIN (SELECT roleid,menuid FROM role_menu WHERE roleid=#{roleid})",
            " r ON m.id=r.menuid "})

    List<Menu> selectByRoleId(int roleId);

    /**
     *查询用户有权限的菜单列表
     * @param userid
     * @return
     */
    @Select({"SELECT id, name, url, icon, menutype, display, parentid FROM menu WHERE id IN(",
            "SELECT menuid FROM role_menu WHERE roleid IN ",
            "(SELECT roleid FROM user_role WHERE userid=#{userid}) OR roleid=-1) AND menutype<>'2' AND `flag`='1'"})
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="url", property="url", jdbcType= JdbcType.VARCHAR),
            @Result(column="icon", property="icon", jdbcType= JdbcType.VARCHAR),
            @Result(column="display", property="display", jdbcType= JdbcType.INTEGER),
            @Result(column="flag", property="flag", jdbcType= JdbcType.CHAR)
    })
    List<Menu> selectByUser(int userid);

    /**
     *查询用户有权限的菜单列表,0是所有用户的默认权限
     * @param userid
     * @return
     */
    @Select({"SELECT url FROM menu WHERE id IN(",
            "SELECT menuid FROM role_menu WHERE roleid IN ",
            "(SELECT roleid FROM user_role WHERE userid=#{userid}) OR roleid=-1) AND `flag`='1'"})
    List<String> selectAuthorities(int userid);

    /**
     *查询超级用户的所有权限，超级用户拥有所有权限
     * @return
     */
    @Select({"SELECT url FROM menu"})
    List<String> selectAuthoritiesByRoot();



    @Select({"CALL role_menu_update(#{menuids},#{roleid},#{userid})"})
    void roleMenuUpdate(HashMap<String, Object> map);

    @Select({"CALL delete_menu(#{menuid})"})
    void deleteMenuById(HashMap<String, Object> map);

    @Select({"SELECT id, name, url, icon, menutype, display, parentid, creator, createtime,",
            " updateuser, updatetime, flag FROM menu WHERE flag='1' AND menutype<>'2'"})

    List<Menu> selectAllEnable();
}