# Core Gaming Game Logic Developer - Tech Test 

## The Test 

Purpose of this test is to create a simple game logic based on detailed specification and prove it's correct.

We realise everyone has different levels of skill and experience when it comes to development, so we have split the test into 3 sections. If you do not have the knowledge to complete them all then that's ok, we just want to see how you approach the problem and get a feel for how you code. Also we want to give you an opportunity to present yourself at your best while working in the comfort of your home. 

As a hint have a look for 'Slot Machine' in the search engine of your choice. If you have any questions, please feel free to get in touch with your point of contact. We’ll get back to you as soon as possible. 

### Test Requirements
 * Git
 * Java 8 (JDK 8).
 * Maven 3.5.4.
 * Ability to view *.xlsx files.

## Review Criteria 

We will be looking for: 

* Correct implementation of given tasks.
* Modular/ Code organization. 
* Generic code where appropriate. 
* Clarity/ Self documenting, comments.
* Unit tests usage and results.
* Data/ results correctness.
* Code formatting.
* Exceptions and error handling. 
* Cool, exciting solutions/ideas.

## Submission requirements 

Please replace the contents of this README.md with: 

1. A covering note explaining the technology choices you have made. 

2. Any instructions required to run your solution. 

Email submission as an attachment or link to the git bundled repository showing your commit history with all your commits on the master branch: 

```
git bundle create <anything>.bundle --all --branches 
```

Please note that we will have to return all submissions that:
* Fail to build
* Fail unit tests

### Setup and Details
 * Grab repository contents either via zip or git pull.
 * If working with compressed file, unpack file contents to folder of your choice.
 * In command line move to project's root folder.
 * To build all modules execute:
```
mvn clean install -DskipTests
```
 * To run a http web server execute: 
```
java -jar Server\target\Server-1.0.1-jar-with-dependencies.jar
```
 * To run tests, in another command line window move to 'project root'/Client folder and execute: 
```
mvn test
```
 * Watch the output for any test fail/ pass information.
 * All data tables can be found in 'profile.xlsx' file under titled tabs. You do not have to parse the xlsx.

### Required Tasks Part 1

1. Find and familiarize yourself with Server, Client and unit tests source files. Hint: The 'test' is structured as a simple client - server application. The server is responsible for performing logic and generating responses to client requests. The client sends requests and contains a test suite which is used to validate given behaviours.
2. Have default project up and running. Both defined 'helloRequest*' tests must succeed. Hint: That should be done without any changes to the code by following 'Setup and Details' steps.
3. Add a 'table' client request and implement server response which will return randomly chosen 'Value' from 'BasicWeightTable' based on the percentage 'Chance'. Hint: For this step ignore data in '99% confidence level' column.
4. Implement statistical test or tests which would run multiple 'table' requests and check aggregated responses against the expected occurences defined in 'BasicWeightTable'. Hint: How many times do you think 'Value' '£3.00' will be retuned from the server? For this step you can use '100k runs error margin' value to compare the results with 99% confidence. 
5. Improve communication protocol to use either Json or XML. Update your tests accordingly.

#### Required Tasks Part 2

1. Add a 'spin' request which will return a 3x3 matrix of symbols. Use 'Symbols' and 'Reels' tables. For every reel, draw one random position and populate the whole matrix column with results starting at that drawn position. Hint: Think slot machines with reels/ wheels. How can those behave?
2. For each result where middle row of your matrix satisfies one of the rules defined in 'Win patterns' return the associated 'Value' along with pattern that has been satisfied (if any).
3. Implement statistical test or tests which would run multiple 'table' requests and check aggregated responses against the 'Expected chance' defined in 'Win patterns' table. Hint: Again you can use '100k runs error margin' value to compare the results with 99% confidence. 

#### Extra Tasks (Easy way to stand out)

1. Add 'Return To Player' (RTP) value in percentage for both 'table' and 'spin'. Assuming each request cost '£3.50'. We will not provide you with table to compare your results this time. Hint: To find the requested result compare total cost spent on initiating requests and total value returned in responses. You can use 1% margin error for RTP comparison. 
2. For extra fun, can you change the server - client to work with WebSockets?