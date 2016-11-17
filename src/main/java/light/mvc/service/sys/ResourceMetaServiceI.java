package light.mvc.service.sys;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.sys.EFResourceMeta;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ResourceMetaServiceI {
    public List<EFResourceMeta> dataGrid(EFResourceMeta resourceMeta);

    public List<EFResourceMeta> dataGrid(EFResourceMeta resourceMeta, PageFilter ph);

    public Long count(EFResourceMeta resourceMeta);

    public Long add(EFResourceMeta resourceMeta);

    public void delete(Long id);

    public void edit(EFResourceMeta resourceMeta);

    public EFResourceMeta get(Long id);

    public List<EFResourceMeta> get(Long metaID, Long typeID);

    public Map<Date, Integer> getDateCount(Long metaID, Long typeID);
}
