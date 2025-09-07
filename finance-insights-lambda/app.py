import json
import boto3
from langchain.document_loaders import S3FileLoader


def lambda_handler(event, context):
    print("Processing S3 event:", event)

    # Simulating some processing by printing the event data
    print("S3 event data:", event['Records'][0]['s3'])
    s3_event = event['Records'][0]['s3']
    bucket_name = s3_event['bucket']['name']
    object_key = s3_event['object']['key']
    print(f"Bucket: {bucket_name}, Object Key: {object_key}")

    # Load file from S3 using S3FileLoad
    loader = S3FileLoader(bucket_name, object_key)
    docs = loader.load()
    for doc in docs:
        print(doc.page_content[:200])

    return {
        'statusCode': 200,
        'body': json.dumps('Finance processing completed')
    }