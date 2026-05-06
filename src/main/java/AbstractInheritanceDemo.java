
abstract class Shape {

   
    abstract void calculateArea();

    
    void display() {
        System.out.println("Calculating area of shape...");
    }
}

class Rectangle extends Shape {

    int length, breadth;

    Rectangle(int length, int breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    @Override
    void calculateArea() {
        int area = length * breadth;
        System.out.println("Area of Rectangle: " + area);
    }
}


class Circle extends Shape {

    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    void calculateArea() {
        double area = 3.14 * radius * radius;
        System.out.println("Area of Circle: " + area);
    }
}

public class AbstractInheritanceDemo {

    public static void main(String[] args) {

        Shape s1 = new Rectangle(10, 5);
        s1.display();
        s1.calculateArea();

        Shape s2 = new Circle(7);
        s2.display();
        s2.calculateArea();
    }
}
