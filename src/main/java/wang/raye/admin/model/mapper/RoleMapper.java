package wang.raye.admin.model.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import wang.raye.admin.model.Role;
import wang.raye.admin.model.RoleCriteria;

import java.util.List;

public interface RoleMapper {
    @SelectProvider(type=RoleSqlProvider.class, method="countByExample")
    int countByExample(RoleCriteria example);

    @DeleteProvider(type=RoleSqlProvider.class, method="deleteByExample")
    int deleteByExample(RoleCriteria example);

    @Delete({
        "delete from role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Delete({
            "delete from role",
            "where id = #{id}"
    })
    int deleteByRoleid(String id);

    @Insert({
        "insert into role (id, name, ",
        "create_time, creator, ",
        "update_time, update_user, ",
        "description)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{creator,jdbcType=INTEGER}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{updateUser,jdbcType=INTEGER}, ",
        "#{description,jdbcType=VARCHAR})"
    })
    int insert(Role record);

    @Select({"SELECT r.id, r.name, r.create_time, r.creator, r.description,u.creator relationUserid",
            " FROM role r ",
            "LEFT JOIN user_role u ON r.id=u.roleid"})
    List<Role> selectRelation(String creator);

    @InsertProvider(type=RoleSqlProvider.class, method="insertSelective")
    int insertSelective(Role record);

    @SelectProvider(type=RoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType= JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType= JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType= JdbcType.TIMESTAMP),
        @Result(column="update_user", property="updateUser", jdbcType= JdbcType.INTEGER),
        @Result(column="description", property="description", jdbcType= JdbcType.VARCHAR)
    })
    List<Role> selectByExample(RoleCriteria example);

    @Select({
        "select",
        "id, name, create_time, creator, update_time, update_user, description",
        "from role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType= JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType= JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType= JdbcType.TIMESTAMP),
        @Result(column="update_user", property="updateUser", jdbcType= JdbcType.INTEGER),
        @Result(column="description", property="description", jdbcType= JdbcType.VARCHAR)
    })
    Role selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "r.id, r.name, r.create_time, r.creator,u.name as creator, r.description",
            "from role r LEFT JOIN admin_user u ON r.creator=u.id",
            "where r.name like #{query} LIMIT #{begin},#{pagesize}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType= JdbcType.TIMESTAMP),
            @Result(column="creator", property="creator", jdbcType= JdbcType.INTEGER),
            @Result(column="description", property="description", jdbcType= JdbcType.VARCHAR)
    })
    List<Role> select(@Param("begin") int begin, @Param("pagesize") int pageSize, @Param("query") String query);

    @UpdateProvider(type=RoleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleCriteria example);

    @UpdateProvider(type=RoleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Role record, @Param("example") RoleCriteria example);

    @UpdateProvider(type=RoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Role record);

    @Update({
        "update role",
        "set name = #{name,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "creator = #{creator,jdbcType=INTEGER},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "update_user = #{updateUser,jdbcType=INTEGER},",
          "description = #{description,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Role record);
}