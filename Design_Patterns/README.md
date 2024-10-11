# LAB 06: Using the Singleton and Adapter Design Patterns

## Introduction
This lab provides an opportunity to implement the Singleton and Adapter design patterns. Additionally, the lab introduces you to two Java features that we have not yet studied in detail:
1. The `enum` keyword, which allows you to define a new data type that enumerates several named options.
2. Reading data from a file using `BufferedReader`, `FileReader`, and `readLine()`.

These features will not be tested in exams or other homework, so you should be able to use them based on the provided example code without further background reading. It may also be helpful to reference the `designpatterns` folder from the class-examples repository.

## Getting Started
1. Accept the assignment from GitHub Classroom, clone your GitHub repository, and import the starter code into Eclipse as a Java Project.
2. Remove any compilation errors in the starter code by adding JUnit5 to your project’s build path.

## The Assignment

### 3.1 Preliminaries
Familiarize yourself with the starter code:
- Draw a class diagram showing the relationships among classes. This diagram is for your own use and need not be submitted for grading.
- Run the `main` method in `ArtistData.java` and inspect the code to understand how it works.
- Run all the JUnit tests and verify that there are no errors or failures.
- Read the code in every Java file.

Open the two data files (`data/bands.csv` and `data/singers.csv`) to understand how they represent their data. These files store data in a simple format known as comma-separated values (CSV). View these files in Eclipse (right-click + open with Text Editor) or another text editor to see the raw text format, and also open them in a spreadsheet program such as Excel.

**Task A:** Add the data for at least one band to the file `data/bands.csv`. Additionally, add some singers to `data/singers.csv`. Run the `main` method in `ArtistData.java` again and verify that the new bands and singers are printed out correctly.

### 3.2 Creating a Singleton
The `ArtistData` constructor reads data from files on the computer’s permanent storage. To avoid re-reading these files every time a new `ArtistData` object is constructed, we can improve the design by making `ArtistData` into a singleton using the singleton design pattern.

**Task B:** Make all necessary alterations to change the `ArtistData` class into a singleton.
- This should require changes to four Java files.
- Ensure that all JUnit tests still pass after your changes.

### 3.3 Creating an Adapter
The `ArtistData` class currently reads data only for artists of type `Band` or `Singer`. We now extend the code to also store data about classical composers using the adapter design pattern. The current online music store that supports only bands and singers is partnering with an existing online music store that supports only classical music by classical composers. The code in `Composer.java` may not be altered in any way. Instead, you must use the adapter pattern to create a suitable new data type that can be used with the existing classes such as `ArtistData`, `MusicStore`, and `ArtistAnalyzer`.

**Task C:**
1. Using the adapter pattern, implement a new data type for classical composers. Follow a class naming convention similar to that in the in-class examples.
2. Create JUnit tests for your new class.
3. Alter `ArtistData.java` so that in addition to the bands and singers files, it reads the composers file `data/composers.csv`.
4. Run the `main` method in `ArtistData` and ensure that the composer data is printed out as expected. Also, check that all JUnit tests still pass.

## Submitting Your Solution
Push your code (and data files) to GitHub regularly for backup purposes and push your final version to submit the assignment. Additionally, submit your lab report via Moodle. The lab report for this lab will consist only of the self-assessment report. Be sure to mention whose repository holds the code to grade!

## Additional Remark
The code in this lab is an introduction to elementary techniques for reading data from files and manipulating that data. However, professional-quality code for managing data would usually employ a different approach, such as using a database rather than CSV files. The simple code provided here for reading CSV files is not robust. Please see the Wikipedia page on CSV files for some ideas on why the approach needs to be more sophisticated. In a future lab, we’ll work more “hands on” with file IO to get better practice figuring out exactly what our `FileReaders` are doing.

## Authors

- @[annepham1512](https://github.com/annepham1512)