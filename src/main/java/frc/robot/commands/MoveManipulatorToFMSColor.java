/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ControlPanelManipulator;
import frc.robot.subsystems.ControlPanelManipulator.ColorOptions;

public class MoveManipulatorToFMSColor extends CommandBase {
  private final ControlPanelManipulator m_CPManipulatorSubsystem;
  private ColorOptions m_FMSAssignedColor;

  private boolean m_cancelCommand = false; //This is eventually returned by isFinished() and can be used to prematurely cancel a command (e.g if the FMS or color sensor returned bad data).

  public MoveManipulatorToFMSColor(ControlPanelManipulator cpManipulator) {
    m_CPManipulatorSubsystem = cpManipulator;

    addRequirements(m_CPManipulatorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_CPManipulatorSubsystem.setManipulatorSpeed(0.0); //Stops the wheel if previously spinning to prevent an accidental change in position.

    m_FMSAssignedColor = m_CPManipulatorSubsystem.getFMSAssignedColor(); //Gets the assigned color by the FMS.
    if(m_FMSAssignedColor == ColorOptions.Unknown) {
      m_cancelCommand = true; //The FMS returned bad or unavailable data, the CPM can't position to that.
    } 

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_CPManipulatorSubsystem.setManipulatorSpeed(Constants.controlPanelManipulatorMovementSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_CPManipulatorSubsystem.setManipulatorSpeed(0.0); //Resets the manipulator's motor to zero.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(m_cancelCommand == true)
      return m_cancelCommand; //The command was abruptly canceled for some reason and had to stop.

    ColorOptions currentFMSReadColor = m_CPManipulatorSubsystem.getFieldMatchedColor(); //Gets the color that the FMS sensor will read.
    if(currentFMSReadColor == m_FMSAssignedColor) {
      return true; //Stops the command, the Control Panel is on the right color.
    } else {
      return false; //Continue moving the CPM.
    }
  }
}
