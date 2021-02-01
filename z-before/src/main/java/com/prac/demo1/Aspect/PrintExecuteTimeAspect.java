package com.prac.demo1.Aspect;/**
 * @Auther: liweizhi
 * @Date: 2019/1/29 14:39
 * @Description:
 */

import com.prac.demo1.annotation.ExecuteTime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Description 打印方法的执行时间
 * @Author liweizhi
 * Date 2019/1/29 下午2:39
 */
@Aspect
@Order(-1)//优先级
@Component
@Slf4j
public class PrintExecuteTimeAspect {

    @Around("@annotation(executeTime)")
    public void restoreDataSource(ProceedingJoinPoint pjp, ExecuteTime executeTime) {
        long startTime = System.currentTimeMillis();
        try {
            pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        log.info("{},{},{},{} 执行耗时:{}毫秒", pjp.getSourceLocation(), pjp.getSignature(), pjp.getTarget(), executeTime.value(), (endTime - startTime));
    }
}
