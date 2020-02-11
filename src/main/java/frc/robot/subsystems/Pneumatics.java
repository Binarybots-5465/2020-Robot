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

  public Pneumatics() {
    pneumaticsCompressor.start();

    //Sets the default positions for the pneumatics system
    setIntakeSolenoid(Constants.PCMIntakeInitialPosition);
    setClimberSolenoid(Constants.PCMClimberInitialPosition);
  }

  public void setIntakeSolenoid(DoubleSolenoid.Value position) {
    intakeSolenoid.set(position);
  }

  public void setClimberSolenoid(DoubleSolenoid.Value position) {
    climberSolenoid.set(position);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
