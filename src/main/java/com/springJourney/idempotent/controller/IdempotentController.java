package com.springJourney.idempotent.controller;

import com.springJourney.common.Result;
import com.springJourney.idempotent.annotation.Idempotent;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class IdempotentController {

    @Idempotent(key = "#input['idempotentId']", expireTime = 4)
    @RequestMapping("/idempotent")
    public Result<String> idempotent(@RequestBody Map<String, String> input) {
        return Result.success("idempotent调用成功" + input.get("idempotentId"));
    }
}
