# Using the Singleton and Adapter Design Patterns

## Introduction
This project explores two essential design patterns in software engineering: the Singleton and Adapter patterns. Additionally, it introduces the use of the `enum` keyword for defining enumerated types and demonstrates basic file reading techniques in Java using `BufferedReader`, `FileReader`, and `readLine()`.

This lab provided an opportunity to build upon these concepts while working with real-world scenarios such as reading data from CSV files to model a music store database. I also received valuable support from the Dickinson College Computer Science faculty in completing this project.

## Getting Started
1. Clone the GitHub repository that was assigned through GitHub Classroom and import the starter code into Eclipse as a Java Project.
2. Resolve any compilation issues by adding JUnit5 to the project’s build path.

## The Assignment

### Preliminaries
First, I familiarized myself with the starter code:
- I created a class diagram (for my own understanding) to illustrate the relationships among the different classes.
- After reviewing the code in `ArtistData.java`, I ran its `main` method to understand the existing functionality.
- I ran all JUnit tests to ensure everything was working correctly and explored the structure of each class.

Next, I examined the `data/bands.csv` and `data/singers.csv` files to understand how they represent band and singer data in CSV format.

**Task A:** I added new band data to the `bands.csv` file and included more singers in the `singers.csv` file. After running the `main` method in `ArtistData.java`, I confirmed that the newly added bands and singers were printed out correctly.

### Implementing the Singleton Pattern
To enhance the efficiency of the `ArtistData` class, I transformed it into a singleton. This modification ensures that the data files are only read once, and subsequent requests use the same instance of `ArtistData`.

**Task B:** I made the necessary changes across four Java files to implement the Singleton pattern. Afterward, I verified that all JUnit tests still passed successfully.

### Implementing the Adapter Pattern
The original `ArtistData` class handled only bands and singers. To extend its functionality and also store data about classical composers, I used the Adapter design pattern. This allowed me to integrate a new data type for composers while ensuring that the existing `Composer.java` file remained unchanged.

**Task C:**
1. I created an adapter class for the composers using a naming convention similar to that in the in-class examples.
2. I wrote JUnit tests to ensure the new adapter class was functioning as expected.
3. I modified `ArtistData.java` to read composer data from a new `data/composers.csv` file in addition to bands and singers.
4. After running the `main` method in `ArtistData.java`, I verified that the composer data was printed correctly, and all JUnit tests passed.

---

## How to Run

1. **Clone** this repository and import it into Eclipse as a Java Project.
2. Add the `JUnit5` library to your build path if necessary.
3. Add your custom band, singer, and composer data to the `data/bands.csv`, `data/singers.csv`, and `data/composers.csv` files.
4. Run the `main` method in the `ArtistData.java` class.
5. Observe the output, which includes data for bands, singers, and composers.

---

## Submitting Your Solution
To submit the assignment, I regularly pushed my code to GitHub as a backup. For the final submission, I pushed the complete version of my code and data files. Additionally, I submitted the self-assessment report through Moodle, as per the course requirements.

---

## Additional Notes
This project is an introduction to basic file I/O techniques and demonstrates how to apply design patterns in Java. While the approach taken to read CSV files is simple, it's not robust for real-world applications. A more sophisticated solution might involve a database system for managing large datasets.

In future labs, I'll be diving deeper into file I/O for more hands-on experience with managing data.

## Authors

- @[annepham1512](https://github.com/annepham1512)

This project was completed with the support of the Dickinson College Computer Science faculty.
