package ic.doc.camera;

public class Camera implements WriteListener {

  private boolean isOn;
  private final Sensor sensor;
  private final MemoryCard memoryCard;

  Camera(Sensor sensor, MemoryCard mc) {
    this.sensor = sensor;
    memoryCard = mc;

    isOn = false;
  }

  public boolean pressShutter() {
    if (!isOn) {
      return false;
    }
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
