/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.util.Color;

import java.util.HashMap;
import java.util.Map;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

public class ControlPanelManipulator extends SubsystemBase {
  
  private static final ColorSensorV3 m_colorSensor = new ColorSensorV3(Port.kOnboard);

  private static final ColorMatch m_colorMatcher = new ColorMatch(); //The Rev Robotics ColorMatch class will provide methods to recognize colors.

  //These targets provide percentages of the RGB color spectrum that align with the Control Panel colors.
  private static final Color redTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private static final Color yellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
  private static final Color blueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private static final Color greenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);

  public enum ColorOptions {
    Red, Yellow, Blue, Green, Unknown
  }

  private WPI_TalonSRX controlPanelMotor = new WPI_TalonSRX(Constants.controlPanelManipulatorCANID);

  private static Map<Color, ColorOptions> colorResult = new HashMap<>();

  public ControlPanelManipulator() {
    m_colorMatcher.addColorMatch(redTarget);
    m_colorMatcher.addColorMatch(yellowTarget);
    m_colorMatcher.addColorMatch(blueTarget);
    m_colorMatcher.addColorMatch(greenTarget); 
    
    //Adds targets to the color map
    colorResult.put(redTarget, ColorOptions.Red);
    colorResult.put(yellowTarget, ColorOptions.Yellow);
    colorResult.put(blueTarget, ColorOptions.Blue);
    colorResult.put(greenTarget, ColorOptions.Green);
  }

  /**
   * Gets the raw color from the color sensor.
   * @return The Color of the color returned by the sensor.
   */
  private Color getRawSensorColor() {
    return m_colorSensor.getColor();
  }

  /**
   * Gets the matched color of returned by the color sensor.
   * @return The enum value of ColorOptions of the closest matched values from the color sensor.
   */
  public ColorOptions getRawMatchedColor() {
    ColorMatchResult matchedColor = m_colorMatcher.matchClosestColor( getRawSensorColor() );

    return colorResult.get(matchedColor.color);
  }

  /**
   * Gets the matched color of what the field sensor sees.
   * @return The enum value of ColorOptions of the matched value that the FMS sees on the Control Panel.
   */
  public ColorOptions getFieldMatchedColor() {
    ColorOptions rawColor = getRawMatchedColor();

    //The difference between the color that the color sensor detects and the FMS detects is offset by 1 wedge.
    switch(rawColor) {
      case Blue:
        return ColorOptions.Red;
      case Green:
        return ColorOptions.Yellow;
      case Yellow: 
        return ColorOptions.Green;
      case Red:
        return ColorOptions.Blue;
      default: //The color sensor detected an unknown color.
        return ColorOptions.Unknown;
    }

  }

  /**
   * Sets the speed for the control panel manipulator's motor.
   */
  public void setManipulatorSpeed(double speed) {
    controlPanelMotor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
