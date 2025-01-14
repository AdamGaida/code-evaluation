package com.dist.interview.slapi;

public interface EvaluationServiceInterface {

    String evaluateProblem(String prompt);
    void saveEvaluation(String score);
    String prompt(String problem,String Solution);
    void wakeUpAgent(String problem,String Solution) throws jade.wrapper.StaleProxyException;
}