package light.mvc.service.sys.impl;


import light.mvc.dao.BaseDaoI;
import light.mvc.model.sys.TEFDictionary;
import light.mvc.model.sys.TEFNotice;
import light.mvc.model.sys.TEFPersonalInfo;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.sys.EFNotice;
import light.mvc.service.sys.EFNoticeServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

@Service
public class EFNoticeServiceImpl implements EFNoticeServiceI {

    @Autowired
    private BaseDaoI<TEFNotice> noticeDao;

    @Autowired
    private BaseDaoI<TEFDictionary> dictionaryDao;

    @Autowired
    private BaseDaoI<TEFPersonalInfo> personalDao;

    @Override
    public Long add(EFNotice notice) {
        // TODO Auto-generated method stub
        TEFNotice t = new TEFNotice();
        BeanUtils.copyProperties(notice, t);
        t.setDictionary(dictionaryDao.get(TEFDictionary.class, notice.getType()));
        t.setPersonal(personalDao.get(TEFPersonalInfo.class, notice.getPersonalID()));
        Serializable id = noticeDao.save(t);
        return (Long) id;
    }

    @Override
    public List<EFNotice> dataGrid(EFNotice notice, PageFilter ph) {
        // TODO Auto-generated method stub
        List<EFNotice> ul = new ArrayList<EFNotice>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = " from TEFNotice t ";
        List<TEFNotice> l = null;
        if (ph != null) {
            l = noticeDao.find(hql + whereHql(notice, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
        } else {
            l = noticeDao.find(hql + whereHql(notice, params) + " order by t.updateDate desc");
        }
        for (TEFNotice t : l) {
            EFNotice u = new EFNotice();
            BeanUtils.copyProperties(t, u);
            ul.add(u);
        }
        return ul;
    }

    @Override
    public Long count(EFNotice notice, PageFilter ph) {
        // TODO Auto-generated method stub
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = " from TEFNotice t ";
        return noticeDao.count("select count(*) " + hql + whereHql(notice, params), params);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        TEFNotice t = noticeDao.get(TEFNotice.class, id);
        if (t != null) {
            t.setUpdateDate(new Date());
            t.setDeleted(1);
        }
        noticeDao.update(t);
    }

    @Override
    public EFNotice get(Long id) {
        // TODO Auto-generated method stub
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        TEFNotice t = noticeDao.get("from TEFNotice t  where t.deleted=0 and t.id = :id", params);
        EFNotice u = null;
        if (t != null) {
            u = new EFNotice();
            BeanUtils.copyProperties(t, u);
            u.setPersonalID(t.getPersonal().getAutoID());
            u.setPersonalName(t.getPersonal().getRealName());
        }
        return u;
    }

    @Override

    public List<EFNotice> gethaveNotNotice(Long personalID, PageFilter ph) {
        // TODO Auto-generated method stub
        return null;
    }


    public void edit(EFNotice notice) {
        // TODO Auto-generated method stub
        TEFNotice t = noticeDao.get(TEFNotice.class, notice.getAutoID());
        if (notice.getContent() != null)
            t.setContent(notice.getContent());
        t.setUpdateDate(new Date());
        noticeDao.update(t);
    }

    @Override
    public void setNotice(Long id) {
        TEFNotice t = noticeDao.get(TEFNotice.class, id);
        if (t != null && t.getIsNotice() == 0) {
            t.setIsNotice(1);
            t.setUpdateDate(new Date());
        }
        noticeDao.update(t);
    }


    private String orderHql(PageFilter ph) {
        String orderString = "";
        if ((ph.getSort() != null) && (ph.getOrder() != null)) {
            orderString = " order by t." + ph.getSort() + " " + ph.getOrder();
        }
        return orderString;
    }


    private String whereHql(EFNotice t, Map<String, Object> params) {//待修改
        String hql = " where t.deleted=0 ";
        if (t != null) {
            if (t.getPersonalID() != null && t.getPersonalID() > 0) {
                hql += " and t.personal.autoID = :autoID";
                params.put("autoID", t.getPersonalID());
            }
            if (t.getType() != null) {
                hql += " and t.type = :type";
                params.put("type", t.getType());
            }
            if (t.getMetaID() != null) {
                hql += " and t.metaID = :metaID";
                params.put("metaID", t.getMetaID());
            }
            if (t.getIsNotice() != null) {
                hql += " and t.isNotice = :isNotice";
                params.put("isNotice", t.getIsNotice());
            }

        }

        return hql;
    }

    @Override
    public List<EFNotice> gethaveNotice(Long personalID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<EFNotice> gethaveNotNotice(Long personalID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<EFNotice> gethaveNotice(Long personalID, PageFilter ph) {
        // TODO Auto-generated method stub
        return null;
    }

}
