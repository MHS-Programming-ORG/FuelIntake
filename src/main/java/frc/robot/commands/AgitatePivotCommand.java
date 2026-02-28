// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PivotSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class AgitatePivotCommand extends Command {
  PivotSubsystem pivot;
  double pivotIn = 0;
  double pivotOut = 0;
  boolean MovingInOrOut;

  /** Creates a new AgitatorCommand. */
  public AgitatePivotCommand(PivotSubsystem newPivotSubsystem) {
    pivot = newPivotSubsystem;
    addRequirements(pivot);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() { 
    MovingInOrOut = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double currentPos = pivot.getPivotEncoder();
    double tolerance = 0.1;

    if (MovingInOrOut && currentPos >= (pivotOut - tolerance)) {
        MovingInOrOut = false;
    } else if (!MovingInOrOut && currentPos <= (pivotIn + tolerance)) {
        MovingInOrOut = true;
    }

    if (MovingInOrOut) {
        pivot.setSetPoint(pivotOut);
    } else {
        pivot.setSetPoint(pivotIn);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    pivot.setSetPoint(pivotIn);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}