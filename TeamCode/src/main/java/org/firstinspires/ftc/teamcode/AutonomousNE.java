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
public class AutonomousNE extends LinearOpMode {
    private DcMotor rightDrive;
    private DcMotor leftDrive;
    private ColorSensor color1;
    @Override
    public void runOpMode() throws InterruptedException {
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        color1 = hardwareMap.get(ColorSensor.class, "color1");
        RobotController controller  = new RobotControllerV1(leftDrive, rightDrive);


        //for navigation points: turn left 90 and go until red
        controller.moveForward(30);
        while (leftDrive.isBusy() && rightDrive.isBusy()) {}

        controller.turn(Math.PI/2);
        while (leftDrive.isBusy() && rightDrive.isBusy()) {}

        controller.moveForward(30);
        while (leftDrive.isBusy() && rightDrive.isBusy()) {}

        controller.turn(Math.PI/2);
        while (leftDrive.isBusy() && rightDrive.isBusy()) {}

        controller.moveForward(30);
        while (leftDrive.isBusy() && rightDrive.isBusy()) {}

        controller.turn(Math.PI/2);
        while (leftDrive.isBusy() && rightDrive.isBusy()) {}

        controller.moveForward(30);
        while (leftDrive.isBusy() && rightDrive.isBusy()) {}

        controller.turn(Math.PI/2);
        while (leftDrive.isBusy() && rightDrive.isBusy()) {}

        while (color1.red() <= color1.blue()) {
            controller.moveForward(1);
        }

    }
}
