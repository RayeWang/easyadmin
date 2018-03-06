package wang.raye.admin.model.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.common.Mapper;
import wang.raye.admin.model.Role;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {


    @Delete({
            "delete from role",
            "where id = #{id}"
    })
    int deleteByRoleid(String id);



    @Select({"SELECT r.id, r.name, r.createtime, r.creator, r.description,u.creator relationUserid",
            " FROM role r ",
            "LEFT JOIN user_role u ON r.id=u.roleid"})
    List<Role> selectRelation(String creator);




    @Select({
            "select",
            "r.id, r.name, r.createtime, r.creator,u.name as creator, r.description",
            "from role r LEFT JOIN admin_user u ON r.creator=u.id",
            "where r.name like #{query} LIMIT #{begin},#{pagesize}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="creator", property="creator", jdbcType= JdbcType.INTEGER),
            @Result(column="description", property="description", jdbcType= JdbcType.VARCHAR)
    })
    List<Role> selectByQuery(@Param("begin") int begin, @Param("pagesize") int pageSize, @Param("query") String query);


    @Select({"SELECT COUNT(id) FROM role WHERE name LIKE #{name}"})
    int selectCountByName(String name);
}