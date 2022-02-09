package ic.doc.camera;

import static org.junit.Assert.assertFalse;

import org.jmock.Expectations;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.internal.InvocationExpectation;
import org.junit.Rule;
import org.junit.Test;

public class CameraTest {

  @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

  private final Sensor sensor = context.mock(Sensor.class);
  private final MemoryCard memCard = context.mock(MemoryCard.class);

  static final byte[] data = { 1, 2, 3, 4};

  @Test
  public void switchingTheCameraOnPowersUpTheSensor() {
    Camera cam = new Camera(sensor, memCard);

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
    Camera cam = new Camera(sensor, memCard);

    context.checking(new Expectations() {{
      allowing(sensor).powerUp();
      exactly(1).of(sensor).powerDown();
    }});
      cam.powerOn();
      cam.powerOff();
  }

  @Test
  public void pressingShutterWhenPowerIsOffDoesNothing() {
    Camera cam = new Camera(sensor, memCard);
    assertFalse(cam.pressShutter());
  }

  @Test
  public void pressingShutterWhenPowerIsOnCopiesDataFromSensorToMemoryCard() {
    Camera cam = new Camera(sensor, memCard);


    context.checking(new Expectations() {{
      allowing(sensor).powerUp();
      exactly(1).of(sensor).readData();
      exactly(1).of(memCard).write(data);
    }});
    cam.powerOn();
    cam.pressShutter();
  }

  @Test
  public void ifDataIsBeingWrittenSwitchingOffCameraDoesNotPowerDownSensor() {
    Camera cam = new Camera(sensor, memCard);

    context.checking(new Expectations() {{
      allowing(sensor).powerUp();
      allowing(sensor).readData();
      allowing(memCard).write(data);
      never(sensor).powerDown();
    }});

    cam.powerOn();
    cam.pressShutter();
    cam.powerOff();
  }

  @Test
  public void onceWritingDataIsCompletedCameraPowersDownSensor() {
    Camera cam = new Camera(sensor, memCard);

    context.checking(new Expectations() {{
      allowing(sensor).powerUp();
      allowing(sensor).readData();
      allowing(memCard).write(data);
      oneOf(sensor).powerDown();
    }});

    cam.powerOn();
    cam.pressShutter();
    cam.writeComplete();
  }

}
