package com.dist.interview.infra;

import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;

@Configuration
public class AgentConfig {

    @Bean
    public void startAgents() throws StaleProxyException {
        Runtime rt = Runtime.instance();
        Profile profile = new ProfileImpl();
        AgentContainer container = rt.createMainContainer(profile);

        AgentController evaluationAgent = container.createNewAgent("EvaluationAgent", "com.dist.interview.infra.EvaluationAgent", null);
        AgentController storageAgent = container.createNewAgent("StorageAgent", "com.dist.interview.infra.StorageAgent", null);

        evaluationAgent.start();
        storageAgent.start();
    }
}

