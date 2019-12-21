#!/bin/sh
docker build . -t micronaut-htt-service
mkdir -p build
docker run --rm --entrypoint cat micronaut-htt-service  /home/application/function.zip > build/function.zip

sam local start-api -t sam.yaml -p 3000

