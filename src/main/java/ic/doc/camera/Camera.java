package ic.doc.camera;

public class Camera implements WriteListener {

  private boolean isOn;
  private boolean isIdle;
  private final Sensor sensor;
  private final MemoryCard memoryCard;
  private final byte[] data;

  Camera(Sensor sensor, MemoryCard mc, byte[] d) {
    this.sensor = sensor;
    memoryCard = mc;
    this.data = d;
    isOn = false;
    isIdle = true;
  }

  public void pressShutter() {
    if (!isOn) {
      return;
    }
    sensor.readData();
    memoryCard.write(data);
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
