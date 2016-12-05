package light.mvc.service.sys;

import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sys.Dictionarytype;

import java.util.List;

/*
 * @author: yeyaowen 
 * 编写日期：2016-8-9
 * 特别注意：初步编写，尚未测试
 * */
public interface DictionarytypeServiceI {


	public void add(Dictionarytype dictionarytype);

	public void delete(Long id);

	public void edit(Dictionarytype dictionarytype);

	public Dictionarytype get(Long id);

	public List<Tree> tree();


}
