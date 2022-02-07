package ic.doc.camera;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertFalse;

public class CameraTest {

  @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

  private final Sensor sensor = context.mock(Sensor.class);
  private final MemoryCard mc = context.mock(MemoryCard.class);


  @Test
  public void switchingTheCameraOnPowersUpTheSensor() {
    Camera cam = new Camera(sensor, mc);

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
    Camera cam = new Camera(sensor, mc);
    cam.powerOn();

      context.checking(new Expectations() {{
        exactly(1).of(sensor).powerDown();
      }});

      cam.powerOff();
  }

  @Test
  public void pressingShutterWhenPowerIsOffDoesNothing() {
    Camera cam = new Camera(sensor, mc);
    assertFalse(cam.pressShutter());
  }
}
