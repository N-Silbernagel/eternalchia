# Eternal Chia 🪙

## Download
```shell
wget https://github.com/N-Silbernagel/eternalchia/releases/download/X.Y.Z/eternalchia.jar
```

## Run
```shell
java -jar eternalchia.jar
```

## Quick Example
run two parallel chia plots forever using 4gb of ram and 2 cpu threads each
```shell
java -jar eternalchia.jar -n 2 -ca "-k 32 -b 4000 -r 2 -t /MYSSD -d /MYHDD"
```

## Interacting
### enter one of the following commands to interact with the application
* "i" will print information on plotting since starting the application
* "s" will cause the application to not start any new processes when the currently running have finished

use ctrl + c to forcefully kill all plotting processes

## Arguments
### number of parallel plots: n
```shell
java -jar eternalchia.jar -n 5
```

### chia cli plotting arguments: ca
```shell
java -jar eternalchia.jar -ca "-k 32 -r 2 -b 4000 -t /PATH/TO/TEMP -d /PATH/TO/PERSIST"
```

### do not actually run chia plot command: dry
```shell
java -jar eternalchia.jar -dry
```

### specify path to chia executable: cp
Will default to simply using "chia" from PATH
```shell
java -jar eternalchia.jar -cp /PATH/TO/CHIA
```