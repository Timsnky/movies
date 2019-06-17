#!/bin/bash

#You can skip step 1 if you already have an existing jar file
echo "Step 1. Package the Spring Application"

cd .. && mvn package

cp target/movies-0.0.1-SNAPSHOT.jar docker/files/movies-0.0.1-SNAPSHOT.jar

cd docker

echo "Step 2. Build the Docker Image"

docker build --tag=safaricom-movies:latest --rm=true .

docker volume create --name=safaricom-movies-config-repo

echo "Step 3. Run the Docker Image"

docker run --name=safaricom-movies -p 8443:8443 -p 8080:8080 --volume=safaricom-movies-config-repo:/var/lib/safaricom-movies/config-repo safaricom-movies:latest