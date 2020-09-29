
A Java program that lets you explore a text-based field full of... mines. See if you can guess which cells have a mine underneath.

The game starts by asking us the number of mines we want to spread throughout the field. The aim of the goal is to guess on which cells - located on a given coordinate x and a given coordinate y - has the program randomly placed the mines. 

If we think that a given pair of coordinates x and y has no mine, we can enter its values followed by the option "free" in order to explore the surrounding terrain. 

On the other hand, if the number of nearby mines indicated by the already revealed cells leads us to think that there is a mine underneath a specific cell that has not yet been uncovered, we enter the same values of x and y followed by the option "mine".

The game ends when accidentally end up exploring a mine hidden in given cell we thought was free (guess what? We lost!!) or when we guess the positions of all the mines within the field (Surprise, surprise: We won!!).