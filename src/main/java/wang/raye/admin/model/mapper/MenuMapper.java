package wang.raye.admin.model.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import wang.raye.admin.model.Menu;
import wang.raye.admin.model.MenuCriteria;

import java.util.HashMap;
import java.util.List;

public interface MenuMapper {
    @SelectProvider(type=MenuSqlProvider.class, method="countByExample")
    int countByExample(MenuCriteria example);

    @DeleteProvider(type=MenuSqlProvider.class, method="deleteByExample")
    int deleteByExample(MenuCriteria example);

    @Delete({
        "delete from menu",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into menu (id, name, ",
        "url, icon, menu_type, ",
        "display, parent_id, ",
        "creator, create_time, ",
        "update_user, update_time, ",
        "status)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{url,jdbcType=VARCHAR}, #{icon,jdbcType=VARCHAR}, #{menuType,jdbcType=CHAR}, ",
        "#{display,jdbcType=INTEGER}, #{parentId,jdbcType=INTEGER}, ",
        "#{creator,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateUser,jdbcType=INTEGER}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{status,jdbcType=CHAR})"
    })
    int insert(Menu record);

    @InsertProvider(type=MenuSqlProvider.class, method="insertSelective")
    int insertSelective(Menu record);

    @SelectProvider(type=MenuSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType= JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType= JdbcType.VARCHAR),
        @Result(column="menu_type", property="menuType", jdbcType= JdbcType.CHAR),
        @Result(column="display", property="display", jdbcType= JdbcType.INTEGER),
        @Result(column="parent_id", property="parentId", jdbcType= JdbcType.INTEGER),
        @Result(column="creator", property="creator", jdbcType= JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType= JdbcType.TIMESTAMP),
        @Result(column="update_user", property="updateUser", jdbcType= JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType= JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType= JdbcType.CHAR)
    })
    List<Menu> selectByExample(MenuCriteria example);

    @Select({"SELECT m.id, m.name, m.url, m.icon, m.menu_type, m.display, m.parent_id, m.status,",
            "r.roleid FROM menu m LEFT JOIN (SELECT roleid,menuid FROM role_menu WHERE roleid=#{roleid})",
            " r ON m.id=r.menuid "})
    @Results({
            @Result(column="parent_id", property="parentId", jdbcType= JdbcType.INTEGER)
    })
    List<Menu> selectByRoleId(int roleId);

    /**
     *查询用户有权限的菜单列表
     * @param userid
     * @return
     */
    @Select({"SELECT id, name, url, icon, menu_type, display, parent_id FROM menu WHERE id IN(",
            "SELECT menuid FROM role_menu WHERE roleid IN ",
            "(SELECT roleid FROM user_role WHERE userid=#{userid}) OR roleid=-1) AND menu_type<>'2' AND `status`='1'"})
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="url", property="url", jdbcType= JdbcType.VARCHAR),
            @Result(column="icon", property="icon", jdbcType= JdbcType.VARCHAR),
            @Result(column="menu_type", property="menuType", jdbcType= JdbcType.CHAR),
            @Result(column="display", property="display", jdbcType= JdbcType.INTEGER),
            @Result(column="parent_id", property="parentId", jdbcType= JdbcType.INTEGER),
            @Result(column="status", property="status", jdbcType= JdbcType.CHAR)
    })
    List<Menu> selectByUser(int userid);

    /**
     *查询用户有权限的菜单列表,0是所有用户的默认权限
     * @param userid
     * @return
     */
    @Select({"SELECT url FROM menu WHERE id IN(",
            "SELECT menuid FROM role_menu WHERE roleid IN ",
            "(SELECT roleid FROM user_role WHERE userid=#{userid}) OR roleid=-1) AND `status`='1'"})
    List<String> selectAuthorities(int userid);

    /**
     *查询超级用户的所有权限，超级用户拥有所有权限
     * @return
     */
    @Select({"SELECT url FROM menu"})
    List<String> selectAuthoritiesByRoot();
    @Select({
        "select",
        "id, name, url, icon, menu_type, display, parent_id, creator, create_time, update_user, ",
        "update_time, status",
        "from menu",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType= JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType= JdbcType.VARCHAR),
        @Result(column="menu_type", property="menuType", jdbcType= JdbcType.CHAR),
        @Result(column="display", property="display", jdbcType= JdbcType.INTEGER),
        @Result(column="parent_id", property="parentId", jdbcType= JdbcType.INTEGER),
        @Result(column="creator", property="creator", jdbcType= JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType= JdbcType.TIMESTAMP),
        @Result(column="update_user", property="updateUser", jdbcType= JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType= JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType= JdbcType.CHAR)
    })
    Menu selectByPrimaryKey(Integer id);

    @UpdateProvider(type=MenuSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuCriteria example);

    @UpdateProvider(type=MenuSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Menu record, @Param("example") MenuCriteria example);

    @UpdateProvider(type=MenuSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Menu record);

    @Update({
        "update menu",
        "set name = #{name,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "icon = #{icon,jdbcType=VARCHAR},",
          "menu_type = #{menuType,jdbcType=CHAR},",
          "display = #{display,jdbcType=INTEGER},",
          "parent_id = #{parentId,jdbcType=INTEGER},",
          "creator = #{creator,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_user = #{updateUser,jdbcType=INTEGER},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "status = #{status,jdbcType=CHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Menu record);

    @Select({"CALL role_menu_update(#{menuids},#{roleid},#{userid})"})
    void roleMenuUpdate(HashMap<String, Object> map);

    @Select({"CALL delete_menu(#{menuid})"})
    void deleteMenuById(HashMap<String, Object> map);
}