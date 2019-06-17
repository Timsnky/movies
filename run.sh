#!/bin/bash

mvn package

cd target

java -jar movies-0.0.1-SNAPSHOT.jar

