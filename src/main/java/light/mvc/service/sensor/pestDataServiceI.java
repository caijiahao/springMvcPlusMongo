package light.mvc.service.sensor;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.sensor.pestData;

import java.util.List;

public interface pestDataServiceI {

    public pestData add(pestData u);

    public List<pestData> dataGrid(pestData u, PageFilter ph);

    public Long count(pestData u, PageFilter ph);

    public void delete(Long id);

    public pestData get(Long id);

    public void edit(pestData u);

}
