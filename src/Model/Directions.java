package Model;

/**
 * Abstract representation of directions. Includes method that returns the direction vector.
 *
 * @author jz192
 */


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

    public enum FourDirections {
        N (0, -1),
        W (-1, 0),
        E (1, 0),
        S (0, 1);

        // declaring private variable for getting values
        private Point vector;

        // getter method
        public Point getDirection()
        {
            return this.vector;
        }

        FourDirections(int x, int y)
        {
            this.vector = new Point(x, y);
        }
    }

    public enum SixDirections {
        NW (-1, -1),
        N (0, -1),
        NE (1, -1),
        W (-1, 0),
        E (1, 0),
        S (0, 1);


        // declaring private variable for getting values
        private Point vector;

        // getter method
        public Point getDirection()
        {
            return this.vector;
        }

        SixDirections(int x, int y)
        {
            this.vector = new Point(x, y);
        }
    }

    public enum TwelveDirections {
        N (0, -1),
        NE (1, -1),
        NEE (2, -1),
        WW (-2, 0),
        W (-1, 0),
        E (1, 0),
        EE (2, 0),
        SWWW (-3, 1),
        SWW (-2, 1),
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

        TwelveDirections(int x, int y)
        {
            this.vector = new Point(x, y);
        }
    }

    public enum ThreeDirections {

        W (-1, 0),
        E (1, 0),
        SW (-1, 1);

        // declaring private variable for getting values
        private Point vector;

        // getter method
        public Point getDirection()
        {
            return this.vector;
        }

        ThreeDirections(int x, int y)
        {
            this.vector = new Point(x, y);
        }
    }


}
