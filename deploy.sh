#!/bin/bash

if [[ "$TRAVIS_TAG" == "" ]]
then
  output=$(sshpass -p $DAVID_PASS scp target/todo-spark.jar $DAVID_USER@$DAVID_URL:~/todo-spark-dev)
  echo "$output"
else
  echo "TAG Build"
fi