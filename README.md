# todo-spark

[![Build Status](https://travis-ci.org/cluttered-code/todo-spark.svg?branch=master)](https://travis-ci.org/cluttered-code/todo-spark)

## Upstart Service Scripts
This must be on the server before the service can be updated in devops
```bash
scp todo-spark-dev.conf root@<HOST>:/etc/init
# or
scp todo-spark.conf root@<HOST>:/etc/init
```
