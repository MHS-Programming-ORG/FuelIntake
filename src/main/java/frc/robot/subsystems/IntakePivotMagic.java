// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;

import static edu.wpi.first.units.Units.KiloOhm;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;


public class IntakePivotMagic extends SubsystemBase {
  MotionMagicConfigs magic;
  TalonFX pivotMotor;
  // DigitalInput sensor;
  TalonFXConfiguration configs;
  MotionMagicVoltage request;
  double setPoint;

  public IntakePivotMagic() {
    pivotMotor = new TalonFX(67);
    magic = new MotionMagicConfigs();
    // sensor = new DigitalInput(4);
    configs = new TalonFXConfiguration();
    request = new MotionMagicVoltage(0);
    setPoint = 0;
    var slot0 = configs.Slot0;
    slot0.kP = 0.005;
    slot0.kI = 0;
    slot0.kD = 0;

    magic.MotionMagicAcceleration = 20;
    magic.MotionMagicCruiseVelocity = 10;

    pivotMotor.getConfigurator().apply(configs);
    pivotMotor.getConfigurator().apply(magic);

}

public void setSetPoint(double newSetPoint){
  setPoint = newSetPoint;
}

// public boolean isDetected(){
//   return sensor.get();
// }

  public double getPivotEncoder(){
return pivotMotor.getPosition().getValueAsDouble();
  }
  

  

 public void setSpeed(double speed)
 {
pivotMotor.set(speed);

 }


  public void resetEncoder(){
    pivotMotor.setPosition(0);
  }


  

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Pivot Encoders", getPivotEncoder());
    //pivotMotor.setControl(request.withPosition(setPoint));
    }
  }
