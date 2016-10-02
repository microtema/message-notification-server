package de.seven.fate.rest.interceptor;

import de.seven.fate.message.enums.UserName;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import static de.seven.fate.util.ClassUtil.getIndexOfParameter;

@Aspect
@Component
public class UserNameInterceptor {


    // @Around("execution(* de.seven.fate.message.resource.MessageResource.getMassages(..))")
    // @Around("execution(* de.seven.fate..resource.*.*(..))")
    @Around("execution(* de.seven.fate.message.resource.MessageResource.*(..))")
    public Object intercept(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature)proceedingJoinPoint.getSignature();

        Object[] args = proceedingJoinPoint.getArgs();

        int indexOfParameter = getIndexOfParameter(signature.getMethod().getParameterAnnotations(), UserName.class);

        if(indexOfParameter != -1){
            args[indexOfParameter] = getUserName();
        }

        return proceedingJoinPoint.proceed(args);
    }

    private Object getUserName() {

       // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "mtema";
    }
}
