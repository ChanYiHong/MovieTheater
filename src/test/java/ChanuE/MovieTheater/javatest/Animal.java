package ChanuE.MovieTheater.javatest;

public abstract class Animal {

    public String owner;

    public Animal(String owner) {
        this.owner = owner;
    }

    public void info(){
        System.out.println("whos?: " + owner);
    }

    public abstract void method1();

}
