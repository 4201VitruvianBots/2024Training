// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.SwerveDependencies.SWERVE;
import frc.robot.SwerveDependencies.SWERVE.DRIVE;

public class RobotContainer {
  private final Joystick leftJoystick = new Joystick(0);
  private final Joystick rightJoystick = new Joystick(1);
  private final XboxController xboxController = new XboxController(2);
      
  private final CommandSwerveDrivetrain m_swerveDrive = new CommandSwerveDrivetrain(SWERVE.DrivetrainConstants, SWERVE.FrontLeftConstants,
          SWERVE.FrontRightConstants,
          SWERVE.BackLeftConstants,
          SWERVE.BackRightConstants);

  public RobotContainer() {
    // Drivetrain code
    if (RobotBase.isReal()) {
      m_swerveDrive.setDefaultCommand(
          m_swerveDrive.applyChassisSpeeds(
              () ->
                  new ChassisSpeeds(
                      leftJoystick.getRawAxis(1) * DRIVE.kMaxSpeedMetersPerSecond,
                      leftJoystick.getRawAxis(0) * DRIVE.kMaxSpeedMetersPerSecond,
                      rightJoystick.getRawAxis(0) * DRIVE.kMaxRotationRadiansPerSecond)));
    }
  }
  

  // Motor IDs!
  // Intake Motors: 30, 31 (Control these simultaneously)
  // Shooter Motors: 41, 42 (Control these independently)
  TalonFX intakeMotor1 = new TalonFX(30);
  
  public void main() {
    
    // Don't worry about the stuff on the outside right now.
    // Just type in here!
    
    if (xboxController.getXButton()) {
        intakeMotor1.set(0.5); // Set the intake motor at 50% speed
    } else {
        intakeMotor1.set(0.0); // Stop the intake motor
    }
    
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
  
  public void disabledInit() {
    m_swerveDrive.applyRequest(SwerveRequest.ApplyChassisSpeeds::new);
  }
  
  public void teleopPeriodic() {
    main();
  }
}
