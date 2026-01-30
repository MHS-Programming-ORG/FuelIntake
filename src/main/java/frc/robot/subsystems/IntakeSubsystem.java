// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX; 
import edu.wpi.first.wpilibj.DigitalInput;
public class IntakeSubsystem extends SubsystemBase {
private TalonFX intakeMotor;

  public IntakeSubsystem() {
  intakeMotor = new TalonFX(1);

}

  public void setSpeed(double speed){
    intakeMotor.set(speed);
  }
  
  public double getEncoder(){
  return intakeMotor.getPosition().getValueAsDouble();
  }

  
  @Override
  public void periodic() {
   SmartDashboard.putNumber("IntakeEncoder", getEncoder());
  }
}
