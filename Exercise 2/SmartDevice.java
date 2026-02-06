public abstract class SmartDevice implements Powerable {
    protected String deviceName;
    protected boolean isOn;

    // Tracks how many devices are currently ON across the entire house
    public static int activeDevicesCount = 0;

    public SmartDevice(String deviceName) {
        this.deviceName = deviceName;
        this.isOn = false;
    }

    @Override
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            activeDevicesCount++;
            System.out.println(deviceName + " is now ON.");
        } else {
            System.out.println(deviceName + " is already ON.");
        }
    }

    @Override
    public void turnOff() {
        if (isOn) {
            isOn = false;
            activeDevicesCount--;
            System.out.println(deviceName + " is now OFF.");
        } else {
            System.out.println(deviceName + " is already OFF.");
        }
    }

    public abstract void performSelfDiagnostic();
}
