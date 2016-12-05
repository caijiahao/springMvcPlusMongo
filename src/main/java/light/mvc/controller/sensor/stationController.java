package light.mvc.controller.sensor;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sensor.Station;
import light.mvc.service.base.ServiceException;
import light.mvc.service.sensor.stationServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/station")
public class stationController extends BaseController{
	
	@Autowired
	private stationServiceI stationS;
	
	
	@RequestMapping("/getPage")
	public String getPage() {
		return "/eumode/station/station";
	}

	@RequestMapping("/treeGrid")
	@ResponseBody
	public List<Station> treeGrid()
	{
		return stationS.treeGrid();
	}
	
	@RequestMapping("/tree")
	@ResponseBody
	public List<Tree> tree(HttpSession session) {
		return stationS.tree();
	}
	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/eumode/station/stationAdd";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Json add(Station station) {
		Json j = new Json();
		try {
			stationS.add(station);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/get")
	@ResponseBody
	public Station get(Long id) {
		return stationS.get(id);
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request,Long id) {
		Station o = stationS.get(id);
		request.setAttribute("station", o);
		return "/eumode/station/pestMoniNodeEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Station org) throws InterruptedException {
		Json j = new Json();
		try {
			stationS.edit(org);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Long id) {
		Json j = new Json();
		try {
			stationS.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (ServiceException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
}
