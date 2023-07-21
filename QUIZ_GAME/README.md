
# StudyBuddy - A Question-Based Practice Tool

## Introduction

`StudyBuddy` is a comprehensive practice tool tailored to quiz users with various types of questions. It offers a unique way to enhance learning through interactive Q&A sessions.

## Question Types

`StudyBuddy` supports three types of questions:

1. **True/False**
2. **Multiple Choice**
3. **Fill in the Blank**

## User Interaction

Upon launching, the program prompts the user to specify a file containing questions. Once loaded, it asks how many questions the user wishes to attempt. The application ensures a graceful handling of any input errors.

For each question, users have three possible actions:

1. **Answer** the question.
2. **Pass** on the question (no points gained or lost).
3. **Delay** the question to revisit it later.

A correct answer adds the question's point value to the user's score, while an incorrect answer deducts the same amount.

## File Format for Questions

Questions are sourced from a user-specified file. Each line represents a single question, with its attributes delimited by semicolons (`;`). No question or answer should contain a `;`.


## FileReader Utility

To assist in reading files, we've provided the `FileReader` utility.

