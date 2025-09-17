# Chess with GUI

This is my implementation of chess with graphical interface as a project for Programming in Java Language in the winter semester.

## Programming documentation

Via this [link](http://localhost:63342/ChessJava/docs/main/cz/cuni/milanenm/framework/package-summary.html), there is a page with automatically generated (by javadoc) documentation of all classes used in the project.

## User documentation

After running a main function (in main/cz/cuni/milanenm/programs.Main), the Menu screen is opened. There are four buttons:
- [ ] [Play with a bot](#play-with-a-bot),
- [ ] [Play with a friend](#play-with-a-friend),
- [ ] [Rules](#rules),
- [ ] [Exit](#exit).

## Play with a bot

By pressing this button, a user will get to the Game screen. There is a classical 8*8 chess board with white and black figures on it. The user is playing with the white figures. A bot (computer) is playing with the black ones.
On the right upper corner is written whose turn is, in the end of the game, there also will appear a message in red colour, who won. On the right lower corner there is a button, by pressing which, the user will return to Menu Screen.

The user starts a game. To make a move, the user has to activate a cell with a figure he wants to move (by pressing on a certain cell). The cells marked with green circles are all possible moves for a chosen figure on the activated cell. After that, the user has to choose a cell he wants to move a chosen figure to and press on it. After user's turn, a bot will make a move, which will be marked in red. If the user or a bot will be in a situation when a king is under immediate attack, the message in red colour "CHECK" will appear on the right upper corner. The game continues until somebody wins (if there is a draw situation, the user has to press on the exit button on the right lower corner).

## Play with a friend

By pressing this button, you get to the Game screen. The rules are the same as in the previous [mode](#play-with-a-bot) when playing with the bot. The difference is that instead of the bot, the second user is playing and moving figures as same as the first user.

## Rules

By pressing this button, the following link https://en.wikipedia.org/wiki/Chess will be opened in user's default browser. It is a wikipedia page with rules of chess to play this game. The game does not handle timer, and during promotion of a pawn, it can be only changed on a queen (as it is usually done). 

## Exit

By pressing this button, you will close the program.