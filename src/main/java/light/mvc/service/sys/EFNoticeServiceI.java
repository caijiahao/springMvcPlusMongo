package light.mvc.service.sys;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.sys.EFNotice;

import java.util.List;


/*
 * @author: yeyaowen 
 * 编写日期：2016-8-9
 * 特别注意：初步编写，尚未测试
 * */
public interface EFNoticeServiceI {

    public List<EFNotice> dataGrid(EFNotice notice, PageFilter ph);

    public Long count(EFNotice notice, PageFilter ph);

    public Long add(EFNotice notice);

    public void delete(Long id);

    public void edit(EFNotice notice);

    public EFNotice get(Long id);

    public List<EFNotice> gethaveNotice(Long personalID);

    public List<EFNotice> gethaveNotNotice(Long personalID);

    public List<EFNotice> gethaveNotice(Long personalID, PageFilter ph);

    public List<EFNotice> gethaveNotNotice(Long personalID, PageFilter ph);

    public void setNotice(Long id);


}
