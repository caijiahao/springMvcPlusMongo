package light.mvc.service.manual;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.manual.EFmanualKeyword;

import java.util.List;

public interface EFManualKeywordServiceI {


	public List<EFmanualKeyword> dataGrid(EFmanualKeyword keyword, PageFilter ph);
	
	public Long count(EFmanualKeyword keyword, PageFilter ph);
	
	public void delete(Long id);
	
	public EFmanualKeyword get(Long id);
	
	public void edit(EFmanualKeyword keyword);
	
	public void add(EFmanualKeyword keyword);
	
	public List<EFmanualKeyword> getAllKeyword(EFmanualKeyword keyword);
	
}
