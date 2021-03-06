/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Pneumatics;

public class ToggleBackBallStopperSolenoid extends CommandBase {
  private final Pneumatics m_pneumaticsSubsystem;

  public ToggleBackBallStopperSolenoid(Pneumatics pneumaticsSubsystem) {
    m_pneumaticsSubsystem = pneumaticsSubsystem;

    addRequirements(m_pneumaticsSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_pneumaticsSubsystem.toggleBackBallStopperSolenoid();
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
    return true; //Exits immediately after called, the solenoid 'latches' the piston state and stays.
  }
}
