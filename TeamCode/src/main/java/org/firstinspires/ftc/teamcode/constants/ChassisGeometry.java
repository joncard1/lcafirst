package org.firstinspires.ftc.teamcode.constants;

/**
 * I have created this class to capture the various geometric data about the robot. This data is
 * used to compute answers such as "move forward 3 feet" or "turn 90 degrees to the right." The
 * geometry of the bot itself is required to know how many rotations of each wheel is required to
 * accomplish that.
 *
 * I have separated it from other files to give the mechanical team an opportunity to edit this file
 * without the possibility of conflict with the software team.
 */
public final class ChassisGeometry {
    public static int WHEEL_DIAMETER = 10; //this is not used right now. If the value changes, we need to rewrite the methods to employ this value
    public static int AXEL_DISTANCE = 0;
    public static double GEAR_RATIO = .25; //one turn of wheel = this many complete turns of motor
    public static double HALF_WIDTH = 21; //centimeters between center of bot and wheel (currently set to center of wheel; might be interior edge)
}
