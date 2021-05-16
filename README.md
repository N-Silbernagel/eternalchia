# Eternal Chia 🪙

## run
```shell
java -jar eternalchia.jar
```

## arguments
### number of parallel plots: <span style="color: green;">n</plot>
```shell
java -jar eternalchia.jar -n 5
```

## plotting arguments
### specify plotting arguments after eternalchia arguments by separating the arguments with two minuses ('<span style="color: green;">--</span>')
```shell
java -jar eternalchia.jar -n 1 -- -k 32 -r 2 -b 4000 -t /PATH/TO/TEMP -d /PATH/TO/PERSIST
```