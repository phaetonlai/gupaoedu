package com.gupaoedu.gp17190.pattern.factory.abstractfactory;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        IBrake brake = CarFactory.getInstance().genBrake();
        brake.brake();

        brake = BicycleFactory.getInstance().genBrake();
        brake.brake();

        IWheel wheel = CarFactory.getInstance().genWheel();
        wheel.turn();

        wheel = BicycleFactory.getInstance().genWheel();
        wheel.turn();
    }
}
