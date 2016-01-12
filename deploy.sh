#!/bin/bash

if [[ "$TRAVIS_TAG" == "" ]]
export SSHPASS=$PASS
then
  export destination="$USER@$URL:/home/$USER/todo-spark-dev"
  echo $destination
  sshpass -e scp target/todo-spark.jar $destination
else
  echo "TAG Build"
fi