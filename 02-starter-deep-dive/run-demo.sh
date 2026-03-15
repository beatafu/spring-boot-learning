#!/bin/bash

echo "========================================================"
echo "Installing modules to local repository..."
echo "========================================================"
./mvnw clean install -DskipTests

if [ $? -ne 0 ]; then
    echo "Build failed!"
    exit 1
fi

echo ""
echo "========================================================"
echo "Starting demo-consumer application..."
echo "========================================================"
cd demo-consumer
../mvnw spring-boot:run

