package light.mvc.service.sensor;

import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sensor.Station;

import java.util.Date;
import java.util.List;


public interface stationServiceI {

	
	public List<Station> treeGrid();

	public void add(Station station);

	public void delete(Long id);

	public void edit(Station station);

	public Station get(Long id);

	public List<Tree> tree();
	
	public List<Station> getAuditPath(Long stationId);
	
	public void update(Station station);
	
	public void updateSensorLastTime(Date lastDate, Long stationId);
}
