// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;

public class PivotSubsystem extends SubsystemBase {
  
  TalonFX pivotMotor;
  DigitalInput pivotLimitSwitch;

  MotionMagicConfigs magic;
  TalonFXConfiguration configs;
  MotionMagicVoltage request;
  SoftwareLimitSwitchConfigs limit;

  double setPoint;

  public PivotSubsystem(int newpivotID, int limitswitchID) {
    pivotMotor = new TalonFX(newpivotID);
    pivotLimitSwitch = new DigitalInput(limitswitchID);
    
    magic = new MotionMagicConfigs();
    configs = new TalonFXConfiguration();
    request = new MotionMagicVoltage(0);
    limit = new SoftwareLimitSwitchConfigs();
    limit.ForwardSoftLimitEnable = false;
    limit.ReverseSoftLimitEnable = false;
    limit.ForwardSoftLimitThreshold = 0;
    limit.ReverseSoftLimitThreshold = 0;
    
    setPoint = 0;

    configs.Slot0.kP = 0;
    configs.Slot0.kI = 0;
    configs.Slot0.kD = 0;
    configs.Slot0.kS = 0 ;
    configs.Slot0.kV = 0;
    configs.Slot0.kG = 0;
    configs.Slot0.kA = 0;
    configs.withCurrentLimits(new CurrentLimitsConfigs().withStatorCurrentLimit(0).withStatorCurrentLimitEnable(false));
    configs.withCurrentLimits(new CurrentLimitsConfigs().withSupplyCurrentLimit(0).withSupplyCurrentLimitEnable(false));

    magic.MotionMagicAcceleration = 0;
    magic.MotionMagicCruiseVelocity = 0;

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
  public boolean isPressed() {
    return !pivotLimitSwitch.get();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Pivot Encoders", getPivotEncoder());
    SmartDashboard.putNumber("Setpoint", setPoint);
    SmartDashboard.putBoolean("Pivot Limit Switch", isPressed());

    if(pivotLimitSwitch.get() && pivotMotor.getVelocity().getValueAsDouble() < 0) {
      resetEncoder();
      setSetPoint(0);
    }

    pivotMotor.setControl(request.withPosition(setPoint));
  }
}
