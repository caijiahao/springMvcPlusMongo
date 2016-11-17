package light.mvc.service.sys;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sys.Role;

import java.util.List;

/*
 * @author: yeyaowen 
 * 编写日期：2016-8-9
 * 特别注意：初步编写，尚未测试
 * */
public interface RoleServiceI {

    public List<Role> dataGrid(Role role, PageFilter ph);

    public Long count(Role role, PageFilter ph);

    public void add(Role role);

    public void delete(Long id);

    public void edit(Role role);

    public Role get(Long id);

    public void grant(Role role);

    public List<Tree> tree();

}
