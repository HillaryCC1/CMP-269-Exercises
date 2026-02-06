import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<SmartDevice> homeHub = new ArrayList<>();

        SmartLight livingRoomLight = new SmartLight("Living Room Light");
        SmartLight kitchenLight = new SmartLight("Kitchen Light");
        SmartThermostat hallwayThermostat = new SmartThermostat("Hallway Thermostat");

        homeHub.add(livingRoomLight);
        homeHub.add(kitchenLight);
        homeHub.add(hallwayThermostat);

        // Logic Test
        livingRoomLight.turnOn();
        hallwayThermostat.turnOn();

        // Should fail because Kitchen Light is still off
        kitchenLight.setLevel(75);

        System.out.println("Active devices count: " + SmartDevice.activeDevicesCount);

        // Polymorphism test
        for (SmartDevice device : homeHub) {
            device.performSelfDiagnostic();
        }
    }
}