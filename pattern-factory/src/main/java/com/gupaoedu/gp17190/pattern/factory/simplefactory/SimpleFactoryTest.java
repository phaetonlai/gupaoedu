package com.gupaoedu.gp17190.pattern.factory.simplefactory;

import com.gupaoedu.gp17190.pattern.factory.Bicycle;
import com.gupaoedu.gp17190.pattern.factory.Car;

public class SimpleFactoryTest {
    public static void main(String[] args) throws Exception {
        VehicleFactory factory = VehicleFactory.getInstance();
        Bicycle bicycle = (Bicycle) factory.genVehicle(Bicycle.class);
        bicycle.run();

        Car car = (Car) factory.genVehicle(Car.class);
        car.run();
    }
}
