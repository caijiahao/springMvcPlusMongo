package light.mvc.service.impl.manual;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.manual.TEFmanualContent;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.manual.EFmanualContent;
import light.mvc.service.manual.EFManualContentServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class EFManualContentServiceImpl implements EFManualContentServiceI {

	@Autowired
	private BaseDaoI<TEFmanualContent> manualDao;
	
	@Override
	public Long add(EFmanualContent m) {
		// TODO Auto-generated method stub
		TEFmanualContent t = new TEFmanualContent();
		BeanUtils.copyProperties(m,t);
		manualDao.save(t);
		return t.getAutoID();
	}

	@Override
	public List<EFmanualContent> dataGrid(EFmanualContent news, PageFilter ph) {
		// TODO Auto-generated method stub
		
		List<EFmanualContent> ul = new ArrayList<EFmanualContent>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TEFmanualContent t ";
		List<TEFmanualContent> l = manualDao.find(hql + whereHql(news, params) + orderHql(ph), params, ph.getPage(), ph.getRows());

		for (TEFmanualContent t : l) {
			EFmanualContent u = new EFmanualContent();
			BeanUtils.copyProperties(t, u);
			//eed.setAsText(u.getContent());
			//u.setContent(eed.getAsText());

			ul.add(u);
		}
		return ul;
	}

	@Override
	public Long count(EFmanualContent news, PageFilter ph) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TEFmanualContent t ";
		return manualDao.count("select count(*) " + hql + whereHql(news, params), params);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		TEFmanualContent t= manualDao.get(TEFmanualContent.class,id);
		t.setDeleted(1);
		t.setUpdateDate(new Date());
		manualDao.update(t);
	}

	@Override
	public EFmanualContent get(Long id) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TEFmanualContent t = manualDao.get("from TEFmanualContent t  where t.autoID = :id", params);
		EFmanualContent u = new EFmanualContent();
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
	
	private String whereHql(EFmanualContent news, Map<String, Object> params) {//待修改
		String hql = "";
		if (news != null) {
			hql += " where t.deleted=0 ";
			if(news.getCategoryCode() != null && news.getCategoryCode().length() > 0){
				hql += " and t.categoryCode like :categoryCode";
				params.put("categoryCode", news.getCategoryCode()+"%%");
			}
			if(news.getTitle() != null && news.getTitle().length() > 0){
				hql += " and t.title like :searchKey";
				params.put("searchKey", "%%"+news.getTitle()+"%%");
			}
			if(news.getManualCategoryID()!=null)
			{
				hql += " and t.manualCategoryID = :manualCategoryID ";
				params.put("manualCategoryID", news.getManualCategoryID());
			}
		}

		return hql;
	}

	@Override
	public void edit(EFmanualContent data) {
		// TODO Auto-generated method stub
		TEFmanualContent t=manualDao.get(TEFmanualContent.class,data.getAutoID());
		t.setCategoryCode(data.getCategoryCode());
		t.setTitle(data.getTitle());
		t.setContent(data.getContent());
		t.setManualCategoryID(data.getManualCategoryID());
		manualDao.update(t);
	}

	@Override
	public List<TEFmanualContent> getAllData() {
		// TODO Auto-generated method stub
		String hql = " from TEFmanualContent t ";
		List<TEFmanualContent> l=manualDao.find(hql);
		
		return l;
	}

	@Override
	public List<EFmanualContent> getBYCategoryCode(String categoryCode) {
		// TODO Auto-generated method stub
		List<EFmanualContent> ul = new ArrayList<EFmanualContent>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("categoryCode", categoryCode+"%%");
		String hql = " from TEFmanualContent t where t.categoryCode like :categoryCode";
		List<TEFmanualContent> l = manualDao.find(hql);
		
		for (TEFmanualContent t : l) {
			EFmanualContent u = new EFmanualContent();
			BeanUtils.copyProperties(t, u);
			String theString =t.getContent().replace("&lt;br /&gt;", "<br/>");
			theString=this.HtmlDecode(theString);
			u.setContent(theString);


			ul.add(u);
		}
		return ul;
	}

	@Override
	public List<EFmanualContent> getBYCategoryCode(String categoryCode,
			PageFilter ph) {
		// TODO Auto-generated method stub
		List<EFmanualContent> ul = new ArrayList<EFmanualContent>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("categoryCode", categoryCode+"%%");
		String hql = " from TEFmanualContent t where t.categoryCode like :categoryCode";
		List<TEFmanualContent> l = manualDao.find(hql+ orderHql(ph), params, ph.getPage(), ph.getRows());
		
		for (TEFmanualContent t : l) {
			EFmanualContent u = new EFmanualContent();
			BeanUtils.copyProperties(t, u);
			String theString =t.getContent().replace("&lt;br /&gt;", "<br/>");
			theString=this.HtmlDecode(theString);
			u.setContent(theString);

			ul.add(u);
		}
		return ul;
	}

	@Override
	public List<EFmanualContent> getBYCategoryCode(String categoryCode,
			PageFilter ph, String searchKey) {
		// TODO Auto-generated method stub
		List<EFmanualContent> ul = new ArrayList<EFmanualContent>();
		Map<String, Object> params = new HashMap<String, Object>();

		String hql = " from TEFmanualContent t";
		EFmanualContent search = new EFmanualContent();
		if(categoryCode != null && categoryCode.length() > 0){
			search.setCategoryCode(categoryCode);
		}
		if(searchKey != null && searchKey.length() > 0){
			search.setTitle(searchKey);
		}
		List<TEFmanualContent> l = manualDao.find(hql + whereHql(search, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
        
		for (TEFmanualContent t : l) {
			EFmanualContent u = new EFmanualContent();
			BeanUtils.copyProperties(t, u);
			String theString =t.getContent().replace("&lt;br /&gt;", "<br/>");
			theString=this.HtmlDecode(theString);
			u.setContent(theString);

			ul.add(u);
		}
		return ul;
	}
	
	public String HtmlEncode(String theString)
	{
		   theString=theString.replace(">", "&gt;");

		   theString=theString.replace("<", "&lt;");

		   theString=theString.replace(" ", "&nbsp;");

		   theString=theString.replace("/", "&quot;");

		   theString=theString.replace("/n", "<br/> ");

		   return theString;
	}
	
	public String HtmlDecode(String theString)
	{
		   theString=theString.replace("&gt;", ">");

		   theString=theString.replace("&lt;", "<");

		   theString=theString.replace("&nbsp;", " ");
		   
		   theString=theString.replace("&amp;nbsp;", " ");
		   return theString;
	}

	

		
}
