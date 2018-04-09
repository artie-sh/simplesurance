package sh.artie.simplesurance;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ApiClient {

    public static final String UNAUTHORIZED = "Bad credentials";
    public static final String NOT_FOUND = "Not Found";
    private RestTemplate restTemplate;
    private StrSubstitutor strSub;
    private EnvironmentParams envParams = new EnvironmentParams();

    public ApiClient() {
        restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new ErrorHandler());
    }

    public ResponseEntity getPayload(String targetUrl) {
        System.out.println("GET " + targetUrl);
        ResponseEntity response = restTemplate.getForEntity(targetUrl, String.class);
        System.out.println(new JSONObject(response.getBody().toString()).toString(4));
        return response;
    }

    public String getGitHubPullRequestUrl() {
        strSub = new StrSubstitutor(envParams.getEnvHashMap(), "{", "}");
        return strSub.replace(envParams.getBaseUrl());
    }

    public String getGitHubAuth() {
        return envParams.getAccessToken()!=null?"?access_token=" + envParams.getAccessToken():"";
    }

    public void setEnvParams(EnvironmentParams envParams) {
        this.envParams = envParams;
    }

}
