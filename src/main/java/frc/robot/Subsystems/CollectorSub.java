package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class CollectorSub extends SubsystemBase{
    
    private TalonSRX collectorMotor = new TalonSRX(15);
    private TalonSRX feederMotorRight = new TalonSRX(13);
    private TalonSRX feederMotorLeft = new TalonSRX(14);

    public CollectorSub(){
    }

    public void Collect(double speed){
        collectorMotor.set(TalonSRXControlMode.PercentOutput, speed);
    }

    public void StopCollect(){
        collectorMotor.set(TalonSRXControlMode.PercentOutput, 0.0);
    }


    public void Feed(){
        feederMotorLeft.set(TalonSRXControlMode.PercentOutput, 0.25);
        feederMotorRight.set(TalonSRXControlMode.PercentOutput, 0.5);
    }

    public void StopFeed(){
        feederMotorLeft.set(TalonSRXControlMode.PercentOutput, 0.0);
        feederMotorRight.set(TalonSRXControlMode.PercentOutput, 0.0);

    }

}

