package light.mvc.service.news;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.news.EFCategory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EFCategoryServiceI {
	
	public void add(EFCategory category);
	
	public List<EFCategory> dataGrid(EFCategory category, PageFilter ph);
	
	public List<EFCategory> getAllData(EFCategory c);
	
	public Long count(EFCategory category, PageFilter ph);
		
	public void delete(Long id, HttpServletRequest request);

	public EFCategory get(Long id);	
	
	public void edit(EFCategory data);
	
	

}
