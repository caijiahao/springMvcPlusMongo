package light.mvc.service.news;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.news.EFNews;

import java.util.List;

public interface EFNewsServiceI {
	
	public Long add(EFNews xxnews);
	
	public List<EFNews> dataGrid(EFNews news, PageFilter ph);
	
	public Long count(EFNews news, PageFilter ph);
		
	public void delete(Long id);

	public EFNews get(Long id);	

	public void edit(EFNews data);
	
	public void publish(EFNews news);
	
	public void unPublish(EFNews news);
	
	public void updateReadCount(Long id);
}
