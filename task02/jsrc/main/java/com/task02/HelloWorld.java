package com.task02;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;

import java.util.HashMap;
import java.util.Map;

@LambdaHandler(lambdaName = "hello_world",
        roleName = "hello_world-role",
        isPublishVersion = true,
        aliasName = "${lambdas_alias_name}"
)
public class HelloWorld implements RequestHandler<APIGatewayProxyRequestEvent, Map<String, Object>> {

    public Map<String, Object> handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        if ("GET".equalsIgnoreCase(request.getHttpMethod()) && "/hello".equals(request.getPath())) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("statusCode", 200);
            resultMap.put("message", "Hello from Lambda");
            return resultMap;
        }
        Map<String, Object> errorResult = new HashMap<>();
        errorResult.put("statusCode", 400);
        errorResult.put("message", "Bad Request");
        return errorResult;
    }
}
