package light.mvc.service.sys.impl;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.sys.TEFLoginUser;
import light.mvc.model.sys.TEFOrganization;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sys.Organization;
import light.mvc.service.base.ServiceException;
import light.mvc.service.sys.OrganizationServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationServiceI {
	
	@Autowired
	private BaseDaoI<TEFLoginUser> userDao;

	@Autowired
	private BaseDaoI<TEFOrganization> organizationDao;

	@Override
	public List<Organization> treeGrid() {
		List<Organization> lr = new ArrayList<Organization>();
		List<TEFOrganization> l = organizationDao
				.find("from TEFOrganization t left join fetch t.organization where t.deleted= 0  order by t.seq");
		if ((l != null) && (l.size() > 0)) {
			for (TEFOrganization t : l) {
				Organization r = new Organization();
				BeanUtils.copyProperties(t, r);
				r.setId(t.getAutoID());
				if (t.getOrganization() != null) {
					r.setPid(t.getOrganization().getAutoID());
					r.setPname(t.getOrganization().getDepartmentName());
				}
				r.setIconCls(t.getIcon());
				lr.add(r);
			}
		}
		return lr;
	}

	@Override
	public void add(Organization org) {
		TEFOrganization t = new TEFOrganization();
		BeanUtils.copyProperties(org, t);
		if ((org.getPid() != null) && !"".equals(org.getPid())) {
			t.setOrganization(organizationDao.get(TEFOrganization.class, org.getPid()));
		}
		t.setCreateDate(new Date());
		organizationDao.save(t);
	}

	@Override
	public void delete(Long id) {
		TEFOrganization t = organizationDao.get(TEFOrganization.class, id);
		del(t);
	}

	private void del(TEFOrganization t) {
		List<TEFLoginUser> list = userDao.find("from TEFPersonalInfo t left join t.organization org where org.autoID="+t.getAutoID());
		if(list!=null&&list.size()>0){
			throw new ServiceException("该部门已经被用户使用");
		}else{
			if ((t.getOrganizations() != null) && (t.getOrganizations().size() > 0)) {
				for (TEFOrganization r : t.getOrganizations()) {
					del(r);
				}
			}
			t.setDeleted(1);
			organizationDao.update(t);
		}
	}

	@Override
	public void edit(Organization r) {
		TEFOrganization t = organizationDao.get(TEFOrganization.class, r.getId());
		t.setDepartmentCode(r.getDepartmentCode());
		t.setIcon(r.getIcon());
		t.setDepartmentName(r.getDepartmentName());
		t.setSeq(r.getSeq());
		if ((r.getPid() != null) && !"".equals(r.getPid())) {
			t.setOrganization(organizationDao.get(TEFOrganization.class, r.getPid()));
		}
		organizationDao.update(t);
	}

	@Override
	public Organization get(Long id) {
		TEFOrganization t = organizationDao.get(TEFOrganization.class, id);
		if(t != null && t.getDeleted() == 0){
			Organization r = new Organization();
			BeanUtils.copyProperties(t, r);
			r.setId(t.getAutoID());
			if (t.getOrganization() != null) {
				r.setPid(t.getOrganization().getAutoID());
				r.setPname(t.getOrganization().getDepartmentName());
			}
			return r;
		}
		return null;
	}

	@Override
	public List<Tree> tree() {
		List<TEFOrganization> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = organizationDao.find("select distinct t from TEFOrganization t where t.deleted= 0 order by t.seq");

		if ((l != null) && (l.size() > 0)) {
			for (TEFOrganization r : l) {
				Tree tree = new Tree();
				tree.setId(r.getAutoID().toString());
				if (r.getOrganization() != null) {
					tree.setPid(r.getOrganization().getAutoID().toString());
				}
				tree.setText(r.getDepartmentName());
				tree.setIconCls(r.getIcon());
				lt.add(tree);
			}
		}
		return lt;
	}
	
	//获取审核的部门组织路径，每一个上级部门都包含其中
	@Override
	public List<Organization> getAuditPath(Long organizationId) {
		List<Organization> result = new ArrayList<Organization>();
		Organization r = get(organizationId);
		if(r != null)
		{
			result.add(r);
			while(r.getPid() != null && r.getPid() > 0)
			{
				r = get(r.getPid());
				if(r != null)
					result.add(r);
			}
		}
		return result;
	}

}
