package org.firstinspires.ftc.teamcode.controller;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import static org.firstinspires.ftc.teamcode.constants.ChassisGeometry.GEAR_RATIO;

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
    //setTargetPosition is probably between 0 and 2
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    public RobotControllerV1 (DcMotor leftDrive, DcMotor rightDrive) {
        this.leftDrive = leftDrive;
        this.rightDrive = rightDrive;
    }
    @Override
    public void moveForward(double centimeters) {
        //target position measured in motor ticks (1440/rotation)
        //10PI cm per 1440 ticks
        //PI cm per 144 ticks
        //144/PI ticks per 1 cm assuming gear ratio of 1
        //144/PI ticks * gear ratio per 1 cm

        //these set directions cause a null pointer exception
        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        //this is still untested
        leftDrive.setTargetPosition((int)(144*Math.PI*centimeters*GEAR_RATIO));
        rightDrive.setTargetPosition((int)(144*Math.PI*centimeters*GEAR_RATIO));
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void turnRight(double degrees) {

    }

    @Override
    public void turnLeft(double degrees) {

    }
}
