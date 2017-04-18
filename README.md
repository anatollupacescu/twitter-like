# README #

Implement a console-based social networking application (similar to Twitter) satisfying the scenarios below.

### Scenarios ###

Posting: Alice can publish messages to a personal timeline

> Alice -> I love the weather today
> Bob -> Damn! We lost!
> Bob -> Good game though.

Reading: Bob can view Alice’s timeline

> Alice
I love the weather today (5 minutes ago)
> Bob
Good game though. (1 minute ago)
Damn! We lost! (2 minutes ago)

Following: Charlie can subscribe to Alice’s and Bob’s timelines, and view an aggregated list of all subscriptions

> Charlie -> I'm in New York today! Anyone want to have a coffee?
> Charlie follows Alice
> Charlie wall
Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)
Alice - I love the weather today (5 minutes ago)

> Charlie follows Bob
> Charlie wall
Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)
Bob - Good game though. (1 minute ago)
Bob - Damn! We lost! (2 minutes ago)
Alice - I love the weather today (5 minutes ago)

### Details ###

The application must use the console for input and output.
Users submit commands to the application. There are four commands. “posting”, “reading”, etc. are not part of the commands; commands always start with the user’s name.

* posting: <user name> -> <message>
* reading: <user name>
* following: <user name> follows <another user>
* wall: <user name> wall

Don't worry about handling any exceptions or invalid commands. Assume that the user will always type the correct commands. Just focus on the sunny day scenarios.
Don’t bother making it work over a network or across processes. It can all be done in memory, assuming that users will all use the same terminal.
Non-existing users should be created as they post their first message. Application should not start with a pre-defined list of users.
Provide instructions on how to run the application.

### Feedback ###

Pros: 

* Descriptive tests in "TwitterlikeTest"
* Has unit tests

Improvements: 

- I can't see baby steps in your commits. The system grew in 8 commits.
- No acceptance tests
- App class is not tested, and it has a lot of code.
- App#start() has more than one responsability
- App#start() has magic numbers
- App#start() Could be splitted in use cases or commands
- The wording "works" is implicit when you write a test. In the test name we usually write the intention of the test. In this case you can use FormatterShould#formatRelativeDatesWhenTimeDifferenceIsZero
- You have only 2 tests but 10 classes.
- TwitterLikeTest Instead of using Mockito's verify printstream you could use an abstraction that represents that concept. We usually use that kind of abstractions for concepts that are outside of our control for ex: External systems, time, frameworks, etc...
- TwitterLikeTest is testing more than one logical concept in its assertions.
- TimelinePresenter. I don't think this class adds anything to the code. Maybe as an Interface to abstract the concept of a Presenter


### To run the app ###

mvn clean test exec:java -Dexec.mainClass="com.test.App"
