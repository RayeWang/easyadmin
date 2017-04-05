package wang.raye.admin.model;

import lombok.Data;

import java.util.Date;

@Data
public class RoleMenu {
    private Integer menuid;

    private Integer roleid;

    private Integer flag;

    private Integer creater;

    private Date createTime;

}