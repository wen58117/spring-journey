package com.springJourney.idempotent.aspect;

import com.springJourney.idempotent.annotation.Idempotent;
import com.springJourney.idempotent.exception.IdempotentException;
import com.springJourney.idempotent.redis.MockRedisTemplate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class IdempotentAspect {

//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private MockRedisTemplate mockRedisTemplate; //模拟redis，实际使用时替换为真实的redisTemplate

    private final ExpressionParser parser = new SpelExpressionParser();

    @Around("@annotation(idempotent)")
    public Object around(ProceedingJoinPoint point, Idempotent idempotent) throws Throwable {
        // 获取方法签名
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        // 获取方法参数名和参数值
        String[] parameterNames = signature.getParameterNames();
        Object[] args = point.getArgs();

        // 创建 SpEL 上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }

        // 解析 SpEL 表达式获取幂等 key
        String spelExpression = idempotent.key();
        String idempotentKey = parser.parseExpression(spelExpression).getValue(context, String.class);

        if (idempotentKey == null || idempotentKey.isEmpty()) {
            throw new IdempotentException("幂等 key 不能为空");
        }

        // 检查幂等 key 是否存在
        if (mockRedisTemplate.hasKey(idempotentKey)) {
            throw new IdempotentException("请勿重复提交");
        }

        // 设置幂等 key 到模拟 Redis
        mockRedisTemplate.opsForValueSet(idempotentKey, "1", idempotent.expireTime(), TimeUnit.SECONDS);

        try {
            // 执行目标方法
            return point.proceed();
        } catch (Exception e) {
            // 发生异常时删除幂等 key
            mockRedisTemplate.delete(idempotentKey);
            throw new IdempotentException("出现异常");
        }
    }
}
