# zWordle
An extended version of [Wordle](https://www.nytimes.com/games/wordle/index.html"Wordle") (Mini Project of EBU4201) with Java Swing.

## How to play
* Guess the Wordle word in tries for no more than 6 time.
* Each guess must be a valid word with the length of **FIVE**.
  * The dictionary contains **3088** popular words from **Wikipedia**.  
* For each letter in a valid word(ie. in a wordlist) you enter, it will turn to:
  * **Green** if it is in the word and in the correct spot.
  * **Yellow** if it is in the word but in the wrong spot.
  * **Gray** if it is not in the word in any spot.

## Compile & Run
If you use IDEA to compile and run this project, you will play game directly.

Else if you want to compile and run this project in cmd, you should find all I/O and add "../" in the front of the path.
Then you can use the command as following in cmd to play the game!
```shell
javac *.java && java zWordle
```

## Regenerate the Javadoc
```shell
javadoc -d ./javadoc ./src/MainFrame/*.java 
```

## Developing Log
### v1.0

**about:** the basic GUI window.

**already have：**

1. Basic GUI window

**Todo：**

1. Wordlist check
2. Logic of background color changing
3. Button action

### v1.1

**about:** complement of Wordle logic.

**already have：**

1. Basic GUI window
2. Logic of words check
3. Button action
4. Background color changing after words check

**Todo：**

1. Message notification window

### v1.2

**about:** complement of message window.

**already have：**

1. Basic GUI window
2. Logic of words check
3. Button action
4. Background color changing after words check
5. Message notification window

**Todo：**

1. write javadoc

### v1.3

**about:** complement of javadoc.

**already have：**

1. Basic GUI window
2. Logic of words check
3. Button action
4. Background color changing after words check
5. Message notification window
6. complement the javadoc annotation

**Todo：**

1. new start window
2. users help
