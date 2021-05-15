# Eternal Chia 🪙

## run
```shell
java -jar eternalchia.jar
```

## arguments
### number of parallel plots: n
```shell
java -jar eternalchia.jar -n 5
```

## plotting arguments
### specify plotting arguments after eternalchia arguments by separating the arguments with a minus ('-')
```shell
java -jar eternalchia.jar -n 1 -- -k 32 -r 2 -b 4000 -t /PATH/TO/TEMP -d /PATH/TO/PERSIST
```