# 5. Patterns

## Materials

[Patterns](https://refactoring.guru/design-patterns)

## VideoLectures
- [Patterns(part 1)](https://youtu.be/q5U92-p-a0s)
- [Patterns(part 2)](https://youtu.be/bR7M\_lv52S4)

## Task #5
Read all materials, try to find a `proper` place to your newly learned patterns in our app. There are a lot of design patterns, but we advise you to pay attention to the following ones:
- Singleton;
- ChainOfResponsibility;
- Fabric.
The application of patterns consists not only in their implementation, but also in knowing their weaknesses and strengths. Therefore, in addition to realising the selected design patterns in the code, you must write the following justification for each pattern (you can send it to me in the messenger, or you can add text to README.md): 
- What is the Design Pattern? 
- Where did you apply it? 
- Justify why you chose this one and not another. What do you gain by using chosen Design Pattern?
## Hints
Rethink your application from SOLID point of view. Keep in mind that in addition to implementing multithreading, we will also work with the database and http. In many ways, we will repeat what we did for the console application for both the database and the http layers. It might be worth coming up with some common interfaces that different versions will implement.

## Task #5.1
You need to write unit tests for your project. The task completion score will be calculated based on the standard functionality of IntelliJ IDEA, which allows you to determine the degree of coverage of classes, methods, and strings by tests. More is better. Of course, it is difficult to achieve 100% results. But you need to try to at least bring this figure to 70%.
## Hints
The more compact you code, the easier to cover it with tests. Don’t write any method if you don’t plan to use it. Even if you don’t use it you still have to cover it with tests.

This is such a long-term task. In order to move on, it is enough to implement at least one pattern and write at least one unit test.
Why is one pattern okay? This theme can be developed until the end of the project. For example, when we have implemented three versions of the store: console, bd, http. Then we can implement the Factory. 
In general, when you come up with where to hang a new pattern, just write that this way and that, for implementing such and such a pattern, for such and such classes for such and such reasons. And I will add you points for the implementation of the new pattern. 
To pass and move on, one pattern is enough, but with patterns as with unit tests, they can be submitted until the end of the projects. Therefore, I check the tests every time there is a new pull request and update the assessment for this task. 
The same goes for Design Patterns. Today the project is too simple to use patterns there, and tomorrow something new will appear and there will be room for another pattern.
