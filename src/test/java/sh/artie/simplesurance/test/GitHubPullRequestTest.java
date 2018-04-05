package sh.artie.simplesurance.test;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import sh.artie.simplesurance.ApiClient;
import static junit.framework.Assert.assertTrue;

public class GitHubPullRequestTest extends BaseTest {

    @Test
    public void githubPullResuestTest() {
        response = apiClient.getPayload(apiClient.getGitHubPullRequestUrl());
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
}
