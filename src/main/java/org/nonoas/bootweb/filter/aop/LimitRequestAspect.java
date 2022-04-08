package org.nonoas.bootweb.filter.aop;

import org.nonoas.bootweb.common.error.BusinessException;
import org.nonoas.bootweb.common.error.ErrorEnum;
import org.nonoas.bootweb.common.restful.IReturnType;
import org.nonoas.bootweb.utils.HttpUtil;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 访问频率切面拦截
 *
 * @author Nonoas
 * @datetime 2022/4/3 22:22
 */
@Aspect
@Component
public class LimitRequestAspect {

    private static final ConcurrentHashMap<String, ExpiringMap<String, Integer>> book = new ConcurrentHashMap<>();

    // 定义切点
    // 让所有有@LimitRequest注解的方法都执行切面方法
    @Pointcut("@annotation(limitRequest)")
    public void executeService(LimitRequest limitRequest) {
    }

    @Around(value = "executeService(limitRequest)", argNames = "pjp,limitRequest")
    public IReturnType doAround(ProceedingJoinPoint pjp, LimitRequest limitRequest) throws Throwable {

        // 获得request对象
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Optional.ofNullable(sra)
                .orElseThrow(() -> new BusinessException(ErrorEnum.UNKNOWN_ERROR))
                .getRequest();

        String ip = HttpUtil.getIpByRequest(request);
        String uri = request.getRequestURI();

        ExpiringMap<String, Integer> uc = book.getOrDefault(uri, ExpiringMap.builder().variableExpiration().build());
        int uCount = uc.getOrDefault(ip, 0);
        // 超过次数，不执行目标方法
        if (uCount >= limitRequest.count()) {
            return ErrorEnum.FREQUENT_REQUEST;
        }
        // 第一次请求时，设置有效时间
        else if (0 == uCount) {
            uc.put(request.getRemoteAddr(), uCount + 1, ExpirationPolicy.CREATED, limitRequest.time(), TimeUnit.MILLISECONDS);
        }
        // 未超过次数， 记录加一
        else {
            uc.put(request.getRemoteAddr(), uCount + 1);
        }

        book.put(request.getRequestURI(), uc);
        // 被拦截方法的返回值
        return (IReturnType) pjp.proceed();
    }


}