package com.gupaoedu.gp17190.pattern.factory.factorymethod;

import com.gupaoedu.gp17190.pattern.factory.Car;
import com.gupaoedu.gp17190.pattern.factory.IVehicle;

public class CarFactory implements IVehicleFactory {
    private static final CarFactory instance = new CarFactory();

    public static CarFactory getInstance() {
        return instance;
    }

    private CarFactory(){}

    public IVehicle genVehicle() {
        return new Car();
    }
}
