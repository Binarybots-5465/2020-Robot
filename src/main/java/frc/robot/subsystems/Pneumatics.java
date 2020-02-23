/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Pneumatics extends SubsystemBase {
  
  public static Compressor pneumaticsCompressor = new Compressor(Constants.PCMCANID);

  public static DoubleSolenoid intakeSolenoid = new DoubleSolenoid(Constants.PCMIntakeForwardPortID, Constants.PCMIntakeReversePortID);
  public static DoubleSolenoid climberSolenoid = new DoubleSolenoid(Constants.PCMClimberForwardPortID, Constants.PCMClimberReversePortID);
  public static DoubleSolenoid backBallStopperSolenoid = new DoubleSolenoid(Constants.PCMBackBallStopperForwardPortID, Constants.PCMBackBallStopperReversePortID);

  public static DoubleSolenoid.Value intakeSolenoidStatus;

  public static DoubleSolenoid.Value backBallStopperSolenoidStatus;

  public Pneumatics() {
    pneumaticsCompressor.start();

    //Sets the default positions for the pneumatics system
    setIntakeSolenoid(Constants.PCMIntakeInitialPosition);
    setClimberSolenoid(Constants.PCMClimberInitialPosition);
    setBackBallStopperSolenoid(Constants.PCMBackBallStopperInitialPosition);

    intakeSolenoidStatus = Constants.PCMIntakeInitialPosition;
    backBallStopperSolenoidStatus = Constants.PCMBackBallStopperInitialPosition;
  }

  public void setIntakeSolenoid(DoubleSolenoid.Value position) {
    intakeSolenoid.set(position);
  }

  public void setClimberSolenoid(DoubleSolenoid.Value position) {
    climberSolenoid.set(position);
  }

  public void setBackBallStopperSolenoid(DoubleSolenoid.Value position) {
    backBallStopperSolenoid.set(position);
  }

  public void toggleIntakeSolenoid() {
    //Inverts the state of the solenoid.
    if(intakeSolenoidStatus == DoubleSolenoid.Value.kForward) { 
      intakeSolenoidStatus = DoubleSolenoid.Value.kReverse;
    } else {
      intakeSolenoidStatus = DoubleSolenoid.Value.kForward;
    }
    
    setIntakeSolenoid(intakeSolenoidStatus);
  }

  public void toggleBackBallStopperSolenoid() {
    //Inverts the state of the stopper solenoid.
    if(backBallStopperSolenoidStatus == DoubleSolenoid.Value.kForward) { 
      backBallStopperSolenoidStatus = DoubleSolenoid.Value.kReverse;
    } else {
      backBallStopperSolenoidStatus = DoubleSolenoid.Value.kForward;
    }

    setBackBallStopperSolenoid(backBallStopperSolenoidStatus);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
