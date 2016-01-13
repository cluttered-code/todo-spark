#!/bin/bash

export SSHPASS=$PASS

if [[ "$TRAVIS_TAG" == "" ]]
then
  echo "DEV Build"
  export DEPLOY_PATH=$DEV_PATH
else
  echo "TAG Build"
  export DEPLOY_PATH=$PROD_PATH
fi

sshpass -e scp -o stricthostkeychecking=no target/todo-spark.jar $USER@$HOST:$DEPLOY_PATH

exit 0