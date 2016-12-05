package light.mvc.pageModel.manual;

import light.mvc.pageModel.sys.User;

import java.util.List;

public class EFExpertList implements java.io.Serializable{
	private List<User>	list;

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}
	

}
