Model Society Group 14
=====================

* Pu Xi
* Arthur Zhao
* Jingchao Zhou

### Introduction

The objective of this project is to develop a program that simulates Cellular Automata. Using the program, the user will be able to choose the model they want to simulate, set up parameters and run and stop the simulations at their own wills. 

From the developer perspective, this program should also be extendable in the sense that it is easy to add a new model or to add a new parameter into a current model. The rules governing the models should also be easily modified. 

In terms of architecture, this project will adopt the MVC (Model, View, Controller) model as the overarching structure of the program. View renders the Model and passes raw user inputs to the controller. Controller, then interprets the inputs and passes the commands to the Model according to different user actions, The model respond to the commands and produces an output.

### Overview

![Image of OverView](https://image.ibb.co/b6zbme/Screen_Shot_2018_09_17_at_2_21_18_AM.png)

At the front end, we will mainly be working with classes from the view part of the MVC framework. They are ViewManager, SimulationView, InfoView and SettingsView. 
ViewManager coordinates between the various views. SimulationView is the class that renders the scene for the simulation. This class would initialize and update JavaFx objects based on the model. There will also be an InfoView class that display the results or information from the simulation. The SettingsView will also have buttons and UI elements that allow user input to set the simulation settings. 

Moving on to the controller part, we have the MainController that receives the input from the EventHandler, which receives input from users through the UI and passes that information to controller. The MainController instantiates an XMLParser to receive the initial parameters, and creates the various classes in Model and configures them to their initial states by calling their methods. The JavaFx Timeline will be implemented in the MainController class so that it is in charge of updating the models and calling the render methods in the View classes.

Finally, the Model comprises the various classes that represent the objects in the simulations. To represent a simulation, we have a Simulation class, which contains cell and Grid class. Simulation class hold variables of the current simulation and facilitates interaction between cells and grid.

### User Interface

Users will interact with our program through a graphic user interface. The GUI contains several components: a slide bar to control the speed of simulation, two number input fields that control the width and height of the grid, four buttons for starting, stopping, resetting and stepping through the simulation and a file picker. The program will give users a warning if the number input fields are empty. 
Here is a picture of the layout of GUI.

![Image of UI](https://image.ibb.co/ijuTXK/unnamed.png)

### Design Details

Model will contain four classes: Grid, Model, Simulation. Model is going to be an abstract class that represents individual cells in. It’s going to have several methods that are reusable for all kinds of simulation, eg. setCurrentState(), updateCurrentState(), getNextState().

Simulation is also an abstract class that represents the generic simulation process. For example, to implement Spreading of Fire, we can create a SpreadingOfFireSimulation class that inherits from Simulation.

View will contain 3 primary classes: SceneManager, GridView and SettingsView. SceneMannager keeps track of the state of every UI element like buttons, slider and input field in the window. GridView is responsible for receiving the objects in model and rendering them. For example, it will update individual cells’ colors based on its current state each frame. Controller uses a XML Parser Class that is responsible for parsing XML files. It contains parameters for initializing a particular simulation and will have a method that checks if the parsed XML file matches the specified format. If not, it will give users a warning message that prompts them to use a file with valid format. EventHandler is responsive to user interaction from UI and for calls methods in Controller class. Controller serves as a middleman between model and view. It will contain method like start(), stop(), reset() and step(). Each method will be invoked by EventHandler once the corresponding button is clicked by users. 

* To apply rules to a cell, first we will count the number of neighbors by calling the getNumOfNeighbors() for the cell and call setState() to set the cell to appropriate state.

* To move to next generation, iterate through each cell in the Grid and call getNextState() on each cell.

* To set a simulation parameter, set the value of a parameter, probCatch, for a simulation, Fire, based on the value given in an XML fire. To set a simulation parameter like probCatch, we can use XML parser to parse the XML file in order to get the value of probCatch and then pass the value to the SpreadingOfFireSimulation class.

* To switch simulation, choose a simulation name that you want to switch to. Call the method renderView() in the Controller class to render the new simulation on the screen.

The inheritance structure for Simulation and Model is desirable here because it makes the program more flexible. Whenever we need to add new simulation behaviors, we can just create a new class that inherits from Simulation and implement its specific behavior. The MVC model is also effective, especially in this team project. Team members can easily divide responsibility and code each individual component without worrying about the detailed implementation of other parts.

### Design Considerations

* Implementation of center cell vs edge cell.

    We had some discussion on how to best implement this functionality. Some ideas brought up were using a bounds-checking method, having an extra outer “invisible” layer of cells, or subclass the cells into different types. The first 2 seems to be more straightforward if we assume a rectangular grid, where each center cell has 4 neighbours while each edge cell has 2 neighbours. However, it would be difficult to implement if the shape of the cells were to be triangular or hexagonal. We eventually decided that having a variable in each cell indicating whether it is an edge might be ideal.

* Superclass for every new simulation to extend

    We also discussed how we should support the different algorithms for each type of simulation. We initially thought of placing the algorithms in the controller, and having the controller tell the models how to respond to changes. However, we realized this was not feasible and decide to have cell as a superclass or interface. Cells using different algorithms will be subclasses of the cell class. They would have mostly the same public methods but differ in terms of private methods and implementation.

* Subclasses for different states

    Another point of discussion brought up was whether to have different classes for different states of cells. This has the benefit of not having our cells cluttered with conditional statements when there are many states and the rules depends on the state of the cell. However, this would result in many classes and might not be necessary. Our team did not come to a consensus on this issue at the moment and agreed to raise it up at the next discussion.
    
    
### Team Responsibilities

Since this project adopts an MVC framework, the pipeline of the project is naturally divided into three parts: Model design, Controller design, and Viewer design. According to individual interests and strengths, Jingchao will be responsible for Model, Arthur will take charge of the Controller, and Xi will be in charge of the Viewer.

The members, though, are well aware of the complexity of the project logic and that Model, Viewer and Controllers interact with each other in a complicated way. Each member, therefore, will be primarily responsible for implementing the structure of our respective class, while all team members will work on convoluted methods and details together to ensure the flow of the project.

The team also noticed that in Basic Implementation, it needs to implement four different simulations. The secondary responsibility of the team members, therefore, is to design the algorithms for one to two simulations. Then the team members will have discussions to evaluate the algorithms and determine how to incorporate the algorithms into the models in a clean and extendible manner.