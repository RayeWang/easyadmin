package wang.raye.admin.model.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.common.Mapper;
import wang.raye.admin.model.AdminUser;

import java.util.List;

public interface AdminUserMapper extends Mapper<AdminUser> {




    @Select({
            "select",
            "u.id, u.name, u.email, u.creator, u.flag, u.logintime,c.name createuser",
            "from admin_user u LEFT JOIN admin_user c ON u.creator=c.id",
            "where u.name LIKE #{query} LIMIT #{begin},#{pagesize}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="psw", property="psw", jdbcType= JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType= JdbcType.VARCHAR),
            @Result(column="creator", property="creator", jdbcType= JdbcType.INTEGER),
            @Result(column="flag", property="flag", jdbcType= JdbcType.INTEGER)
    })
    List<AdminUser> selectByQuery(@Param("begin") int begin, @Param("pagesize") int pageSize, @Param("query") String query);


    @Select({"SELECT COUNT(id) FROM admin_user WHERE name LIKE #{name}"})
    int selectCountByName(String name);

    /**
     * 登录后更新用户最后登录时间
     * @param user
     * @return
     */
    @Update({"UPDATE admin_user SET logintime=CURRENT_TIMESTAMP WHERE name=#{name} AND psw=#{psw}"})
    int updateLoginTime(AdminUser user);

}