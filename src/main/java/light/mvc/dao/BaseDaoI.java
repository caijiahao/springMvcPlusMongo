package light.mvc.dao;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface BaseDaoI<T> {

    public Serializable save(T o);

    public void delete(T o);

    public void update(T o);

    public void saveOrUpdate(T o);

    public T get(Class<T> c, Serializable id);

    public T get(String hql);

    public T get(String hql, Map<String, Object> params);

    public List<T> find(String hql);

    public List<T> find(String hql, Map<String, Object> params);

    public List<T> find(String hql, int page, int rows);

    public List<T> find(String hql, Map<String, Object> params, int page, int rows);

    public Long count(String hql);

    public Long count(String hql, Map<String, Object> params);

    public int executeHql(String hql);

    public int executeHql(String hql, Map<String, Object> params);

    public List<Object[]> findBySql(String sql);

    public List<Object[]> findBySql(String sql, int page, int rows);

    public List<Object[]> findBySql(String sql, Map<String, Object> params);

    public List<Object[]> findBySql(String sql, Map<String, Object> params, int page, int rows);

    public int executeSql(String sql);

    public int executeSql(String sql, Map<String, Object> params);

    public BigInteger countBySql(String sql);

    public BigInteger countBySql(String sql, Map<String, Object> params);

    public void deleteFile(String filePath, HttpServletRequest request);//通过request获取网站根目录的绝对路径

}
