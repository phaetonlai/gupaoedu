package com.gupaoedu.gp17190.pattern.factory.factorymethod;

import com.gupaoedu.gp17190.pattern.factory.Bicycle;
import com.gupaoedu.gp17190.pattern.factory.Car;

public class FactoryMethodTest {
    public static void main(String[] args) {
        Bicycle bicycle = (Bicycle) BicycleFactory.getInstance().genVehicle();
        bicycle.run();

        Car car = (Car) CarFactory.getInstance().genVehicle();
        car.run();
    }
}
