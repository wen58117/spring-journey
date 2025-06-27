package com.springJourney.eventListening.entity;

import lombok.Data;

@Data
public class SysLogVo {
    private String className;
    private String methodName;
    private String requestParam;
    private String responseParam;
    private String title;
}
