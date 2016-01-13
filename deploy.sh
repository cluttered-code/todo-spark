#!/bin/bash

export SSHPASS=$PASS

if [[ "$TRAVIS_TAG" == "" ]]
then
  echo "DEV Build"
  export TODO_SERVICE=todo-spark-dev
  export DEPLOY_PATH=/home/$USER/$TODO_SERVICE
else
  echo "TAG Build"
  export TODO_SERVICE=todo-spark
  export DEPLOY_PATH=/home/$USER/$TODO_SERVICE
fi

echo "Sending Jar to $USER@$HOST:$DEPLOY_PATH..."
sshpass -e scp -o stricthostkeychecking=no target/todo-spark.jar $USER@$HOST:$DEPLOY_PATH

echo "Restart Service..."
sshpass -e ssh root@$HOST << EOF
  service $TODO_SERVICE restart
EOF