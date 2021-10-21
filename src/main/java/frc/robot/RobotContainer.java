// Code taken from https://github.com/Team1710CTO/MK3-Swerve

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Commands.CollectorCom;
import frc.robot.Commands.SwerveDriveCommand;
import frc.robot.Subsystems.CollectorSub;
import frc.robot.Subsystems.SwerveDrivetrain;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

    private final XboxController controller = new XboxController(0);

    private final SwerveDrivetrain drivetrain = new SwerveDrivetrain();

    private final CollectorSub collector = new CollectorSub();

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        drivetrain.setDefaultCommand(new SwerveDriveCommand(drivetrain, controller));
        collector.setDefaultCommand(new CollectorCom(controller, collector));
    }
}