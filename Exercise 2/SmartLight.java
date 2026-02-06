public class SmartLight extends SmartDevice implements Adjustable {
    private int brightness; // 0-100

    public SmartLight(String deviceName) {
        super(deviceName);
        this.brightness = 0;
    }

    // turnOn/turnOff already correctly manage activeDevicesCount in SmartDevice,
    // but we can leave them inherited or override if you want custom messages.
    // The inherited versions already only change the count when state changes.

    @Override
    public void setLevel(int level) {
        if (!isOn) {
            System.out.println("Cannot adjust: Device is OFF.");
            return;
        }
        if (level < 0) level = 0;
        if (level > 100) level = 100;

        brightness = level;
        System.out.println(deviceName + " brightness set to " + brightness + ".");
    }

    @Override
    public void performSelfDiagnostic() {
        System.out.println("Checking LED health...");
    }
}
