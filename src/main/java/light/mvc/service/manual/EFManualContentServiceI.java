package light.mvc.service.manual;

import light.mvc.model.manual.TEFmanualContent;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.manual.EFmanualContent;

import java.util.List;

public interface EFManualContentServiceI {
    public Long add(EFmanualContent category);

    public List<EFmanualContent> dataGrid(EFmanualContent category, PageFilter ph);

    public List<TEFmanualContent> getAllData();

    public Long count(EFmanualContent category, PageFilter ph);

    public void delete(Long id);

    public EFmanualContent get(Long id);

    public void edit(EFmanualContent data);

    public List<EFmanualContent> getBYCategoryCode(String categoryCode);

    public List<EFmanualContent> getBYCategoryCode(String categoryCode, PageFilter ph);

    public List<EFmanualContent> getBYCategoryCode(String categoryCode, PageFilter ph, String searchKey);
}
