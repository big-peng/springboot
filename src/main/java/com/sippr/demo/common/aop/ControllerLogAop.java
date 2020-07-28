package com.sippr.demo.common.aop;

import com.sippr.demo.util.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenXiangpeng
 */
@Aspect
@Component
@Slf4j
public class ControllerLogAop {
    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    /**
     * 定义一个切入点
     */
    @Pointcut("execution(public * com.sippr.demo.modules..controller..*.*(..))")
    public void executeController() {
    }

    @Before("executeController()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = joinPoint.getArgs();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        startTime.set(System.currentTimeMillis());

        log.info("[" + startTime.get() + "]---------------接收到请求，记录请求内容------------");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);

        String ipAddr = IPUtils.getIpAddr(request);
        String url = request.getRequestURL().toString();
        String httpMeth = request.getMethod();
        String classFn = joinPoint.getSignature().getName();
        String classMeth = joinPoint.getSignature().getDeclaringTypeName() + "." + classFn;

        //这一步获取到的方法有可能是代理方法也有可能是真实方法
        Method m = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //判断代理对象本身是否是连接点所在的目标对象，不是的话就要通过反射重新获取真实方法
        if (joinPoint.getThis().getClass() != joinPoint.getTarget().getClass()) {
//            m = ReflectUtil.getMethod(joinPoint.getTarget().getClass(), m.getName(), m.getParameterTypes());
        }
        //通过真实方法获取该方法的参数名称
        LocalVariableTableParameterNameDiscoverer paramNames = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = paramNames.getParameterNames(m);
        //获取连接点方法运行时的入参列表
        Object[] args = joinPoint.getArgs();
        //将参数名称与入参值一一对应起来
        Map<String, Object> params = new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            if (args[i] == null || args[i].getClass().toString().contains("HttpServletResponse")
                    || args[i].getClass().toString().contains("HttpServletRequest")
                    || args[i].getClass().toString().contains("MultipartHttpServletRequest")
                    || args[i].getClass().toString().contains("HttpSession")
                    || args[i].getClass().toString().contains("BindingResult") || args[i].getClass().toString().contains("CommonsMultipartFile")
                    || args[i].getClass().toString().contains("CommonsMultipartResolver")) {
                continue;
            }
            params.put(parameterNames[i], args[i]);
        }
        //String strParam = params.size() > 0 ? new JSONObject(params).toString() : mapParam.toString();

        log.info("[" + startTime.get() + "]URL          : " + url);
        log.info("[" + startTime.get() + "]HTTP_METHOD  : " + httpMeth);
        log.info("[" + startTime.get() + "]IP           : " + ipAddr);
        log.info("[" + startTime.get() + "]CLASS_METHOD : " + classMeth);
        //log.info("[" + startTime.get() + "]REQ_PARAM    : " + strParam);
    }

    @AfterReturning("executeController()")
    public void doAfterReturning(JoinPoint joinPoint) {
        String classFn = joinPoint.getSignature().getName();
        String classMeth = joinPoint.getSignature().getDeclaringTypeName() + "." + classFn;
        log.info("[" + startTime.get() + "]ARGS : " + Arrays.toString(joinPoint.getArgs()));

        log.info("[" + startTime.get() + "]处理请求 {} 耗时 {} 毫秒", classMeth, (System.currentTimeMillis() - startTime.get()));
    }
}
