package org.firstinspires.ftc.teamcode.controller;

/**
 * This class implements that functionality defined in RobotController. The reason to separate
 * RobotController from RobotControllerV1 is you never know how to code will end up looking. Perhaps
 * there will be one class called Robot that implements RobotController and SensorProvider (an
 * interface I haven't written that provides access to the sensor data). In that case, it's not
 * important whether one class or two classes or a whole bunch of classes are involved in the
 * process; any variable of type RobotController can be expected to move the robot as described by
 * these methods.
 */
public class RobotControllerV1 implements RobotController {
    @Override
    public void moveForward(float centimeters) {

    }

    @Override
    public void turnRight(float degrees) {

    }

    @Override
    public void turnLeft(float degrees) {

    }
}
