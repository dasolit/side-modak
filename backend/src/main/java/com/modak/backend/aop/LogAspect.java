package com.modak.backend.aop;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LogAspect {
  Logger log = LoggerFactory.getLogger(this.getClass().getName());

  @Pointcut("execution(* com.modak.backend.contoller.*Controller.*(..))")
  public void controller() {}

  @Around("controller()")
  public Object writeLogControllerRequest(ProceedingJoinPoint joinPoint) throws Throwable {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

    String controllerName = joinPoint.getSignature().getDeclaringType().getName();
    String methodName = joinPoint.getSignature().getName();
    Map<String, Object> params = new HashMap<>();
    try {
      String decodedURI = URLDecoder.decode(request.getRequestURI(), "UTF-8");
      params.put("controller", controllerName);
      params.put("method", methodName);
      params.put("params", getParams(request));
      params.put("log_time", System.currentTimeMillis());
      params.put("request_uri", decodedURI);
      params.put("http_method", request.getMethod());
    } catch (Exception e) {
      log.error("LoggerAspect error", e.getMessage());
    }
    log.info("[{}] [{}] [{}.{}] [{}]",
        params.get("http_method"),
        params.get("request_uri"),
        params.get("controller"),params.get("method"),
        params.get("params"));
    return joinPoint.proceed();
  }

  private static JSONObject getParams(HttpServletRequest request) {
    JSONObject jsonObject = new JSONObject();
    Enumeration<String> params = request.getParameterNames();
    while (params.hasMoreElements()) {
      String param = params.nextElement();
      String replaceParam = param.replaceAll("\\.", "-");
      jsonObject.put(replaceParam, request.getParameter(param));
    }
    return jsonObject;
  }
}
