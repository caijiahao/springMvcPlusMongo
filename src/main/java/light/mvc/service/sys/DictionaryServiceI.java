package light.mvc.service.sys;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.sys.Dictionary;

import java.util.List;

/*
 * @author: yeyaowen 
 * 编写日期：2016-8-9
 * 特别注意：初步编写，尚未测试
 * */
public interface DictionaryServiceI {

	public List<Dictionary> dataGrid(Dictionary dictionary, PageFilter ph);

	public Long count(Dictionary dictionary, PageFilter ph);

	public void add(Dictionary dictionary);

	public void delete(Long id);

	public void edit(Dictionary dictionary);

	public Dictionary get(Long id);

	public List<Dictionary> combox(String code);
	
	public List<Dictionary> getDictionaryByCategoryCode(String code);
	
	public List<Dictionary> getDictionaryByCategoryID(Long id);

	public Dictionary checkUnique(Dictionary dictionary);
	
	public Dictionary getDefaultDictionary(String categoryCode);
	
	public Dictionary getDictionary(String categoryCode, String contentCode);

}

