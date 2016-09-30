# Josephus problem in Scala

To run the program, follow the following steps.

You must have Java 1.8, Scala 2.11.8 and SBT 0.13 installed in your environment.

Go to the project directory and hit sbt. Once you are within sbt:

- to compile: `compile`
- to run with n=8 and k=5: `run 8 5`
- to test: `test`

## Test Results:
For this, I used [ScalaTest](http://www.scalatest.org/) and here are the test results.

###JosephusSolutionSpec:

#### JoshphusSolution Main App

- should show usage for no-args
- should show usage for insufficient args=1
- should show usage for more than 2 args
- should show usage for args with 0 value
- should show usage for n < 1
- should show usage for k < 1

#### findSafePosition function
- should work for lower bound inputs 1 and 1
- should work for n=100,000,000, k=1
- should work for n=1000,000, k=2
- should work for n=1000,000, k=100
- should work for n=1, k=100
- should work for n=k even case, n=4, k=4
- should work for n=k odd case, n=5, k=5
- should return 3 for input 3 and 2

Run completed in 1 second, 878 milliseconds.

Total number of tests run: 14

Suites: completed 1, aborted 0

Tests: succeeded 14, failed 0, canceled 0, ignored 0, pending 0

All tests passed.

[success] Total time: 7 s, completed Sep 30, 2016 12:05:13 AM

