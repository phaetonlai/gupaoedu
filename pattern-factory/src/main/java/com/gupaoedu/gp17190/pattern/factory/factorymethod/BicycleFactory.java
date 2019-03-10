package com.gupaoedu.gp17190.pattern.factory.factorymethod;

import com.gupaoedu.gp17190.pattern.factory.Bicycle;
import com.gupaoedu.gp17190.pattern.factory.IVehicle;

public class BicycleFactory implements IVehicleFactory {
    private static final BicycleFactory instance = new BicycleFactory();

    public static BicycleFactory getInstance() {
        return instance;
    }

    private BicycleFactory(){}

    public IVehicle genVehicle() {
        return new Bicycle();
    }
}
