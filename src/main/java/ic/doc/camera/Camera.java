package ic.doc.camera;

public class Camera implements WriteListener {

  private boolean isOn;
  private final Sensor sensor;
  private final MemoryCard memoryCard;
  static final byte[] data = { 1, 2, 3, 4};

  Camera(Sensor sensor, MemoryCard mc) {
    this.sensor = sensor;
    memoryCard = mc;

    isOn = false;
  }

  public boolean pressShutter() {
    if (!isOn) {
      return false;
    }
    sensor.readData();
    memoryCard.write(data);
    return true;
  }

  public void powerOn() {
    isOn = true;
    sensor.powerUp();
  }

  public void powerOff() {
    sensor.powerDown();
    isOn = false;
  }

  @Override
  public void writeComplete() {}
}
