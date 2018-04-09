package sh.artie.simplesurance.test;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import sh.artie.simplesurance.ApiClient;
import static junit.framework.Assert.assertTrue;

public class BaseTest {

    protected ApiClient apiClient = new ApiClient();
    protected ResponseEntity response;
    protected JSONObject jsonResponse;

    protected JSONObject getJsonFromResponse(ResponseEntity response) {
        JSONObject jsonResponse = null;
        try {
            jsonResponse = new JSONObject(response.getBody().toString());
        } catch (JSONException e) {
            System.out.println("JSONException!");
            e.printStackTrace();
        }
        assertTrue("Getting JSON from response failed", jsonResponse != null);
        return jsonResponse;
    }

    protected void assertResponseStatus(ResponseEntity response, HttpStatus expected) {
        assertTrue(String.format("HttpStatus mismatch: %s expected vs. %s actual", expected, response.getStatusCode()), response.getStatusCode().equals(expected));
    }
}
