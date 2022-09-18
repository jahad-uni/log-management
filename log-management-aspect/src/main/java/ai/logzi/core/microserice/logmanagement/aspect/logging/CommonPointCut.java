package ai.logzi.core.microserice.logmanagement.aspect.logging;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointCut {
    @Pointcut("execution(* ai.logzi.core.microservice.logmanagement.api.controller.LogPipelineController.*(..)))")
    public void logPipelineControllerLog(){}

    /*
        we want to consider all the methods within the classes defined in package com.example and subpackage it
     */
    @Pointcut("within(ai.logzi..*)")
    public void logPipelineControllerLog3(){}
}
