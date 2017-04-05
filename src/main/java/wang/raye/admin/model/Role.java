package wang.raye.admin.model;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Integer id;

    private String name;

    private Date createTime;

    private Integer creator;

    private Date updateTime;

    private Integer updateUser;

    private String description;
    private String creatorUser;
    /** 页面中使用*/
    private int userid;

}