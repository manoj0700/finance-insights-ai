import { Stack, StackProps } from 'aws-cdk-lib';
import { Construct } from 'constructs';
import * as s3 from 'aws-cdk-lib/aws-s3';
import * as lambda from 'aws-cdk-lib/aws-lambda';
import * as s3n from 'aws-cdk-lib/aws-s3-notifications';
import * as iam from 'aws-cdk-lib/aws-iam';
import * as path from 'path';
import * as cdk from 'aws-cdk-lib';

export class FinanceInsightsAiStack extends Stack {
  constructor(scope: Construct, id: string, props?: StackProps) {
    super(scope, id, props);

    // S3 bucket
    const bucket = new s3.Bucket(this, 'FinanceCsvBucket', {
      versioned: false,
      removalPolicy: cdk.RemovalPolicy.DESTROY,
      autoDeleteObjects: true
    });

    // Lambda function
    const financeLambda = new lambda.Function(this, 'FinanceProcessor', {
      runtime: lambda.Runtime.JAVA_11,
      handler: 'finance.insights.financeInsightsHandler::handleRequest',
      code: lambda.Code.fromAsset(path.join(__dirname, '../lambda/app/build/libs/app-1.0.jar')),
      environment: {
        BUCKET_NAME: bucket.bucketName,
        BEDROCK_REGION: 'us-east-1',
        MODEL_ARN: 'arn:aws:bedrock:us-east-1:200407595956:inference-profile/us.anthropic.claude-3-haiku-20240307-v1:0'
      },
      memorySize: 1024,
      timeout: cdk.Duration.seconds(120),
    });

    // Grant Lambda access to S3
    bucket.grantRead(financeLambda);

    // IAM permissions for Bedrock
    financeLambda.addToRolePolicy(new iam.PolicyStatement({
      actions: ['bedrock:InvokeModel'],
      resources: ['*'], // narrow this if possible
    }));

    // Trigger Lambda on file upload
    bucket.addEventNotification(
      s3.EventType.OBJECT_CREATED_PUT,
      new s3n.LambdaDestination(financeLambda)
    );
  }
}