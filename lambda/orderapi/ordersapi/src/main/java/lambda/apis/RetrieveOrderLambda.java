package lambda.apis;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lambda.dto.Order;

public class RetrieveOrderLambda {

    public APIGatewayProxyResponseEvent RetrieveOrder(APIGatewayProxyRequestEvent requestEvent) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Order order = new Order(123, "Mac Book", 102);
        String jsonOutput = objectMapper.writeValueAsString(order);



        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonOutput);

    }

}
