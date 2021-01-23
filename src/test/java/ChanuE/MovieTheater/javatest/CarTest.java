package ChanuE.MovieTheater.javatest;

import java.util.Set;

public class CarTest {

    public static void main(String[] args) {

        CarFactory carFactory = CarFactory.getInstance();

        Car car1 = carFactory.createCar("희수");
        Car car2 = carFactory.createCar("희수");
        System.out.println(car1 == car2);

        Car car3 = carFactory.createCar("재현");
        Car car4 = carFactory.createCar("재현");
        System.out.println(car3 == car4);

        System.out.println(car1 == car2);

        CarFactory.print();

    }

}
