/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.CircleKick;
import frc.robot.commands.ControlledShooting;
import frc.robot.commands.DriveArcade;
import frc.robot.commands.DriveForwardAutoNoPID;
import frc.robot.commands.DriveForwardAutoPID;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.GalacticSearch;
import frc.robot.commands.GetGameColor;
import frc.robot.commands.IntakeDown;
import frc.robot.commands.IntakeUp;
import frc.robot.commands.KickBackward;
import frc.robot.commands.KickForward;
import frc.robot.commands.PanelForward;
import frc.robot.commands.PositionControl;
import frc.robot.commands.RunLiftDown;
import frc.robot.commands.RunLiftUp;
import frc.robot.commands.RunWinch;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Lift;
import frc.robot.RobotContainer;


/*
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  private DriverStation m_driverStation;
  
  private Command driveSlalom;  
  private Command galcSearch;
  /*
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  
  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.

    Camera thisObject = new Camera();
    
    galcSearch = new GalacticSearch(RobotContainer.m_drivetrain, thisObject);
    driveSlalom = new DriveStraight(RobotContainer.m_drivetrain); 
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */

  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    
    //SmartDashboard.putNumber("Drivetrain Encoder Left: ", RobotContainer.m_drivetrain.getLeftDriveEncoderCount());
    //SmartDashboard.putNumber("Drivetrain Encoder Right: ", RobotContainer.m_drivetrain.getRightDriveEncoderCount());
    //SmartDashboard.putNumber("Lift Encoder: ", RobotContainer.liftObject.liftEncoder.get());

  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */

  @Override
  public void autonomousInit() {
   	m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
    //What does the above do? Check for next time.
    if(driveSlalom != null){
      driveSlalom.schedule();
    }
    
    
    
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    //DriveForwardAutoPID autoObjectForStraight = new DriveForwardAutoPID(RobotContainer.m_drivetrain);
    //autoObjectForStraight.execute();
    //DriveForwardAutoNoPID driverAuto = new DriveForwardAutoNoPID(RobotContainer.m_drivetrain);
    //driverAuto.execute();
    //DriveUAuto uAuto = new DriveUAuto(RobotContainer.m_drivetrain);
    //uAuto.execute();

    //driveObject.driveStraightDistance(1000, true, 0.80);
    //driveSlalom(driveObject);
    //driveObject.driveStraightDistance(90, true, 0.60);

  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    
   
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {

  }

}
