package wang.raye.admin.model.mapper;


import wang.raye.admin.model.Menu;
import wang.raye.admin.model.MenuCriteria;
import wang.raye.admin.model.MenuCriteria.Criteria;
import wang.raye.admin.model.MenuCriteria.Criterion;

import java.util.List;
import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class MenuSqlProvider {

    public String countByExample(MenuCriteria example) {
        BEGIN();
        SELECT("count(*)");
        FROM("menu");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(MenuCriteria example) {
        BEGIN();
        DELETE_FROM("menu");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(Menu record) {
        BEGIN();
        INSERT_INTO("menu");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getUrl() != null) {
            VALUES("url", "#{url,jdbcType=VARCHAR}");
        }
        
        if (record.getIcon() != null) {
            VALUES("icon", "#{icon,jdbcType=VARCHAR}");
        }
        
        if (record.getMenuType() != null) {
            VALUES("menu_type", "#{menuType,jdbcType=CHAR}");
        }
        
        if (record.getDisplay() != null) {
            VALUES("display", "#{display,jdbcType=INTEGER}");
        }
        
        if (record.getParentId() != null) {
            VALUES("parent_id", "#{parentId,jdbcType=INTEGER}");
        }
        
        if (record.getCreator() != null) {
            VALUES("creator", "#{creator,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUser() != null) {
            VALUES("update_user", "#{updateUser,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateTime() != null) {
            VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            VALUES("status", "#{status,jdbcType=CHAR}");
        }
        
        return SQL();
    }

    public String selectByExample(MenuCriteria example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("name");
        SELECT("url");
        SELECT("icon");
        SELECT("menu_type");
        SELECT("display");
        SELECT("parent_id");
        SELECT("creator");
        SELECT("create_time");
        SELECT("update_user");
        SELECT("update_time");
        SELECT("status");
        FROM("menu");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        Menu record = (Menu) parameter.get("record");
        MenuCriteria example = (MenuCriteria) parameter.get("example");
        
        BEGIN();
        UPDATE("menu");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            SET("name = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getUrl() != null) {
            SET("url = #{record.url,jdbcType=VARCHAR}");
        }
        
        if (record.getIcon() != null) {
            SET("icon = #{record.icon,jdbcType=VARCHAR}");
        }
        
        if (record.getMenuType() != null) {
            SET("menu_type = #{record.menuType,jdbcType=CHAR}");
        }
        
        if (record.getDisplay() != null) {
            SET("display = #{record.display,jdbcType=INTEGER}");
        }
        
        if (record.getParentId() != null) {
            SET("parent_id = #{record.parentId,jdbcType=INTEGER}");
        }
        
        if (record.getCreator() != null) {
            SET("creator = #{record.creator,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUser() != null) {
            SET("update_user = #{record.updateUser,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateTime() != null) {
            SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            SET("status = #{record.status,jdbcType=CHAR}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("menu");
        
        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("name = #{record.name,jdbcType=VARCHAR}");
        SET("url = #{record.url,jdbcType=VARCHAR}");
        SET("icon = #{record.icon,jdbcType=VARCHAR}");
        SET("menu_type = #{record.menuType,jdbcType=CHAR}");
        SET("display = #{record.display,jdbcType=INTEGER}");
        SET("parent_id = #{record.parentId,jdbcType=INTEGER}");
        SET("creator = #{record.creator,jdbcType=INTEGER}");
        SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        SET("update_user = #{record.updateUser,jdbcType=INTEGER}");
        SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        SET("status = #{record.status,jdbcType=CHAR}");
        
        MenuCriteria example = (MenuCriteria) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(Menu record) {
        BEGIN();
        UPDATE("menu");
        
        if (record.getName() != null) {
            SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getUrl() != null) {
            SET("url = #{url,jdbcType=VARCHAR}");
        }
        
        if (record.getIcon() != null) {
            SET("icon = #{icon,jdbcType=VARCHAR}");
        }
        
        if (record.getMenuType() != null) {
            SET("menu_type = #{menuType,jdbcType=CHAR}");
        }
        
        if (record.getDisplay() != null) {
            SET("display = #{display,jdbcType=INTEGER}");
        }
        
        if (record.getParentId() != null) {
            SET("parent_id = #{parentId,jdbcType=INTEGER}");
        }
        
        if (record.getCreator() != null) {
            SET("creator = #{creator,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUser() != null) {
            SET("update_user = #{updateUser,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateTime() != null) {
            SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            SET("status = #{status,jdbcType=CHAR}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }

    protected void applyWhere(MenuCriteria example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}