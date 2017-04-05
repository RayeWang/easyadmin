package wang.raye.admin.model.mapper;


import wang.raye.admin.model.AdminUser;
import wang.raye.admin.model.AdminUserCriteria;
import wang.raye.admin.model.AdminUserCriteria.Criteria;
import wang.raye.admin.model.AdminUserCriteria.Criterion;

import java.util.List;
import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class AdminUserSqlProvider {

    public String countByExample(AdminUserCriteria example) {
        BEGIN();
        SELECT("count(*)");
        FROM("admin_user");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(AdminUserCriteria example) {
        BEGIN();
        DELETE_FROM("admin_user");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(AdminUser record) {
        BEGIN();
        INSERT_INTO("admin_user");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPsw() != null) {
            VALUES("psw", "#{psw,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            VALUES("email", "#{email,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUser() != null) {
            VALUES("update_user", "#{updateUser,jdbcType=INTEGER}");
        }
        
        if (record.getCreator() != null) {
            VALUES("creator", "#{creator,jdbcType=INTEGER}");
        }
        
        if (record.getFlag() != null) {
            VALUES("flag", "#{flag,jdbcType=INTEGER}");
        }
        
        if (record.getLastLoginTime() != null) {
            VALUES("last_login_time", "#{lastLoginTime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String selectByExample(AdminUserCriteria example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("name");
        SELECT("psw");
        SELECT("email");
        SELECT("update_time");
        SELECT("update_user");
        SELECT("creator");
        SELECT("flag");
        SELECT("last_login_time");
        FROM("admin_user");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        AdminUser record = (AdminUser) parameter.get("record");
        AdminUserCriteria example = (AdminUserCriteria) parameter.get("example");
        
        BEGIN();
        UPDATE("admin_user");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            SET("name = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getPsw() != null) {
            SET("psw = #{record.psw,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            SET("email = #{record.email,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUser() != null) {
            SET("update_user = #{record.updateUser,jdbcType=INTEGER}");
        }
        
        if (record.getCreator() != null) {
            SET("creator = #{record.creator,jdbcType=INTEGER}");
        }
        
        if (record.getFlag() != null) {
            SET("flag = #{record.flag,jdbcType=INTEGER}");
        }
        
        if (record.getLastLoginTime() != null) {
            SET("last_login_time = #{record.lastLoginTime,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("admin_user");
        
        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("name = #{record.name,jdbcType=VARCHAR}");
        SET("psw = #{record.psw,jdbcType=VARCHAR}");
        SET("email = #{record.email,jdbcType=VARCHAR}");
        SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        SET("update_user = #{record.updateUser,jdbcType=INTEGER}");
        SET("creator = #{record.creator,jdbcType=INTEGER}");
        SET("flag = #{record.flag,jdbcType=INTEGER}");
        SET("last_login_time = #{record.lastLoginTime,jdbcType=TIMESTAMP}");
        
        AdminUserCriteria example = (AdminUserCriteria) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(AdminUser record) {
        BEGIN();
        UPDATE("admin_user");
        
        if (record.getName() != null) {
            SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPsw() != null) {
            SET("psw = #{psw,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            SET("email = #{email,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUser() != null) {
            SET("update_user = #{updateUser,jdbcType=INTEGER}");
        }
        
        if (record.getCreator() != null) {
            SET("creator = #{creator,jdbcType=INTEGER}");
        }
        
        if (record.getFlag() != null) {
            SET("flag = #{flag,jdbcType=INTEGER}");
        }
        
        if (record.getLastLoginTime() != null) {
            SET("last_login_time = #{lastLoginTime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }

    protected void applyWhere(AdminUserCriteria example, boolean includeExamplePhrase) {
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