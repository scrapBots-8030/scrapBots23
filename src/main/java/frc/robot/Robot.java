// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSDhhhh license file in the root directory of this project.

//Imports the neccesarry modules from wpilib
package frc.robot;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */

 //startspublic class Robot, part of the TimedRobot robot type
public class Robot extends TimedRobot{
  private WPI_VictorSPX m_leftDrive1 = new WPI_VictorSPX(14);
  private WPI_VictorSPX m_leftDrive2 = new WPI_VictorSPX(1);
  private MotorControllerGroup m_left = new MotorControllerGroup(m_leftDrive1, m_leftDrive2);
  private WPI_VictorSPX m_rightDrive1 = new WPI_VictorSPX(0);
  private WPI_VictorSPX m_rightDrive2 = new WPI_VictorSPX(15);
  private MotorControllerGroup m_right = new MotorControllerGroup(m_rightDrive1, m_rightDrive2);
  private DifferentialDrive m_robotDrive = new DifferentialDrive(m_left, m_right);
  private Timer m_timer = new Timer();
  public Joystick m_joystick = new Joystick(0);

  @Override
  public void robotInit() {
    m_left.setInverted(true);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */

  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */

  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  /** This function is called periodically during autonomous. */

  @Override
  public void autonomousPeriodic() {
    while (m_timer.get() <= 1.9) { 
      m_robotDrive.arcadeDrive(.7, 0);
    }
    while (m_timer.get() >= 1.4 && m_timer.get() <= 2.8) {
        m_robotDrive.arcadeDrive(-.30, 0);
    }
    while (m_timer.get() >= 2.8 && m_timer.get() <= 3.6) {
          m_robotDrive.arcadeDrive(-.25, 0);
    }
      m_robotDrive.stopMotor();
  }

  /** This function is called once when teleop is enabled. */

  @Override
  public void teleopInit() {
    
  }
  /** This function is called periodically during operator control. */

  @Override
  public void teleopPeriodic() {
    // operates as a "boost" capability, in essence while the Y button is pressed the robot uses 90% throttle
    if (m_joystick.getRawAxis(3) > 0){
      while (m_joystick.getRawAxis(3) > 0){
        m_robotDrive.arcadeDrive(m_joystick.getRawAxis(1) * -0.9, m_joystick.getRawAxis(0) * -0.9);
      }
    } 
    if (m_joystick.getRawAxis(3) < 1) {
      while (m_joystick.getRawAxis(3) < 1){
       m_robotDrive.arcadeDrive(m_joystick.getRawAxis(1) * -0.55, m_joystick.getRawAxis(0) * -0.65);
      }
    }
    if (m_joystick.getRawAxis(2) <= 0){
      while (m_joystick.getRawAxis(2) <= 0){
      m_robotDrive.arcadeDrive(0, m_joystick.getRawAxis(2) * -.95);
      }
    }
    else{
      m_robotDrive.arcadeDrive(m_joystick.getRawAxis(1) * -0.75, m_joystick.getRawAxis(0) * -0.85);
    }
    } 

  /** This function is called once when the robot is disabled. */

  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */

  @Override
  public void disabledPeriodic() {
  }
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */

  @Override
  public void testPeriodic() {
    if (m_joystick.getRawAxis(4) < 1) {
      while (m_joystick.getRawAxis(4) < 1){
       m_robotDrive.arcadeDrive(m_joystick.getRawAxis(2) * -0.55, m_joystick.getRawAxis(1) * -0.65);
      }
    }
      else {
      m_robotDrive.arcadeDrive(m_joystick.getRawAxis(2) * -0.55, m_joystick.getRawAxis(1) * -0.55);
      }
    
  }

  /** This function is called once when the robot is first started up. */

  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  
  @Override
  public void simulationPeriodic() {}
}


