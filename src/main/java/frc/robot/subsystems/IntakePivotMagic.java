// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static edu.wpi.first.units.Units.KiloOhm;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;


public class IntakePivotMagic extends SubsystemBase {
  MotionMagicConfigs magic;
  TalonFX pivotMotor;
  TalonFXConfiguration configs;
  MotionMagicVoltage request;
  double setPoint;

  public IntakePivotMagic() {
    pivotMotor = new TalonFX(9);
    magic = new MotionMagicConfigs();
    configs = new TalonFXConfiguration();
    setPoint = 0;
    request = new MotionMagicVoltage(0);
    
    configs.Slot0.kP = 1.0;
    configs.Slot0.kI = 0;
    configs.Slot0.kD = 0;
    configs.HardwareLimitSwitch.ReverseLimitRemoteSensorID = 0;
    configs.HardwareLimitSwitch.ReverseLimitEnable = true;
    configs.HardwareLimitSwitch.ReverseLimitAutosetPositionEnable = true;
    configs.HardwareLimitSwitch.ReverseLimitAutosetPositionValue = 0;
    configs.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
    configs.SoftwareLimitSwitch.ReverseSoftLimitThreshold = 0;


    magic.MotionMagicAcceleration = 20;
    magic.MotionMagicCruiseVelocity = 10;

    pivotMotor.getConfigurator().apply(configs);
    pivotMotor.getConfigurator().apply(magic);
    
}

public void setSetPoint(double newSetPoint){
  setPoint = newSetPoint;
}


  public double getPivotEncoder(){
return pivotMotor.getPosition().getValueAsDouble();
  }
  

  public int isDetected(){
 return  pivotMotor.getReverseLimit().getValue().value;
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
    SmartDashboard.putNumber("Setpoint", setPoint);
    SmartDashboard.putNumber("isDetected", isDetected());
    pivotMotor.setControl(request.withPosition(setPoint));
    }
  }
