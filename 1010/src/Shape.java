import java.awt.*;

public class Shape {
    Block[][] shape;
    boolean selected;
    boolean possible;
    Color color;

    Shape() {
        shape = new Block[Properties.max_shape_size][Properties.max_shape_size];
    }

    public void setPossible(boolean set) {
        possible = set;
    }

    boolean isAvailable() {
        return possible;
    }

    int randomDest(int n) {
        return (int) (Math.random() * n) + 1;
    }
}

class Shape1 extends Shape {
    Shape1() {
        color = Properties.shape1Color;
        shape[2][2] = new Block(color);
    }
}

class Shape2 extends Shape {
    Shape2() {
        color = Properties.shape2Color;
        int d = randomDest(2);
        if (d == 1) shape[1][2] = new Block(color);
        if (d == 2) shape[2][1] = new Block(color);
        shape[2][2] = new Block(color);
    }
}

class Shape3 extends Shape {
    Shape3() {
        color = Properties.shape3Color;
        int d = randomDest(4);
        if (d == 1 || d == 2) shape[1][2] = new Block(color);
        if (d == 2 || d == 3) shape[2][1] = new Block(color);
        if (d == 3 || d == 4) shape[3][2] = new Block(color);
        if (d == 4 || d == 1) shape[2][3] = new Block(color);
        shape[2][2] = new Block(color);
    }
}

class Shape4 extends Shape {
    Shape4() {
        color = Properties.shape4Color;
        int d = randomDest(2);
        if (d == 1) {
            shape[1][2] = new Block(color);
            shape[3][2] = new Block(color);
        } else if (d == 2) {
            shape[2][1] = new Block(color);
            shape[2][3] = new Block(color);
        }
        shape[2][2] = new Block(color);
    }
}

class Shape5 extends Shape {
    Shape5() {
        color = Properties.shape5Color;
        shape[1][1] = new Block(color);
        shape[1][2] = new Block(color);
        shape[2][1] = new Block(color);
        shape[2][2] = new Block(color);
    }
}

class Shape6 extends Shape {
    Shape6() {
        color = Properties.shape6Color;
        int d = randomDest(8);
        if (d == 1 || d == 2 || d == 3 || d == 4) shape[1][1] = new Block(color);
        if (d == 3 || d == 4 || d == 5 || d == 6) shape[1][3] = new Block(color);
        if (d == 5 || d == 6 || d == 7 || d == 8) shape[3][3] = new Block(color);
        if (d == 7 || d == 8 || d == 1 || d == 2) shape[3][1] = new Block(color);
        if (d == 1 || d == 2 || d == 4 || d == 7) shape[2][1] = new Block(color);
        if (d == 3 || d == 4 || d == 6 || d == 1) shape[1][2] = new Block(color);
        if (d == 5 || d == 6 || d == 8 || d == 3) shape[2][3] = new Block(color);
        if (d == 7 || d == 8 || d == 2 || d == 5) shape[3][2] = new Block(color);
    }
}

class Shape7 extends Shape {
    Shape7() {
        color = Properties.shape7Color;
        int d = randomDest(4);
        if (d != 1) shape[1][3] = new Block(color);
        if (d != 2) shape[1][1] = new Block(color);
        if (d != 3) shape[3][1] = new Block(color);
        if (d != 4) shape[3][3] = new Block(color);
        if (d == 3 || d == 4) shape[1][2] = new Block(color);
        if (d == 1 || d == 2) shape[3][2] = new Block(color);
        if (d == 1 || d == 4) shape[2][1] = new Block(color);
        if (d == 2 || d == 3) shape[2][3] = new Block(color);
    }
}

class Shape8 extends Shape {
    Shape8() {
        color = Properties.shape8Color;
        int d = randomDest(2);
        if (d == 1) {
            shape[0][2] = new Block(color);
            shape[1][2] = new Block(color);
            shape[3][2] = new Block(color);
            shape[4][2] = new Block(color);
        }
        if (d == 2) {
            shape[2][0] = new Block(color);
            shape[2][1] = new Block(color);
            shape[2][3] = new Block(color);
            shape[2][4] = new Block(color);
        }
        shape[2][2] = new Block(color);
    }
}

class Shape9 extends Shape {
    Shape9() {
        color = Properties.shape9Color;
        shape[1][1] = new Block(color);
        shape[1][2] = new Block(color);
        shape[1][3] = new Block(color);
        shape[2][1] = new Block(color);
        shape[2][2] = new Block(color);
        shape[2][3] = new Block(color);
        shape[3][1] = new Block(color);
        shape[3][2] = new Block(color);
        shape[3][3] = new Block(color);
    }
}