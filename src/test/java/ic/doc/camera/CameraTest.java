package ic.doc.camera;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class CameraTest {

  @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

  Sensor sensor = context.mock(Sensor.class);
  MemoryCard mc = context.mock(MemoryCard.class);

  Camera cam = new Camera(sensor);
  @Test
  public void switchingTheCameraOnPowersUpTheSensor() {


    context.checking(
        new Expectations() {
          {
            exactly(1).of(sensor).powerUp();
          }
        });

    cam.powerOn();
  }

  @Test
    public void switchingCameraOffPowersDownSensor() {
      context.checking(new Expectations() {{
        exactly(1).of(sensor).powerDown();
      }});

      cam.powerOff();
  }
}
