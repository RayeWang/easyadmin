package wang.raye.admin.model;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

/**
 * 用户角色
 */
@Data
public class UserRole {
    @Id
    private Integer userid;
    @Id
    private Integer roleid;

    private Integer creator;

    private Date createtime;


}