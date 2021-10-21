package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Subsystems.CollectorSub;
import edu.wpi.first.wpilibj.GenericHID;

public class CollectorCom extends CommandBase{
    private final int collectButton = 0;
    private final int feedButton = 1;
    private final XboxController controller;
    private CollectorSub collector;

    private int shotTimer = 0;

    public CollectorCom(XboxController controller, CollectorSub collector){
        this.collector = collector;
        addRequirements(collector);
        this.controller = controller;
    }
    
    @Override
    public void execute()
    {
        if(controller.getRawButton(collectButton))
        {
            final var driveSpeed = controller.getY(GenericHID.Hand.kLeft)/2 + 0.5;
            collector.Collect(driveSpeed);
        }
        else
            collector.StopCollect();
        
        if(controller.getRawButton(feedButton)){
            collector.Feed();
            shotTimer = 10;
        }
        else if(shotTimer > 0){
            collector.Feed();
            shotTimer--;
        }
        else
            collector.StopFeed();
    }
}
