// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
  private SparkMax frontLeftMotor;
  private SparkMaxConfig frontLeftConfig;
  private SparkMax frontRightMotor;
  private SparkMaxConfig frontRightConfig;
  private SparkMax backLeftMotor;
  private SparkMaxConfig backLeftConfig;
  private SparkMax backRightMotor;
  private SparkMaxConfig backRightConfig;
  private DifferentialDrive drive;
  private XboxController Controller;


  /** Creates a new subsystem. */
  public Drive() {
    frontLeftMotor = new SparkMax(1, MotorType.kBrushless);
    frontRightMotor = new SparkMax(2, MotorType.kBrushless);
    backLeftMotor = new SparkMax(3, MotorType.kBrushless);
    backRightMotor = new SparkMax(4, MotorType.kBrushless);

    frontLeftConfig = new SparkMaxConfig();
    frontRightConfig = new SparkMaxConfig();
    backLeftConfig = new SparkMaxConfig();
    backRightConfig = new SparkMaxConfig();

    frontLeftConfig
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(60)
      .inverted(true);

    frontRightConfig
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(60);

    backLeftConfig
      .apply(frontLeftConfig)
      .follow(frontLeftMotor);

    backRightConfig
      .apply(frontRightConfig)
      .follow(frontRightMotor);

    frontLeftMotor.configure(frontLeftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    frontRightMotor.configure(frontRightConfig, ResetMode. kResetSafeParameters,PersistMode.kPersistParameters);
    backLeftMotor.configure(backLeftConfig,  ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
    backRightMotor.configure(backRightConfig,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);

    drive = new DifferentialDrive(frontLeftMotor,frontRightMotor);
  }

  public void arcadeJoystickDrive (XboxController controller){
    drive.arcadeDrive(MathUtil.applyDeadband (controller.getLeftY(),0.05)/1.4, MathUtil.applyDeadband(controller.getRightX(), 0.05)/1.35);

  }

  public void tankJoystickDrive (XboxController controller){
    drive.tankDrive (MathUtil.applyDeadband (controller.getLeftY(), 0.05)/1.4, MathUtil.applyDeadband(controller.getRightY(), 0.05)/1.4);
  }

  public void arcadeDrive( double speed, double rotationSpeed){
    drive.arcadeDrive (speed, rotationSpeed);
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
