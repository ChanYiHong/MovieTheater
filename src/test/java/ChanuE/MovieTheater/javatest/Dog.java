package ChanuE.MovieTheater.javatest;

public class Dog extends Animal{

    public Dog(String owner) {
        super(owner);
    }

    @Override
    public void method1() {
        System.out.println("bow");
    }

    public void method2() {
        System.out.println("과연 나올까?");
    }
}
