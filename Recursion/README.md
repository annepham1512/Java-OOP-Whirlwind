# Recursion Lab

## Introduction
In this lab, I developed recursive definitions for several problems and implemented those definitions as Java programs. This assignment enhanced my understanding of recursion and its applications in programming.

## Getting Started
I accepted the assignment from GitHub Classroom, cloned the `lab08-yourname` repository, and imported it as a Java project. I expanded the `recursion` package to begin my work.

## The Assignment
I developed, implemented, and tested recursive solutions for the following problems:

### 1. Pyramid Area
I wrote a method to compute the number of boxes in a pyramid based on the number of boxes in the base. Each layer of the pyramid is stacked directly on top of the previous one. I implemented and tested the `computePyramidBoxes()` method in the `PyramidBoxCount` class, ensuring to cover all base and recursive cases.

### 2. Exponentiation
I provided a recursive definition for computing \(a^b\) (where both \(a\) and \(b\) are non-negative integers). I utilized a more efficient approach to compute the exponentiation by employing repeated squaring. I implemented and tested the `exp` method in the `Exponentiation` class, ensuring to test both base and recursive cases.

### 3. Efficient Palindrome Checker
I improved a previously studied recursive approach to determine if a string is a palindrome, avoiding the creation of new `String` objects. Instead of using `substring()`, I utilized the `charAt()` method and passed additional parameters to retain the original string during recursion. I modified the `isPalindrome()` method in the `Palindrome.java` file and created test cases for each base and recursive scenario.

### 4. Sierpinski Triangle
I implemented a recursive program to generate the Sierpinski triangle, a fractal pattern described by Polish mathematician Waclaw Sierpinski. I developed the `drawSierpinski()` method in the `SierpinskiPanel` class. The triangle can be visualized by running the `main()` method in the `SierpinskiGUI` class, which displays an order 6 Sierpinski Triangle. 

![The Sierpinski triangle](img/image.png)



---

This project was completed with the support of the Dickinson College Computer Science faculty.

