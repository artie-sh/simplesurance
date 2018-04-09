package sh.artie.simplesurance;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class ErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        boolean hasError = false;
        int rawStatusCode = response.getStatusCode().value();
        if (rawStatusCode != 200){
            hasError = true;
        }
        return hasError;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {}
}
