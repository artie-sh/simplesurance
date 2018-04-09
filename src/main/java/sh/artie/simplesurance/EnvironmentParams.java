package sh.artie.simplesurance;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentParams {

    private String baseUrl = "https://api.github.com/repos/{userName}/{repoName}/pulls/{pullRequestNumber}";
    private String accessToken;
    private Map<String, String> envParams;

    public EnvironmentParams() {
        envParams = new HashMap<>();
        envParams.put("userName", System.getProperty("userName", "artie-sh"));
        envParams.put("repoName", System.getProperty("repoName", "toro_tests"));
        envParams.put("pullRequestNumber", System.getProperty("pullRequestNumber", "1"));
        accessToken = System.getProperty("accessToken", "07f04fc4e95ba04d194a99c17da5f48b4f9503f2");
    }

    public String getBaseUrl() { return baseUrl; }
    public String getAccessToken() { return accessToken; }
    public Map<String, String> getEnvHashMap() { return envParams; }

    public void setUserName(String userName) {
        envParams.put("userName", userName);
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
