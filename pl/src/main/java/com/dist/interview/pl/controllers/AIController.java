package com.dist.interview.pl.controllers;
import com.dist.interview.sl.EvaluationAgent;
import com.dist.interview.slapi.EvaluationServiceInterface;
import jade.wrapper.StaleProxyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AIController {
    @Autowired
    private EvaluationServiceInterface serviceInterface;

    @GetMapping ("/generate")
    public ResponseEntity<String> ask(@RequestBody EvaluationRequest request) throws StaleProxyException {
        serviceInterface.wakeUpAgent(
                       request.getProblem(),
                       request.getCode()
       );
        return ResponseEntity.ok(EvaluationAgent.score);
    }
}
