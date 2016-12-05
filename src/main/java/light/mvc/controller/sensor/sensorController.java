package light.mvc.controller.sensor;

import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.sensor.Sensor;
import light.mvc.pageModel.sensor.Station;
import light.mvc.service.sensor.monitoringNodeServiceI;
import light.mvc.service.sensor.pestSensorServiceI;
import light.mvc.service.sensor.stationServiceI;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/sensor")
public class sensorController {
	@Autowired
	private pestSensorServiceI Service;
	
	@Autowired
	private stationServiceI stationS;
	
	@Autowired
	private monitoringNodeServiceI moniService;
	
	@RequestMapping("/getpage")
	public String getPage() {
		return "/eumode/sensor/sensordata";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public  Grid dataGrid(Sensor data, PageFilter ph,HttpServletRequest request) {
//		spring自动将参数注入到ph对象中
		Grid grid = new Grid();
	//	List<monitoringNode> moniNodeList = null;
		if(data.getStation()!=null&&data.getStation()!=0)
		{			
			Station s = stationS.get(data.getStation());
			data.setSerialNum(s.getSerialNum());
	//		monitoringNode node =new monitoringNode();
	//		node.setStation(data.getStation());
	//		 moniNodeList =moniService.dataGrid(node, ph);
			
		}
	//	request.setAttribute("moniNodeList", moniNodeList);
		grid.setRows(Service.dataGrid(data, ph));
		grid.setTotal(Service.count(data, ph));
		return grid;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String ids,HttpServletRequest request) {
		JSONArray jsonIds;
		Json j = new Json();
		try {
			jsonIds = new JSONArray(ids);
			for (int i = 0; i < jsonIds.length(); i++) {
				long id = jsonIds.getLong(i);
				Service.delete(id,request);
			}
			
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/eumode/news/newsAdd";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Json add(Sensor nobj) {
		Json j = new Json();
			try {
				Service.add(nobj);
				j.setSuccess(true);
				j.setMsg("添加成功！");
			} catch (Exception e) {
				j.setMsg(e.getMessage());
			}
		
		return j;
	}
	
	
	@RequestMapping("/getSearchPage")
	public String getSearchPage() {
		return "/eumode/sensor/sensorSearch";
	}
	
	
	@RequestMapping("/search")
	@ResponseBody
	public Json search(Object nobj) {
		Json j = new Json();
			try {
				//Service.add(nobj);
				j.setSuccess(true);
				j.setMsg("添加成功！");
			} catch (Exception e) {
				j.setMsg(e.getMessage());
			}
		
		return j;
	}
}
