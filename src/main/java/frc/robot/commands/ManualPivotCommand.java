
package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PivotSubsystem;
import java.util.function.DoubleSupplier;

public class ManualPivotCommand extends Command {
  PivotSubsystem pivotSubsystem;
  DoubleSupplier joystick;
  public ManualPivotCommand(PivotSubsystem newPivotSubsystem, DoubleSupplier newjoystick) {
pivotSubsystem = newPivotSubsystem;
joystick = newjoystick;
addRequirements(pivotSubsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() 
  {
    double realJoystickSpeed = joystick.getAsDouble();
    pivotSubsystem.setJoystickSpeed(realJoystickSpeed);
    SmartDashboard.putNumber("realJoystickSpeed", realJoystickSpeed);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
