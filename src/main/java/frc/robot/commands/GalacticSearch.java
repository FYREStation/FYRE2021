package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.VisionProcessingPipeline;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.vision.VisionThread;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.vision.VisionRunner;

public class GalacticSearch extends CommandBase {

    private Drivetrain drive_train;
	private DriverStation driver_station;
	private Intake the_Intake;
	private Camera theCamera;
	private VisionThread visionThread;

	private final Object imgLock = new Object();
	private static final int IMG_WIDTH = 720; //On testing day make sure we googled the right camera
	private static final int IMG_HEIGHT = 480;
	private int numObjectsDetected = 0;
	public static int retrievalCount = 0;
	
	private double centerX = IMG_WIDTH/2;
	private double centerY;
	private boolean isRed = true;
	private double startingDistance;
	private double finalDistance;
	private double currentHeading = 0;

    public GalacticSearch(Drivetrain driveTrain, Intake theIntake, Camera allCamera) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		drive_train = driveTrain;
		the_Intake = theIntake;
        addRequirements(drive_train);
        theCamera = allCamera;
        
		
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		startingDistance = drive_train.getLeftDriveEncoderDistance();
		finalDistance = startingDistance + 100;
		startProcessing();
		drive_train.resetGyro();
		theEntireSequence();
		//drive_train.resetGyro();
	} 

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		//findBall();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
        return true;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		drive_train.tankDriving(0, 0);
		the_Intake.stopIntake();
	}

	public void startProcessing(){
		visionThread = new VisionThread(theCamera.getCamera(), new VisionProcessingPipeline(), pipeline -> {
		
		if (pipeline.findBlobsOutput().toArray().length > 0) {
			
			//Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
			synchronized (imgLock) {
			//	centerX = r.x + (r.width / 2);
			//	centerY = r.y + (r.length/2);
			System.out.println("Pipeline is printing: " + pipeline.findBlobsOutput().toArray()[0].pt.x);
			System.out.println("Pipeline is printing: " + pipeline.findBlobsOutput().toArray()[0].pt.y);
			}
		}
		
		numObjectsDetected = pipeline.findBlobsOutput().toArray().length;
		currentHeading = drive_train.getGyro();
		});
		visionThread.start();
		
	}

	public void findBall(){
		synchronized (imgLock) {
			centerX = this.centerX;
			centerY = this.centerY;
		}
		double turn = centerX - (IMG_WIDTH / 2); 
		
		while(centerY < 300){
			drive_train.arcadeDrive(0.65, turn * 0.005);
		}

	}

	public void getBall(){
		double currentPlace = drive_train.getLeftDriveEncoderDistance();
		double destination = currentPlace + 15;
		drive_train.tankDriving(0.6,0.6);
		the_Intake.runIntakeUp();

		while(currentPlace < destination){
			currentPlace = drive_train.getLeftDriveEncoderDistance();
			
		}
		drive_train.tankDriving(0, 0);
		the_Intake.stopIntake();
		retrievalCount++;
	}

	public void transitionToNextBall(){

		
		if(isRed && retrievalCount == 1)
		{
			drive_train.arcadeDrive(0, -0.55);
			while(numObjectsDetected == 0)
			{
				System.out.println("Looking for Ball!");
			}			
		}
		else if(isRed && retrievalCount == 2)
		{
			drive_train.arcadeDrive(0, 0.55);
			while(numObjectsDetected == 0)
			{
				System.out.println("Looking for Ball!");
			}		
		}
		else if(!isRed && retrievalCount == 1)
		{
			drive_train.arcadeDrive(0, 0.55);
			while(numObjectsDetected == 0)
			{
				System.out.println("Looking for Ball!");
			}	
		}
		else if(!isRed && retrievalCount == 2)
		{
			drive_train.arcadeDrive(0, -0.55);
			while(numObjectsDetected == 0)
			{
				System.out.println("Looking for Ball!");
			}	
		}
	}

	public void pausing(double timeToWait){
		double startTime = System.currentTimeMillis();
		double finalTime = startTime + timeToWait;
		while(startTime < finalTime){
			startTime = System.currentTimeMillis();
		}
	}

	public void endOfProgram()
	{
		double currentPlace = 0;
		drive_train.resetLeftDriveEncoderCount();
		currentHeading = drive_train.getGyro();
		if(currentHeading > 10)
		{
			drive_train.arcadeDrive(0, -0.55);
			while(currentHeading > 10)
			{
				currentHeading = drive_train.getGyro();
			}
		}
		else if(currentHeading < -10)
		{
			drive_train.arcadeDrive(0, 0.55);
			while(currentHeading < -10)
			{
				currentHeading = drive_train.getGyro();
			}
		}
		else
		{
			if(isRed)
			{
				drive_train.arcadeDrive(0.55, 0);
				drive_train.resetLeftDriveEncoderCount();
				while(currentPlace < 150)
				{
					currentPlace = drive_train.getLeftDriveEncoderDistance();
				}
				end(false);
				
			}
			else
			{
				drive_train.arcadeDrive(0.55, 0);
				drive_train.resetLeftDriveEncoderCount();
				while(currentPlace < 60)
				{
					currentPlace = drive_train.getLeftDriveEncoderDistance();
				}
				end(false);
			}
		}
	}

	public void determinePath(){
		startingDistance = drive_train.getLeftDriveEncoderDistance();
		if(startingDistance > finalDistance){
			isRed = false;
		}
	}

	public void theEntireSequence(){
		findBall();
		getBall();
		determinePath();
		transitionToNextBall();
		findBall();
		getBall();
		transitionToNextBall();
		findBall();
		getBall();
		transitionToNextBall();
		endOfProgram();
	}

}
