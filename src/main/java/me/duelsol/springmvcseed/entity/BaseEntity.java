package me.duelsol.springmvcseed.entity;

import me.duelsol.springmvcseed.framework.ApplicationContextHolder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 14/10/21
 * Time: 20:02
 */
public abstract class BaseEntity {

    private Integer id = null;
    private Date createTime = null;
    private Date updateTime = null;

    public abstract String getTableName();

    public final void save() {
        StringBuilder sql = new StringBuilder();

        Class clazz = this.getClass();
        String tableName = this.getTableName();
        Integer id = this.getId();
        Date currentDate = new Date();
        Field[] fields = clazz.getDeclaredFields();

        NamedParameterJdbcTemplate template = ApplicationContextHolder.getInstance().getBean(NamedParameterJdbcTemplate.class);
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(this);
        KeyHolder keyholder = new GeneratedKeyHolder();

        if (id == null) {
            sql.append("insert into ").append(tableName).append(" (id, createTime, updateTime");
            StringBuilder values = new StringBuilder("(null, :createTime, :updateTime");
            for (Field field : fields) {
                String name = field.getName();
                sql.append(", ").append(name);
                values.append(", :").append(name);
            }
            sql.append(")").append(" values ").append(values.toString()).append(")");

            this.setCreateTime(currentDate);
            this.setUpdateTime(currentDate);

            template.update(sql.toString(), sqlParameterSource, keyholder);

            this.setId(keyholder.getKey().intValue());
        } else {
            sql.append("update ").append(tableName).append(" set updateTime = :updateTime");
            for (Field field : fields) {
                String name = field.getName();
                sql.append(", ").append(name).append(" = :").append(name);
            }
            sql.append(" where id = ").append(id);

            this.setUpdateTime(currentDate);

            template.update(sql.toString(), sqlParameterSource, keyholder);
        }
    }

    public final void delete() {
        JdbcTemplate template = ApplicationContextHolder.getInstance().getBean(JdbcTemplate.class);
        template.update("delete from " + this.getTableName() + " where id = " + this.getId());

        this.setId(null);
        this.setCreateTime(null);
        this.setUpdateTime(null);
    }

    public final Integer getId() {
        return this.id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public final Date getCreateTime() {
        return this.createTime;
    }

    private void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public final Date getUpdateTime() {
        return this.updateTime;
    }

    private void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
