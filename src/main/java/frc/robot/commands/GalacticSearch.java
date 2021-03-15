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

	private double centerX;
	private double centerY;
	private final Object imgLock = new Object();
	private static final int IMG_WIDTH = 640; //On testing day make sure we googled the right camera
	private static final int IMG_HEIGHT = 480;
	private int numObjectsDetected = 0;
	public static int retrievalCount = 0;
	private boolean isInRetrieval = false;
	
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
		startProcessing();
	} 

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		updateImage();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
        return false;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		drive_train.tankDriving(0, 0);
		the_Intake.stopIntake();
	}

	public void startProcessing(){
		visionThread = new VisionThread(theCamera.getCamera(), new VisionProcessingPipeline(), pipeline -> {
		theCamera.getCamera().setResolution(IMG_WIDTH, IMG_HEIGHT);
		if (pipeline.findBlobsOutput().toArray().length > 0) {
			
			//Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
			synchronized (imgLock) {
				centerX = pipeline.findBlobsOutput().toArray()[0].pt.x;
				centerY = pipeline.findBlobsOutput().toArray()[0].pt.y;
				System.out.println("Pipeline is printing: " + pipeline.findBlobsOutput().toArray()[0].pt.x);
				System.out.println("Pipeline is printing: " + pipeline.findBlobsOutput().toArray()[0].pt.y);
			}
		}
		
		numObjectsDetected = pipeline.findBlobsOutput().toArray().length;
		});
		visionThread.start();
		
	}

	public void updateImage(){
			if(!isInRetrieval){
			synchronized (imgLock) {
				centerX = this.centerX;
				centerY = this.centerY;
			}

			double turn = ((centerX - (IMG_WIDTH / 2))/1280); //Change if ball is not centered on camera
			
			double turnTotal = 0.2;
			if(turn < 0){
				turnTotal = -turnTotal;
			}
			
			//System.out.println("Turn :" +turn);
			drive_train.arcadeDrive(0.55,  turnTotal + turn);
			
			if(centerY > 300){
				ballRetrievalSequence();
			}
		}
		
	}

	public void ballRetrievalSequence(){
		isInRetrieval = true;
		double currentPlace = drive_train.getLeftDriveEncoderDistance();
		double destination = currentPlace + 30;
		drive_train.tankDriving(0.505,0.5);
		the_Intake.runIntakeUp();
		//found out this value soon
		while(currentPlace < destination){
			currentPlace = drive_train.getLeftDriveEncoderDistance();
			
		}
		drive_train.tankDriving(0, 0);
		the_Intake.stopIntake();
		
		/*
		drive_train.arcadeDrive(0, -0.2);
		while (numObjectsDetected == 0){
			System.out.println("Scanning... No objects detected..");
		}
		drive_train.arcadeDrive(0.0, 0.0);
		*/

		isInRetrieval = false;
	}

}
