#!/bin/bash

export SSHPASS=$PASS

if [[ "$TRAVIS_TAG" == "" ]]
then
  echo "DEV Build"
  export PATH=$DEV_PATH
else
  echo "TAG Build"
  export PATH=$PROD_PATH
fi

exit 0