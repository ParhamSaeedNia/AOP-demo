package ir.sematec;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Date;

@Component
@Aspect
public class EmployeeCRUDAspect {



    //@Around("execution(* ir.sematec.*.getEmployeeById(..))")
    @Around("execution(* ir.sematec.*.*(..))")
    public Object userAdvice( ProceedingJoinPoint joinPoint) throws Throwable{
        StopWatch watch = new StopWatch();
        watch.start();
        Object result = joinPoint.proceed();
        watch.stop();
        System.out.println("@Around: call took-"+ watch.getTotalTimeMillis()+" ms");
        return result;

//        System.out.println("@Around: Before calculation-"+ new Date());
//        joinPoint.proceed();
//        System.out.println("@Around: After calculation-"+ new Date());
    }

    @Before("execution(* ir.sematec.*.getEmployeeById(..))")
    public void logBeforeV1(JoinPoint joinPoint)
    {
        System.out.println("EmployeeCRUDAspect.logBeforeV1() : " + joinPoint.getSignature().getName());
    }

    @Before("execution(* ir.sematec.*.*(..))")
    public void logBeforeV2(JoinPoint joinPoint)
    {
        System.out.println("EmployeeCRUDAspect.logBeforeV2() : " + joinPoint.getSignature().getName());

        System.out.println(joinPoint.getArgs()[0]);
    }
//
    @After("execution(* ir.sematec.*.*(..))")
    public void logAfterV1(JoinPoint joinPoint)
    {
        System.out.println("EmployeeCRUDAspect.logAfterV1() : " + joinPoint.getSignature().getName());
    }

//    @After("execution(* EmployeeManager.*(..))")
//    public void logAfterV2( JoinPoint joinPoint)
//    {
//        System.out.println("EmployeeCRUDAspect.logAfterV2() : " + joinPoint.getSignature().getName());
//    }
}
