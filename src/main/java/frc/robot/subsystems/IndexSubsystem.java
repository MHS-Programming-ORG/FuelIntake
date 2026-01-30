package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX; // Use WPI_TalonFX if using WPILib motor control groups

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexSubsystem extends SubsystemBase {
    private TalonFX indexMotor;
    public IndexSubsystem() {
        indexMotor = new TalonFX(5);
    }
    public void setIndexSpeed(double speed){
        indexMotor.set(speed);
    }

    @Override
    public void periodic() {
        
        
    }
    }

