// Code taken from https://github.com/Team1710CTO/MK3-Swerve

package frc.robot.Commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.SwerveDrivetrain;
//import frc.robot.Subsystems.SwerveModuleMK3;

public class SwerveDriveCommand extends CommandBase {

    private final SwerveDrivetrain drivetrain;
    private final XboxController controller;

    // Slew rate limiters to make joystick inputs more gentle; 1/3 sec from 0 to 1.
    private final SlewRateLimiter xspeedLimiter = new SlewRateLimiter(6);
    private final SlewRateLimiter yspeedLimiter = new SlewRateLimiter(6);
    private final SlewRateLimiter rotLimiter = new SlewRateLimiter(6);

    public SwerveDriveCommand(SwerveDrivetrain drivetrain, XboxController controller) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);

        this.controller = controller;
    }

    @Override
    public void execute() {
        // Get the x speed. We are inverting this because Xbox controllers return
        // negative values when we push forward.
        final double deadband = 0.1;
        double xSpeedTemp;

        if(Math.abs(controller.getY(GenericHID.Hand.kLeft)) > deadband){
             xSpeedTemp = -xspeedLimiter.calculate(controller.getY(GenericHID.Hand.kLeft))
                            * SwerveDrivetrain.kMaxSpeed;
        }
        else{
            xSpeedTemp = 0;
        }
        final var xSpeed = xSpeedTemp;

        // Get the y speed or sideways/strafe speed. We are inverting this because
        // we want a positive value when we pull to the left. Xbox controllers
        // return positive values when you pull to the right by default.
        double ySpeedTemp;

        if(Math.abs(controller.getX(GenericHID.Hand.kLeft)) > deadband){
            ySpeedTemp = -yspeedLimiter.calculate(controller.getX(GenericHID.Hand.kLeft))
                        * SwerveDrivetrain.kMaxSpeed;
        }
        else{
            ySpeedTemp = 0;
        }
        final var ySpeed = ySpeedTemp;

        // Get the rate of angular rotation. We are inverting this because we want a
        // positive value when we pull to the left (remember, CCW is positive in
        // mathematics). Xbox controllers return positive values when you pull to
        // the right by default.
        double rSpeedTemp;

        if(Math.abs(controller.getX(GenericHID.Hand.kRight)) > deadband){
            rSpeedTemp = -rotLimiter.calculate(controller.getX(GenericHID.Hand.kRight))
                        * SwerveDrivetrain.kMaxAngularSpeed;
        }
            else{
            rSpeedTemp = 0;
        }
        final var rot = rSpeedTemp;

        boolean calibrate = controller.getBumper(GenericHID.Hand.kLeft);

        drivetrain.drive(xSpeed, ySpeed, rot, false, calibrate);

    }

}