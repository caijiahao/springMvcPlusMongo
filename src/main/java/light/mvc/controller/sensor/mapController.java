package light.mvc.controller.sensor;

import light.mvc.controller.base.BaseController;
import light.mvc.service.sys.ResourceServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/map")
public class mapController extends BaseController{

	@Autowired
	private ResourceServiceI resourceService;
	

	

}
