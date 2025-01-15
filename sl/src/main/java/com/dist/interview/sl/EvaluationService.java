package com.dist.interview.sl;

import com.dist.interview.slapi.EvaluationServiceInterface;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.StaleProxyException;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EvaluationService implements EvaluationServiceInterface {
    @Autowired
    private OllamaChatModel chatModel;

    @Override
    public String evaluateProblem(String prompt) {
        ChatResponse response= chatModel.call(
                new Prompt(
                        prompt,
                        OllamaOptions.create().withModel("llama2").withTemperature(0.6)
                )


        );
        return response.getResult().getOutput().getContent();
    }

    @Override
    public void saveEvaluation(String score) {
    }
    @Override
    public void wakeUpAgent(String problem,String solution) throws StaleProxyException {
        Object[] args = new Object[2];
        args[0] = problem;
        args[1] = solution;
        Runtime rt = Runtime.instance();
        Profile profile = new ProfileImpl();
        AgentContainer container = rt.createMainContainer(profile);
        /*AgentController storageAgent = container.createNewAgent("StorageAgent", "com.dist.interview.sl.StorageAgent", null);*/
        AgentController evaluationAgent = container.createNewAgent("EvaluationAgent", "com.dist.interview.sl.EvaluationAgent", args);


        /*storageAgent.start();*/
        evaluationAgent.start();

    }

    @Override
    public String prompt(String problem, String code) {
        String prompt = "Here is a problem statement:\n" +
                problem + "\n\n" +
                "Here is the provided solution code:\n" +
                code + "\n\n" +
                "Please evaluate the code against the following criteria:\n" +
                "- Correctness: Does the code solve the problem as described?\n" +
                "- Efficiency: Is the code optimized and efficient in its approach?\n" +
                "- Standards: Does the code follow standard coding practices?\n" +
                "- Robustness: Does the code handle edge cases effectively?\n" +
                "- Readability: Is the code well-structured and easy to understand?\n\n" +
                "Each matched criterion is worth 2 points. Return only the final score as a single integer (between 0 and 10) with no explanation.\n";

        return prompt;
    }

}
