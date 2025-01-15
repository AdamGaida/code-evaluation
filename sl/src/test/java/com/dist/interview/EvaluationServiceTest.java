package com.dist.interview;

import com.dist.interview.sl.EvaluationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EvaluationServiceTest {

    @Mock
    private OllamaChatModel chatModel;
    @InjectMocks
    private EvaluationService evaluationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEvaluateProblem() {
        String problem = "Problem description here.";
        String expectedOutput = "This is the evaluation result.";

        ChatResponse mockResponse = mock(ChatResponse.class);
        when(mockResponse.getResult().getOutput().getContent()).thenReturn(expectedOutput);
        when(chatModel.call(any(Prompt.class))).thenReturn(mockResponse);

        String result = evaluationService.evaluateProblem(problem);

        verify(chatModel, times(1)).call(any(Prompt.class));
        assert result.equals(expectedOutput);
    }

    @Test
    void testSaveEvaluation() {

    }

    /*@Test
    //na9es lenna
    void testWakeUpAgent() throws StaleProxyException {
        String problem = "";
        String solution = "";

        Runtime mockRuntime = mock(Runtime.class);
        AgentContainer mockContainer = mock(AgentContainer.class);
        AgentController mockAgentController = mock(AgentController.class);

        when(Runtime.instance()).thenReturn(mockRuntime);
        when(mockRuntime.createMainContainer(any())).thenReturn(mockContainer);
        when(mockContainer.createNewAgent(anyString(), anyString(), any())).thenReturn(mockAgentController);

        evaluationService.wakeUpAgent(problem, solution);

        verify(mockContainer, times(1)).createNewAgent("EvaluationAgent", "com.dist.interview.sl.EvaluationAgent", new Object[]{problem, solution});
        verify(mockAgentController, times(1)).start();
    }*/

    @Test
    void testPrompt() {
        String problem = "Describe a problem";
        String code = "Code that solves the problem";

        String expectedPrompt = "Here is a problem statement:\n" +
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

        String result = evaluationService.prompt(problem, code);

        assert result.equals(expectedPrompt);
    }
}
