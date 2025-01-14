package com.dist.interview.pl.controllers;

import lombok.Data;

@Data
public  class EvaluationRequest {
    private Long useId;
    private String problem;
    private String code;

    }
