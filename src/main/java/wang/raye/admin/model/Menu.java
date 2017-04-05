package wang.raye.admin.model;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Data
public class Menu {
    private Integer id;

    private String name;

    private String url;
    private String text;

    private String type;
    private String icon;

    private String menuType;

    private Integer display;

    private Integer parentId;

    private Integer creator;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;

    private String status;
    private HashMap<String,Object> additionalParameters;
    /** 页面中使用*/
    private int roleid;
    private List<Menu> children;

}