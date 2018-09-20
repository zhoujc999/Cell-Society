Cell Society : Inheritance Review Questions
===

NetIDs: jz192, fx23

* Part 1

  * What is an implementation decision that your design is encapsulating (i.e., hiding) for other areas of the program?

    The model aspect of the program. Other areas of the program cannot modify the behavior of the simulation. They can only set the simulation and receive snapshots of the simulation.

  * What inheritance hierarchies are you intending to build within your area and what behavior are they based around?

    Abstract classes for cell, grid, simulation.

  * What parts within your area are you trying to make closed and what parts open to take advantage of this polymorphism you are creating?

    All objects in simulation are closed. Other parts need to set the initial state and query it to get the state of the simulation.

  * What exceptions (error cases) might occur in your area and how will you handle them (or not, by throwing)?

    It would throw IllegalArgumentException.
    
  * Why do you think your design is good (also define what your measure of good is)?

    There is a clear separation of the different objects. It also uses Enum class to represent different states of the cells. 

* Part 2

  * How is your area linked to/dependent on other areas of the project?

    It receives the initial parameters from the Controller and provides a representation of the simulation when View calls one of its methods.

  * Are these dependencies based on the other class's behavior or implementation?

    Yes. The other classes must pass in the initial parameters in the correct format.

  * How can you minimize these dependencies?

  * Go over one pair of super/sub classes in detail to see if there is room for improvement.

    I would like to implement interfaces.

  * Focus on what things they have in common (these go in the superclass) and what about them varies (these go in the subclass).

    The initialization methods are similar while the calculateNextState methods are different.

* Part 3

  * Come up with at least five use cases for your part (most likely these will be useful for both teams).

    Abstract methods and classes.

  * What feature/design problem are you most excited to work on?

    The fire spreading simulation.
    
  * What feature/design problem are you most worried about working on?

    Ensuring the efficiency of the various methods.

    