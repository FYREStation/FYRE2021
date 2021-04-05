/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.commands.IntakeUp;
import frc.robot.commands.InvertedDrive;
import frc.robot.commands.InvertedTypeDrive;
import frc.robot.commands.RunLiftUp;
import frc.robot.commands.RunWinch;
import frc.robot.commands.SetThrottle;
import frc.robot.commands.ShooterPwrDown;
import frc.robot.commands.ShooterPwrUp;
import frc.robot.commands.ThrottleDown;
import frc.robot.commands.ThrottleUp;
import frc.robot.commands.RunLiftDown;
import frc.robot.commands.RunLiftSetpoint;
import frc.robot.commands.CircleKick;
import frc.robot.commands.ControlledShooting;
import frc.robot.commands.IntakeDown;
import frc.robot.commands.KickForward;
import frc.robot.commands.PanelForward;
import frc.robot.commands.PositionControl;
import frc.robot.commands.KickBackward;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.ShooterAndKick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.Intake;
import frc.robot.commands.DriveArcade;
import frc.robot.commands.GetGameColor;
import frc.robot.commands.DriveStraight;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  //SendableChooser<Command> m_chooser = new SendableChooser<>();
  private final DriveStraight m_AutoCommand = new DriveStraight(m_drivetrain);

  SendableChooser<Command> m_chooser = new SendableChooser<>();
  public static Drivetrain m_drivetrain = new Drivetrain();
  public static Lift liftObject = new Lift();
  public static ShooterAndKick shooterObject = new ShooterAndKick();
  public static Camera cameraObject = new Camera();
  public static Intake intakeObject = new Intake();
  public static ControlPanel controlPanelObject = new ControlPanel();

  // The robot's subsystems and commands are defined here...
  public static Joystick driverController = new Joystick(Constants.DRIVER_CONTROLLER);
  public static XboxController xboxController = new XboxController(Constants.XBOX_CONTROLLER);
  public static Button D1; 
  public static Button D2;
  public static Button D3;
  public static Button D4;
  public static Button D5;
  public static Button D6;
  public static Button D7;
  public static Button D8;
  public static Button D9;
  public static Button D10;
  public static Button D11;
  public static Button D12;
  public static Button D13;
  public static Button X1;
  public static Button X2;
  public static Button X3;
  public static Button X4;
  public static Button X5;
  public static Button X6;
  public static Button X7;
  public static Button X8;
  public static Button X9;
  public static Button X10;
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer(){
    ConfigureButtonBindings();
  
    //double moveSpeed = -RobotContainer.xboxContoller.getRawAxis(Constants.XBOX_LYAXIS);
    //double rotateSpeed = RobotContainer.xboxContoller.getRawAxis(Constants.XBOX_LXAXIS);
  
    /*
    m_drivetrain.setDefaultCommand(new DriveArcade(m_drivetrain,
    () -> driverController.getY(GenericHID.Hand.kLeft),
    () -> ((driverController.getTwist()-0.5))));

    X4.whileHeld(new IntakeUp(intakeObject));
    X5.whileHeld(new IntakeDown(intakeObject));
    //X2.toggleWhenPressed(new RunLiftSetpoint(liftObject));
    X7.whileHeld(new RunWinch(liftObject));
    X6.whileHeld(new RunLiftUp(liftObject));
    X2.whileHeld(new RunLiftDown(liftObject));
    //X3.whenPressed(new CircleKick(shooterObject));
    X1.whileHeld(new ControlledShooting(shooterObject));
    X3.whileHeld(new KickForward(shooterObject));
    //X10.whileHeld(new KickBackward(shooterObject));
    X8.whileHeld(new GetGameColor(controlPanelObject)); //This gets the color that FMS wants
    X9.whileHeld(new PanelForward(controlPanelObject));
    X10.toggleWhenPressed(new PositionControl(controlPanelObject)); //This Detects Color

    D2.whenPressed(new SetThrottle());
    D5.whenPressed(new ThrottleUp());
    D6.whenPressed(new ThrottleDown());
    */
    
    
    //This set is for Xbox driver and joy manipulator
    
    m_drivetrain.setDefaultCommand(new DriveArcade(m_drivetrain,
    () -> (xboxController.getY(GenericHID.Hand.kLeft)),
    () -> (xboxController.getX(GenericHID.Hand.kLeft)),
    () -> (xboxController.getY(GenericHID.Hand.kRight)),
    () -> (xboxController.getX(GenericHID.Hand.kRight))
    ));
    

    D4.whileHeld(new IntakeUp(intakeObject));
    D6.whileHeld(new IntakeDown(intakeObject));
    //D6.toggleWhenPressed(new RunLiftSetpoint(liftObject));
    D7.whileHeld(new RunWinch(liftObject));
    D11.whileHeld(new RunLiftUp(liftObject));
    D9.whileHeld(new RunLiftDown(liftObject));
    //D3.whenPressed(new CircleKick(shooterObject));
    D1.whileHeld(new ControlledShooting(shooterObject));
    D2.whileHeld(new KickForward(shooterObject));
    //D10.whileHeld(new KickBackward(shooterObject));
    //D3.whileHeld(new GetGameColor(controlPanelObject)); //This gets the color that FMS wants
    //D5.whileHeld(new PanelForward(controlPanelObject));
    D3.whenPressed(new ShooterPwrUp());
    D5.whenPressed(new ShooterPwrDown());
    D8.whenPressed(new PositionControl(controlPanelObject)); //This Detects Color

    X2.whenPressed(new SetThrottle());
    X5.whenPressed(new ThrottleUp());
    X6.whenPressed(new ThrottleDown());
    
    X4.whenPressed(new InvertedDrive());
    X3.whenPressed(new InvertedTypeDrive());
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

  private void ConfigureButtonBindings() {
    X1 = new JoystickButton(xboxController, 1);
    X2 = new JoystickButton(xboxController, 2);
    X3 = new JoystickButton(xboxController, 3);
    X4 = new JoystickButton(xboxController, 4);
    X5 = new JoystickButton(xboxController, 5);
    X6 = new JoystickButton(xboxController, 6);
    X7 = new JoystickButton(xboxController, 7);
    X8 = new JoystickButton(xboxController, 8);
    X9 = new JoystickButton(xboxController, 9);
    X10 = new JoystickButton(xboxController, 10);
 


    D1 = new JoystickButton(driverController, 1);
    D2 = new JoystickButton(driverController, 2);
    D3 = new JoystickButton(driverController, 3);
    D4 = new JoystickButton(driverController, 4);
    D5 = new JoystickButton(driverController, 5);
    D6 = new JoystickButton(driverController, 6);
    D7 = new JoystickButton(driverController, 7);
    D8 = new JoystickButton(driverController, 8);
    D9 = new JoystickButton(driverController, 9);
    D10 = new JoystickButton(driverController, 10);
    D11 = new JoystickButton(driverController, 11);
    D12 = new JoystickButton(driverController, 12);
  }

  



  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_AutoCommand;
    
    //return m_chooser.getSelected();
  }
}
