package Models;

public class Directions {
    public enum EightDirections {
        NW (-1, -1),
        N (0, -1),
        NE (1, -1),
        W (-1, 0),
        E (1, 0),
        SW (-1, 1),
        S (0, 1),
        SE (1, 1);

        // declaring private variable for getting values
        private Point vector;

        // getter method
        public Point getDirection()
        {
            return this.vector;
        }

        EightDirections(int x, int y)
        {
            this.vector = new Point(x, y);
        }
    }

}
