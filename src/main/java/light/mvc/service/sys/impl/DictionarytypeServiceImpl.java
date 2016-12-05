package light.mvc.service.sys.impl;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.sys.TEFDictionaryCategory;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sys.Dictionarytype;
import light.mvc.service.sys.DictionarytypeServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DictionarytypeServiceImpl implements DictionarytypeServiceI {

	@Autowired
	private BaseDaoI<TEFDictionaryCategory> dictionarytypeDao;

	@Override
	public void add(Dictionarytype r) {
		TEFDictionaryCategory t = new TEFDictionaryCategory();
		t.setName(r.getName());
		t.setSeq(r.getSeq());
		t.setDescription(r.getDescription());
		dictionarytypeDao.save(t);
	}

	@Override
	public void delete(Long id) {
		TEFDictionaryCategory t = dictionarytypeDao.get(TEFDictionaryCategory.class, id);
		dictionarytypeDao.delete(t);
	}

	@Override
	public void edit(Dictionarytype r) {
		TEFDictionaryCategory t = dictionarytypeDao.get(TEFDictionaryCategory.class, r.getId());
		t.setDescription(r.getDescription());
		t.setName(r.getName());
		t.setSeq(r.getSeq());
		dictionarytypeDao.update(t);
	}

	@Override
	public Dictionarytype get(Long id) {
		TEFDictionaryCategory t = dictionarytypeDao.get(TEFDictionaryCategory.class, id);
		Dictionarytype r = new Dictionarytype();
		r.setDescription(t.getDescription());
		r.setId(t.getAutoID());
		r.setName(t.getName());
		r.setSeq(t.getSeq());
		return r;
	}

	@Override
	public List<Tree> tree() {
		List<TEFDictionaryCategory> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = dictionarytypeDao.find("select distinct t from TEFDictionaryCategory t order by t.seq");

		if ((l != null) && (l.size() > 0)) {
			for (TEFDictionaryCategory r : l) {
				Tree tree = new Tree();
				tree.setId(r.getAutoID().toString());
				if (r.getDictionaryCategory() != null) {
					tree.setPid(r.getDictionaryCategory().getAutoID().toString());
					tree.setIconCls("icon_folder");
				}else{
					tree.setIconCls("icon_company");
				}
				tree.setText(r.getName());
				lt.add(tree);
			}
		}
		return lt;
	}


}
