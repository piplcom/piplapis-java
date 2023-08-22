package com.pipl.api;

import com.pipl.api.data.fields.Vehicle;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class VehicleTest {

    private static final String vin = "1FTWW31R98ED96001";

    @Test
    public void testIsVinChecksumValid() {
        Vehicle vehicle = new Vehicle(vin);
        boolean valid = vehicle.isVinChecksumValid();
        assertTrue(valid);
    }

    @Test
    public void testIsValid() {
        Vehicle vehicle = new Vehicle(vin);
        boolean valid = vehicle.isVinValid();
        assertTrue(valid);
    }
}
