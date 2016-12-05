package light.mvc.service.sensor;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.sensor.Sensor;
import light.mvc.pageModel.sensor.Station;
import light.mvc.pageModel.sensor.monitoringNode;
import org.json.JSONArray;
import org.json.JSONException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface pestSensorServiceI {
	
	 public void add(Sensor data);
	  
	  public List<Sensor> dataGrid(Sensor data, PageFilter ph);
	  
	  public Long count(Sensor data, PageFilter ph);
	  
	  public void delete(Long id, HttpServletRequest request);
	  
	  public Sensor get(Long id);
	  
	  public void edit(Sensor data);
	  
	  public List<Object[]> getPredictChart(int moniNodeId, String startTime, String endTime);
	  
	  public void insertApi(JSONArray dataJson, Station station, List<monitoringNode> nodeList) throws JSONException;
	  
	  public Sensor getLastSensor(long serialNum);
}
