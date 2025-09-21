# Tech Context: GenAI Finance Insights

## Technologies Used

### Infrastructure & Deployment
- **AWS CDK**: v2.x with TypeScript for Infrastructure as Code
- **Node.js**: For CDK development and build processes
- **TypeScript**: Primary language for infrastructure definitions
- **Jest**: Testing framework for CDK constructs

### Runtime & Compute
- **AWS Lambda**: Python 3.11 runtime
- **Python**: Primary language for business logic
- **LangChain**: Document processing and AI integration framework

### AI & Machine Learning
- **Amazon Bedrock**: Managed AI service
- **Claude 3 Haiku**: Anthropic's language model via Bedrock
- **Model ARN**: `arn:aws:bedrock:us-east-1:200407595956:inference-profile/us.anthropic.claude-3-haiku-20240307-v1:0`

### Storage & Events
- **Amazon S3**: Object storage for file uploads
- **S3 Event Notifications**: Trigger Lambda on file uploads

### Development Setup

### Project Structure
```
GenAI-Finance-Insights/
├── bin/                    # CDK app entry point
├── lib/                    # CDK stack definitions
├── finance-insights-lambda/ # Lambda function code
├── test/                   # Unit tests
├── memory-bank/            # Project documentation
└── package.json           # Node.js dependencies
```

### Dependencies
#### CDK Dependencies (package.json)
- `aws-cdk-lib`: Core CDK library
- `constructs`: CDK constructs framework
- TypeScript and Jest for development

#### Lambda Dependencies (requirements.txt)
- `langchain`: Document processing framework
- `boto3`: AWS SDK for Python (implicit in Lambda)

### Technical Constraints

### AWS Limits
- **Lambda Memory**: Currently 1024MB (adjustable up to 10GB)
- **Lambda Timeout**: Currently 120 seconds (adjustable up to 15 minutes)
- **S3 Object Size**: No explicit limit set (AWS default 5TB)
- **Bedrock Rate Limits**: Model-specific limits apply

### Regional Constraints
- **Bedrock Region**: us-east-1 (required for Claude 3 Haiku)
- **Deployment Region**: Should match Bedrock region for optimal performance

### Security Constraints
- **IAM Permissions**: Lambda has minimal required permissions
- **Network**: No VPC configuration (uses default Lambda networking)

## Tool Usage Patterns

### Development Workflow
```bash
npm run build    # Compile TypeScript
npm run test     # Run Jest tests
cdk diff         # Preview changes
cdk deploy       # Deploy to AWS
```

### Local Development
- CDK for infrastructure changes
- Local Python development for Lambda function
- No local testing setup for Bedrock integration

### Deployment Pattern
- Single stack deployment
- All resources in same AWS account/region
- Automatic cleanup on stack deletion

## Current Technical Debt

### Missing Components
1. **PDF Processing**: No PDF-specific document loader
2. **Bedrock Integration**: Client not implemented in Lambda
3. **Error Handling**: Minimal error management
4. **Logging**: Basic print statements only
5. **Testing**: No Lambda function tests

### Performance Issues
1. **Cold Starts**: No optimization for Lambda cold starts
2. **Memory Usage**: Not optimized for different file sizes
3. **Timeout Handling**: No graceful timeout management

### Security Gaps
1. **Broad Permissions**: Bedrock permissions are too wide (`*`)
2. **No Input Validation**: Files processed without validation
3. **No Encryption**: No explicit encryption configuration

## Required Improvements

### Immediate Needs
1. Implement Bedrock client in Lambda
2. Add PDF processing capability
3. Implement proper error handling
4. Add input validation and file type detection

### Future Enhancements
1. Add CloudWatch logging and monitoring
2. Implement result storage mechanism
3. Add API Gateway for result retrieval
4. Optimize Lambda performance
5. Add comprehensive testing suite
