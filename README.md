# TicTacToe
This project is an implementation of the TicTacToe game, 
including 3 different AI levels (Easy, Medium. Hard), as well
as the ability to play as a user.

Easy: Just makes random moves.

Medium: Checks if there is the opportunity to block the opponent or
to win, if not, makes a random move.

Hard: Always plays the best possible move using the minimax algorithm. 

To use it you need to specify the inputs via the command line. 
*start* *typePlayerOne* *typePlayerTwo* or *exit*

Examples: \
start easy user \
start hard hard \
start user medium \
exit

This will execute the game accordingly to your specifications. 
Then if you selected the user option you will be asked to give the 
coordinates where you want to place your next move.

Like the following: \
1 3 \
2 2 \
3 2

