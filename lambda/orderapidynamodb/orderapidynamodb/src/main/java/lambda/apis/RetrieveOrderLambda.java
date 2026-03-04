package lambda.apis;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lambda.dto.Order;

public class RetrieveOrderLambda {

    public APIGatewayProxyResponseEvent RetrieveOrder(APIGatewayProxyRequestEvent requestEvent) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();


        AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.defaultClient();
        ScanResult scanResult = dynamoDB.scan(new ScanRequest().withTableName(System.getenv("ORDERS_TABLE")));
        var oders = scanResult.getItems().stream()
                .map(item-> new Order(item.get("id").getS(),
                        item.get("itemName").getS(),
                        Integer.parseInt(item.get("quantity").getN()))).toList();
        String jsonOutput = objectMapper.writeValueAsString(oders);



        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonOutput);

    }

}
