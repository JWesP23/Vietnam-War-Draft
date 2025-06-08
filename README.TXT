# Vietnam Draft Lottery

A Java terminal application that tells users whether they would have been drafted into the Vietnam War based on their birthday.

## Description

This program reads data from a text file containing the draft order for each day of the year. The user inputs their birthday (in MM/DD format), and the program:

- Determines their draft number and whether they would have been drafted
- Provides the closest alternate date that would have resulted in a different outcome
- Continues to prompt the user until they choose to exit

The application demonstrates use of arrays, file input, exception handling, recursion, and polymorphism.

## Features

- Reads draft data from a `.txt` file
- Uses an array of custom `Date` objects (`DateDrafted`, `DateNotDrafted`)
- Handles invalid input and provides user-friendly error messages
- Allows repeated queries in a single session

## How to Run

## Requirements

- Java JDK 11 or higher

## How to Run
```bash
git clone https://github.com/JWesP23/Vietnam-War-Draft.git
cd Vietnam-War-Draft
javac *.java
java VietnamDraftLottery
```
