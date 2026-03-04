package lambda.apis;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lambda.dto.Order;


public class CreateOrderLambda {

	public APIGatewayProxyResponseEvent createOrder(APIGatewayProxyRequestEvent requestEvent) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.readValue(requestEvent.getBody(), Order.class);
		
		
		return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("Order Created Successfully of Order ID : "+order.id);
		
	}
	
}
