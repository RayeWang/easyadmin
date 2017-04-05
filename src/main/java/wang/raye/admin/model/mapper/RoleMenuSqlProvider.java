package wang.raye.admin.model.mapper;

import wang.raye.admin.model.RoleMenu;
import wang.raye.admin.model.RoleMenuCriteria;
import wang.raye.admin.model.RoleMenuCriteria.Criteria;
import wang.raye.admin.model.RoleMenuCriteria.Criterion;

import java.util.List;
import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class RoleMenuSqlProvider {

    public String countByExample(RoleMenuCriteria example) {
        BEGIN();
        SELECT("count(*)");
        FROM("role_menu");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(RoleMenuCriteria example) {
        BEGIN();
        DELETE_FROM("role_menu");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(RoleMenu record) {
        BEGIN();
        INSERT_INTO("role_menu");
        
        if (record.getMenuid() != null) {
            VALUES("menuid", "#{menuid,jdbcType=INTEGER}");
        }
        
        if (record.getRoleid() != null) {
            VALUES("roleid", "#{roleid,jdbcType=INTEGER}");
        }
        
        if (record.getFlag() != null) {
            VALUES("flag", "#{flag,jdbcType=INTEGER}");
        }
        
        if (record.getCreater() != null) {
            VALUES("creater", "#{creater,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String selectByExample(RoleMenuCriteria example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("menuid");
        } else {
            SELECT("menuid");
        }
        SELECT("roleid");
        SELECT("flag");
        SELECT("creater");
        SELECT("create_time");
        FROM("role_menu");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        RoleMenu record = (RoleMenu) parameter.get("record");
        RoleMenuCriteria example = (RoleMenuCriteria) parameter.get("example");
        
        BEGIN();
        UPDATE("role_menu");
        
        if (record.getMenuid() != null) {
            SET("menuid = #{record.menuid,jdbcType=INTEGER}");
        }
        
        if (record.getRoleid() != null) {
            SET("roleid = #{record.roleid,jdbcType=INTEGER}");
        }
        
        if (record.getFlag() != null) {
            SET("flag = #{record.flag,jdbcType=INTEGER}");
        }
        
        if (record.getCreater() != null) {
            SET("creater = #{record.creater,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("role_menu");
        
        SET("menuid = #{record.menuid,jdbcType=INTEGER}");
        SET("roleid = #{record.roleid,jdbcType=INTEGER}");
        SET("flag = #{record.flag,jdbcType=INTEGER}");
        SET("creater = #{record.creater,jdbcType=INTEGER}");
        SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        
        RoleMenuCriteria example = (RoleMenuCriteria) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(RoleMenu record) {
        BEGIN();
        UPDATE("role_menu");
        
        if (record.getFlag() != null) {
            SET("flag = #{flag,jdbcType=INTEGER}");
        }
        
        if (record.getCreater() != null) {
            SET("creater = #{creater,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("menuid = #{menuid,jdbcType=INTEGER}");
        WHERE("roleid = #{roleid,jdbcType=INTEGER}");
        
        return SQL();
    }

    protected void applyWhere(RoleMenuCriteria example, boolean includeExamplePhrase) {
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