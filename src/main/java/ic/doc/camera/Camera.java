package ic.doc.camera;

public class Camera implements WriteListener {

  private boolean isOn;
  final private Sensor sensor;

  Camera(Sensor sensor) {
    this.sensor = sensor;
    isOn = false;
  }

  public void pressShutter() {
    // not implemented
  }

  public void powerOn() {
    sensor.powerUp();
  }

  public void powerOff() {
    // not implemented
  }

  @Override
  public void writeComplete() {
  }
}

