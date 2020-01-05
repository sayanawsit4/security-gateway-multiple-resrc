#!/bin/bash

echo "Performing a clean Maven build"
./mvnw clean package -DskipTests=true

echo "Building the UAA"
cd uaa
docker build --tag scg-demo-uaa .
cd ..

echo "Building the Gateway"
cd security-gateway
docker build --tag scg-demo-security-gateway .
cd ..

echo "Building the Service"
cd secured-service
docker build --tag scg-demo-secured-service .
cd ..

echo "Building the Service 2"
cd secured-service2
docker build --tag scg-demo-secured-service2 .
cd ..