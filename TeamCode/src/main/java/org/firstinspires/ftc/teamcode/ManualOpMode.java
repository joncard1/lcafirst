/*
Copyright 2019 FIRST Tech Challenge Team 16773

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode;
import java.lang.Math;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.controller.RobotController;
import org.firstinspires.ftc.teamcode.controller.RobotControllerV1;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Remove a @Disabled the on the next line or two (if present) to add this opmode to the Driver Station OpMode list,
 * or add a @Disabled annotation to prevent this OpMode from being added to the Driver Station
 */
@TeleOp

public class ManualOpMode extends LinearOpMode implements Gamepad.GamepadCallback {
    private Blinker expansion_Hub_2;
    //private DcMotor backLeft; leave these commented until we need them
    //private DcMotor backRight;
    private DcMotor rightDrive;
    private DcMotor leftDrive;
    private CRServo servo1;
    private Gyroscope imu;

    private String message;
    double leftPower = 0;
    double rightPower = 0;
    double servoPosition = 0;

    @Override
    public void runOpMode() {
        //RobotController controller  = new RobotControllerV1 (leftDrive, rightDrive);
        expansion_Hub_2 = hardwareMap.get(Blinker.class, "Expansion Hub 2");
        //backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        //backRight = hardwareMap.get(DcMotor.class, "backRight");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        servo1 = hardwareMap.get(CRServo.class, "servo1");
        imu = hardwareMap.get(Gyroscope.class, "imu");
        try {
            Gamepad newGamepad = new Gamepad(this);
            newGamepad.copy(gamepad1);
            this.gamepad1 = newGamepad;
        } catch (Exception e) {
            telemetry.addData("Status", "Exception");
            telemetry.update();
        }

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        this.message = "Running";
        // run until the end of the match (driver presses STOP)
        short controls = -1;
        leftDrive.setDirection(Direction.REVERSE);
        rightDrive.setDirection(Direction.FORWARD);

        while (opModeIsActive()) {
            telemetry.addData("Status", message);
            telemetry.update();

            if (this.gamepad1.a) {
                controls = 1;
            }
            if (this.gamepad1.a && this.gamepad1.b) {
                controls = 0;
            }
            if (this.gamepad1.dpad_up) {
                servo1.setDirection(Direction.REVERSE);
                servo1.setPower(1);
            }
            if (this.gamepad1.dpad_down) {
                servo1.setDirection(Direction.FORWARD);
                servo1.setPower(1);
            }
            if (this.gamepad1.x) {
                servo1.setPower(0);
            }
            /*if (controls == -1) { //driving control mode
                leftPower = -gamepad1.left_stick_y;
                rightPower = -gamepad1.right_stick_y;
            }*/
            if (controls == 1) { //standard control mode (for now)
                leftPower = -gamepad1.left_stick_y;
                rightPower = -gamepad1.right_stick_y;
                //clean up these if statements
                if (gamepad1.left_bumper && gamepad1.right_bumper) {
                    turn(0);
                    //catches if both left and right are pressed
                } else if (this.gamepad1.left_bumper) {
                    turn(-0.1);
                } else if (this.gamepad1.right_bumper) {
                    turn(0.1);
                } else {
                    turn(0);
                }
                if (gamepad1.left_trigger > 0 && gamepad1.right_trigger > 0) {
                    stopMoving();
                    //this catches if both forward and reverse are pressed. Result is braking
                } else if (gamepad1.left_trigger > 0){
                    leftPower = -gamepad1.left_trigger / 5;
                    rightPower = -gamepad1.left_trigger / 5;
                } else if (gamepad1.right_trigger > 0) {
                    leftPower = gamepad1.right_trigger / 5;
                    rightPower = gamepad1.right_trigger / 5;
                }
            }
            if(controls == 0){
                leftPower = -gamepad1.right_stick_y;
                rightPower = -gamepad1.right_stick_y;
                if(gamepad1.right_stick_x < 1 && gamepad1.right_stick_x > -1){
                    speedConservingTurn(gamepad1.right_stick_x);
                }
            }
            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);

        }
    }
    public void gamepadChanged(Gamepad gamepad) {
        message = "In changed";

    }
    public void stopMoving() {
        leftPower = 0;
        rightPower = 0;
    }
    public void turn(double turnRate) { //negative value = left turn, positive value = right turn
        leftPower = turnRate;
        rightPower = -turnRate;
    }
    //The difference between speedConservingTurn and turn is that turn causes the wheel of the turn to go in reverse. We should try out both of these.
    public void speedConservingTurn(double turnRate) { //negative value = left turn, positive value = right turn
        if(turnRate < 0){
            leftPower = leftPower * -turnRate;
            //rightPower = turnRate;
        } else {
            //leftPower = leftPower;
            rightPower = turnRate * turnRate;
        }
    }
   /* public void leftWheel(double throttle) {
        if(throttle > 0){
            leftDrive.setDirection(Direction.FORWARD);
        } else {
            leftDrive.setDirection(Direction.REVERSE);
        }
        leftDrive.setPower(Math.abs(throttle));


    }
    public void rightWheel(double throttle) {
        if(throttle > 0){
            rightDrive.setDirection(Direction.REVERSE);
        } else {
            rightDrive.setDirection(Direction.FORWARD);
        }
        rightDrive.setPower(Math.abs(throttle));
    }*/
}
