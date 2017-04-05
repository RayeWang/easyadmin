package wang.raye.admin.model;

import lombok.Data;

import java.util.Date;

@Data
public class AdminUser {
    private Integer id;

    private String name;

    private String psw;

    private String email;

    private Date updateTime;

    private Integer updateUser;

    private Integer creator;

    private Integer flag;

    private Date lastLoginTime;

}