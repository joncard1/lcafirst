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
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Gyroscope;
@TeleOp
public class ExperimentalDriveMode extends LinearOpMode /*implements Gamepad.GamepadCallback*/ {
    private Blinker expansion_Hub_2;
    private DcMotor rightDrive;
    private DcMotor leftDrive;
   // private CRServo servo1;
    private Gyroscope imu;

    private String message;
    double leftPower = 0;
    double rightPower = 0;
    int throttle = 1;
    double [] throttleLevels = new double [] {0.1, 0.5, 1.0};


    @Override
    public void runOpMode() {
        //RobotController controller  = new RobotControllerV1 (leftDrive, rightDrive);
        expansion_Hub_2 = hardwareMap.get(Blinker.class, "Expansion Hub 2");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
       // servo1 = hardwareMap.get(CRServo.class, "servo1");
        imu = hardwareMap.get(Gyroscope.class, "imu");
        boolean adjust = true;
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        this.message = "Running";
        // run until the end of the match (driver presses STOP)
        leftDrive.setDirection(Direction.REVERSE);
        rightDrive.setDirection(Direction.FORWARD);

        while (opModeIsActive()) {

            //initially sets speed values to triggers (later conditionals change this as necessary)
            leftPower = currentSpeed();
            rightPower = currentSpeed();

            //Decides whether to turn and if so, whether to do a moving turn (stop the inside wheel) or a stationary turn (reverse the inside wheel)
            if (forward() || reverse()) {
                if (rightTurn() && leftTurn()) {
                    //does nothing if both are pressed
                } else if (leftTurn()) {
                    movingTurn(-1);
                } else if (rightTurn()) {
                    movingTurn(1);
                }
            } else {
                //basic stationary turning
                if (leftTurn() && rightTurn()) {
                    //does nothing if both are pressed
                } else if (leftTurn()) {
                    turn(-throttleLevels[throttle] / 2);
                } else if (rightTurn()) {
                    turn(throttleLevels[throttle] / 2);
                }
            }

            //throttle level adjustment via D-Pad
            if (this.gamepad1.dpad_up && throttle < throttleLevels.length - 1 && adjust) {
                throttle++;
                adjust = false;
            } else if (this.gamepad1.dpad_down && throttle > 0 && adjust) {
                throttle--;
                adjust = false;
            } else if (!this.gamepad1.dpad_up && !this.gamepad1.dpad_down) {
                adjust = true;
            }

            //finally sets power based on what the variables leftPower and rightPower are after all checks
            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);

            /*if(this.gamepad1.a) {
                servo1.setDirection(Direction.REVERSE);
                servo1.setPower(1);
            }else if(this.gamepad1.b){
                servo1.setDirection(Direction.FORWARD);
                servo1.setPower(1);
            }else{
                servo1.setPower(0);
            }*/
        }
    }

    public void stopMoving() {
        leftPower = 0;
        rightPower = 0;
    }
    public void turn(double turnRate) { //negative value = left turn, positive value = right turn
        leftPower = turnRate;
        rightPower = -turnRate;
    }
    public void movingTurn (int turnDirection) { //1 for right, -1 for left
        //this method stops one wheel based on inputted turn direction
        if (turnDirection == 1) {
            rightPower = 0;
        } else if (turnDirection == -1) {
            leftPower = 0;
        }
    }
    public boolean forward () {
        return this.gamepad1.right_trigger > 0;
    }
    public boolean reverse () {
        return this.gamepad1.left_trigger > 0;
    }
    public boolean rightTurn () {
        return this.gamepad1.right_bumper;
    }
    public boolean leftTurn () {
        return this.gamepad1.left_bumper;
    }
    public double currentSpeed () {
        return (gamepad1.right_trigger - gamepad1.left_trigger) * throttleLevels[throttle];
    }
}
