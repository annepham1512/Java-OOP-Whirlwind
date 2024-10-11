# Using the Singleton and Adapter Design Patterns

## Introduction
In this project, I explored two important software design patterns: the Singleton and Adapter patterns. The project also introduced me to the `enum` keyword for defining enumerated types and demonstrated basic file reading techniques in Java, such as using `BufferedReader`, `FileReader`, and `readLine()`.

Through this lab, I was able to apply these concepts to real-world scenarios, such as reading data from CSV files and modeling a music store database. I completed this project with the support of the Dickinson College Computer Science faculty.

## Getting Started
1. Clone the GitHub repository assigned through GitHub Classroom and import the starter code into Eclipse as a Java Project.
2. Add JUnit5 to the project's build path to resolve any compilation issues.

## The Assignment

### Preliminaries
To begin, I explored the starter code and developed a class diagram to understand the relationships between the different classes. I then ran the `main` method in `ArtistData.java` to observe how the code worked and made sure to run all JUnit tests, confirming that the provided code was functioning correctly.

I reviewed the `data/bands.csv` and `data/singers.csv` files, which contained band and singer data in CSV format.

**Task A:** I added my own data for a new band to the `bands.csv` file and updated the `singers.csv` file with additional singers. After running the `main` method in `ArtistData.java`, I verified that my additions were printed correctly.

### Implementing the Singleton Pattern
To improve the efficiency of the `ArtistData` class, I transformed it into a singleton, ensuring that the CSV files are read only once, even if multiple instances of `ArtistData` are created.

**Task B:** I made the necessary changes across four Java files to implement the Singleton pattern. After implementing these changes, I re-ran the JUnit tests to ensure they all passed.

### Implementing the Adapter Pattern
The `ArtistData` class initially handled only bands and singers. To extend its functionality and incorporate data about classical composers, I used the Adapter design pattern. This approach allowed me to introduce a new data type for composers without modifying the existing `Composer.java` file.

**Task C:**
1. I created an adapter class for composers, following a naming convention similar to the examples provided in class.
2. I wrote JUnit tests to validate the new adapter class.
3. I modified `ArtistData.java` to read composer data from a new `data/composers.csv` file, alongside the existing band and singer data.
4. After running the `main` method in `ArtistData.java`, I confirmed that the composer data was printed correctly, and all JUnit tests continued to pass.

---

## How to Run

1. **Clone** this repository and import it into Eclipse as a Java Project.
2. Add the `JUnit5` library to your build path, if necessary.
3. Update the `data/bands.csv`, `data/singers.csv`, and `data/composers.csv` files with your own band, singer, and composer data.
4. Run the `main` method in `ArtistData.java` to see the output, which should include data for bands, singers, and composers.

---

## Submitting Your Solution
For submission, I regularly pushed my code to GitHub for backup purposes. I pushed the final version of my code and data files to GitHub and submitted the self-assessment report through Moodle, as required.

---

## Additional Notes
This project introduces basic file I/O techniques and showcases how to apply common design patterns in Java. Although the approach for reading CSV files is basic, professional software typically uses more robust solutions, such as databases, for managing large datasets.

In future labs, I look forward to gaining more hands-on experience with file I/O and exploring more sophisticated approaches.

## Authors

- @[annepham1512](https://github.com/annepham1512)

This project was completed with the support of the Dickinson College Computer Science faculty.
