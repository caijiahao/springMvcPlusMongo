package light.mvc.service.manual;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.manual.EFExpertList;
import light.mvc.pageModel.manual.EFmanualCategory;
import light.mvc.pageModel.manual.EFmanualCategoryList;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EFManualCategoryServiceI {
	public void add(EFmanualCategory category);
	
	public List<EFmanualCategory> dataGrid(EFmanualCategory category, PageFilter ph);
	
	public List<EFmanualCategory> treeGrid(EFmanualCategory category);
	
	public List<EFmanualCategory> getAllData();
	
	public Long count(EFmanualCategory category, PageFilter ph);
		
	public void delete(Long id, HttpServletRequest request);

	public EFmanualCategory get(Long id);	
	
	public EFmanualCategory getByCode(String code);	
	
	public EFmanualCategory getExpertAndChild(Long id);	
	
	public EFmanualCategoryList getChild(Long id);	
	
	public List<EFmanualCategory> getByPid(Long id);	
	
	public EFExpertList  getExpertList(Long id);	
	
	public void edit(EFmanualCategory data);
	
	public List<Tree> tree();
	
	public List<Tree> allTree();
	
	public List<Tree> getTreeByCode(String categoryCode);
}
