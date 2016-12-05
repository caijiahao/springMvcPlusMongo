package light.mvc.service.sys.impl;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.sys.TEFResource;
import light.mvc.pageModel.base.SessionInfo;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sys.Resource;
import light.mvc.service.sys.ResourceServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResourceServiceImpl implements ResourceServiceI {

	@Autowired
	private BaseDaoI<TEFResource> resourceDao;

	@Override
	public List<Resource> treeGrid() {
		List<Resource> lr = new ArrayList<Resource>();
		List<TEFResource> l = resourceDao
				.find("select distinct t from TEFResource t left join fetch t.parentResource  order by t.seq");
		if ((l != null) && (l.size() > 0)) {
			for (TEFResource t : l) {
				Resource r = new Resource();
				BeanUtils.copyProperties(t, r);
				r.setName(t.getResourceName());
				r.setResourcetype(t.getResourceType());
				r.setCstate(t.getActive());
				r.setId(t.getAutoID());
				if (t.getParentResource() != null) {
					r.setPid(t.getParentResource().getAutoID());
					r.setPname(t.getParentResource().getResourceName());
				}
				r.setIconCls(t.getIcon());
				lr.add(r);
			}
		}
		return lr;
	}

	@Override
	public void add(Resource r) {
		TEFResource t = new TEFResource();
		t.setCreateDate(new Date());
		t.setDescription(r.getDescription());
		t.setIcon(r.getIcon());
		t.setResourceName(r.getName());
		if ((r.getPid() != null) && !"".equals(r.getPid())) {
			t.setParentResource(resourceDao.get(TEFResource.class, r.getPid()));
		}
		t.setResourceType(r.getResourcetype());
		t.setSeq(r.getSeq());
		t.setActive(r.getCstate());
		t.setUrl(r.getUrl());
		resourceDao.save(t);
	}

	@Override
	public void delete(Long id) {
		TEFResource t = resourceDao.get(TEFResource.class, id);
		del(t);
	}

	private void del(TEFResource t) {
		if ((t.getResources() != null) && (t.getResources().size() > 0)) {
			for (TEFResource r : t.getResources()) {
				del(r);
			}
		}
		resourceDao.delete(t);
	}

	@Override
	public void edit(Resource r) {
		TEFResource t = resourceDao.get(TEFResource.class, r.getId());
		t.setDescription(r.getDescription());
		t.setIcon(r.getIcon());
		t.setResourceName(r.getName());
		if ((r.getPid() != null) && !"".equals(r.getPid())) {
			t.setParentResource(resourceDao.get(TEFResource.class, r.getPid()));
		}
		t.setResourceType(r.getResourcetype());
		t.setSeq(r.getSeq());
		t.setActive(r.getCstate());
		t.setUrl(r.getUrl());
		resourceDao.update(t);
	}

	@Override
	public Resource get(Long id) {
		TEFResource t = resourceDao.get(TEFResource.class, id);
		Resource r = new Resource();
		BeanUtils.copyProperties(t, r);
		r.setCstate(t.getActive());
		r.setResourcetype(t.getResourceType());
		r.setName(t.getResourceName());
		r.setId(t.getAutoID());
		r.setDescription(t.getDescription());
		if (t.getParentResource() != null) {
			r.setPid(t.getParentResource().getAutoID());
			r.setPname(t.getParentResource().getResourceName());
		}
		return r;
	}

	@Override
	public List<Tree> tree(SessionInfo sessionInfo) {
		List<TEFResource> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("resourceType", 0);// 菜单类型的资源

		if (sessionInfo != null) {
			if("admin".equals(sessionInfo.getLoginname())){
				l = resourceDao
						.find("select distinct t from TEFResource t  where t.resourceType = :resourceType  order by t.seq",
								params);
			}else{
				params.put("userId", Long.valueOf(sessionInfo.getId()));// 自查自己有权限的资源
				l = resourceDao
					.find("select distinct t from TEFResource t join fetch t.roles role join role.users user where t.resourceType = :resourceType and user.autoID = :userId order by t.seq",
							params);
			}
		} else {
			return null;
		}

		if ((l != null) && (l.size() > 0)) {
			for (TEFResource r : l) {
				Tree tree = new Tree();
				tree.setId(r.getAutoID().toString());
				if (r.getParentResource() != null) {
					tree.setPid(r.getParentResource().getAutoID().toString());
				}else{
					tree.setState("closed");
				}
				tree.setText(r.getResourceName());
				tree.setIconCls(r.getIcon());
				Map<String, Object> attr = new HashMap<String, Object>();
				attr.put("url", r.getUrl());
				tree.setAttributes(attr);
				lt.add(tree);
			}
		}
		return lt;
	}

	@Override
	public List<Tree> allTree(boolean flag) {
		List<TEFResource> l = null;
		List<Tree> lt = new ArrayList<Tree>();
		if(flag){
			l = resourceDao.find("select distinct t from TEFResource t left join fetch t.parentResource  order by t.seq");
		}else{
			l = resourceDao.find("select distinct t from TEFResource t left join fetch t.parentResource where t.resourceType =0 order by t.seq");
		}
		if ((l != null) && (l.size() > 0)) {
			for (TEFResource r : l) {
				Tree tree = new Tree();
				tree.setId(r.getAutoID().toString());
				if (r.getParentResource() != null) {
					tree.setPid(r.getParentResource().getAutoID().toString());
				}
				tree.setText(r.getResourceName());
				tree.setIconCls(r.getIcon());
				Map<String, Object> attr = new HashMap<String, Object>();
				attr.put("url", r.getUrl());
				tree.setAttributes(attr);
				lt.add(tree);
			}
		}
		return lt;
	}

	@Override
	public List<String> resourceAllList() {
		List<String> resourceList = new ArrayList<String>();
		List<TEFResource> l = resourceDao.find("select distinct t from TEFResource t left join fetch t.parentResource  order by t.seq");
		for (int i = 0; i < l.size(); i++) {
			resourceList.add(l.get(i).getUrl());
		}
		return resourceList;
	}
}
