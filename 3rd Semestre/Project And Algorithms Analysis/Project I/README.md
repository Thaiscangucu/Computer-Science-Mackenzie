# Sorting Algorithms Comparison

This project is a C-based application developed for the Design and Analysis of Algorithms course. It compares the performance and logic of two classic sorting algorithms—Insertion Sort and Merge Sort—by processing words from a text file.

## About the Project

The application reads a set of words from an input file, performs lexicographical sorting using two different methods, and records the number of operational steps taken by each algorithm to demonstrate their respective complexities.

### Key Features
* **File I/O**: Reads up to 10,000 words from a source file (in.txt) and outputs the sorted results into separate text files (out1.txt and out2.txt).
* **Insertion Sort Implementation**: An in-place sorting algorithm that builds the final sorted array one item at a time.
* **Merge Sort Implementation**: A divide-and-conquer algorithm that recursively splits the array and merges subarrays back together in order.
* **Step Tracking**: Counts and displays the number of steps performed by each algorithm to provide a basis for complexity analysis.

## Technologies Used

* **Language**: C.
* **Libraries**: Standard I/O (stdio.h), String manipulation (string.h), and Standard Library (stdlib.h).
* **Concepts Applied**: Algorithm complexity, lexicographical comparison, and file management.

## How to Run the Project

1. **Prerequisites**: Ensure you have a C compiler installed, such as gcc.
2. **File Preparation**: Create a file named in.txt in the root directory and populate it with the words you wish to sort.
3. **Compilation**:
   Compile the project using the following command:
   ```bash
   gcc main.c -o sorting_comparison
   ```
4. **Execution**:
  Run the compiled program:
  ```bash
  ./sorting_comparison
  ```

## Authors
Thais Ferreira Canguçu

Pedro Moniz Canto
