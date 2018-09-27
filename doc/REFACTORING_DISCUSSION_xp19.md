# Checklist Refactoring

# Duplication Refactoring
There is no duplicated code that is detected by the tool.

# General Refactoring
I chose to refactor my CellGridPane. Right now, my CellGridPane class is not 
flexible at all as it only works for square grids. So, I convert my CellGridPane
into an interface. Then I add a new RectCellGridPane that implements CellGridPane 
interface. If I want to add new types of grid pane like hexagon grid pane or 
triangular grid pane, I can just create a new class that implements this interface
and add its corresponding implementation inside the new class.

# Longest Method Refactoring
Currently, in my *View* package, there is no particularly long method.