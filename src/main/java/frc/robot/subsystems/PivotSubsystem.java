// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static edu.wpi.first.units.Units.KiloOhm;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;

public class PivotSubsystem extends SubsystemBase {
  
  TalonFX pivotMotor;
  DigitalInput pivotLimitSwitch;

  MotionMagicConfigs magic;
  TalonFXConfiguration configs;
  MotionMagicVoltage request;

  double setPoint;

  public PivotSubsystem() {
    pivotMotor = new TalonFX(3);
    pivotLimitSwitch = new DigitalInput(0);
    
    magic = new MotionMagicConfigs();
    configs = new TalonFXConfiguration();
    request = new MotionMagicVoltage(0);
    
    setPoint = 0;

    configs.Slot0.kP = 1.0;
    configs.Slot0.kI = 0;
    configs.Slot0.kD = 0;

    magic.MotionMagicAcceleration = 20;
    magic.MotionMagicCruiseVelocity = 10;

    pivotMotor.getConfigurator().apply(configs);
    pivotMotor.getConfigurator().apply(magic);
  }

  public void setSetPoint(double newSetPoint) {
    setPoint = newSetPoint;
  }

  public double getPivotEncoder() {
    return pivotMotor.getPosition().getValueAsDouble();
  }

  public void setSpeed(double speed) {
    pivotMotor.set(speed);

  }

  public void resetEncoder() {
    pivotMotor.setPosition(0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Pivot Encoders", getPivotEncoder());
    SmartDashboard.putNumber("Setpoint", setPoint);
    SmartDashboard.putBoolean("Pivot Limit Switch", pivotLimitSwitch.get());

    if(pivotLimitSwitch.get() && pivotMotor.getVelocity().getValueAsDouble() < 0) {
      resetEncoder();
    }

    pivotMotor.setControl(request.withPosition(setPoint));
  }
}
