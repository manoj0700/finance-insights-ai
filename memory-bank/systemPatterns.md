# System Patterns: GenAI Finance Insights

## System Architecture
```
S3 Bucket → Lambda Function → Amazon Bedrock → Results
    ↓           ↓                    ↓
File Upload → Processing → AI Analysis → Insights
```

### Core Components
1. **S3 Bucket**: Entry point for file uploads with event notifications
2. **Lambda Function**: Serverless compute for processing logic
3. **Amazon Bedrock**: AI service for generating financial insights
4. **LangChain**: Framework for document loading and processing

## Key Technical Decisions

### Infrastructure Choices
- **AWS CDK**: Infrastructure as Code for reproducible deployments
- **Serverless Architecture**: Cost-effective, auto-scaling solution
- **Event-Driven Processing**: S3 events trigger Lambda execution
- **Python Runtime**: Lambda uses Python 3.11 for AI/ML library compatibility

### Processing Pattern
- **Event-Driven**: S3 PUT events automatically trigger processing
- **Asynchronous**: Non-blocking processing of uploaded files
- **Stateless**: Each Lambda invocation is independent

### Security Pattern
- **IAM Roles**: Lambda has specific permissions for S3 and Bedrock
- **Least Privilege**: Minimal required permissions granted
- **Resource-Based Access**: S3 bucket policies control access

## Design Patterns in Use

### Document Processing Pattern
```python
# Current pattern in app.py
1. Receive S3 event
2. Extract bucket and key information
3. Load document using LangChain S3FileLoader
4. Process content (currently basic logging)
```

### Multi-Format Handler Pattern (To Implement)
```
File Upload → Format Detection → Appropriate Processor
                    ↓                    ↓
                CSV Handler         PDF Handler
                    ↓                    ↓
                Structured Data    Text Extraction
                    ↓                    ↓
                    → Bedrock Analysis ←
```

## Component Relationships

### CDK Stack Components
- **S3 Bucket**: `FinanceCsvBucket` with auto-delete and versioning disabled
- **Lambda Function**: `FinanceProcessor` with 1024MB memory, 120s timeout
- **IAM Policies**: Bedrock invoke permissions and S3 read/write access
- **Event Notification**: S3 PUT events trigger Lambda

### Lambda Environment
- **BEDROCK_REGION**: us-east-1
- **MODEL_ARN**: Claude 3 Haiku inference profile
- **Runtime**: Python 3.11 with LangChain dependencies

## Critical Implementation Paths

### Current Implementation
1. S3 event received by Lambda
2. Extract bucket name and object key
3. Use LangChain S3FileLoader to load document
4. Basic content logging (incomplete)

### Required Implementation
1. **File Type Detection**: Determine if file is CSV or PDF
2. **Format-Specific Processing**: Different handlers for each format
3. **Bedrock Integration**: Send processed content to AI model
4. **Result Storage**: Store or return generated insights
5. **Error Handling**: Comprehensive error management

## Performance Considerations
- **Memory**: 1024MB allocated for Lambda (may need adjustment for large files)
- **Timeout**: 120 seconds (may need increase for complex analysis)
- **Concurrency**: Default Lambda concurrency limits apply
- **Cost**: Pay-per-use model for all services

## Scalability Patterns
- **Auto-scaling**: Lambda automatically scales with demand
- **Parallel Processing**: Multiple files can be processed simultaneously
- **Resource Limits**: Monitor and adjust Lambda limits as needed
