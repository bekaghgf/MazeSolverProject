# MazeSolverProject 🧩

A Java project for generating and solving a maze using the DFS (depth-first search) algorithm.  
Generation is performed using recursive backtracking, and the solution is to find a path from the input to the output.

---

## 📌 Features

- Generate a random maze of a given size
- Automatic size correction to odd (for correct generation)
- Search for a path from the entrance to the exit
- Output the maze to the console
- Visualization of the found path with the symbols `.`

---

## 🚀 How to launch

1. Make sure you have JDK (Java 8+)
installed 2. Download or clone the project:

   ```bash
   git clone https://github.com/bekaghgf/MazeSolverProject.git
   cd MazeSolverProject
   ```

3. Compile and run the program:

   ```bash
   javac MazeSolverProject.java
   java MazeSolverProject
   ```

---

## 🧮 How to enter dimensions

At startup, the program will ask you to enter the dimensions of the maze.:

```
Enter number of rows (min. 10): 
Enter number of columns (min. 10):
```

- Dimensions **must be at least 10x10**
- If you enter even numbers, they will be automatically increased by 1 (to odd ones)

---

## 🧠 How it works

- The maze is created as a two-dimensional array `char[][]`, where:
— `#` - walls
— space ` ` - passage
  - `.` — the path from start to exit
- Generation: recursive backtracking using a stack and random selection of directions
- Pathfinding: DFS with labeling of visited cells

---

## 📷 Output example

```
Generated Maze:
#############
# #         #
# ####### ###
#       #   #
####### ### #
#     #     #
# ### ##### #
#   #     # #
### ### ### #
#   # #     #
# ### #######
#           #
#############

Path found:
#############
#.#         #
#.####### ###
#.......#   #
#######.### #
#.....#.....#
#.###.#####.#
#...#...  #.#
###.###.###.#
#...# #.....#
#.### #######
#...........#
#############
```