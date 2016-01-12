#!/bin/bash

if [[ "$TRAVIS_TAG" == "" ]]
then
  echo "DEV Build"
else
  echo "TAG Build"
fi