package light.mvc.service.impl.news;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.news.TEFnews;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.news.EFNews;
import light.mvc.service.news.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

@Service
public class EFNewsServiceImpl implements EFNewsServiceI{

	@Autowired
	private BaseDaoI<TEFnews> newsDao;
	
	@Override
	public Long add(EFNews m) {
		// TODO Auto-generated method stub
		TEFnews t = new TEFnews();
		BeanUtils.copyProperties(m,t);
		Serializable id = newsDao.save(t);
		return (Long)id;
	}

	@Override
	public List<EFNews> dataGrid(EFNews news, PageFilter ph) {
		// TODO Auto-generated method stub
		
		List<EFNews> ul = new ArrayList<EFNews>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " select t.AutoID,t.PersonalID,t.Author,t.Title,t.Content,t.WebPath,t.PublishDate,t.ReadCount,t.Status,t.CategoryID from newscontent t ";
		if(news.getCategoryTypeID() != null){
			hql += " join newscategory c on t.categoryID=c.AutoID and c.type=:categoryType ";
			params.put("categoryType", news.getCategoryTypeID());
		}
	 	List<Object[]> l = newsDao.findBySql(hql + whereHql(news, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
	 	//List<TEFnews> l = newsDao.find(hql + whereHql(news, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
	 	for (Object[] obj : l) {
			EFNews u = new EFNews();			
			u.setAutoID(Long.parseLong(obj[0].toString()));
			u.setPersonalID(Long.parseLong(obj[1].toString()));
			u.setAuthor((String)obj[2]);
			u.setTitle((String)obj[3]);
			u.setContent((String)obj[4]);
			u.setWebPath((String)obj[5]);
			u.setPublishDate((Date)obj[6]);
			u.setReadCount(Integer.parseInt(obj[7].toString()));
			u.setStatus(Long.parseLong(obj[8].toString()));
			u.setCategoryID(Long.parseLong(obj[9].toString()));
			//BeanUtils.copyProperties(t, u);
			ul.add(u);
		}
		return ul;
	}

	@Override
	public Long count(EFNews news, PageFilter ph) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		
		String hql = " select count(t.autoid) from newscontent t ";
		if(news.getCategoryTypeID() != null){
			hql += " join newscategory c on t.categoryID=c.AutoID and c.type=:categoryType ";
			params.put("categoryType", news.getCategoryTypeID());
		}
		hql += whereHql(news, params);
		return newsDao.countBySql(hql, params).longValue();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		TEFnews t= newsDao.get(TEFnews.class,id);
		if(t != null){
			t.setDeleted(1);
		}
		newsDao.update(t);
	}

	@Override
	public EFNews get(Long id) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TEFnews t = newsDao.get("from TEFnews t  where t.deleted=0 and t.id = :id", params);
		EFNews u = null;
		if(t != null)
		{
			u = new EFNews();
			BeanUtils.copyProperties(t, u);
		}
		return u;
	}

	private String orderHql(PageFilter ph) {
		String orderString = "";
		if ((ph.getSort() != null) && (ph.getOrder() != null)) {
			orderString = " order by t." + ph.getSort() + " " + ph.getOrder();
		}
		return orderString;
	}
	
	private String whereHql(EFNews news, Map<String, Object> params) {//待修改
		String hql = " where t.deleted=0 ";
		if (news != null) {
			if(news.getStatus() != null){
				hql += " and t.status=:status ";
				params.put("status", news.getStatus());
			}
			if(news.getCategoryID() != null){
				hql += " and t.categoryID=:categoryID ";
				params.put("categoryID", news.getCategoryID());
			}
			if(news.getTitle() != null){
				hql += " and t.title like :title ";
				params.put("title", "%" + news.getTitle() + "%");
			}
			if(news.getPersonalID() != null){
				hql += " and t.personalID=:personalID ";
				params.put("personalID", news.getPersonalID());
			}
			if(news.getSearchStartDate() != null){
				hql += " and t.publishDate >= :searchStDate ";
				params.put("searchStDate", news.getSearchStartDate());
			}
			if(news.getSearchEndDate() != null){
				hql += " and t.publishDate < :searchEndDate ";
				params.put("searchEndDate", news.getSearchEndDate());
			}
		}

		return hql;
	}

	@Override
	public void edit(EFNews news) {
		TEFnews t = newsDao.get(TEFnews.class, news.getAutoID());
		t.setTitle(news.getTitle());
		t.setContent(news.getContent());
		t.setCategoryID(news.getCategoryID());
		newsDao.update(t);
	}
	
	@Override
	public void publish(EFNews news){
		TEFnews t = newsDao.get(TEFnews.class, news.getAutoID());
		t.setWebPath(news.getWebPath());
		t.setStatus(news.getStatus());
		t.setPublishDate(new Date());
		newsDao.update(t);
	}
	
	@Override
	public void unPublish(EFNews news){
		TEFnews t = newsDao.get(TEFnews.class, news.getAutoID());
		t.setPublishDate(null);
		t.setStatus(news.getStatus());
		newsDao.update(t);
	}

	@Override
	public void updateReadCount(Long id) {
		String hql = "update TEFnews set readCount = readCount + 1 where autoID = :autoID";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("autoID", id);
		newsDao.executeHql(hql, params);
	}
}
