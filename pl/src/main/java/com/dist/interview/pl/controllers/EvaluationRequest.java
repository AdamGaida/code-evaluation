package com.dist.interview.pl.controllers;

import lombok.Data;

@Data
public  class EvaluationRequest {
    private String problem;
    private String code;

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
