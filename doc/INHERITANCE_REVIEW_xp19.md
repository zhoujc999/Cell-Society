## Part 1
1. I encapsulated my event handler for UI elements and settings for them.
2. I'm not planning to use inheritance hierarchies in my part because I'm responsible
for the frontend part. I will be more likely to use inheritance if I'm responsible for
the backend or model class.
3. I make the event handler closed for modification but UI elements open for extension.
4. When users choose a file from the file chooser, they may cause a file-not-found error.
I will handle it by using a try-and-catch block and possibly pop up a warning dialog box.
5. I think my design is good because I separate the UI elements with application logic and avoid 
lots of potential boiler-plate code for setting up UI elements. It also makes it easier
to change certain attributes for UI elements in a separate file.

## Part 2

1. My UIManager class is dependent on the Controller's event handler methods.
2. Yes, these dependencies based on the other class's behavior.
3. Merge UIManager into Controller.
4. In my part, there is no inheritance relationship.


## Part 3
1. * respond to users mouse click
    * let users pick an xml file
    * check potential bad input from users
    * pass user input to backend
2. I'm most excited to work on implementing the GUI.
3. I'm most worried about setting up grid view that connects to the backend.