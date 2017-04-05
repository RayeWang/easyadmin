package wang.raye.admin.model.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import wang.raye.admin.model.Role;
import wang.raye.admin.model.UserRole;
import wang.raye.admin.model.UserRoleCriteria;

import java.util.HashMap;
import java.util.List;

public interface UserRoleMapper {
    @SelectProvider(type=UserRoleSqlProvider.class, method="countByExample")
    int countByExample(UserRoleCriteria example);

    @DeleteProvider(type=UserRoleSqlProvider.class, method="deleteByExample")
    int deleteByExample(UserRoleCriteria example);

    @Delete({
        "delete from user_role",
        "where userid = #{userid,jdbcType=INTEGER}",
          "and roleid = #{roleid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(@Param("userid") Integer userid, @Param("roleid") Integer roleid);
    @Delete({
            "delete from user_role",
            "where roleid = #{roleid}"
    })
    int deleteByRoleid(String roleid);
    @Insert({
        "insert into user_role (userid, roleid, ",
        "creator, create_time)",
        "values (#{userid,jdbcType=INTEGER}, #{roleid,jdbcType=INTEGER}, ",
        "#{creator,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(UserRole record);

    @InsertProvider(type=UserRoleSqlProvider.class, method="insertSelective")
    int insertSelective(UserRole record);

    @SelectProvider(type=UserRoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="userid", property="userid", jdbcType= JdbcType.INTEGER, id=true),
        @Result(column="roleid", property="roleid", jdbcType= JdbcType.INTEGER, id=true),
        @Result(column="creator", property="creator", jdbcType= JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType= JdbcType.TIMESTAMP)
    })
    List<UserRole> selectByExample(UserRoleCriteria example);

    @Select({"SELECT r.id, r.name, r.create_time, r.creator, r.description,u.name creatorUser,u1.userid ",
        "FROM role r LEFT JOIN admin_user u ON r.creator=u.id",
        " LEFT JOIN (SELECT roleid,userid FROM user_role WHERE userid=#{userId}) u1 ON r.id=u1.roleid  "})
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType= JdbcType.TIMESTAMP),
            @Result(column="creator", property="creator", jdbcType= JdbcType.INTEGER),
            @Result(column="description", property="description", jdbcType= JdbcType.VARCHAR),
            @Result(column = "creatorUser",property = "creatorUser",jdbcType = JdbcType.INTEGER)
    })
    List<Role> selectByUserId(int userId);
    @Select({
        "select",
        "userid, roleid, creator, create_time",
        "from user_role",
        "where userid = #{userid,jdbcType=INTEGER}",
          "and roleid = #{roleid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="userid", property="userid", jdbcType= JdbcType.INTEGER, id=true),
        @Result(column="roleid", property="roleid", jdbcType= JdbcType.INTEGER, id=true),
        @Result(column="creator", property="creator", jdbcType= JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType= JdbcType.TIMESTAMP)
    })
    UserRole selectByPrimaryKey(@Param("userid") Integer userid, @Param("roleid") Integer roleid);

    @UpdateProvider(type=UserRoleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleCriteria example);

    @UpdateProvider(type=UserRoleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleCriteria example);

    @UpdateProvider(type=UserRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserRole record);



    @Select({"CALL user_role_update(#{roleids},#{userid},#{creator})"})
    void userRoleUpdate(HashMap<String, Object> map);
}