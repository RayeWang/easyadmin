package wang.raye.admin.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * 系统菜单
 * Create by Raye on 2018年3月1日21:12:51
 */
@Data
public class Menu {
    @Id
    private Integer id;

    private String name;

    private String url;

    private String icon;

    private String menutype;

    private Integer display;

    private Integer parentid;

    private Integer creator;

    private Date createtime;

    private Integer updateuser;

    private Date updatetime;

    private String flag;


    @Transient
    private boolean selected;
    @Transient
    private String type;
    @Transient
    private int roleid;
    @Transient
    private List<Menu> children;
}