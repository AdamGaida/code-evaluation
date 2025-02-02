package com.dist.interview.sl;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import org.springframework.beans.factory.annotation.Autowired;


public class EvaluationAgent extends Agent {
    public static String score;
   @Autowired
    private EvaluationService evaluationService;

    protected void setup() {
        addBehaviour(new EvaluateBehaviour());
    }

    private class EvaluateBehaviour extends OneShotBehaviour {
        public void action() {
            Object[] args = getArguments();
            String problem = args[0].toString();
            String solution = args[1].toString();

            String score = evaluationService.evaluateProblem(evaluationService.prompt(problem, solution));
            EvaluationAgent.score=score;
            /*sendMessageToStorageAgent(score);*/
        }

    }

    private void sendMessageToStorageAgent(String score) {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new jade.core.AID("StorageAgent", jade.core.AID.ISLOCALNAME));
        msg.setContent(score);
        send(msg);
    }
}
