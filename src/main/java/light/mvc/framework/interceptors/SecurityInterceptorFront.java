package light.mvc.framework.interceptors;

import light.mvc.framework.constant.GlobalConstant;
import light.mvc.pageModel.base.SessionInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 权限拦截器
 * 
 */
public class SecurityInterceptorFront implements HandlerInterceptor {

	private List<String> excludeUrls;// 不需要拦截的资源

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	/**
	 * 完成页面的render后调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) throws Exception {

	}

	/**
	 * 在调用controller具体方法后拦截
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
			ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在调用controller具体方法前拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		SessionInfo sessionInfoExpert = (SessionInfo) request.getSession().getAttribute(GlobalConstant.SESSION_INFO_EXPERT);
		SessionInfo sessionInfoMember = (SessionInfo) request.getSession().getAttribute(GlobalConstant.SESSION_INFO_MEMBER);
		
		if (excludeUrls.contains(url)|| (url.indexOf("/baseUtil/") > -1) || ((url.indexOf("/front") > -1)&&(url.indexOf("/base") > -1))||url.indexOf("/mobile/")>-1) {// 如果要访问的资源是不需要验证的
			return true;
		}

		
		if (((sessionInfoExpert == null) || (sessionInfoExpert.getId() == null))&&((sessionInfoMember == null) || (sessionInfoMember.getId() == null))) {// 如果没有登录或登录超时
			request.getRequestDispatcher("/WEB-INF/views//front/login.jsp").forward(request, response);
			return false;
		}

		return true;
	}
}
