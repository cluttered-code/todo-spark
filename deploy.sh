#!/bin/bash

if [[ "$TRAVIS_TAG" == "" ]]
then
  export SSHPASS=$DAVID_PASS
  sshpass -e scp target/todo-spark.jar $DAVID_USER@$DAVID_URL:/home/deploy/todo-spark-dev
else
  echo "TAG Build"
fi