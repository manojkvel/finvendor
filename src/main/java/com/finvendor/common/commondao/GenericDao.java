package com.finvendor.common.commondao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @param <E>
 * @author ayush on 28/Apr/2018
 */
public abstract class GenericDao<E> {

    @Autowired
    private SessionFactory sessionFactory;
    private final Class<E> entityClass;

    @SuppressWarnings("unchecked")
    public GenericDao() {
        this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    public E findById(final Serializable id) {
        return (E) getSession().get(this.entityClass, id);
    }

    public Serializable save(E entity) {
        return getSession().save(entity);
    }

    public void saveOrUpdate(E entity) {
        getSession().saveOrUpdate(entity);
    }

    public void delete(E entity) {
        getSession().delete(entity);
    }

    public void deleteAll() {
        List<E> entities = findAll();
        for (E entity : entities) {
            getSession().delete(entity);
        }
    }

    protected List<Object[]> findByColumnAndCondition(String tableName, String[] cols, Map<String, Object> conditions) {
        StringBuilder sb = new StringBuilder();

        if (cols != null) {
            sb.append("SELECT ");
            for (String col : cols) {
                sb.append(col).append(",");
            }
            sb.deleteCharAt(sb.toString().length()-1);
            sb.append(" FROM ").append(tableName).append(" WHERE ");
        } else {
            sb.append("SELECT * FROM ").append(tableName).append(" WHERE ");
        }
        for (String colName : conditions.keySet()) {
            sb.append(colName).append("=:").append(colName).append(" and ");
        }

        String hsql = removeLastAnd(sb.toString());
        SQLQuery sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(hsql);
        for (Map.Entry<String, Object> entry : conditions.entrySet()) {
            String columnName = entry.getKey();
            Object columnValue = entry.getValue();
            sqlQuery.setParameter(columnName, columnValue);
        }
        return (List<Object[]>) sqlQuery.list();
    }

    private static String removeLastAnd(String str) {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length - 5; i++) {
            char c = chars[i];
            sb.append(c);
        }
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    public List<E> findAll() {
        return getSession().createCriteria(this.entityClass).list();
    }

    public void clear() {
        getSession().clear();
    }

    public void flush() {
        getSession().flush();
    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
