package light.mvc.service.sys.impl;

import light.mvc.dao.BaseDaoI;
import light.mvc.framework.constant.GlobalConstant;
import light.mvc.model.manual.TEFmanualCategory;
import light.mvc.model.sys.*;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.SessionInfo;
import light.mvc.pageModel.sys.User;
import light.mvc.service.sys.UserServiceI;
import light.mvc.utils.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/*
 * @author: yeyaowen 
 * 编写日期：2016-8-9
 * 特别注意：初步编写，尚未测试
 * */
@Service
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private BaseDaoI<TEFLoginUser> userDao;
	
	@Autowired
	private BaseDaoI<TEFPersonalInfo> userInfoDao;
	
	@Autowired
	private BaseDaoI<TEFRole> roleDao;
	
	@Autowired
	private BaseDaoI<TEFOrganization> organizationDao;
	
	@Autowired
	private BaseDaoI<TEFmanualCategory> categoryDao;
	
	@Override
	public List<User> dataGrid(User user, PageFilter ph) {
		// Author：yeyaowen 2016-8-9
		List<User> ul = new ArrayList<User>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TEFLoginUser t ";
		List<TEFLoginUser> l= userDao.find(hql + whereHql(user, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for(TEFLoginUser t:l)
		{
			User u= new User();
			BeanUtils.copyProperties(t, u);
			TEFPersonalInfo tp= t.getPersonalInfo();
			BeanUtils.copyProperties(tp, u);
			TEFRole role=tp.getRole();
			if(role!=null)
			{
				u.setRoleIds(role.getAutoID());
				u.setRoleNames(role.getRoleName());
			}
			if(tp.getOrganization()!=null)
			{
				u.setOrganizationId(tp.getOrganization().getAutoID());
				u.setOrganizationName(tp.getOrganization().getDepartmentName());
			}
			ul.add(u);
		}
		
		return ul;
	}

	@Override
	public Long count(User user, PageFilter ph) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TEFLoginUser t ";
		return userDao.count("select count(*) " + hql + whereHql(user, params), params);
	}

	@Override
	public Long add(User u) {
		// TODO Auto-generated method stub
		// @author:yeyaowen  2016-8-9
		// des: 初步设计
		
		TEFLoginUser t= new TEFLoginUser();
		BeanUtils.copyProperties(u, t);
		TEFPersonalInfo tp = new TEFPersonalInfo();
		BeanUtils.copyProperties(u, tp);
		if(u.getOrganizationId()!=null)
		{
		tp.setOrganization(organizationDao.get(TEFOrganization.class, u.getOrganizationId()));
		}
		tp.setRole(roleDao.get(TEFRole.class, u.getRoleIds()));
		t.setPersonalInfo(tp);
		t.setPassword(MD5Util.md5(u.getPassword()));
		t.setStatus(GlobalConstant.ENABLE);
		t.setCreateDate(new Date());
		Long returnid = (Long)userInfoDao.save(tp);
		userDao.save(t);
		return returnid;
	}

	@Override
	public void delete(Long id) {
		// Author：yeyaowen 2016-8-9
		TEFLoginUser t= userDao.get(TEFLoginUser.class, id);
		TEFPersonalInfo tp =userInfoDao.get(TEFPersonalInfo.class, id);
		if(t!=null&&tp!=null)
		{
		t.setActive(0);
		tp.setActive(0);
		t.setDeleted(1);
		tp.setDeleted(1);
		}
	}

	@Override
	public void edit(User u) {
		
		/*@author : yeyaowen 2016-8-9
		 *  
		 * */
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("autoID", u.getAutoID());
		TEFLoginUser t = userDao.get("from TEFLoginUser l where l.personalInfo.autoID = :autoID and l.active=1 and l.deleted=0", params);
		TEFPersonalInfo tp = t.getPersonalInfo();
		if(u.getPassword()!=null&&!"".equals(u.getPassword())){
			t.setPassword(MD5Util.md5(u.getPassword()));
		}
		if(u.getLoginName() != null && u.getLoginName().length() > 0){
			t.setLoginName(u.getLoginName());
		}
		if(u.getRealName() != null && u.getRealName().length() > 0){
			tp.setRealName(u.getRealName()); //姓名不保存空值
		}
		if(u.getAddress() != null && u.getAddress().length() > 0){
			tp.setAddress(u.getAddress());  //地址不保存空值
		}
		tp.setPhoneNumber(u.getPhoneNumber());
		tp.setEmail(u.getEmail());
		tp.setAge(u.getAge());
		tp.setSex(u.getSex());
		tp.setTechType(u.getTechType());  		//专长
		tp.setTechTitle(u.getTechTitle());		//职称
		tp.setNeedPublish(u.getNeedPublish());	//是否发布
		tp.setDescription(u.getDescription());
		if(u.getOrganizationId() != null && u.getOrganizationId() > 0){
			tp.setOrganization(organizationDao.get(TEFOrganization.class, u.getOrganizationId()));
		}
		if(u.getRoleIds() != null && u.getRoleIds() > 0){
			tp.setRole(roleDao.get(TEFRole.class, u.getRoleIds()));
		}

		userDao.update(t);
		userInfoDao.update(tp);
	}

	@Override
	public User get(Long personalID) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("autoID", personalID);
		//System.out.println(id);
		TEFLoginUser loginUser = userDao.get("from TEFLoginUser l where l.personalInfo.autoID = :autoID and l.active=1 and l.deleted=0", params);
		TEFPersonalInfo t = userInfoDao.get("from TEFPersonalInfo t  left join fetch t.role role where t.autoID = :autoID and t.active=1 and t.deleted=0", params);
		if(loginUser == null || t == null){
			return null;
		}
		User u = new User();
		BeanUtils.copyProperties(t, u);
		u.setLoginName(loginUser.getLoginName());
		u.setLoginAutoID(loginUser.getAutoID()); //登录表的AutoID
		if(t.getOrganization()!=null){
			u.setOrganizationId(t.getOrganization().getAutoID());
			u.setOrganizationName(t.getOrganization().getDepartmentName());
		}

		if (t.getRole() != null ) {

			u.setRoleIds(t.getRole().getAutoID());
			u.setRoleNames(t.getRole().getRoleName());
		}
		
		Set<TEFmanualCategory> s = t.getManualCategorys();
		if ((s != null) && !s.isEmpty()) {
			boolean b = false;
			String ids = "";
			String names = "";
			for(TEFmanualCategory tr:s)
			{
				if (b) {
					ids += ",";
					names += ",";
				} else {
					b = true;
				}
				ids+=tr.getAutoID();
				names+=tr.getCategoryName();
			}
			u.setManualIDs(ids);
			u.setManualNames(names);
				
		}
		
		return u;
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		// Author：yeyaowen 2016-8-9
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loginName", user.getLoginName());
		params.put("password", MD5Util.md5(user.getPassword()));
		TEFLoginUser t= userDao.get("from TEFLoginUser t where t.loginName = :loginName and t.password = :password", params);
		
		if (t != null && t.getPersonalInfo() != null) {
			User u = get(t.getPersonalInfo().getAutoID());
			return u;
		}
		return null;
	}
	//EFPersonalInfo t = userInfoDao.get("from EFPersonalInfo t  left join fetch t.role role where t.autoID = :autoID", params);
	@Override
	public List<String> resourceList(Long id) {
		// TODO Auto-generated method stub
		List<String> resourceList = new ArrayList<String>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("autoID", id);
		//System.out.println(id);
		TEFPersonalInfo t = userInfoDao.get(
				"from TEFPersonalInfo t join fetch t.role role join fetch role.resources resource where t.autoID = :autoID", params);
		if (t != null) {
			//System.out.println("test1");
			TEFRole roles = t.getRole();
			if ((roles != null)) {
				
					Set<TEFResource> resources = roles.getResources();
					if ((resources != null) && !resources.isEmpty()) {
						for (TEFResource resource : resources) {
							if ((resource != null) && (resource.getUrl() != null)) {
								resourceList.add(resource.getUrl());
							}
						}
					}
				
			}
			return resourceList;
		}
		return null;
		
	}

	@Override
	public boolean editUserPwd(SessionInfo sessionInfo, String oldPwd,
			String pwd) {
		// TODO Auto-generated method stub
		// Author：yeyaowen 2016-8-9
		TEFLoginUser u= userDao.get(TEFLoginUser.class, sessionInfo.getId());
		if(u.getPassword().equalsIgnoreCase(MD5Util.md5(oldPwd)))
		{
			u.setPassword(MD5Util.md5(pwd));
			return true;
		}
		return false;
	}
	
	@Override
	public boolean editUserPwd(long autoID,String oldPwd, String pwd) {
		// TODO Auto-generated method stub
		// Author：yeyaowen 2016-8-9
		TEFLoginUser u= userDao.get(TEFLoginUser.class, autoID);
		if(u.getPassword().equalsIgnoreCase(MD5Util.md5(oldPwd)))
		{
			u.setPassword(MD5Util.md5(pwd));
			return true;
		}
		return false;
	}

	@Override
	public User getByLoginName(User user) {
		// TODO Auto-generated method stub
		TEFLoginUser t= userDao.get("from TEFLoginUser t  where t.loginName = '"+user.getLoginName()+"'");
		User u =new User();
		if(t!=null){
			u = get(t.getPersonalInfo().getAutoID());
			//BeanUtils.copyProperties(t, u);
		}else{
			return null;
		}
		return u;
	}
	
	
	private String whereHql(User user, Map<String, Object> params) {
		// author:yeyaowen   暂未完成的方法
		String hql = "";
		if (user != null) {
			hql += " where 1=1 ";
			if (user.getRealName() != null) {
				hql += " and t.personalInfo.realName like :name";
				params.put("name", "%%" + user.getRealName() + "%%");
			}
			if(user.getOrganizationId()!=null){
				hql += " and t.personalInfo.organization.autoID ="+user.getOrganizationId();
			}
			if(user.getSearchKey()!=null&&user.getSearchValue()!=null)
			{
				hql +="and t.personalInfo."+user.getSearchKey()+" like :searchValue";
				params.put("searchValue","%%" + user.getSearchValue() + "%%");
			}
		}
		return hql;
	}

	private String orderHql(PageFilter ph) {
		String orderString = "";
		if ((ph.getSort() != null) && (ph.getOrder() != null)) {
			orderString = " order by t.personalInfo." + ph.getSort() + " " + ph.getOrder();
		}
		return orderString;
	}

	@Override
	public List<User> getAllExpert() {
		// TODO Auto-generated method stub
		List<User> ul = new ArrayList<User>();
		
		String hql = " from TEFPersonalInfo t where t.role.autoID=2";
		List<TEFPersonalInfo> l= userInfoDao.find(hql);
		if ((l != null) && (l.size() > 0)) {
		for(TEFPersonalInfo tp:l)
		{
			User u= get(tp.getAutoID());
			/*
			BeanUtils.copyProperties(tp, u);
			TEFRole role=tp.getRole();
			if(role!=null)
			{
				u.setRoleIds(role.getAutoID());
				u.setRoleNames(role.getRoleName());
			}
			if(tp.getOrganization()!=null)
			{
				u.setOrganizationId(tp.getOrganization().getAutoID());
				u.setOrganizationName(tp.getOrganization().getDepartmentName());
			}*/
			ul.add(u);
		}
		return ul;
		}
		
		return null;
	}

	@Override
	public void setManual(User user) {
		// TODO Auto-generated method stub
		TEFPersonalInfo t = userInfoDao.get(TEFPersonalInfo.class,user.getAutoID());
		if((user.getManualIDs()!=null)&&!user.getManualIDs().equals("")){
		
			String ids="";
			boolean b= false;
			for(String id:user.getManualIDs().split(",")){
				if(b){
					ids+=",";
				}else{
					b = true;
				}
				ids+=id;
			}
		
			t.setManualCategorys(new HashSet<TEFmanualCategory>(categoryDao.find("select distinct t from TEFmanualCategory t where t.autoID in("
			+ ids + ")")));
		}else{
			t.setManualCategorys(null);
		}
	}

}
