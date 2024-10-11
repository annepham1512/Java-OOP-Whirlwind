Drawable/Scaleable Shapes II, Using Inheritance

## Introduction
In this project, which builds on the previous lab, I worked on expanding the collection of shapes by implementing them using inheritance to reduce code repetition. I created new classes (`Line`, `Text`, `Rectangle`, `Square`, `Ellipse`, and `Circle`) that represent various drawable and scaleable shapes. This lab focuses on how to effectively use inheritance and interfaces in Java to create a more efficient and flexible structure for drawing shapes.

## Overview
This lab demonstrates the use of the `Drawable` and `Scaleable` interfaces, as well as an inheritance hierarchy to model different shapes. By inheriting from a base `Point` class and building subclasses for each shape, I was able to implement a wide variety of shapes with minimal code duplication.

The `DrawableObjectList` class from the previous lab was also extended to manage collections of these shapes, making it easier to perform bulk operations like drawing or scaling. The goal was to create shapes that could be displayed in a static picture or animated on the screen.

---

## Getting Started
1. Accept the assignment from GitHub Classroom, clone the repository, and import the project into Eclipse.
2. Ensure there are no compilation errors by adding JUnit5 to your project’s build path if necessary.

---

## Design
The `Point` class is provided as the base class, representing a single pixel at a given (`x`, `y`) coordinate, and it implements the `Drawable` interface. From this base class, I created several subclasses to represent lines, rectangles, ellipses, and more.

Each class in this hierarchy inherits as much functionality as possible from its parent class. For example, the `Circle` class is a subclass of `Ellipse`, which itself is a subclass of `Point`. This means that `Circle` inherits the methods and properties of `Point` and `Ellipse`, while only overriding or adding new functionality specific to circles.

### Key Components:
- **Point**: Represents a point in 2D space and implements the `Drawable` interface.
- **Line**: A subclass of `Point` that represents a line segment between two points.
- **Rectangle** and **Square**: Subclasses that represent rectangular and square shapes.
- **Ellipse** and **Circle**: Represent oval and circular shapes, respectively, with scaling functionality.

The inheritance hierarchy reduces code duplication and makes it easier to manage shared behavior among similar shapes.

---

## The Assignment
In this lab, I implemented and tested the following classes:
- **Line**
- **Text**
- **Rectangle**
- **Square**
- **Ellipse**
- **Circle**

Each class is a subclass of `Point` (or one of its subclasses) and implements both `Drawable` and/or `Scaleable` interfaces as needed. After implementing each class, I tested them with JUnit to ensure functionality. Finally, I created a program that displays a picture or animation using at least one of each shape.

### Key Concepts:
- **Inheritance**: Sharing behavior across classes to reduce redundancy.
- **Polymorphism**: Treating all shapes as `Drawable` objects, allowing them to be managed and drawn uniformly.
- **JUnit Testing**: Ensuring that the new functionality works as expected, especially for new or overridden methods in the subclasses.

---

## How to Run
1. **Clone** the repository from GitHub Classroom and import it into Eclipse.
2. **Add JUnit5** to the build path if necessary.
3. **Test the classes** by running the provided JUnit test cases.
4. **Run the program** to see a picture or animation using the shapes (`Line`, `Rectangle`, `Square`, `Ellipse`, `Circle`, and `Text`).

---

## Example Game Screen

Here’s an example of what the program looks like when running:

![Drawable/Scaleable Shapes II](img/image.png)

---

## Submitting Your Solution
As I progressed, I regularly pushed code to GitHub for backup. For final submission, I pushed the complete solution and submitted a self-assessment report on Gradescope as required.

---

## Authors

- @[annepham1512](https://github.com/annepham1512)

---

This project was completed with the support of the Dickinson College Computer Science faculty.
