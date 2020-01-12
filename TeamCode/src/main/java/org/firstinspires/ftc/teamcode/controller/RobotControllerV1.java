package org.firstinspires.ftc.teamcode.controller;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.ColorSensor;

import static org.firstinspires.ftc.teamcode.constants.ChassisGeometry.GEAR_RATIO;
import static org.firstinspires.ftc.teamcode.constants.ChassisGeometry.HALF_WIDTH;


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

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        leftDrive.setTargetPosition((int)(leftDrive.getCurrentPosition() + (144/Math.PI)*centimeters*GEAR_RATIO));
        rightDrive.setTargetPosition((int)(rightDrive.getCurrentPosition() + (144/Math.PI)*centimeters*GEAR_RATIO));
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDrive.setPower(1);
        rightDrive.setPower(1);
    }

    @Override
    public void turn(double radians) {
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        leftDrive.setTargetPosition((int)(leftDrive.getCurrentPosition() + (144/Math.PI)*HALF_WIDTH*radians*GEAR_RATIO));
        rightDrive.setTargetPosition((int)(rightDrive.getCurrentPosition() + (144/Math.PI)*HALF_WIDTH*radians*GEAR_RATIO));
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDrive.setPower(1);
        rightDrive.setPower(1);
    }
    //double right = -Math.PI/2;
    //double left = Math.PI/2;
    @Override
    public void newMove(double distance){
        distance = distance * 120.02 * 3 / 140;//cm to cm. the full field is 1200.2*3 mm and the virtual field is 1400mm
        moveForward(distance);while (leftDrive.isBusy() && rightDrive.isBusy()) {};
    }
    //right = -pi/2
    //left = pi/2
    @Override
    public void newTurn(double angle){
        angle = -angle;
        turn(angle);while (leftDrive.isBusy() && rightDrive.isBusy()) {};
    }
    @Override
    public void goUntilBlue(ColorSensor color1){
        while (color1.green()>50||color1.red()>50||color1.blue()<200) {
            moveForward(1);
        }
    }
    @Override
    public void goUntilRed(ColorSensor color1){
        while (color1.green()>100||color1.red()<200||color1.blue()>100) {
            moveForward(1);
        }
    }

} 
