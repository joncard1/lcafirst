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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

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

public class RobotControllerTestOpMode extends LinearOpMode implements Gamepad.GamepadCallback {
    private Blinker expansion_Hub_2;
    //private DcMotor backLeft; leave these commented until we need them
    //private DcMotor backRight;
    private DcMotor rightDrive;
    private DcMotor leftDrive;
    private Servo servo1;
    private Gyroscope imu;

    private String message;

    @Override
    public void runOpMode() {
        //RobotController controller  = new RobotControllerV1 (leftDrive, rightDrive);
        expansion_Hub_2 = hardwareMap.get(Blinker.class, "Expansion Hub 2");
        //backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        //backRight = hardwareMap.get(DcMotor.class, "backRight");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");

        RobotController controller  = new RobotControllerV1(leftDrive, rightDrive);
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

        while (opModeIsActive()) {
            telemetry.addData("Status", message);
            telemetry.update();
           controller.moveForward(30);
        }
    }
    public void gamepadChanged(Gamepad gamepad) {
        message = "In changed";

    }
}