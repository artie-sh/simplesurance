package sh.artie.simplesurance;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentParams {

    private String baseUrl = "https://api.github.com/repos/{gitHubUserName}/{gitHubRepoName}/pulls/{gutHubPullRequestNumber}";
    private Map<String, String> envParams;

    public EnvironmentParams() {
        envParams = new HashMap<>();
        envParams.put("gitHubUserName", System.getProperty("gitHubUserName", "artie-sh"));
        envParams.put("gitHubRepoName", System.getProperty("gitHubRepoName", "toro_tests"));
        envParams.put("gutHubPullRequestNumber", System.getProperty("gutHubPullRequestNumber", "1"));
    }

    public String getBaseUrl() { return baseUrl; }
    public Map<String, String> getEnvHashMap() { return envParams; }
}
