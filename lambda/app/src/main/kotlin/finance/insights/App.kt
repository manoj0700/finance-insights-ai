package finance.insights

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse
import software.amazon.awssdk.core.SdkBytes
import java.nio.charset.StandardCharsets
import java.util.Base64

class financeInsightsHandler : RequestHandler<Map<String, Any>, String> {
    override fun handleRequest(input: Map<String, Any>, context: Context): String {
        context.logger.log("Received input: $input")

        val modelArn = System.getenv("MODEL_ARN") ?: "anthropic.claude-instant-v1"
        val region = System.getenv("BEDROCK_REGION") ?: "us-east-1"

        val body = """
        {
          "anthropic_version": "bedrock-2023-05-31",
          "max_tokens": 1000,
          "messages": [
            {
              "role": "user",
              "content": [
                {
                  "type": "text",
                  "text": "Hello Claude! Can you explain gravity in simple terms?"
                }
              ]
            }
          ]
        }
        """.trimIndent()
        


        context.logger.log("Invoking model with body: $body")
        
        try {
            val client = BedrockRuntimeClient.builder()
                .region(Region.of(region))
                .build()

            val request = InvokeModelRequest.builder()
                .modelId(modelArn)
                .contentType("application/json")
                .accept("application/json")
                .body(SdkBytes.fromUtf8String(body))
                .build()

            val response: InvokeModelResponse = client.invokeModel(request)
            val responseBody = response.body().asUtf8String()

            context.logger.log("Model response: $responseBody")
            return "Model replied: $responseBody"

        } catch (e: Exception) {
            context.logger.log("Error invoking model: ${e.message}")
            return "Error: ${e.message}"
        }
    }
}