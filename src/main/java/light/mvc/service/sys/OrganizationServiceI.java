package light.mvc.service.sys;

import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sys.Organization;

import java.util.List;

/*
 * @author: yeyaowen 
 * 编写日期：2016-8-9
 * 特别注意：初步编写，尚未测试
 * */
public interface OrganizationServiceI {

	public List<Organization> treeGrid();

	public void add(Organization organization);

	public void delete(Long id);

	public void edit(Organization organization);

	public Organization get(Long id);

	public List<Tree> tree();
	
	public List<Organization> getAuditPath(Long organizationId);
}
