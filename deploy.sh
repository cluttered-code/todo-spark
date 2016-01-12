#!/bin/bash

if [[ "$TRAVIS_TAG" == "" ]]
then
  echo "`ls -l`"
  echo "`ls -l target/`"
  sshpass -e scp target/todo-spark.jar $USER@$URL:/home/$USER/todo-spark-dev
else
  echo "TAG Build"
fi