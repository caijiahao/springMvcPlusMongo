package light.mvc.controller.sensor;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.sensor.pestData;
import light.mvc.service.sensor.pestDataServiceI;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pestData")
public class pestDataController extends BaseController{

	@Autowired
	private pestDataServiceI pestDataService;
	
	@RequestMapping("/getPage")
	public String getPage() {
		return "/eumode/pestData/pestdata";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public  Grid dataGrid(pestData data, PageFilter ph) {
//		spring自动将参数注入到ph对象中
		Grid grid = new Grid();
		grid.setRows(pestDataService.dataGrid(data, ph));
		grid.setTotal(pestDataService.count(data, ph));
		return grid;
	}
	
	/*
	@RequestMapping("/pestSensordataGrid")
	@ResponseBody
	public  Grid pestSensordataGrid(pestDataWithSensor data, PageFilter ph) {
//		spring自动将参数注入到ph对象中
		Grid grid = new Grid();
		grid.setRows(Service.PestSensorDataGrid(data, ph));
		grid.setTotal(Service.count());
		return grid;
	}
	*/
	
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String ids) {
		JSONArray jsonIds;
		Json j = new Json();
		try {
			jsonIds = new JSONArray(ids);
			for (int i = 0; i < jsonIds.length(); i++) {
				long id = jsonIds.getLong(i);
				pestDataService.delete(id);
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
		return "/eumode/pestData/pestdataAdd";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Json add(pestData nobj) {
		Json j = new Json();
			try {
				pestDataService.add(nobj);
				j.setSuccess(true);
				j.setMsg("添加成功！");
			} catch (Exception e) {
				j.setMsg(e.getMessage());
			}
		
		return j;
	}
	
	@RequestMapping("/getSearchPage")
	public String getSearchPage() {
		return "/eumode/pestData/pestdataSearch";
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
