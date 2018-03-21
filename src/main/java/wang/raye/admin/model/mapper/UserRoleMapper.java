package wang.raye.admin.model.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.common.Mapper;
import wang.raye.admin.model.Role;
import wang.raye.admin.model.UserRole;

import java.util.HashMap;
import java.util.List;

public interface UserRoleMapper extends Mapper<UserRole> {

    @Delete({
            "delete from user_role",
            "where roleid = #{roleid}"
    })
    int deleteByRoleid(String roleid);




    @Select({"SELECT r.id, r.name, r.createtime, r.creator, r.description,u.name creatoruser,u1.userid ",
        "FROM role r LEFT JOIN admin_user u ON r.creator=u.id",
        " LEFT JOIN (SELECT roleid,userid FROM user_role WHERE userid=#{userId}) u1 ON r.id=u1.roleid  "})
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="creator", property="creator", jdbcType= JdbcType.INTEGER),
            @Result(column="description", property="description", jdbcType= JdbcType.VARCHAR),
            @Result(column = "userid",property = "userid",jdbcType = JdbcType.INTEGER)
    })
    List<Role> selectByUserId(int userId);



    @Select({"CALL user_role_update(#{roleids},#{userid},#{creator})"})
    void userRoleUpdate(HashMap<String, Object> map);

    @Delete({"DELETE user_role WHERE userid=#{id}"})
    int deleteByUserId(int id);
}