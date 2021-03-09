package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.VisionProcessingPipeline;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.Drivetrain;
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
	private Camera theCamera;
	private VisionThread visionThread;

    
    public GalacticSearch(Drivetrain driveTrain, Camera allCamera) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		
		drive_train = driveTrain;
		
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
    
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
        return true;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		
	}

	public void startProcessing(){
		visionThread = new VisionThread(theCamera.getCamera(), new VisionProcessingPipeline(), pipeline -> {
		if (pipeline.findBlobsOutput().toArray().length > 0) {
			//Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
			//synchronized (imgLock) {
			//    centerX = r.x + (r.width / 2);
			System.out.println(pipeline.findBlobsOutput());
			//}
		}
		});
		visionThread.start();
	}
}
