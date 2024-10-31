package tests.token;

import auth.TokenManager;
import base.BaseTest;
import org.junit.jupiter.api.Test;

/**
 * TokenGenerationTest verifies that tokens can be generated successfully
 * and that the BaseTest setup and tear down methods function as expected.
 */
public class TokenGenerationTest extends BaseTest {

    @Test
    public void testSupervisorTokenGeneration() {
        // Generate and print the Supervisor token
        String supervisorToken = TokenManager.getSupervisorToken();
        System.out.println("Generated Supervisor Token: " + supervisorToken);
    }

    @Test
    public void testOperatorTokenGeneration() {
        // Generate and print the Operator token
        String operatorToken = TokenManager.getOperatorToken();
        System.out.println("Generated Operator Token: " + operatorToken);
    }
}
