# Darwin's World - Lab 03

This project simulates a game called Darwin's World, where creatures inhabit a 2D grid, interact with their environment, and evolve based on predefined behaviors. Each creature belongs to a species, which defines its color and actions. The goal of the simulation is to create species that can survive and spread by infecting other creatures.

---

## Overview

Darwin's World is a turn-based simulation where creatures follow species-specific programs. The simulation progresses as each creature takes actions like moving, turning, or infecting other creatures based on its program. The objective is to develop creatures that can outlast others and ensure the survival of their species.

Each creature:
- Occupies a grid square in the world.
- Faces one of four directions (up, down, left, or right).
- Belongs to a species, which defines its behavior and appearance.

### Sample World

![Darwin's World Sample](img/image.png)

---

## Project Structure

The project is composed of several key components:

### Species Files
Each species is defined by a text file that includes:
- **Species Name**: e.g., Flytrap, Rover.
- **Color**: The display color of the species, e.g., green, blue.
- **Program**: A set of instructions such as `left`, `hop`, and `infect`, which control the species' behavior during the simulation.

Some pre-supplied species include:
- **Flytrap**: Spins in place and infects nearby creatures.
- **Rover**: Moves in a straight line and infects creatures in front of it.
- **Food**: Moves in a square pattern and serves as food for other species.
- **Hop**: Continuously moves forward unless blocked.

### Creature Behavior

Each creature follows a program that consists of multiple steps determining its actions. The available instructions include:
- **hop**: Moves forward if the square ahead is empty.
- **left / right**: Turns 90 degrees left or right.
- **infect**: Infects the creature directly in front, if it's from a different species.
- **conditional instructions**: Commands like `ifwall`, `ifempty`, `ifsame`, and `ifenemy` allow creatures to perform different actions based on the environment.

The programs are specified in species text files, and the game processes them for each creature turn by turn.

### User Interface

The graphical interface (GUI) enables users to:
- **Add Creatures**: Load species files and place creatures in the world.
- **Start Simulation**: Initialize the world and start a new simulation.
- **Next Turn**: Manually process the next turn for all creatures.
- **Continue**: Automatically run the simulation for up to 200 turns.

---

## How to Run

1. **Clone** this repository and import it into your Java IDE (e.g., Eclipse).
2. Add the provided species files (`Flytrap.txt`, `Rover.txt`, `Food.txt`, etc.) to the `src` directory.
3. Run the `DarwinGUI.java` class to launch the simulation.
4. Use the GUI to add creatures and start the simulation.
5. Observe how the creatures behave, interact, and evolve as the simulation progresses.

---

## Authors

- @[annepham1512](https://github.com/annepham1512)

This project was completed with the support of the Dickinson College Computer Science faculty.
