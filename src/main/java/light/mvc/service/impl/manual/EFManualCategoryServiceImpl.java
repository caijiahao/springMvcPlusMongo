package light.mvc.service.impl.manual;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.manual.TEFmanualCategory;
import light.mvc.model.sys.TEFPersonalInfo;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.manual.EFExpertList;
import light.mvc.pageModel.manual.EFmanualCategory;
import light.mvc.pageModel.manual.EFmanualCategoryList;
import light.mvc.pageModel.sys.User;
import light.mvc.service.manual.EFManualCategoryServiceI;
import light.mvc.utils.coder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class EFManualCategoryServiceImpl implements EFManualCategoryServiceI {

    @Autowired
    private BaseDaoI<TEFmanualCategory> categoryDao;

    @Override
    public void add(EFmanualCategory category) {
        // TODO Auto-generated method stub
        TEFmanualCategory t = new TEFmanualCategory();
        if (category.getParentID() == null) {
            category.setParentID((long) 0);
        }
        BeanUtils.copyProperties(category, t);
        if (category.getParentID() != null) {
            coder c = new coder();
            t.setParent(categoryDao.get(TEFmanualCategory.class, category.getParentID()));
            String pre = t.getParent().getCategoryCode();
            Map<String, Object> params = new HashMap<String, Object>();
            String sql = " select count(*) from manualcategory t where t.deleted=0 and t.parentid=:parentid";
            params.put("parentid", category.getParentID());
            Long count = categoryDao.countBySql(sql, params).longValue();
            t.setCategoryCode(coder.getCoder(pre, count + 1));
        }
        categoryDao.save(t);

    }

    @Override
    public List<EFmanualCategory> dataGrid(EFmanualCategory category,
                                           PageFilter ph) {
        // TODO Auto-generated method stub
        List<EFmanualCategory> ul = new ArrayList<EFmanualCategory>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = " from TEFmanualCategory t ";
        List<TEFmanualCategory> l = categoryDao.find(hql + whereHql(category, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
        if ((l != null) && (l.size() > 0)) {
            for (TEFmanualCategory t : l) {
                EFmanualCategory u = new EFmanualCategory();
                BeanUtils.copyProperties(t, u);
                if (t.getParent() != null) {
                    u.setParentID(t.getParent().getAutoID());
                }
                ul.add(u);
            }
        }
        return ul;
    }

    @Override
    public List<EFmanualCategory> getAllData() {
        // TODO Auto-generated method stub

        String hql = " from TEFmanualCategory t ";
        List<TEFmanualCategory> l = categoryDao.find(hql);
        List<EFmanualCategory> ul = new ArrayList<EFmanualCategory>();
        if ((l != null) && (l.size() > 0)) {
            for (TEFmanualCategory t : l) {
                EFmanualCategory u = new EFmanualCategory();
                BeanUtils.copyProperties(t, u);
                if (t.getParent() != null) {
                    u.setParentID(t.getParent().getAutoID());
                }
                ul.add(u);
            }
        }
        return ul;
    }

    @Override
    public Long count(EFmanualCategory category, PageFilter ph) {
        // TODO Auto-generated method stub
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = " from TEFmanualCategory t ";
        return categoryDao.count("select count(*) " + hql + whereHql(category, params), params);
    }

    @Override
    public void delete(Long id, HttpServletRequest request) {
        // TODO Auto-generated method stub
        TEFmanualCategory t = categoryDao.get(TEFmanualCategory.class, id);
        t.setUpdateDate(new Date());
        t.setDeleted(1);
        categoryDao.update(t);
    }

    @Override
    public EFmanualCategory get(Long id) {
        // TODO Auto-generated method stub
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        TEFmanualCategory t = categoryDao.get("from TEFmanualCategory t  where t.autoID = :id", params);
        EFmanualCategory u = new EFmanualCategory();
        BeanUtils.copyProperties(t, u);
        if (t.getParent() != null) {
            u.setParentID(t.getParent().getAutoID());
        }
        Set<TEFPersonalInfo> s = t.getUsers();
        if ((s != null) && !s.isEmpty()) {
            boolean b = false;
            String ids = "";
            String names = "";
            for (TEFPersonalInfo tr : s) {
                if (b) {
                    ids += ",";
                    names += ",";
                } else {
                    b = true;
                }
                ids += tr.getAutoID();
                names += tr.getRealName();
            }
            u.setExpertIds(ids);
            u.setExpertNames(names);

        }

        return u;
    }

    @Override
    public void edit(EFmanualCategory data) {
        // TODO Auto-generated method stub
        TEFmanualCategory t = categoryDao.get(TEFmanualCategory.class, data.getAutoID());
        t.setCategoryCode(data.getCategoryCode());
        t.setCategoryDescription(data.getCategoryDescription());
        t.setCategoryName(data.getCategoryName());
        if (data.getParentID() != 0) {
            TEFmanualCategory category = categoryDao.get(TEFmanualCategory.class, data.getParentID());
            t.setParent(category);
        }
        categoryDao.update(t);
    }


    private String orderHql(PageFilter ph) {
        String orderString = "";
        if ((ph.getSort() != null) && (ph.getOrder() != null)) {
            orderString = " order by t." + ph.getSort() + " " + ph.getOrder();
        }
        return orderString;
    }

    private String whereHql(EFmanualCategory category, Map<String, Object> params) {//待修改
        String hql = "";
        if (category != null) {
            hql += " where t.deleted=0 ";
            if (category.getCategoryCode() != null) {
                hql += "and t.categoryCode like :categoryCode";
                params.put("categoryCode", category.getCategoryCode() + "%%");
            }
        }

        return hql;
    }

    @Override
    public List<Tree> tree() {
        // TODO Auto-generated method stub

        List<TEFmanualCategory> l = null;
        List<Tree> lt = new ArrayList<Tree>();
        Map<String, Object> params = new HashMap<String, Object>();
        l = categoryDao.find("from TEFmanualCategory t");

        if (l != null && (l.size() > 0)) {
            for (TEFmanualCategory r : l) {
                Tree tree = new Tree();
                tree.setId(r.getAutoID().toString());
                if (r.getParent() != null) {
                    tree.setPid(r.getParent().getAutoID().toString());
                } else {
                    tree.setState("closed");
                }
                tree.setText(r.getCategoryName());
                Map<String, Object> code = new HashMap<String, Object>();
                code.put("code", r.getCategoryCode());
                tree.setAttributes(code);
                lt.add(tree);
            }
        }


        return lt;
    }

    @Override
    public List<Tree> allTree() {
        // TODO Auto-generated method stub
        List<TEFmanualCategory> l = null;
        List<Tree> lt = new ArrayList<Tree>();
        Map<String, Object> params = new HashMap<String, Object>();
        l = categoryDao.find("select distinct t from TEFmanualCategory t left join fetch t.parent ");
        if (l != null && (l.size() > 0)) {
            for (TEFmanualCategory r : l) {
                Tree tree = new Tree();
                tree.setId(r.getAutoID().toString());
                if (r.getParent() != null) {
                    tree.setPid(r.getParent().getAutoID().toString());
                }
                tree.setText(r.getCategoryName());
                Map<String, Object> code = new HashMap<String, Object>();
                code.put("code", r.getCategoryCode());
                tree.setAttributes(code);
                lt.add(tree);
            }
        }
        return lt;
    }

    @Override
    public EFmanualCategory getExpertAndChild(Long id) {
        // TODO Auto-generated method stub
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        TEFmanualCategory t = categoryDao.get("from TEFmanualCategory t  where t.autoID = :id", params);
        EFmanualCategory u = new EFmanualCategory();
        BeanUtils.copyProperties(t, u);
        if (t.getParent() != null) {
            u.setParentID(t.getParent().getAutoID());
        }
        Set<TEFPersonalInfo> s = t.getUsers();
        if ((s != null) && !s.isEmpty()) {
            boolean b = false;
            String ids = "";
            String names = "";
            for (TEFPersonalInfo tr : s) {
                if (b) {
                    ids += ",";
                    names += ",";
                } else {
                    b = true;
                }
                ids += tr.getAutoID();
                names += tr.getRealName();
            }
            u.setExpertIds(ids);
            u.setExpertNames(names);

        }
        String hql = "from TEFmanualCategory t where t.parent.autoID =:id";
        List<TEFmanualCategory> trs = categoryDao.find(hql, params);
        if ((trs != null) && (trs.size() > 0)) {
            u.setIsParent(1);
            boolean b = false;
            String ids = "";
            String names = "";
            for (TEFmanualCategory tt : trs) {
                if (b) {
                    ids += ",";
                    names += ",";
                } else {
                    b = true;
                }
                ids += tt.getAutoID();
                names += tt.getCategoryName();
            }
            u.setChildIds(ids);
            u.setChildNames(names);

        }

        return u;
    }

    @Override
    public EFmanualCategoryList getChild(Long id) {
        // TODO Auto-generated method stub
        Map<String, Object> params = new HashMap<String, Object>();
        //System.out.println(id);
        params.put("id", id);
        String hql = "from TEFmanualCategory t where t.parent.autoID =:id";
        List<TEFmanualCategory> l = categoryDao.find(hql, params);
        List<EFmanualCategory> ul = new ArrayList<EFmanualCategory>();
        EFmanualCategoryList list = new EFmanualCategoryList();
        if ((l != null) && (l.size() > 0)) {
            list.setIsParent(1);
            for (TEFmanualCategory t : l) {
                EFmanualCategory u = new EFmanualCategory();
                BeanUtils.copyProperties(t, u);
                if (t.getParent() != null) {
                    u.setParentID(t.getParent().getAutoID());
                }
                long childPid = t.getAutoID();
                Map<String, Object> params2 = new HashMap<String, Object>();
                params2.put("childPid", childPid);
                String hql2 = " select count(*) from TEFmanualCategory t  where t.parent.autoID =:childPid";
                if (categoryDao.count(hql2, params2) > 0) {
                    u.setIsParent(1);
                } else {
                    u.setIsParent(0);
                }
                ul.add(u);
            }
        }
        list.setList(ul);
        return list;


    }

    @Override
    public EFExpertList getExpertList(Long id) {
        // TODO Auto-generated method stub
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        TEFmanualCategory t = categoryDao.get("from TEFmanualCategory t  where t.autoID = :id", params);
        Set<TEFPersonalInfo> s = t.getUsers();
        List<User> ul = new ArrayList<User>();
        EFExpertList list = new EFExpertList();
        if ((s != null) && !s.isEmpty()) {
            for (TEFPersonalInfo tr : s) {
                User u = new User();
                BeanUtils.copyProperties(tr, u);
                ul.add(u);
            }

        }
        list.setList(ul);


        return list;
    }

    @Override
    public List<EFmanualCategory> getByPid(Long id) {
        // TODO Auto-generated method stub
        Map<String, Object> params = new HashMap<String, Object>();
        List<EFmanualCategory> ul = new ArrayList<EFmanualCategory>();
        List<TEFmanualCategory> l;
        //System.out.println(id);
        if (id != 0) {
            params.put("id", id);
            String hql = "from TEFmanualCategory t where t.parent.autoID =:id and t.deleted=0";
            l = categoryDao.find(hql, params);
        } else {
            String hql = "from TEFmanualCategory t where t.parent= null and t.deleted=0";
            l = categoryDao.find(hql);

        }
        if ((l != null) && (l.size() > 0)) {
            for (TEFmanualCategory t : l) {
                EFmanualCategory u = new EFmanualCategory();
                BeanUtils.copyProperties(t, u);
                ul.add(u);
            }
        }

        return ul;

    }

    @Override
    public List<Tree> getTreeByCode(String categoryCode) {
        // TODO Auto-generated method stub
        List<TEFmanualCategory> l = null;
        List<Tree> lt = new ArrayList<Tree>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("categoryCode", "%%" + categoryCode + "%%");
        l = categoryDao.find("select distinct t from TEFmanualCategory t left join fetch t.parent where t.categoryCode like :categoryCode and t.deleted=0", params);
        if (l != null && (l.size() > 0)) {
            for (TEFmanualCategory r : l) {
                Tree tree = new Tree();
                tree.setId(r.getAutoID().toString());
                if (r.getParent() != null) {
                    tree.setPid(r.getParent().getAutoID().toString());
                }
                tree.setText(r.getCategoryName());
                Map<String, Object> code = new HashMap<String, Object>();
                code.put("code", r.getCategoryCode());
                tree.setAttributes(code);
                lt.add(tree);
            }
        }
        //System.out.println(l.size());
        return lt;
    }

    @Override
    public List<EFmanualCategory> treeGrid(EFmanualCategory category) {
        // TODO Auto-generated method stub
        List<EFmanualCategory> ul = new ArrayList<EFmanualCategory>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = " select distinct t from TEFmanualCategory t left join fetch t.parent ";
        if (category.getCategoryCode() != null) {
            params.put("categoryCode", "%%" + category.getCategoryCode() + "%%");
            hql += "where t.categoryCode like :categoryCode and t.deleted=0";

        }
        List<TEFmanualCategory> l = categoryDao.find(hql, params);
        if ((l != null) && (l.size() > 0)) {
            for (TEFmanualCategory t : l) {
                EFmanualCategory u = new EFmanualCategory();
                BeanUtils.copyProperties(t, u);
                u.setId(t.getAutoID());
                if (t.getParent() != null) {
                    u.setParentID(t.getParent().getAutoID());
                }
                ul.add(u);
            }
        }
        return ul;
    }

    @Override
    public EFmanualCategory getByCode(String code) {
        // TODO Auto-generated method stub
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", code);
        TEFmanualCategory t = categoryDao.get("from TEFmanualCategory t  where t.categoryCode = :code", params);
        EFmanualCategory u = new EFmanualCategory();
        BeanUtils.copyProperties(t, u);
        if (t.getParent() != null) {
            u.setParentID(t.getParent().getAutoID());
        }
        Set<TEFPersonalInfo> s = t.getUsers();
        if ((s != null) && !s.isEmpty()) {
            boolean b = false;
            String ids = "";
            String names = "";
            for (TEFPersonalInfo tr : s) {
                if (b) {
                    ids += ",";
                    names += ",";
                } else {
                    b = true;
                }
                ids += tr.getAutoID();
                names += tr.getRealName();
            }
            u.setExpertIds(ids);
            u.setExpertNames(names);

        }

        return u;
    }


}
