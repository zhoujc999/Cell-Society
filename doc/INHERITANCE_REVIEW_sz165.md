Inheritance Discussion Sep 20
=====================
###Part 1
1. Implementation decision encapsulating: xml parser
2. Hierarchy: there's hierarchy in terms of stage including frames. Other than that, there's no apparent hierarchies since I'm just implementing the controller
3. Closed and open: since I'm implementing a single controller, there aren't much area for opening. Basically everything is closed.
4. Exception: file format exception. If the file chosen is not of the format i desire, I will ask the user to choose again
5. My design is good since it's very independent of the viewer and the model. I will also try to handle errors that can happen with viewer

###Part 2
1. The controller has to rely on the implementation of the model. It will initiate it and pass it to the viewer. It is up to the viewer to render the correct content.
2. Implementation
3. By checking error and handling problematic return value
4. By the time this log is written, there hasn't been plans for inheritance in the controller

###Part 3
1. 
    * Users invoking different modes:
        * game of life
        * ocean
        * racial separation
        * forest fire
    * Users adjusting different parameters:
        * ratio
        * animation speed
        * grid size
    * None of this will be directly received by the controller, but the controller will pass changes to the corresponding handler
2. I'm most excited about handling different simulation cases
3. I'm most worried about handling different grid shapes