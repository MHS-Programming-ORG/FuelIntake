// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;


public class IntakePivot extends SubsystemBase {
  TalonFX pivotMotor;
  PIDController pivotPID;
  double pidSetPoint;
  boolean pidON = true;
  double pidSpeed = 0;
  double joystickSpeed = 0;
  double outPutSpeed = 0;
  DigitalInput sensor;

  public IntakePivot() {
    pidSetPoint = 0;
    pivotMotor = new TalonFX(1);//14
    pivotPID = new PIDController(0.05, 0, 0);
    pivotMotor.setPosition(0);
    sensor = new DigitalInput(0);
}
  
public boolean isDetected(){
  return sensor.get();
}

  public double getPivotEncoder(){
return pivotMotor.getPosition().getValueAsDouble();
  }
  
  public double calculatePivotPID(double setPoint){
    return pivotPID.calculate(getPivotEncoder(), setPoint);
  }

 public void setSpeed(double speed)
 {
pivotMotor.set(speed);

 }

  public void setPoint(double newSetPoint){
    pidSetPoint = newSetPoint;
  }

  public void resetEncoder(){
    pivotMotor.setPosition(0);
  }

  public void PIDOn()
  {
   pidON = true;
  }

  public void PIDOff()
  {

  pidON = false;
  }

  public void setJoystickSpeed(double sped)
  {
    //if(sped <= 0.1 ){
    //sped = 0;
   // }
   // else if(sped >= -0.1){
   // sped = 0;
   // }
   joystickSpeed = sped;

  }
  

  @Override
  public void periodic() {
    pidSpeed = calculatePivotPID(pidSetPoint);
    SmartDashboard.putNumber("Pivot Encoders", getPivotEncoder());
    SmartDashboard.putNumber("PID Claw Point", pidSetPoint);
    SmartDashboard.putNumber("JoystickSpeed", joystickSpeed);
    SmartDashboard.putNumber("OutputSpeed", outPutSpeed);
    SmartDashboard.putBoolean("PID", pidON);
    SmartDashboard.putBoolean("SensorWork", sensor.get());
    //SmartDashboard.putNumber("Calculate PID", calculatePivotPID());
    if(pidON){
      outPutSpeed = pidSpeed;
    }
    else{
      outPutSpeed = joystickSpeed;
    }
setSpeed(outPutSpeed);

    if(sensor.get()){
      setPoint(0);

    }
  }
}
