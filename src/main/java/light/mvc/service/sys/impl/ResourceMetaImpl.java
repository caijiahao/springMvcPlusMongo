package light.mvc.service.sys.impl;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.sys.TEFDictionary;
import light.mvc.model.sys.TEFResourceMeta;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.sys.EFResourceMeta;
import light.mvc.service.sys.ResourceMetaServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ResourceMetaImpl implements ResourceMetaServiceI {


    @Autowired
    private BaseDaoI<TEFDictionary> dictionaryDao;
    @Autowired
    private BaseDaoI<TEFResourceMeta> resourceMetaDao;

    @Override
    public List<EFResourceMeta> dataGrid(EFResourceMeta resourceMeta) {
        // TODO Auto-generated method stub
        List<EFResourceMeta> ul = new ArrayList<EFResourceMeta>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = " from TEFResourceMeta t ";
        List<TEFResourceMeta> l = resourceMetaDao.find(hql + whereHql(resourceMeta, params), params);
        for (TEFResourceMeta t : l) {
            EFResourceMeta u = new EFResourceMeta();
            BeanUtils.copyProperties(t, u);
            ul.add(u);
        }
        return ul;
    }

    @Override
    public List<EFResourceMeta> dataGrid(EFResourceMeta resourceMeta, PageFilter ph) {
        // TODO Auto-generated method stub
        List<EFResourceMeta> ul = new ArrayList<EFResourceMeta>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = " from TEFResourceMeta t ";
        List<TEFResourceMeta> l = resourceMetaDao.find(hql + whereHql(resourceMeta, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
        for (TEFResourceMeta t : l) {
            EFResourceMeta u = new EFResourceMeta();
            BeanUtils.copyProperties(t, u);
            ul.add(u);
        }
        return ul;
    }

    @Override
    public Long count(EFResourceMeta resourceMeta) {
        // TODO Auto-generated method stub
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = " from TEFResourceMeta t ";
        return resourceMetaDao.count("select count(*) " + hql + whereHql(resourceMeta, params), params);
    }

    @Override
    public Long add(EFResourceMeta resourceMeta) {
        // TODO Auto-generated method stub
        TEFResourceMeta t = new TEFResourceMeta();
        BeanUtils.copyProperties(resourceMeta, t);
        t.setDictionary(dictionaryDao.get(TEFDictionary.class, resourceMeta.getType()));
        t.setUpdateDate(new Date());
        Serializable id = resourceMetaDao.save(t);
        return (Long) id;
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        TEFResourceMeta t = resourceMetaDao.get(TEFResourceMeta.class, id);
        if (t != null) {
            t.setDeleted(1);
            t.setUpdateDate(new Date());
        }
        resourceMetaDao.update(t);
    }

    @Override
    public void edit(EFResourceMeta resourceMeta) {
        // TODO Auto-generated method stub
        TEFResourceMeta t = resourceMetaDao.get(TEFResourceMeta.class, resourceMeta.getAutoID());
        t.setMetaPath(resourceMeta.getMetaPath());
        t.setMetaDescription(resourceMeta.getMetaDescription());
        t.setThumbMetaPath(resourceMeta.getThumbMetaPath());
        resourceMetaDao.update(t);
    }

    @Override
    public EFResourceMeta get(Long id) {
        // TODO Auto-generated method stub
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        TEFResourceMeta t = resourceMetaDao.get("from TEFResourceMeta t  where t.deleted=0 and t.autoID = :id", params);
        EFResourceMeta u = null;
        if (t != null) {
            u = new EFResourceMeta();
            BeanUtils.copyProperties(t, u);
            u.setType(t.getDictionary().getAutoID());
        }
        return u;
    }

    private String orderHql(PageFilter ph) {
        String orderString = "";
        if ((ph.getSort() != null) && (ph.getOrder() != null)) {
            orderString = " order by t." + ph.getSort() + " " + ph.getOrder();
        }
        return orderString;
    }

    private String whereHql(EFResourceMeta t, Map<String, Object> params) {//待修改
        String hql = " where t.deleted=0 ";
        if (t != null) {
            if (t.getType() != null) {
                hql += " and t.dictionary.autoID = :type";
                params.put("type", t.getType());
            }
            if (t.getMetaID() != null) {
                hql += " and t.metaID = :metaID";
                params.put("metaID", t.getMetaID());
            }
            if (t.getCreateDate() != null) {
                hql += " and (t.createDate >= :startCreateDate and t.createDate < :endCreateDate )";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();
                cal.setTime(t.getCreateDate());
                cal.add(Calendar.DATE, 1);
                params.put("startCreateDate", t.getCreateDate());
                params.put("endCreateDate", cal.getTime());
            }
        }

        return hql;
    }

    @Override
    public List<EFResourceMeta> get(Long metaID, Long typeID) {
        // TODO Auto-generated method stub
        List<EFResourceMeta> ul = new ArrayList<EFResourceMeta>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("metaID", metaID);
        params.put("typeID", typeID);
        String hql = "from TEFResourceMeta t where t.deleted = 0 and t.metaID = :metaID and t.dictionary.autoID= :typeID order by t.autoID";
        List<TEFResourceMeta> l = resourceMetaDao.find(hql, params);
        if ((l != null) && (l.size() > 0)) {
            for (TEFResourceMeta t : l) {
                EFResourceMeta resource = new EFResourceMeta();
                BeanUtils.copyProperties(t, resource);
                resource.setType(t.getDictionary().getAutoID());
                resource.setTypeName(t.getDictionary().getText());
                if (t.getMetaDescription() != null) {
                    String[] strd = t.getMetaDescription().split(";@;");
                    //System.out.println(strd.length);
                    if (strd.length == 2) {
                        resource.setDescription(strd[0]);
                        resource.setAddress(strd[1]);
                    }
                }
                ul.add(resource);
            }
            return ul;
        }

        return null;
    }

    //按天获取指定项目资源的数量
    @Override
    public Map<Date, Integer> getDateCount(Long metaID, Long typeID) {
        Map<Date, Integer> ul = new HashMap<Date, Integer>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("metaID", metaID);
        params.put("typeID", typeID);
        String hql = "select date(t.CreateDate) as 'date', count(t.AutoID) as 'count' from resourcemeta t where t.deleted=0 and t.MetaID = :metaID and t.Type = :typeID group by date(t.CreateDate)";
        List<Object[]> l = resourceMetaDao.findBySql(hql, params);
        for (Object[] obj : l) {
            ul.put((Date) obj[0], Integer.parseInt(obj[1].toString()));
        }

        return ul;
    }

}
