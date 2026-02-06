public class SmartThermostat extends SmartDevice implements Adjustable {
    private int temperature; // 60-80

    public SmartThermostat(String deviceName) {
        super(deviceName);
        this.temperature = 70; // reasonable default
    }

    @Override
    public void turnOn() {
        System.out.println("HVAC System Starting...");
        super.turnOn();
    }

    @Override
    public void setLevel(int level) {
        if (!isOn) {
            System.out.println("Cannot adjust: Device is OFF.");
            return;
        }
        if (level < 60) level = 60;
        if (level > 80) level = 80;

        temperature = level;
        System.out.println(deviceName + " temperature set to " + temperature + " degrees.");
    }

    @Override
    public void performSelfDiagnostic() {
        System.out.println("Checking thermostat sensors...");
    }
}