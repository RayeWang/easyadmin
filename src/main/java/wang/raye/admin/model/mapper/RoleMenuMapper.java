package wang.raye.admin.model.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import wang.raye.admin.model.RoleMenu;
import wang.raye.admin.model.RoleMenuCriteria;

import java.util.List;

public interface RoleMenuMapper {
    @SelectProvider(type=RoleMenuSqlProvider.class, method="countByExample")
    int countByExample(RoleMenuCriteria example);

    @DeleteProvider(type=RoleMenuSqlProvider.class, method="deleteByExample")
    int deleteByExample(RoleMenuCriteria example);

    @Delete({
        "delete from role_menu",
        "where menuid = #{menuid,jdbcType=INTEGER}",
          "and roleid = #{roleid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(@Param("menuid") Integer menuid, @Param("roleid") Integer roleid);

    @Delete({
            "delete from role_menu",
            "where roleid = #{roleid}"
    })
    int deleteByRoleid(String roleid);

    @Insert({
        "insert into role_menu (menuid, roleid, ",
        "flag, creator, create_time)",
        "values (#{menuid,jdbcType=INTEGER}, #{roleid,jdbcType=INTEGER}, ",
        "#{flag,jdbcType=INTEGER}, #{creator,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(RoleMenu record);

    @InsertProvider(type=RoleMenuSqlProvider.class, method="insertSelective")
    int insertSelective(RoleMenu record);

    @SelectProvider(type=RoleMenuSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="menuid", property="menuid", jdbcType= JdbcType.INTEGER, id=true),
        @Result(column="roleid", property="roleid", jdbcType= JdbcType.INTEGER, id=true),
        @Result(column="flag", property="flag", jdbcType= JdbcType.INTEGER),
        @Result(column="creator", property="creator", jdbcType= JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType= JdbcType.TIMESTAMP)
    })
    List<RoleMenu> selectByExample(RoleMenuCriteria example);

    @Select({
        "select",
        "menuid, roleid, flag, creator, create_time",
        "from role_menu",
        "where menuid = #{menuid,jdbcType=INTEGER}",
          "and roleid = #{roleid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="menuid", property="menuid", jdbcType= JdbcType.INTEGER, id=true),
        @Result(column="roleid", property="roleid", jdbcType= JdbcType.INTEGER, id=true),
        @Result(column="flag", property="flag", jdbcType= JdbcType.INTEGER),
        @Result(column="creator", property="creator", jdbcType= JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType= JdbcType.TIMESTAMP)
    })
    RoleMenu selectByPrimaryKey(@Param("menuid") Integer menuid, @Param("roleid") Integer roleid);

    @UpdateProvider(type=RoleMenuSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") RoleMenu record, @Param("example") RoleMenuCriteria example);

    @UpdateProvider(type=RoleMenuSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") RoleMenu record, @Param("example") RoleMenuCriteria example);

    @UpdateProvider(type=RoleMenuSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RoleMenu record);

    @Update({
        "update role_menu",
        "set flag = #{flag,jdbcType=INTEGER},",
          "creator = #{creator,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where menuid = #{menuid,jdbcType=INTEGER}",
          "and roleid = #{roleid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RoleMenu record);
}