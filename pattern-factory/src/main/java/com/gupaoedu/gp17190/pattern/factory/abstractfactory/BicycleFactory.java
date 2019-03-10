package com.gupaoedu.gp17190.pattern.factory.abstractfactory;

public class BicycleFactory implements IVehicleFactory {
    private static final BicycleFactory instance = new BicycleFactory();

    public static BicycleFactory getInstance() {
        return instance;
    }

    private BicycleFactory(){}

    public IWheel genWheel() {
        return new BicycleWheel();
    }

    public IBrake genBrake() {
        return new BicycleBrake();
    }
}
