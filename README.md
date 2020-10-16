A Java program that lets you explore a text-based field full of... mines. See if you can guess which cells have a mine underneath.
Originally developed as a Hyperskill project: https://hyperskill.org/projects/77?track=1

The game starts by asking you the number of mines you want to spread throughout the field. The aim of the goal is to guess on which cells - located on a given coordinate x and a given coordinate y - has the program randomly placed the mines. 

If you think that a given pair of coordinates x and y has no mine, you can enter its values followed by the option "free" in order to explore the surrounding terrain. 

On the other hand, if the number of nearby mines indicated by the already revealed cells leads you to think that there is a mine underneath a specific cell that has not yet been uncovered, you can enter the same values of x and y followed by the option "mine".

The game ends when you accidentally fall upon a mine hidden in a given cell you thought was free (guess what? You lost!!) or when you guess the positions of all the mines within the field (Surprise, surprise: You won!!).