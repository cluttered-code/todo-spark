#!/bin/bash

export SSHPASS=$PASS

if [[ "$TRAVIS_TAG" == "" ]]
then
  echo "DEV Build"
  sshpass -e scp target/todo-spark.jar $USER@$HOST:$PATH -o stricthostkeychecking=no
else
  echo "TAG Build"
fi