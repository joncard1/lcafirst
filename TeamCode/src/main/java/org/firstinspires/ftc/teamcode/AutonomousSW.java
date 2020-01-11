/*package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * I have provided this class for the possibility that we are in autonomous mode and started in the
 * corner in the starting side, on the left if you stand in the starting side and face the scoring
 * side.
 *
@TeleOp
public class AutonomousSW extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

    }
}*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.controller.RobotController;
import org.firstinspires.ftc.teamcode.controller.RobotControllerV1;

/**
 * I have provided this class for the possibility that we are in autonomous mode and started in the
 * corner in the scoring side, on the right if you stand in the starting side and face the scoring
 * side.
 */
@Autonomous
public class AutonomousSW extends LinearOpMode {
    public ColorSensor color1 = hardwareMap.get(ColorSensor.class, "color1");
    public DcMotor rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
    public DcMotor leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
    public RobotController controller  = new RobotControllerV1(leftDrive, rightDrive);
    double right = -Math.PI/2;
    double left = Math.PI/2;

    @Override
    public void runOpMode() throws InterruptedException {
        //maybe use while opModeIsActive?
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        color1 = hardwareMap.get(ColorSensor.class, "color1");
        controller  = new RobotControllerV1(leftDrive, rightDrive);

        controller.newMove(15);
        controller.newTurn(left);
        controller.goUntilBlue(color1);
        controller.newTurn(right);
        controller.newTurn(right);
        controller.newTurn(left);
        controller.newMove(45);
        controller.newTurn(right);
        controller.newMove(12);
        controller.newTurn(right);
        controller.newMove(45);
        controller.newTurn(right);
        controller.goUntilBlue(color1);
        controller.newMove(15);

        //for navigation points: turn left 90 and go until red
        /*controller.moveForward(30);
        while (leftDrive.isBusy() && rightDrive.isBusy()) {}

        controller.turn(Math.PI/2*-1);
        while (leftDrive.isBusy() && rightDrive.isBusy()) {}


        while (color1.blue() >= color1.red()) {
            controller.moveForward(1);
        }*/
        //consider adding distance sensor. I dont know if this is the one we have, but it seems straightforward to impliment: https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599715-ultrasonic-sensors-measuring-robot-distance-to-a-surface
    }
}











































