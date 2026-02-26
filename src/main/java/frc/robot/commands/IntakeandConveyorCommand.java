// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class IntakeandConveyorCommand extends Command {
 ConveyorSubsystem Conveyor;
 IntakeSubsystem Intake;
  public IntakeandConveyorCommand(IntakeSubsystem newintakeSubsystem, ConveyorSubsystem newConveyorCommandIntake) {
  Intake = newintakeSubsystem;
  Conveyor = newConveyorCommandIntake;
   addRequirements(Intake, Conveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  Conveyor.setConveyorSpeed(0.5); 
   Intake.setSpeed(-0.6);
  }



  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
     Intake.setSpeed(0);
     Conveyor.setConveyorSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
