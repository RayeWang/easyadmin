package wang.raye.admin.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserRole {
    private Integer userid;

    private Integer roleid;

    private Integer creater;

    private Date createTime;

}