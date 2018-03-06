package wang.raye.admin.model.mapper;

import org.apache.ibatis.annotations.Delete;
import tk.mybatis.mapper.common.Mapper;
import wang.raye.admin.model.RoleMenu;

public interface RoleMenuMapper extends Mapper<RoleMenu> {

    @Delete({
            "delete from role_menu",
            "where roleid = #{roleid}"
    })
    int deleteByRoleid(String roleid);




}