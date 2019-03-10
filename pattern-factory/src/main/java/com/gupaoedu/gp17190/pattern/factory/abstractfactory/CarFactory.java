package com.gupaoedu.gp17190.pattern.factory.abstractfactory;

public class CarFactory implements IVehicleFactory {
    private static final CarFactory instance = new CarFactory();

    public static CarFactory getInstance() {
        return instance;
    }

    private CarFactory() {}

    public IWheel genWheel() {
        return new CarWheel();
    }

    public IBrake genBrake() {
        return new CarBrake();
    }
}
