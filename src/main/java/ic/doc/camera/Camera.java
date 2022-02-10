package ic.doc.camera;

public class Camera implements WriteListener {

  private boolean isOn;
  private boolean isIdle;
  private final Sensor sensor;
  private final MemoryCard memoryCard;

  Camera(Sensor sensor, MemoryCard mc) {
    this.sensor = sensor;
    memoryCard = mc;
    isOn = false;
    isIdle = true;
  }

  public void pressShutter() {
    if (!isOn) {
      return;
    }

    memoryCard.write(sensor.readData());
    isIdle = false;
  }

  public void powerOn() {
    isOn = true;
    sensor.powerUp();
  }

  public void powerOff() {
    if (isIdle) {
      sensor.powerDown();
      isOn = false;
    }
  }

  @Override
  public void writeComplete() {
    isIdle = true;
    sensor.powerDown();
  }
}
