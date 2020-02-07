package org.firstinspires.ftc.teamcode.controller;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

import static org.firstinspires.ftc.teamcode.constants.ChassisGeometry.GEAR_RATIO;
import static org.firstinspires.ftc.teamcode.constants.ChassisGeometry.HALF_WIDTH;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


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
    public DcMotor leftDrive;
    public DcMotor rightDrive;
    public DistanceSensor distance_sensor;
    public Servo servo1;
    public ColorSensor colorMain;
    public double right = -Math.PI/2;
    public double left = Math.PI/2;
    public double color1Disabled = 1;
    public double numberOfTimesHitColor = 0;
    public RobotControllerV1 (DcMotor leftDrive, DcMotor rightDrive) {
       this.rightDrive = rightDrive;
       this.leftDrive = leftDrive;
    }
    @Override
    public void moveForward(double centimeters) {
        //target position measured in motor ticks (1440/rotation)
        //10PI cm per 1440 ticks
        //PI cm per 144 ticks
        //144/PI ticks per 1 cm assuming gear ratio of 1
        //144/PI ticks * gear ratio per 1 cm

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        leftDrive.setTargetPosition((int)(leftDrive.getCurrentPosition() + (144/Math.PI)*centimeters*GEAR_RATIO));
        rightDrive.setTargetPosition((int)(rightDrive.getCurrentPosition() + (144/Math.PI)*centimeters*GEAR_RATIO));
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDrive.setPower(1);
        rightDrive.setPower(1);
    }

    @Override
    public void turn(double radians) {
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        leftDrive.setTargetPosition((int)(leftDrive.getCurrentPosition() + (144/Math.PI)*HALF_WIDTH*radians*GEAR_RATIO));
        rightDrive.setTargetPosition((int)(rightDrive.getCurrentPosition() + (144/Math.PI)*HALF_WIDTH*radians*GEAR_RATIO));
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDrive.setPower(1);
        rightDrive.setPower(1);
    }
    @Override
    public void newMove(double distance){
        moveForward(distance);while (leftDrive.isBusy() && rightDrive.isBusy()) {};
    }
    //right = -pi/2
    //left = pi/2
    @Override
    public void newTurn(double rad){
        rad = -rad;
        autoTurn(rad,1);while (leftDrive.isBusy() && rightDrive.isBusy()) {};
    }
    @Override
    public void goUntilBlue(ColorSensor color1){
        if(color1Disabled==1) {
            while(getDistance()>45*3){
                autoMove(1,.4);
            }
    }else{
        while (color1.red() <= color1.blue()) {
            moveForward(1);
        }
    }
    }
    @Override
    public void goUntilRed(ColorSensor color1){
        if(color1Disabled==1) {
                while(getDistance()>45*3){
                    autoMove(1,.4);
                }
            
        }else{
            while (color1.red() >= color1.blue()) {
                moveForward(1);
            }
        }
    }
    @Override
    public void initializeDistanceSensor1(DistanceSensor sensorInQuestion){
        distance_sensor = sensorInQuestion;
    }
    @Override
    public double getDistance(){
    return distance_sensor.getDistance(DistanceUnit.MM);
    }
    @Override
    public void initializeDCMotor1(DcMotor motor){
        leftDrive = motor;
    }
    public void initializeServo1(Servo servo){
        servo1 = servo;
    }
    public void initializeColorSensor(ColorSensor color1){
        colorMain = color1;
    };
    public void autoTurn(double rad, double pow){
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        leftDrive.setTargetPosition((int)(leftDrive.getCurrentPosition() + (144/Math.PI)*HALF_WIDTH*rad*GEAR_RATIO));
        rightDrive.setTargetPosition((int)(rightDrive.getCurrentPosition() + (144/Math.PI)*HALF_WIDTH*rad*GEAR_RATIO));
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        double i = 0;
        while(i<pow) {
            leftDrive.setPower(i);
            rightDrive.setPower(i);
            i+=.001;
        }
    };
    public void autoMove(double dist, double pow){
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        leftDrive.setTargetPosition((int)(leftDrive.getCurrentPosition() + (144/Math.PI)*dist*GEAR_RATIO));
        rightDrive.setTargetPosition((int)(rightDrive.getCurrentPosition() + (144/Math.PI)*dist*GEAR_RATIO));
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        double i = 0;
        while(i<pow) {
            leftDrive.setPower(i);
            rightDrive.setPower(i);
            i+=.001;
        }
    };
}
