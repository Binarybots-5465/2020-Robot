/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Pneumatics;

public class RetractIntakeSolenoid extends CommandBase {
  private final Pneumatics m_pnSubsystem;

  public RetractIntakeSolenoid(Pneumatics pnSubsystem) {
    m_pnSubsystem = pnSubsystem;

    addRequirements(m_pnSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_pnSubsystem.setIntakeSolenoid(DoubleSolenoid.Value.kReverse); //Retracts the piston so the balls in the intake are unobstructed and can move through
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true; //Exits immediately after called, the solenoid 'latches' the piston state and stays
  }
}
