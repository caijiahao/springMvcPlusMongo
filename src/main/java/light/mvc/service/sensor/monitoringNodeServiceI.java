package light.mvc.service.sensor;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.sensor.monitoringNode;

import java.util.List;

public interface monitoringNodeServiceI {

    public monitoringNode add(monitoringNode node);

    public List<monitoringNode> dataGrid(monitoringNode node, PageFilter ph);

    public Long count(monitoringNode node, PageFilter ph);

    public void delete(Long id);

    public monitoringNode get(Long id);

    public void edit(monitoringNode node);
}
