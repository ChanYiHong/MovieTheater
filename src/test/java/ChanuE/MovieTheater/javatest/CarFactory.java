package ChanuE.MovieTheater.javatest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CarFactory {

    public static CarFactory carFactory = new CarFactory();
    private static HashMap<String, Car> hashMap = new HashMap<String, Car>();

    private CarFactory(){}

    public static CarFactory getInstance(){
        return carFactory;
    }

    public Car createCar(String name){
        if(!hashMap.containsKey(name)) {
            hashMap.put(name, new Car(name));
        }
        return hashMap.get(name);
    }

    public static void print(){
        Set<String> keySet = hashMap.keySet();
        keySet.stream()
                .forEach(k -> {
                    String key = k.toString();
                    Car car = hashMap.get(key);
                    System.out.println(key + " = " + car);
                });
    }
}
