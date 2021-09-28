package com.sterp.multitenant.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

public class CustomInterceptor implements HandlerInterceptor {

//	@Autowired
//	IRolesService rolesService;

//	@Autowired
//	ISubModuleService subModuleService;

	@Autowired
	HttpSession session;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("************************************");
//		String URL = request.getRequestURI();
//		session.removeAttribute("clickedSubMenu");
//		session.removeAttribute("parentURL");
//		if (!URL.contains("login") && !URL.contains("accessdenied") && !URL.contains("error") && !URL.contains("kcc-ui")
//				&& !URL.contains("admin")) {
//			String[] url = URL.split("/");
//			if (url.length >= 3) {
//				URL = url[2];
//			}
//
//			Modules module = null;
//			if (subModuleService != null) {
//				SubModules submodule = this.subModuleService.getByPath(URL);
//				if (submodule != null) {
//					module = submodule.getModule();
//					request.setAttribute("clickedSubMenu", URL);
//					request.setAttribute("parentURL", module.getTitle());
//					session.setAttribute("clickedSubMenu", URL);
//					session.setAttribute("parentURL", module.getTitle());
//				}
//			}
//			return true;
//		} else if (!URL.contains("login") && !URL.contains("accessdenied") && !URL.contains("error")
//				&& !URL.contains("kcc-ui") && URL.contains("admin")) {
//			String mainURL = URL;
//			String[] url = URL.split("/");
//			if (url.length >= 3) {
//				URL = url[2];
//			}
//			request.setAttribute("clickedSubMenu", URL);
//			request.setAttribute("parentURL", mainURL);
//			session.setAttribute("clickedSubMenu", URL);
//			session.setAttribute("parentURL", mainURL);
//		}
 		return true;
	}
}
