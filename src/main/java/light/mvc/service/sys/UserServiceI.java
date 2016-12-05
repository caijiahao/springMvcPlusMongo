package light.mvc.service.sys;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.SessionInfo;
import light.mvc.pageModel.sys.User;

import java.util.List;

/*
 * @author: yeyaowen 
 * 编写日期：2016-8-9
 * 特别注意：初步编写，尚未测试
 * */
public interface UserServiceI {

	public List<User> dataGrid(User user, PageFilter ph);
	
	public List<User> getAllExpert();

	public Long count(User user, PageFilter ph);

	public Long add(User user);

	public void delete(Long id);

	public void edit(User user);

	public User get(Long personalID);

	public User login(User user);

	public List<String> resourceList(Long id);

	public boolean editUserPwd(SessionInfo sessionInfo, String oldPwd, String pwd);
	
	public boolean editUserPwd(long autoID, String oldPwd, String pwd);

	public User getByLoginName(User user);
	
	public void setManual(User user);

}
