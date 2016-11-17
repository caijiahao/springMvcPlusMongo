package light.mvc.service.sys.impl;

import light.mvc.dao.BaseDaoI;
import light.mvc.framework.constant.GlobalConstant;
import light.mvc.model.sys.TEFDictionary;
import light.mvc.model.sys.TEFDictionaryCategory;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.sys.Dictionary;
import light.mvc.service.sys.DictionaryServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DictionaryServiceImpl implements DictionaryServiceI {


    @Autowired
    private BaseDaoI<TEFDictionary> dictionaryDao;

    @Autowired
    private BaseDaoI<TEFDictionaryCategory> dictionarytypeDao;

    @Override
    public void add(Dictionary r) {
        TEFDictionary t = new TEFDictionary();
        t.setIsDefault(GlobalConstant.NOT_DEFAULT);
        t.setActive(GlobalConstant.ENABLE);
        t.setCode(r.getCode());
        t.setText(r.getText());
        t.setSeq(r.getSeq());
        t.setDictionaryCategory(dictionarytypeDao.get(TEFDictionaryCategory.class, r.getDictionarytypeId()));
        dictionaryDao.save(t);
    }

    @Override
    public void delete(Long id) {
        TEFDictionary t = dictionaryDao.get(TEFDictionary.class, id);
        t.setDeleted(1);
        dictionaryDao.update(t);
    }

    @Override
    public void edit(Dictionary r) {
        TEFDictionary t = dictionaryDao.get(TEFDictionary.class, r.getId());
        t.setText(r.getText());
        t.setSeq(r.getSeq());
        t.setCode(r.getCode());
        t.setActive(r.getActive());
        t.setDictionaryCategory(dictionarytypeDao.get(TEFDictionaryCategory.class, r.getDictionarytypeId()));
        dictionaryDao.update(t);
    }

    @Override
    public Dictionary get(Long id) {
        TEFDictionary t = dictionaryDao.get(TEFDictionary.class, id);
        Dictionary r = new Dictionary();
        r.setId(t.getAutoID());
        r.setIsDefault(t.getIsDefault());
        r.setText(t.getText());
        r.setSeq(t.getSeq());
        r.setCode(t.getCode());
        if (t.getDictionaryCategory() != null) {
            r.setDictionarytypeId(t.getDictionaryCategory().getAutoID());
            r.setDictionarytypeName(t.getDictionaryCategory().getName());
        }
        r.setActive(t.getActive());
        return r;
    }

    @Override
    public List<Dictionary> dataGrid(Dictionary dictionary, PageFilter ph) {
        List<Dictionary> ul = new ArrayList<Dictionary>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = " from TEFDictionary t ";
        List<TEFDictionary> l = dictionaryDao.find(hql + whereHql(dictionary, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
        for (TEFDictionary t : l) {
            Dictionary u = new Dictionary();
            BeanUtils.copyProperties(t, u);
            u.setId(t.getAutoID());

            if (t.getDictionaryCategory() != null) {
                u.setDictionarytypeId(t.getDictionaryCategory().getAutoID());
                u.setDictionarytypeName(t.getDictionaryCategory().getName());
            }
            ul.add(u);
        }
        return ul;
    }

    @Override
    public Long count(Dictionary dictionary, PageFilter ph) {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = " from TEFDictionary t ";
        return dictionaryDao.count("select count(*) " + hql + whereHql(dictionary, params), params);
    }

    private String whereHql(Dictionary dictionary, Map<String, Object> params) {
        String hql = "";
        if (dictionary != null) {
            hql += " where t.deleted=0";
            if (dictionary.getText() != null) {
                hql += " and t.name like :name";
                params.put("name", "%%" + dictionary.getText() + "%%");
            }
            if (dictionary.getDictionarytypeId() != null) {
                hql += " and t.dictionaryCategory.id = :dictionarytypeId";
                params.put("dictionarytypeId", dictionary.getDictionarytypeId());
            }
        }
        return hql;
    }

    private String orderHql(PageFilter ph) {
        String orderString = "";
        if ((ph.getSort() != null) && (ph.getOrder() != null)) {
            orderString = " order by t." + ph.getSort() + " " + ph.getOrder();
        }
        return orderString;
    }

    @Override
    public List<Dictionary> combox(String code) {
        List<Dictionary> ld = new ArrayList<Dictionary>();
        List<TEFDictionary> lt = dictionaryDao.find("from TEFDictionary t where t.active=1 and t.dictionaryCategory.code='" + code + "' order by t.seq");
        if (lt != null && lt.size() > 0) {
            for (int i = 0; i < lt.size(); i++) {
                if (lt.get(i).getActive() == 1 && lt.get(i).getDeleted() == 0) {
                    Dictionary d = new Dictionary();
                    d.setId(lt.get(i).getAutoID());
                    d.setCode(lt.get(i).getCode());
                    d.setText(lt.get(i).getText());
                    ld.add(d);
                }
            }
        }
        return ld;
    }

    @Override
    public Dictionary checkUnique(Dictionary dictionary) {
        String query = "from TEFDictionary t where t.code = :code and t.dictionaryCategory.autoID =  :categoryid";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", dictionary.getCode());
        params.put("categoryid", dictionary.getDictionarytypeId());
        TEFDictionary t = dictionaryDao.get(query, params);
        if (t != null) {
            Dictionary r = new Dictionary();
            r.setId(t.getAutoID());
            r.setIsDefault(t.getIsDefault());
            r.setText(t.getText());
            r.setSeq(t.getSeq());
            r.setCode(t.getCode());
            if (t.getDictionaryCategory() != null) {
                r.setDictionarytypeId(t.getDictionaryCategory().getAutoID());
                r.setDictionarytypeName(t.getDictionaryCategory().getName());
            }
            r.setActive(t.getActive());
            return r;
        } else {
            return null;
        }
    }

    @Override
    public Dictionary getDefaultDictionary(String categoryCode) {
        if (categoryCode.isEmpty()) {
            return null;
        }
        String query = "from TEFDictionary t where t.dictionaryCategory.code = : categorycode and t.isDefault=1";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("categorycode", categoryCode);
        TEFDictionary t = dictionaryDao.get(query, params);
        if (t != null) {
            Dictionary r = new Dictionary();
            r.setId(t.getAutoID());
            r.setIsDefault(t.getIsDefault());
            r.setText(t.getText());
            r.setSeq(t.getSeq());
            r.setCode(t.getCode());
            if (t.getDictionaryCategory() != null) {
                r.setDictionarytypeId(t.getDictionaryCategory().getAutoID());
                r.setDictionarytypeName(t.getDictionaryCategory().getName());
            }
            r.setActive(t.getActive());
            return r;
        } else {
            return null;
        }
    }

    @Override
    public Dictionary getDictionary(String categoryCode, String contentCode) {
        if (categoryCode.isEmpty() || contentCode.isEmpty()) {
            return null;
        }
        String query = "from TEFDictionary t where t.dictionaryCategory.code = :categorycode and t.code = :contentcode";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("categorycode", categoryCode);
        params.put("contentcode", contentCode);
        TEFDictionary t = dictionaryDao.get(query, params);
        if (t != null) {
            Dictionary r = new Dictionary();
            r.setId(t.getAutoID());
            r.setIsDefault(t.getIsDefault());
            r.setText(t.getText());
            r.setSeq(t.getSeq());
            r.setCode(t.getCode());
            if (t.getDictionaryCategory() != null) {
                r.setDictionarytypeId(t.getDictionaryCategory().getAutoID());
                r.setDictionarytypeName(t.getDictionaryCategory().getName());
            }
            r.setActive(t.getActive());
            return r;
        } else {
            return null;
        }
    }

    @Override
    public List<Dictionary> getDictionaryByCategoryCode(String code) {
        // TODO Auto-generated method stub
        if (code.isEmpty()) {
            return null;
        }
        String hql = "from TEFDictionary t where t.deleted=0 and t.dictionaryCategory.code = :code";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", code);
        List<TEFDictionary> lt = dictionaryDao.find(hql, params);
        List<Dictionary> dl = new ArrayList<Dictionary>();
        if (lt != null && lt.size() > 0) {
            for (TEFDictionary t : lt) {
                Dictionary r = new Dictionary();
                r.setId(t.getAutoID());
                r.setIsDefault(t.getIsDefault());
                r.setText(t.getText());
                r.setSeq(t.getSeq());
                r.setCode(t.getCode());
                if (t.getDictionaryCategory() != null) {
                    r.setDictionarytypeId(t.getDictionaryCategory().getAutoID());
                    r.setDictionarytypeName(t.getDictionaryCategory().getName());
                }
                r.setActive(t.getActive());
                dl.add(r);
            }
        }
        return dl;
    }

    @Override
    public List<Dictionary> getDictionaryByCategoryID(Long id) {
        // TODO Auto-generated method stub
        if (id == null || id == 0) {
            return null;
        }
        String hql = "from TEFDictionary t where t.deleted=0 and t.dictionaryCategory.autoID = :id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("autoID", id);
        List<TEFDictionary> lt = dictionaryDao.find(hql, params);
        List<Dictionary> dl = new ArrayList<Dictionary>();
        if (lt != null && lt.size() > 0) {
            for (TEFDictionary t : lt) {
                Dictionary r = new Dictionary();
                r.setId(t.getAutoID());
                r.setIsDefault(t.getIsDefault());
                r.setText(t.getText());
                r.setSeq(t.getSeq());
                r.setCode(t.getCode());
                if (t.getDictionaryCategory() != null) {
                    r.setDictionarytypeId(t.getDictionaryCategory().getAutoID());
                    r.setDictionarytypeName(t.getDictionaryCategory().getName());
                }
                r.setActive(t.getActive());
                dl.add(r);
            }
        }
        return dl;
    }

}
