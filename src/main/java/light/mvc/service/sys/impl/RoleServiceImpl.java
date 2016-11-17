package light.mvc.service.sys.impl;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.sys.TEFResource;
import light.mvc.model.sys.TEFRole;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sys.Role;
import light.mvc.service.sys.RoleServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleServiceI {

    @Autowired
    private BaseDaoI<TEFRole> roleDao;

    @Autowired
    private BaseDaoI<TEFResource> resourceDao;

    @Override
    public void add(Role r) {
        TEFRole t = new TEFRole();
        t.setIsDefault(0);
        t.setRoleName(r.getName());
        ////t.setSeq(r.getSeq());
        t.setDescription(r.getDescription());
        roleDao.save(t);
    }

    @Override
    public void delete(Long id) {
        TEFRole t = roleDao.get(TEFRole.class, id);
        roleDao.delete(t);
    }

    @Override
    public void edit(Role r) {
        TEFRole t = roleDao.get(TEFRole.class, r.getId());
        t.setDescription(r.getDescription());
        t.setRoleName(r.getName());
        t.setIsDefault(r.getIsdefault());
        //t(r.getSeq());
        roleDao.update(t);
    }

    @Override
    public Role get(Long id) {
        TEFRole t = roleDao.get(TEFRole.class, id);
        Role r = new Role();
        r.setDescription(t.getDescription());
        r.setId(t.getAutoID());
        r.setIsdefault(t.getIsDefault());
        r.setName(t.getRoleName());
        //r.setSeq(t.getSeq());
        Set<TEFResource> s = t.getResources();
        if ((s != null) && !s.isEmpty()) {
            boolean b = false;
            String ids = "";
            String names = "";
            for (TEFResource tr : s) {
                if (b) {
                    ids += ",";
                    names += ",";
                } else {
                    b = true;
                }
                ids += tr.getAutoID();
                names += tr.getResourceName();
            }
            r.setResourceIds(ids);
            r.setResourceNames(names);
        }
        return r;
    }

    @Override
    public List<Role> dataGrid(Role role, PageFilter ph) {
        List<Role> ul = new ArrayList<Role>();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = " from TEFRole t ";
        List<TEFRole> l = roleDao.find(hql + whereHql(role, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
        for (TEFRole t : l) {
            Role u = new Role();
            BeanUtils.copyProperties(t, u);
            u.setId(t.getAutoID());
            u.setName(t.getRoleName());
            u.setIsdefault(t.getIsDefault());
            ul.add(u);
        }
        return ul;
    }

    @Override
    public Long count(Role role, PageFilter ph) {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = " from TEFRole t ";
        return roleDao.count("select count(*) " + hql + whereHql(role, params), params);
    }

    private String whereHql(Role role, Map<String, Object> params) {
        String hql = "";
        if (role != null) {
            hql += " where 1=1 ";
            if (role.getName() != null) {
                hql += " and t.name like :name";
                params.put("name", "%%" + role.getName() + "%%");
            }
            if (role.getIsdefault() != null) {
                hql += "and t.isDefault= :isDefault";
                params.put("isDefault", role.getIsdefault());
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
    public void grant(Role role) {
        TEFRole t = roleDao.get(TEFRole.class, role.getId());
        if ((role.getResourceIds() != null) && !role.getResourceIds().equalsIgnoreCase("")) {
            String ids = "";
            boolean b = false;
            for (String id : role.getResourceIds().split(",")) {
                if (b) {
                    ids += ",";
                } else {
                    b = true;
                }
                ids += id;
            }
            t.setResources(new HashSet<TEFResource>(resourceDao.find("select distinct t from TEFResource t where t.id in ("
                    + ids + ")")));
        } else {
            t.setResources(null);
        }
    }

    @Override
    public List<Tree> tree() {
        List<TEFRole> l = null;
        List<Tree> lt = new ArrayList<Tree>();

        l = roleDao.find("select distinct t from TEFRole t");

        if ((l != null) && (l.size() > 0)) {
            for (TEFRole r : l) {
                Tree tree = new Tree();
                tree.setId(r.getAutoID().toString());
                tree.setText(r.getRoleName());
                lt.add(tree);
            }
        }
        return lt;
    }
}
