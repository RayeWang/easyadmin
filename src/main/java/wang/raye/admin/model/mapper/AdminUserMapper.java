package wang.raye.admin.model.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import wang.raye.admin.model.AdminUser;
import wang.raye.admin.model.AdminUserCriteria;

import java.util.List;

public interface AdminUserMapper {
    @SelectProvider(type=AdminUserSqlProvider.class, method="countByExample")
    int countByExample(AdminUserCriteria example);

    @DeleteProvider(type=AdminUserSqlProvider.class, method="deleteByExample")
    int deleteByExample(AdminUserCriteria example);

    @Delete({
        "delete from admin_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into admin_user (id, name, ",
        "psw, email, update_time, ",
        "update_user, creator, ",
        "flag, last_login_time)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{psw,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{updateUser,jdbcType=INTEGER}, #{creator,jdbcType=INTEGER}, ",
        "#{flag,jdbcType=INTEGER}, #{lastLoginTime,jdbcType=TIMESTAMP})"
    })
    int insert(AdminUser record);

    @InsertProvider(type=AdminUserSqlProvider.class, method="insertSelective")
    int insertSelective(AdminUser record);

    @SelectProvider(type=AdminUserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
        @Result(column="psw", property="psw", jdbcType= JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType= JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType= JdbcType.TIMESTAMP),
        @Result(column="update_user", property="updateUser", jdbcType= JdbcType.INTEGER),
        @Result(column="creator", property="creator", jdbcType= JdbcType.INTEGER),
        @Result(column="flag", property="flag", jdbcType= JdbcType.INTEGER),
        @Result(column="last_login_time", property="lastLoginTime", jdbcType= JdbcType.TIMESTAMP)
    })
    List<AdminUser> selectByExample(AdminUserCriteria example);

    @Select({
            "select",
            "u.id, u.name, u.email, u.creator, u.flag, u.last_login_time,c.name createUser",
            "from admin_user u LEFT JOIN admin_user c ON u.creator=c.id",
            "where u.name LIKE #{query} LIMIT #{begin},#{pagesize}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="psw", property="psw", jdbcType= JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType= JdbcType.VARCHAR),
            @Result(column="creator", property="creator", jdbcType= JdbcType.INTEGER),
            @Result(column="flag", property="flag", jdbcType= JdbcType.INTEGER),
            @Result(column="last_login_time", property="lastLoginTime", jdbcType= JdbcType.TIMESTAMP)
    })
    List<AdminUser> select(@Param("begin") int begin, @Param("pagesize") int pageSize, @Param("query") String query);

    @Select({
        "select",
        "id, name, psw, email, update_time, update_user, creator, flag, last_login_time",
        "from admin_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
        @Result(column="psw", property="psw", jdbcType= JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType= JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType= JdbcType.TIMESTAMP),
        @Result(column="update_user", property="updateUser", jdbcType= JdbcType.INTEGER),
        @Result(column="creator", property="creator", jdbcType= JdbcType.INTEGER),
        @Result(column="flag", property="flag", jdbcType= JdbcType.INTEGER),
        @Result(column="last_login_time", property="lastLoginTime", jdbcType= JdbcType.TIMESTAMP)
    })
    AdminUser selectByPrimaryKey(Integer id);

    @UpdateProvider(type=AdminUserSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AdminUser record, @Param("example") AdminUserCriteria example);

    @UpdateProvider(type=AdminUserSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AdminUser record, @Param("example") AdminUserCriteria example);

    /**
     * 登录后更新用户最后登录时间
     * @param user
     * @return
     */
    @Update({"UPDATE admin_user SET last_login_time=CURRENT_TIMESTAMP WHERE name=#{name} AND psw=#{psw}"})
    int updateLoginTime(AdminUser user);
    @UpdateProvider(type=AdminUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AdminUser record);

    @Update({
        "update admin_user",
        "set name = #{name,jdbcType=VARCHAR},",
          "psw = #{psw,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "update_user = #{updateUser,jdbcType=INTEGER},",
          "creator = #{creator,jdbcType=INTEGER},",
          "flag = #{flag,jdbcType=INTEGER},",
          "last_login_time = #{lastLoginTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AdminUser record);
}