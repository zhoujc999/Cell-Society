module Cellular_Automaton {

    requires transitive javafx.fxml;

    requires transitive javafx.base;
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires transitive java.xml;

    exports View to javafx.graphics, javafx.fxml;

    opens View to javafx.graphics, javafx.fxml;

}
