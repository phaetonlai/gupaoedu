package com.gupaoedu.gp17190.pattern.factory.simplefactory;

import com.gupaoedu.gp17190.pattern.factory.IVehicle;

public class VehicleFactory {
    private static VehicleFactory ourInstance = new VehicleFactory();

    public static VehicleFactory getInstance() {
        return ourInstance;
    }

    private VehicleFactory() {
    }

    public IVehicle genVehicle(Class clazz) throws Exception{
        if (null != clazz) {
            return (IVehicle) clazz.newInstance();
        }
        return null;
    }
}
