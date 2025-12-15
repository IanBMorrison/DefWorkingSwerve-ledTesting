package frc.robot.subsystems.swervedrive;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.motorcontrol.Spark;


public class Leds extends SubsystemBase {
    private final Spark m_Led;
    private final PhotoelectricSensor sensor;

    public Leds(int ledPin) {
        this.m_Led = new Spark(ledPin);
    }
    public void setGreen() {
        m_Led.set(0.77);
    }
    public void setRed() {
        m_Led.set(0.61);
    }
    public void setOrange(){
        m_Led.set(0.65);
    }
    public void setYellow() {
        m_Led.set(0.69);
    }
    public void setWhite() {
        m_Led.set(0.93);
    }
    public void strobeWhite() {
        m_Led.set(0.05);
    }
    public void turnOff() {
        m_Led.set(0.0);
    }
    public void periodic(){
        if (sensor.isTripped()){
            setRed();
        }else if(DriverStation.isDisabled()){
            setYellow();
        }else{
            setGreen();
        }
    }

    
}