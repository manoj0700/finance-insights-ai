# Project Brief: GenAI Finance Insights

## Project Overview
GenAI Finance Insights is an AWS-based serverless application that processes financial documents (CSV and PDF files) uploaded to S3 and provides AI-powered insights using Amazon Bedrock.

## Core Requirements
- **File Processing**: Automatically process CSV and PDF files when uploaded to S3
- **AI Analysis**: Use Amazon Bedrock for analyzing financial data and generating insights
- **Multi-Format Support**: Handle both structured (CSV) and unstructured (PDF) financial documents
- **Serverless Architecture**: Built with AWS CDK, Lambda, and S3
- **Event-Driven**: Trigger processing on S3 file uploads

## Technical Stack
- **Infrastructure**: AWS CDK (TypeScript)
- **Compute**: AWS Lambda (Python 3.11)
- **Storage**: Amazon S3
- **AI/ML**: Amazon Bedrock
- **Framework**: LangChain for document processing

## Project Goals
1. Create a scalable finance data processing pipeline for multiple file formats
2. Provide automated insights on financial CSV and PDF documents using Amazon Bedrock
3. Leverage generative AI for financial analysis across different document types
4. Maintain serverless, cost-effective architecture

## Current Status
- Basic CDK infrastructure is set up
- Lambda function structure exists with basic S3 file loading
- LangChain integration partially implemented
- Missing: Complete Amazon Bedrock integration, PDF processing capability, error handling, testing

## Success Criteria
- Successfully process uploaded CSV and PDF files
- Generate meaningful financial insights using Amazon Bedrock
- Handle different file formats appropriately
- Handle errors gracefully
- Maintain cost-effective serverless operation
