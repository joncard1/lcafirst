package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.teamcode.controller.RobotController;
import org.firstinspires.ftc.teamcode.controller.RobotControllerV1;

/**
 * I have provided this class for the possibility that we are in autonomous mode and started in the
 * corner in the scoring side, on the right if you stand in the starting side and face the scoring
 * side.
 */
@Autonomous
public class AutonomousTest extends LinearOpMode {
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
        //color1 = hardwareMap.get(ColorSensor.class, "color1");
        distanceSensor1 = hardwareMap.get(DistanceSensor.class, "distance1");

        //controller.initializeDistanceSensor1(distanceSensor1);
        telemetry.addData("status","starting");
        telemetry.update();
        controller.newTurn(Math.PI/2);

        //consider adding distance sensor. I dont know if this is the one we have, but it seems straightforward to impliment: https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599715-ultrasonic-sensors-measuring-robot-distance-to-a-surface
    }
}
