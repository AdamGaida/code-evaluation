package com.dist.interview.pl.controllers;
//import com.dist.interview.sl.AICallService;
//import com.dist.interview.sl.Prompt;
import com.dist.interview.slapi.EvaluationServiceInterface;
import jade.wrapper.StaleProxyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIController {
    @Autowired
    private EvaluationServiceInterface serviceInterface;

    @GetMapping("/api/v1/generate")
    public void ask(@RequestBody EvaluationRequest request) throws StaleProxyException {
        serviceInterface.wakeUpAgent(
                       request.getProblem(),
                       request.getCode()
       );
    }
}
