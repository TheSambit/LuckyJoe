# Josephus problem in Scala

To run the program, follow the following steps. 

You must have Java 1.8, Scala 2.11.8 and SBT 0.13 installed in your environment.

Go to the project directory and hit sbt. Once you are within sbt:

=> to compile: `compile`
=> to run with n=8 and k=5: `run 8 5`
=> to test: `test`

# Here are the test results:
[info] JosephusSolutionSpec:
[info] JoshphusSolution Main App
[info] - should show usage for no-args
[info] - should show usage for insufficient args=1
[info] - should show usage for more than 2 args
[info] - should show usage for args with 0 value
[info] - should show usage for n < 1
[info] - should show usage for k < 1
[info] findSafePosition function
[info] - should work for lower bound inputs 1 and 1
[info] - should work for n=100,000,000, k=1
[info] - should work for n=1000,000, k=2
[info] - should work for n=1000,000, k=100
[info] - should work for n=1, k=100
[info] - should work for n=k even case, n=4, k=4
[info] - should work for n=k odd case, n=5, k=5
[info] - should return 3 for input 3 and 2
[info] Run completed in 1 second, 878 milliseconds.
[info] Total number of tests run: 14
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 14, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
[success] Total time: 7 s, completed Sep 30, 2016 12:05:13 AM

