/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public Joystick driveJoystick;
  public Joystick auxJoystick;
  
  private DriverStation ds = DriverStation.getInstance();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the Joysticks and button bindings
    configureJoysticks();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureJoysticks() {

    //Automatically searches for the joysticks, if the names could not be found it defaults association to the next open ports 
    ArrayList<String> foundJoysticksName = new ArrayList<String>();
    for(int port = 0; port <= 5; port++) {
      String jName = ds.getJoystickName(port);
      System.out.println("Registered DS Joystick: " + jName);

      if(jName.equals(Constants.driveJoystickName)) { //Found Driver Joystick
        driveJoystick = new Joystick(port);
        foundJoysticksName.add(jName);
        System.out.println("Found Drive Joystick: " + jName + " on port " + port);
      }
      if(jName.equals(Constants.auxJoystickName)) { //Found Aux Joystick
        auxJoystick = new Joystick(port);
        foundJoysticksName.add(jName);
        System.out.println("Found Aux Joystick: " + jName + " on port " + port);
      }
    }
    if(foundJoysticksName.size() < 2) {
      System.out.print("Only found " + foundJoysticksName.size() + " joystick(s):");
      for(String arrString : foundJoysticksName) { System.out.print(" " + arrString);}

      System.out.println("\n Make sure you can see both (" + Constants.driveJoystickName + ") and (" + Constants.auxJoystickName + ") in the driverstation!");
      System.out.println("Assigned the Drive joystick to PORT 0 & Aux joystick to PORT 1.");
      
      driveJoystick = new Joystick(0);
      auxJoystick = new Joystick(1);
    }


  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
