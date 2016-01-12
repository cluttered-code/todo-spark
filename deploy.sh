#!/bin/bash

if [[ "$TRAVIS_TAG" == "" ]]
then
  export SSHPASS=$DAVID_PASS
  echo "SSHPAS:" $SSHPASS
  sshpass -e scp target/todo-spark.jar $DAVID_USER@$DAVID_URL:~/todo-spark-dev
else
  echo "TAG Build"
fi