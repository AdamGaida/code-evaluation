package com.dist.interview.sl;

import com.dist.interview.slapi.EvaluationServiceInterface;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import org.springframework.beans.factory.annotation.Autowired;


public class StorageAgent extends Agent {

    @Autowired
    private EvaluationServiceInterface evaluationService;

    protected void setup() {
        addBehaviour(new StoreBehaviour());
    }

    private class StoreBehaviour extends CyclicBehaviour {
        public void action() {
            ACLMessage msg = receive();
            if (msg != null) {
                String[] data = msg.getContent().split(";");
                if (data.length == 3) {
                    evaluationService.saveEvaluation(data[0]);
                }
            } else {
                block();
            }
        }
    }
}
