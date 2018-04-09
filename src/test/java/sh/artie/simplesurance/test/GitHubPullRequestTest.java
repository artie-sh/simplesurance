package sh.artie.simplesurance.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import sh.artie.simplesurance.ApiClient;
import sh.artie.simplesurance.EnvironmentParams;

import static junit.framework.Assert.assertTrue;

public class GitHubPullRequestTest extends BaseTest {

    @Test
    public void githubPullRequestValidTest() {
        response = apiClient.getPayload(apiClient.getGitHubPullRequestUrl() + apiClient.getGitHubAuth());
        assertResponseStatus(response, HttpStatus.OK);

        jsonResponse = getJsonFromResponse(response);

        String responseUrlActual = jsonResponse.getString("url");
        String isMergeable = jsonResponse.get("mergeable").toString();
        String requestUrlExpected = apiClient.getGitHubPullRequestUrl();

        assertTrue("Some of the essential fields seem to be missing",
                jsonResponse.has("created_at") &&
                            jsonResponse.has("head") &&
                            jsonResponse.has("user") &&
                            jsonResponse.has("base") &&
                            jsonResponse.has("id"));
        assertTrue(String.format("Urls mismatch: %s actual vs. %s expected", responseUrlActual, requestUrlExpected),
                responseUrlActual.equals(requestUrlExpected));


        assertTrue(String.format("Mergeable field broken: %s actual", isMergeable),
                isMergeable.equals("true") ||
                        isMergeable.equals("false") ||
                        isMergeable.equals("null"));
    }

    @Test
    public void githubPullRequestInvalidUserTest() {
        EnvironmentParams envParams = new EnvironmentParams();
        envParams.setUserName(RandomStringUtils.randomAlphabetic(8));
        apiClient.setEnvParams(envParams);
        response = apiClient.getPayload(apiClient.getGitHubPullRequestUrl() + apiClient.getGitHubAuth());
        jsonResponse = getJsonFromResponse(response);

        assertResponseStatus(response, HttpStatus.NOT_FOUND);
        String message = jsonResponse.getString("message");
        assertTrue(String.format("Message mismach: %s expected vs. %s actual", ApiClient.NOT_FOUND, message), message.equals(ApiClient.NOT_FOUND));
    }

    @Test
    public void githubPullRequestInvalidAccessTokenTest() {
        EnvironmentParams envParams = new EnvironmentParams();
        envParams.setAccessToken(RandomStringUtils.randomAlphabetic(40));
        apiClient.setEnvParams(envParams);

        response = apiClient.getPayload(apiClient.getGitHubPullRequestUrl() + apiClient.getGitHubAuth());
        jsonResponse = getJsonFromResponse(response);

        assertResponseStatus(response, HttpStatus.UNAUTHORIZED);
        String messaage = jsonResponse.getString("message");
        assertTrue(String.format("Message mismach: %s expected vs. %s actual", ApiClient.UNAUTHORIZED, messaage), messaage.equals(ApiClient.UNAUTHORIZED));
    }
}
