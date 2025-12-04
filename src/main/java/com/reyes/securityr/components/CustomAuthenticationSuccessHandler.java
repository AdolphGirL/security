package com.reyes.securityr.components;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/** 複寫登入成功後的處理 */

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	/***/
	private final RequestCache requestCache = new HttpSessionRequestCache();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		/** 若是 AJAX / Fetch（通常 X-Requested-With 或 Accept: application/json） */
		String xRequestedWith = request.getHeader("X-Requested-With");
		String accept = request.getHeader("Accept") == null ? "" : request.getHeader("Accept");
		
		if ("XMLHttpRequest".equalsIgnoreCase(xRequestedWith) || accept.contains("application/json")) {
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("application/json;charset=UTF-8");
			String username = authentication.getName();
			
			/** 回傳簡單 JSON，可根據需求擴充（roles, token, redirectUrl, ...）*/
			String json = String.format("{\"status\":\"ok\",\"username\":\"%s\"}", username);
			response.getOutputStream().write(json.getBytes(StandardCharsets.UTF_8));
			response.getOutputStream().flush();
			return;
		}
		
		/** 一般 HTML 存取（瀏覽器表單） 先嘗試從 RequestCache 取回被攔截的原始請求（SavedRequest），若有則 redirect 回去 */
		SavedRequest saved = requestCache.getRequest(request, response);
		if (saved != null) {
			String targetUrl = saved.getRedirectUrl();
			response.sendRedirect(targetUrl);
			return;
		}
		
		/** 若無 saved request，則 redirect 到預設首頁或 forward（依需求） */
		response.sendRedirect(request.getContextPath() + "/index");
		
	}

}
