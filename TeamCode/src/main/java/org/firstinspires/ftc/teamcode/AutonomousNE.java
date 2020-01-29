package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.controller.RobotController;
import org.firstinspires.ftc.teamcode.controller.RobotControllerV1;

/**
 * I have provided this class for the possibility that we are in autonomous mode and started in the
 * corner in the scoring side, on the right if you stand in the starting side and face the scoring
 * side.
 */
@Autonomous
public class AutonomousNE extends LinearOpMode {
    private ColorSensor color1;// = hardwareMap.get(ColorSensor.class, "color1");;
    private DcMotor rightDrive;// = hardwareMap.get(DcMotor.class, "rightDrive");
    private DcMotor leftDrive;// = hardwareMap.get(DcMotor.class, "leftDrive");
    private DistanceSensor distanceSensor1;// = hardwareMap.get(DistanceSensor.class, "distance1");
    RobotController controller;//  = new RobotControllerV1(leftDrive, rightDrive);

    public double right = -Math.PI/2;
    public double left = Math.PI/2;
    
    @Override
    public void runOpMode() throws InterruptedException {
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        controller  = new RobotControllerV1(leftDrive, rightDrive);
        color1 = hardwareMap.get(ColorSensor.class, "color1");
        distanceSensor1 = hardwareMap.get(DistanceSensor.class, "distance1");

        //controller.initializeDistanceSensor1(distanceSensor1);
        telemetry.addData("status","starting");
        telemetry.update();

        controller.initializeColorSensor(color1);
        /*while(distanceSensor1.getDistance(DistanceUnit.MM) > 100){
            telemetry.addData("distance",distanceSensor1.getDistance(DistanceUnit.MM));
            telemetry.update();
            controller.newMove(1);
        }*/


        
        controller.newMove(15);
        controller.goUntilRed(color1);
        controller.newMove(15);
        controller.newTurn(right);
        controller.newMove(45);
        controller.newTurn(left);
        controller.newMove(12);
        controller.newTurn(left);
        controller.newMove(45);
        controller.newTurn(left);
        controller.goUntilRed(color1);
        controller.newMove(15);








        //for navigation points: turn left 90 and go until red
        //controller.moveForward(30);while (leftDrive.isBusy() && rightDrive.isBusy()) {}

        //controller.turn(Math.PI/2*-1);while (leftDrive.isBusy() && rightDrive.isBusy()) {}//left turn

        /*while (color1.red() >= color1.blue()) { //figure out offset/telemetry
            //figure out how to speed this up
            controller.moveForward(1);
        }*/

       /* controller.moveForward(240);while (leftDrive.isBusy() && rightDrive.isBusy()) {}
        controller.turn(Math.PI/2);while (leftDrive.isBusy() && rightDrive.isBusy()) {}
        controller.moveForward(120);while (leftDrive.isBusy() && rightDrive.isBusy()) {}
        controller.turn(Math.PI/2*-1);while (leftDrive.isBusy() && rightDrive.isBusy()) {}
        controller.moveForward(30);while (leftDrive.isBusy() && rightDrive.isBusy()) {}
        controller.turn(Math.PI/2*-1);while (leftDrive.isBusy() && rightDrive.isBusy()) {}
        controller.moveForward(120);while (leftDrive.isBusy() && rightDrive.isBusy()) {}
        controller.turn(Math.PI/2*-1);while (leftDrive.isBusy() && rightDrive.isBusy()) {}
        while (color1.red() >= color1.blue()) {
            controller.moveForward(1);
        }
        controller.moveForward(120);while (leftDrive.isBusy() && rightDrive.isBusy()) {}
        controller.turn(Math.PI/2*-1);while (leftDrive.isBusy() && rightDrive.isBusy()) {}
        controller.turn(Math.PI/2*-1);while (leftDrive.isBusy() && rightDrive.isBusy()) {}
        while (color1.red() >= color1.blue()) {
            controller.moveForward(1);
        }


        while (true) {
            if(color1.red() >= color1.blue()){
                controller.moveForward(240);while (leftDrive.isBusy() && rightDrive.isBusy()) {}
                controller.turn(Math.PI/2*-1);while (leftDrive.isBusy() && rightDrive.isBusy()) {}
                controller.turn(Math.PI/2*-1);while (leftDrive.isBusy() && rightDrive.isBusy()) {}
            }
        }*/





        //consider adding distance sensor. I dont know if this is the one we have, but it seems straightforward to impliment: https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599715-ultrasonic-sensors-measuring-robot-distance-to-a-surface
    }
}
