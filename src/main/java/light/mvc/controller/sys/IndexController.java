package light.mvc.controller.sys;

import light.mvc.controller.base.BaseController;
import light.mvc.framework.constant.GlobalConstant;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.SessionInfo;
import light.mvc.pageModel.sys.User;
import light.mvc.service.sys.ResourceServiceI;
import light.mvc.service.sys.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class IndexController extends BaseController {

    @Autowired
    private UserServiceI userService;

    @Autowired
    private ResourceServiceI resourceService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(GlobalConstant.SESSION_INFO_ADMIN);
        request.setAttribute("sessionInfo", sessionInfo);
        if ((sessionInfo != null) && (sessionInfo.getId() != null)) {
            return "/eumode/index";
        }
        request.setAttribute("originUrl", "/admin/index");
        return "/eumode/login";
    }

    @ResponseBody
    @RequestMapping("/login")
    public Json login(User user, HttpSession session) {
        Json j = new Json();
        User sysuser = userService.login(user);
        if (sysuser != null) {
            j.setSuccess(true);
            j.setMsg("登陆成功！");

            SessionInfo sessionInfo = new SessionInfo();
            sessionInfo.setId(sysuser.getAutoID());
            sessionInfo.setLoginname(sysuser.getLoginName());
            sessionInfo.setName(sysuser.getRealName());
            // user.setIp(IpUtil.getIpAddr(getRequest()));
            sessionInfo.setResourceList(userService.resourceList(sysuser.getAutoID()));
            sessionInfo.setResourceAllList(resourceService.resourceAllList());
            session.setAttribute(GlobalConstant.SESSION_INFO_ADMIN, sessionInfo);
            sessionInfo.setSessionId(session.getId());
//			获取管理员的role类型name属性，回传给客户端进行分类，这里我们认为每个管理员的role类型都只有一个
            sessionInfo.setRoleType(sysuser.getRoleNames());
            j.setObj(sessionInfo);
        } else {
            j.setMsg("用户名或密码错误！");
        }
        return j;
    }

    @ResponseBody
    @RequestMapping("/logout")
    public Json logout(HttpSession session) {
        Json j = new Json();
        if (session != null) {
            session.invalidate();
        }
        j.setSuccess(true);
        j.setMsg("注销成功！");
        return j;
    }

}
