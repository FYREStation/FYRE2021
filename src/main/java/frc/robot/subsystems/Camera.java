package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Camera extends SubsystemBase{
    public static Servo cameraServo;
    UsbCamera usbCamera;

    public Camera(){
        //cameraServo = new Servo(Constants.CAMERA_SERVO);
        prepareVisionProcessing();
        
    }

    public void prepareVisionProcessing(){     
        // Creates UsbCamera and MjpegServer [1] and connects them
        usbCamera = CameraServer.getInstance().startAutomaticCapture();
        /*
        MjpegServer mjpegServer1 = new MjpegServer("serve_USB Camera 0", 1181);
        mjpegServer1.setSource(usbCamera);

         // Creates the CvSink and connects it to the UsbCamera
        CvSink cvSink = new CvSink("opencv_USB Camera 0");
        cvSink.setSource(usbCamera);

        // Creates the CvSource and MjpegServer [2] and connects them
        CvSource outputStream = new CvSource("Blur", PixelFormat.kMJPEG, 640, 480, 30);
        MjpegServer mjpegServer2 = new MjpegServer("serve_Blur", 1182);
        mjpegServer2.setSource(outputStream);
        */
    }
    
    public UsbCamera getCamera(){
        return usbCamera;
    }

    @Override
    public void periodic() {
      //servoTest();
    }
}