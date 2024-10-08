package frc.robot;

// Imports that allow the usage of REV Spark Max motor controllers
import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

//import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.cameraserver.CameraServer;


public class Robot extends TimedRobot {
  
 
  CANSparkBase leftRear = new CANSparkMax(2, MotorType.kBrushless);
  CANSparkBase leftFront = new CANSparkMax(3, MotorType.kBrushless);
  CANSparkBase rightRear = new CANSparkMax(4, MotorType.kBrushless);
  CANSparkBase rightFront = new CANSparkMax(5, MotorType.kBrushless);
  CANSparkBase m_launchWheelTop = new CANSparkMax(6, MotorType.kBrushless);
  
  CANSparkBase m_launchWheelBottom = new CANSparkMax(7, MotorType.kBrushless);
  CANSparkBase m_intake = new CANSparkMax(8, MotorType.kBrushless);

  //private final DigitalInput intakeLimitSwitch = new DigitalInput(0);


  DifferentialDrive m_drivetrain;

  //Joystick m_driverController = new Joystick(1);
  //Joystick m_manipController = new Joystick(2);
  PS5Controller m_driverController = new PS5Controller(1);
  PS5Controller m_manipController = new PS5Controller(2);


  // --------------- Magic numbers. Use these to adjust settings. ---------------

// Current limits//
  static final int DRIVE_CURRENT_LIMIT_A = 60;
  static final int INTAKE_CURRENT_LIMIT_A = 60;
  static final int LAUNCHERTOP_CURRENT_LIMIT_A = 60;
  static final int LAUNCHERBOTTOM_CURRENT_LIMIT_A = 60;

 //Speeds//
  static final double INTAKE_OUT_SPEED = .35;
  static final double INTAKE_IN_SPEED = .55;
  static final double INTAKE_FIRE_SPEED = 1.0;
  static final double LAUNCHERTOP_BUMPER_SPEED = .45;
  static final double LAUNCHERBOTTOM_BUMPER_SPEED = .45;
  static final double LAUNCHERTOP_STAGE_SPEED = .43;
  static final double LAUNCHERBOTTOM_STAGE_SPEED = .25;
  static final double LAUNCHERTOP_FERRY_SPEED = .90;
  static final double LAUNCHBOTTOM_FERRY_SPEED = .90;
  static final double LAUNCHERTOP_AMP_SPEED = .20;
  static final double LAUNCHERBOTTOM_AMP_SPEED = .20;
  static final double LAUNCHERTOP_POSITION_SPEED = .25;
  static final double LAUNCHERBOTTOM_POSITION_SPEED = .25;

 
  @Override
  public void robotInit() {
 



    /*
     * Apply the current limit to the drivetrain motors
     */
    leftRear.setSmartCurrentLimit(DRIVE_CURRENT_LIMIT_A);
    leftFront.setSmartCurrentLimit(DRIVE_CURRENT_LIMIT_A);
    rightRear.setSmartCurrentLimit(DRIVE_CURRENT_LIMIT_A);
    rightFront.setSmartCurrentLimit(DRIVE_CURRENT_LIMIT_A);
    m_launchWheelTop.setSmartCurrentLimit(LAUNCHERTOP_CURRENT_LIMIT_A);
    m_launchWheelBottom.setSmartCurrentLimit(LAUNCHERBOTTOM_CURRENT_LIMIT_A);
    m_intake.setSmartCurrentLimit(INTAKE_CURRENT_LIMIT_A);

    /*
     * Tells the rear wheels to follow the same commands as the front wheels
     */
    leftRear.follow(leftFront);
    rightRear.follow(rightFront);

    /*
     * One side of the drivetrain must be inverted, as the motors are facing opposite directions
     */
    leftFront.setInverted(true);
    rightFront.setInverted(false);
  

    m_drivetrain = new DifferentialDrive(leftFront, rightFront);

    //CameraServer.startAutomaticCapture();

    m_launchWheelTop.setIdleMode(IdleMode.kCoast);
    m_launchWheelBottom.setIdleMode(IdleMode.kCoast);
  }

 
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Time (seconds)", Timer.getFPGATimestamp());
  }


  /*
   * Auto constants, change values below in autonomousInit()for different autonomous behaviour
   *
   * A delayed action starts X seconds into the autonomous period
   *
   * A time action will perform an action for X amount of seconds
   *
   * Speeds can be changed as desired and will be set to 0 when
   * performing an auto that does not require the system
   */
  double AUTO_LAUNCH_DELAY_S;
  double AUTO_LAUNCH_DELAY_S2;
  double AUTO_LAUNCH_DELAY_S3;
  double AUTO_LAUNCH_DELAY_S4;
  double AUTO_DRIVE_DELAY_S;
  double AUTO_DRIVE_DELAY_S2;
  double AUTO_POSITION_DELAY_S;

  double AUTO_DRIVE_TIME_S1;
  double AUTO_DRIVE_TIME_S2;

  double AUTO_DRIVE_SPEED;
  double AUTO_LAUNCHERTOP_SPEED_S1;
  double AUTO_LAUNCHERBOTTOM_SPEED_S1;
  double AUTO_LAUNCHERTOP_SPEED_S2;
  double AUTO_LAUNCHERBOTTOM_SPEED_S2;
  double AUTO_NOTETOP_POSITION_SPEED_S1;
  double AUTO_NOTEBOTTOM_POSITION_SPEED_S1;
  double AUTO_INTAKE_FIRE_SPEED;
  double AUTO_INTAKE_POSITION_SPEED;

  double autonomousStartTime;

  @Override
  public void autonomousInit() {
   

    leftRear.setIdleMode(IdleMode.kBrake);
    leftFront.setIdleMode(IdleMode.kBrake);
    rightRear.setIdleMode(IdleMode.kBrake);
    rightFront.setIdleMode(IdleMode.kBrake);

    //AUTO_LAUNCH_DELAY_S = 4;
    AUTO_LAUNCH_DELAY_S2 = 6;
    AUTO_DRIVE_DELAY_S = 4;
   // AUTO_POSITION_DELAY_S = 0;
    //AUTO_DRIVE_DELAY_S2 = 0;
   // AUTO_LAUNCH_DELAY_S3 = 0;

    AUTO_DRIVE_TIME_S1 = 3.5;
    AUTO_DRIVE_SPEED = 0.5;
    AUTO_LAUNCHERTOP_SPEED_S1 = .38;
    AUTO_LAUNCHERBOTTOM_SPEED_S1 = .38;
   // AUTO_LAUNCHERTOP_SPEED_S2 = .4;
    //AUTO_LAUNCHERBOTTOM_SPEED_S2 = .4;
    //AUTO_NOTETOP_POSITION_SPEED_S1 = .30;
    //AUTO_NOTEBOTTOM_POSITION_SPEED_S1 = .30;
    AUTO_INTAKE_FIRE_SPEED = .6;
   // AUTO_INTAKE_POSITION_SPEED = .35;
    
    
    autonomousStartTime = Timer.getFPGATimestamp();
    autonomousStartTime = Timer.getFPGATimestamp();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

    double timeElapsed = Timer.getFPGATimestamp() - autonomousStartTime;

    if(timeElapsed < AUTO_DRIVE_DELAY_S)
    {
   
      m_drivetrain.arcadeDrive(0, 0);
    }
  
    if(timeElapsed < AUTO_LAUNCH_DELAY_S2 + AUTO_DRIVE_TIME_S1)
    {
   
      m_drivetrain.arcadeDrive(AUTO_DRIVE_SPEED, 0);
    }
   
   else
    {
    ;
      m_drivetrain.arcadeDrive(0, 0);
    }
  }
   
  



  @Override
  public void teleopInit() {
   
    leftRear.setIdleMode(IdleMode.kBrake);
    leftFront.setIdleMode(IdleMode.kBrake);
    rightRear.setIdleMode(IdleMode.kBrake);
    rightFront.setIdleMode(IdleMode.kBrake);
  }

  @Override
  public void teleopPeriodic() {

   
    if (m_manipController.getRawButton(6)) {
      
      m_launchWheelTop.set(LAUNCHERTOP_POSITION_SPEED);
      m_launchWheelBottom.set(LAUNCHERBOTTOM_POSITION_SPEED);
      m_intake.set(-INTAKE_IN_SPEED);
   
   }
    else if(m_manipController.getRawButtonReleased(6))
    {
      m_intake.set(0);
     
    }

   
     if (m_manipController.getRawButton(5)) {
      m_intake.set(-INTAKE_FIRE_SPEED);
    
   }
    else if(m_manipController.getRawButtonReleased(5))
    {
      m_intake.set(0);
     
    }
        if (m_manipController.getRawButton(3))
    {
      m_intake.set(INTAKE_OUT_SPEED);
      m_launchWheelTop.set(LAUNCHERTOP_POSITION_SPEED);

      m_launchWheelBottom.set(LAUNCHERBOTTOM_POSITION_SPEED);
      
    }
    else if(m_manipController.getRawButtonReleased(3))
    {
      m_intake.set(0);
      m_launchWheelTop.set(0);
      m_launchWheelBottom.set(0);
    
    }

    

    if(m_manipController.getPOV() == 0)
    {
      m_launchWheelTop.set(-LAUNCHERTOP_BUMPER_SPEED);
      m_launchWheelBottom.set(-LAUNCHERBOTTOM_BUMPER_SPEED);
    }
    else if(m_manipController.getPOV() == 90)
    {
      m_launchWheelTop.set(-LAUNCHERTOP_FERRY_SPEED);
      m_launchWheelBottom.set(-LAUNCHBOTTOM_FERRY_SPEED);
    }
    else if(m_manipController.getPOV() == 180)
    {
      m_launchWheelTop.set(-LAUNCHERTOP_STAGE_SPEED);
      m_launchWheelBottom.set(-LAUNCHERBOTTOM_STAGE_SPEED);
    }
    else if(m_manipController.getPOV() == 270)
    {
      
      m_launchWheelTop.set(-LAUNCHERTOP_AMP_SPEED);
      m_launchWheelBottom.set(-LAUNCHERBOTTOM_AMP_SPEED);
    }
    else
    {
      m_launchWheelTop.set(0);
      m_launchWheelBottom.set(0);
     
    }
  
  
    
  //m_drivetrain.arcadeDrive(m_driverController.getRawAxis(1), m_driverController.getRawAxis(4));
  m_drivetrain.arcadeDrive(m_driverController.getRawAxis(1)*.85, m_driverController.getRawAxis(2)*.85);
    
  }
}


