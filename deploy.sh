#!/bin/bash

if [[ "$TRAVIS_TAG" == "" ]]
export SSHPASS=$PASS
then
  destination="$USER@$URL:/home/$USER/todo-spark-dev"
  echo destination
  sshpass -e scp target/todo-spark.jar $USER@$URL:/home/$USER/todo-spark-dev
else
  echo "TAG Build"
fi