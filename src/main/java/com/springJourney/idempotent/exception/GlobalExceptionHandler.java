package com.springJourney.idempotent.exception;

import com.springJourney.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {"com.springJourney.idempotent"}) // 指定要处理的包路径下的controller
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理幂等性异常
     * @param e 幂等性异常
     * @return 统一响应结果
     */
    @ExceptionHandler(IdempotentException.class)
    public Result<Void> handleIdempotentException(IdempotentException e) {
        log.error("幂等性异常: {}", e.getMessage(), e);
        return Result.fail(400, e.getMessage());
    }

    /**
     * 处理运行时异常
     * @param e 运行时异常
     * @return 统一响应结果
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<Void> handleRuntimeException(RuntimeException e) {
        log.error("运行时异常: {}", e.getMessage(), e);
        return Result.fail(500, e.getMessage());
    }

    /**
     * 处理其他异常
     * @param e 异常
     * @return 统一响应结果
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("系统异常: {}", e.getMessage(), e);
        return Result.fail(500, e.getMessage());
    }


}