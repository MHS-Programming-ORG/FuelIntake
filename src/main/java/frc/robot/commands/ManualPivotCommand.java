
package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakePivot;
import java.util.function.DoubleSupplier;
public class ManualPivotCommand extends Command {
  IntakePivot pivotSubsystem;
  DoubleSupplier joystick;
  public ManualPivotCommand(IntakePivot newPivotSubsystem, DoubleSupplier newjoystick) {
pivotSubsystem = newPivotSubsystem;
joystick = newjoystick;
addRequirements(pivotSubsystem);
  }

  @Override
  public void initialize() {
    pivotSubsystem.PIDOff();
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
    pivotSubsystem.PIDOn();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
