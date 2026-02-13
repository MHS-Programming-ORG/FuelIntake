// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.configs.TalonFXConfiguration;

public class ConveyorSubsystem extends SubsystemBase {
     private TalonFX conveyorMotor;
     private TalonFXConfiguration configs;
    public ConveyorSubsystem(int newConveyorID) {
        conveyorMotor = new TalonFX(newConveyorID);
        configs = new TalonFXConfiguration();
        configs.withCurrentLimits(new CurrentLimitsConfigs().withStatorCurrentLimit(0).withStatorCurrentLimitEnable(false));
        configs.withCurrentLimits(new CurrentLimitsConfigs().withSupplyCurrentLimit(0).withSupplyCurrentLimitEnable(false));
        conveyorMotor.getConfigurator().apply(configs);
    }
    public void setConveyorSpeed(double speed){
        conveyorMotor.set(speed);
    }

    @Override
    public void periodic() {
        
        
    }
}
