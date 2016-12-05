package light.mvc.controller.sensor;

import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.sensor.monitoringNode;
import light.mvc.service.sensor.monitoringNodeServiceI;
import light.mvc.service.sensor.pestDataServiceI;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/pestMoniNode")
public class moniNodeController {
	@Autowired
	private monitoringNodeServiceI moniService;
	
	@Autowired
	private pestDataServiceI pestDataService;
	
	@RequestMapping("/getPage")
	public String getPage() {
		return "/eumode/MoniNode/MoniNodeList";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public  Grid dataGrid(monitoringNode data, PageFilter ph) {
//		spring自动将参数注入到ph对象中
		
		Grid grid = new Grid();
		grid.setRows(moniService.dataGrid(data, ph));
		grid.setTotal(moniService.count(data, ph));
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
				moniService.delete(id);
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
		return "/eumode/MoniNode/MoniNodeAdd";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Json add(monitoringNode nobj) {
		Json j = new Json();
			try {
				moniService.add(nobj);
				j.setSuccess(true);
				j.setMsg("添加成功！");
			} catch (Exception e) {
				j.setMsg(e.getMessage());
			}
		
		return j;
	}
	
	/*
	@RequestMapping("/getMoniNodeInfo")
	public String getNodeInfo(HttpServletResponse response,HttpServletRequest request)
	{
		long nid=Integer.parseInt(request.getParameter("idString"));
		monitoringNode n=moniService.get(nid);
		request.setAttribute("moniNode_info", n);
		List<pestData> data= pestDataService.getAlldataFromNodeId(nid);
		request.setAttribute("node_data", data);
		return "/admin/pestMoniNode/MoniNodeInfo";
	}
	
	@RequestMapping("/getMoniNodeLocation")
	public void getNodeLocation(HttpServletResponse response) throws JSONException//暂时使用该方法
	{
		List<monitoringNode> moniN = moniService.getAllData();
		PrintWriter out = null;
		response.setContentType("text/json");
		 response.setCharacterEncoding("UTF-8"); 
		JSONArray jsonArray = new JSONArray();

		
		
		for(int i=0;i<moniN.size();i++)
		{
			JSONObject obj = new JSONObject();
	
			monitoringNode n1=(monitoringNode)moniN.get(i);
			obj.put("location_x", n1.getLocationX());
			obj.put("location_y", n1.getLocationY());
			obj.put("moninodeId", n1.getAutoID());
			obj.put("moniNodeName", n1.getName());
			jsonArray.put(obj);

		}
	
		String nodeJson=jsonArray.toString();
		try {
			out = response.getWriter();
			out.write(nodeJson);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	*/
	@RequestMapping("/getSearchPage")
	public String getSearchPage() {
		return "/eumode/pestMoniNode/pestMoniSearch";
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
