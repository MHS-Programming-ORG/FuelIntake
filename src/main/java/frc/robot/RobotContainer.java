// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.ConveyorCommands.RunConveyorCommandForward;
import frc.robot.commands.ConveyorCommands.RunConveyorCommandReverse;
import frc.robot.commands.IntakeRollerCommands.RunUntilDetectedCommand;
import frc.robot.commands.IntakeRollerCommands.runIntakeCommand;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.commands.IntakePivotCommands.AgitatePivotCommand;
import frc.robot.commands.IntakePivotCommands.MoveToPositionMagicCommand;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
 private final CommandXboxController m_driverController = 
      new CommandXboxController(0);
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem(20);
  private final PivotSubsystem m_intakePivot = new PivotSubsystem(19, 0);
  private final ConveyorSubsystem m_ConveyorSubsystem = new ConveyorSubsystem(15);
  private final RunConveyorCommandReverse m_RunConveyorCommandReverse = new RunConveyorCommandReverse(m_ConveyorSubsystem);
  private final RunConveyorCommandForward m_RunConveyorCommandForward = new RunConveyorCommandForward(m_ConveyorSubsystem);
  private final InstantCommand m_StopConveyor = new InstantCommand(() -> m_ConveyorSubsystem.setConveyorSpeed(0));
  private final MoveToPositionMagicCommand m_moveToPositionMagicCommand = new MoveToPositionMagicCommand(m_intakePivot, 10.5, 0.1);
  private final MoveToPositionMagicCommand m_movePivotInCommand = new MoveToPositionMagicCommand(m_intakePivot, 0, 0.5);
  private final runIntakeCommand m_IntakeCommand = new runIntakeCommand(m_intakeSubsystem, m_intakePivot, m_ConveyorSubsystem);
  private final AgitatePivotCommand m_AgitatePivotCommand = new AgitatePivotCommand(m_intakePivot, m_intakeSubsystem);

  
  

  // Replace with CommandPS4Controller or CommandJoystick if needed
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    //m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand()); // test intake
    //m_driverController.y().whileTrue(m_IntakeCommand);
    //m_driverController.x().whileTrue(new runIntakeCommand(m_intakeSubsystem)); // test intake
    //m_driverController.rightBumper().whileTrue(m_ManualPivotCommand);
    //  m_driverController.a().whileTrue(m_RunConveyorCommandReverse);
    //   m_driverController.b().whileTrue(m_RunConveyorCommandForward);
    //  m_driverController.a().whileFalse(m_StopConveyor);
    //   m_driverController.b().whileFalse(m_StopConveyor); 
    m_driverController.y().onTrue(m_moveToPositionMagicCommand);
    m_driverController.a().onTrue(m_movePivotInCommand);
    m_driverController.x().whileTrue(m_IntakeCommand);
    m_driverController.b().whileTrue(m_AgitatePivotCommand);
   //m_driverController.x().onTrue(new MoveToPositionMagicCommand(m_intakePivotMagic, 0, 0.5));
    // m_driverController.y().whileTrue(new InstantCommand(()-> m_intakePivotMagic.setVoltage(0.4)));
    // m_driverController.y().whileFalse(new InstantCommand(()-> m_intakePivotMagic.setVoltage(0)));

  }

  public PivotSubsystem getPivotSubsystem(){
    return m_intakePivot;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
