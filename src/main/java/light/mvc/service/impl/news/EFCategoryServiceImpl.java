package light.mvc.service.impl.news;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.news.TEFcategory;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.news.EFCategory;
import light.mvc.service.news.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EFCategoryServiceImpl implements EFCategoryServiceI{

	@Autowired
	private BaseDaoI<TEFcategory> categoryDao;
	
	@Override
	public void add(EFCategory m) {
		// TODO Auto-generated method stub
		TEFcategory t= new TEFcategory();
		BeanUtils.copyProperties(m,t);
		categoryDao.save(t);
	}

	@Override
	public List<EFCategory> dataGrid(EFCategory category, PageFilter ph) {
		// TODO Auto-generated method stub
		
		List<EFCategory> ul = new ArrayList<EFCategory>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TEFcategory t ";
		List<TEFcategory> l = categoryDao.find(hql + whereHql(category, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (TEFcategory t : l) {
			EFCategory u = new EFCategory();
			BeanUtils.copyProperties(t, u);
			ul.add(u);
		}
		return ul;
	}

	@Override
	public Long count(EFCategory category, PageFilter ph) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TEFcategory t ";
		return categoryDao.count("select count(*) " + hql + whereHql(category, params), params);
	}

	@Override
	public void delete(Long id, HttpServletRequest request) {
		// TODO Auto-generated method stub
		TEFcategory t= categoryDao.get(TEFcategory.class,id);
		if(t != null){
			t.setDeleted(1);
		}
		categoryDao.update(t);
	}

	@Override
	public EFCategory get(Long id) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TEFcategory t = categoryDao.get("from TEFcategory t  where t.deleted=0 and t.id = :id", params);
		EFCategory u = null;
		if(t != null)
		{
			u = new EFCategory();
			BeanUtils.copyProperties(t, u);
		}
		BeanUtils.copyProperties(t, u);
		return u;
	}

	private String orderHql(PageFilter ph) {
		String orderString = "";
		if ((ph.getSort() != null) && (ph.getOrder() != null)) {
			orderString = " order by t." + ph.getSort() + " " + ph.getOrder();
		}
		return orderString;
	}
	
	private String whereHql(EFCategory category, Map<String, Object> params) {//待修改
		String hql = " where t.deleted=0 ";
		if (category != null) {
			if(category.getCategoryName() != null){
				hql += " and t.categoryName like :categoryName";
				params.put("categoryName", "%" + category.getCategoryName() + "%");
			}
			if(category.getType() != null){
				hql += " and t.type = :type";
				params.put("type", category.getType());
			}
		}

		return hql;
	}

	@Override
	public void edit(EFCategory data) {
		// TODO Auto-generated method stub
		TEFcategory t = categoryDao.get(TEFcategory.class, data.getAutoID());
		BeanUtils.copyProperties(data, t);
		categoryDao.update(t);
	}

	@Override
	public List<EFCategory> getAllData(EFCategory c) {
		// TODO Auto-generated method stub
		List<EFCategory> categories = new ArrayList<EFCategory>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TEFcategory t ";
		List<TEFcategory> l = categoryDao.find(hql + whereHql(c,params), params);
		for(TEFcategory cFcategory : l){
			EFCategory category = new EFCategory();
			BeanUtils.copyProperties(cFcategory, category);
			categories.add(category);
		}
		return categories;
	}
		


}
