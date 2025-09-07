FROM public.ecr.aws/lambda/python:3.9

# Copy dependencies first (for caching)
COPY requirements.txt ./
RUN pip install -r requirements.txt

# Copy function code
COPY app.py ${LAMBDA_TASK_ROOT}/

CMD ["app.lambda_handler"]