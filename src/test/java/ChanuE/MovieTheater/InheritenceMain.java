package ChanuE.MovieTheater;

public class InheritenceMain {

    public static void main(String[] args) {

        Mother person1 = new Child1();

        person1.method1();

        Child1 person2 = new Child1();

        person2.method1();
        person2.method2();

        Mother mother = new Mother();

        mother.method1();

    }

}
