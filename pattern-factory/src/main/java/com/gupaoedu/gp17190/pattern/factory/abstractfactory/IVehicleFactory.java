package com.gupaoedu.gp17190.pattern.factory.abstractfactory;

public interface IVehicleFactory {
    IWheel genWheel();

    IBrake genBrake();
}
