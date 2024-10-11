# Drawable/Scaleable Shapes using Interfaces

## Introduction
In this project, which is part of Lab 5, I explored working with interfaces and polymorphism to create shapes that can be drawn and animated on the screen. The final product is an application that allows drawing pictures or performing animations with different shapes.

## Overview
This project focuses on two key interfaces: `Drawable` and `Scaleable`. These interfaces provide a uniform way to handle a variety of shapes—such as circles, rectangles, and lines—allowing them to be drawn and scaled. Additionally, the `DrawableObjectList` class is provided to manage collections of `Drawable` objects, making it easy to perform operations like drawing and scaling on all objects at once.

### Key components:
- **Drawable Interface**: Defines methods for drawing shapes, setting colors, and handling visibility.
- **Scaleable Interface**: Defines a method for scaling objects by a specified factor.

As part of the project, I implemented custom shape classes that adhere to these interfaces and tested them thoroughly before assembling the final program to display animations or static drawings.

## Project Structure
The structure of the project includes:
- `DrawableObjectList`: A class that manages a collection of `Drawable` objects.
- `Circle` class: A sample class that implements both `Drawable` and `Scaleable` interfaces.
- Custom shape classes: Additional shapes that implement either `Drawable` and/or `Scaleable` interfaces.
- Example programs: Programs like `Bullseye` and `Annihilation`, which demonstrate drawing and animations using these interfaces.

## How to Run
1. Clone this repository from GitHub Classroom.
2. Import the project into Eclipse.
3. Add JUnit5 to the build path to run tests if necessary.
4. Compile and run sample programs like `Bullseye` to see an example of drawing and animation, or create your own drawing using the provided `Drawable` and `Scaleable` classes.

## Example Game Screen

Here’s an example of what the game or drawing looks like when running:

![Drawable/Scaleable Shapes](img/image.png)

---

## Authors

- @[annepham1512](https://github.com/annepham1512)

---

This project was completed with the support of the Dickinson College Computer Science faculty.
