package light.mvc.service.impl.manual;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.manual.TEFmanualKeyword;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.manual.EFmanualKeyword;
import light.mvc.service.manual.EFManualKeywordServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EFManualKeywordServiceImpl implements
		EFManualKeywordServiceI {

	@Autowired
	private BaseDaoI<TEFmanualKeyword> keywordDao;
	
	@Override
	public List<EFmanualKeyword> dataGrid(EFmanualKeyword keyword, PageFilter ph) {
		// TODO Auto-generated method stub
		List<EFmanualKeyword> ul = new ArrayList<EFmanualKeyword>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TEFmanualKeyword t ";
		List<TEFmanualKeyword> l= keywordDao.find(hql +whereHql(keyword, params)+ orderHql(ph), params, ph.getPage(), ph.getRows());
		if ((l != null) && (l.size() > 0)) {
			for(TEFmanualKeyword t:l)
			{
				EFmanualKeyword u= new EFmanualKeyword();
				BeanUtils.copyProperties(t, u);
				ul.add(u);
				
			}
		}
		return ul;
	}

	private String orderHql(PageFilter ph) {
		String orderString = "";
		if ((ph.getSort() != null) && (ph.getOrder() != null)) {
			orderString = " order by t." + ph.getSort() + " " + ph.getOrder();
		}
		return orderString;
	}
	
	private String whereHql(EFmanualKeyword keyword, Map<String, Object> params) {//待修改
		String hql = "";
		if (keyword != null) {
			hql += " where t.deleted=0 ";
			if(keyword.getManualContentID() != null && keyword.getManualContentID() > 0){
				hql += " and t.manualContentID=:manualContentID";
				params.put("manualContentID", keyword.getManualContentID());
			}
			if(keyword.getCategoryID() != null && keyword.getCategoryID() > 0){
				hql += " and t.categoryID=:categoryID";
				params.put("categoryID", keyword.getCategoryID());
			}
			if(keyword.getKeyword() != null && keyword.getKeyword().length() > 0){
				hql += " and t.keyword like :keyword";
				params.put("keyword", "%%" + keyword.getKeyword() + "%%");
			}
			if(keyword.getCategoryCode() != null && keyword.getCategoryCode().length() > 0){
				hql += " and t.categoryCode like :categoryCode";
				params.put("categoryCode", keyword.getCategoryCode() + "%%");
			}
		}

		return hql;
	}
	@Override
	public Long count(EFmanualKeyword keyword, PageFilter ph) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TEFmanualKeyword t ";
		return keywordDao.count("select count(*) " + hql + whereHql(keyword, params), params);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		TEFmanualKeyword t = keywordDao.get(TEFmanualKeyword.class,id);
		t.setDeleted(1);
		t.setUpdateDate(new Date());
		keywordDao.update(t);
	}

	@Override
	public EFmanualKeyword get(Long id) {
		// TODO Auto-generated method stub
		TEFmanualKeyword t = keywordDao.get(TEFmanualKeyword.class,id);
		EFmanualKeyword u= new EFmanualKeyword();
		BeanUtils.copyProperties(t, u);
		return u;
	}

	@Override
	public void edit(EFmanualKeyword keyword) {
		// TODO Auto-generated method stub
		TEFmanualKeyword t = keywordDao.get(TEFmanualKeyword.class,keyword.getAutoID());
		BeanUtils.copyProperties(keyword, t);
		t.setUpdateDate(new Date());
		keywordDao.update(t);

	}

	@Override
	public void add(EFmanualKeyword keyword) {
		// TODO Auto-generated method stub
		TEFmanualKeyword t = new TEFmanualKeyword();
		BeanUtils.copyProperties(keyword, t);
		t.setCreateDate(new Date());
		t.setUpdateDate(new Date());
		keywordDao.save(t);

	}
	
	@Override
	public List<EFmanualKeyword> getAllKeyword(EFmanualKeyword keyword){
		List<EFmanualKeyword> ul = new ArrayList<EFmanualKeyword>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TEFmanualKeyword t ";
		List<TEFmanualKeyword> l= keywordDao.find(hql + whereHql(keyword, params) + " order by t.keyword asc", params);
		if ((l != null) && (l.size() > 0)) {
			for(TEFmanualKeyword t:l)
			{
				EFmanualKeyword u= new EFmanualKeyword();
				BeanUtils.copyProperties(t, u);
				ul.add(u);
			}
		}
		return ul;
	}

}
