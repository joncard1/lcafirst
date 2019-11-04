package org.firstinspires.ftc.teamcode.controller;

/**
 * I defined this interface to describe how to interact with an object of this type. It is the
 * responsibility of whatever object implements it to fulfill the promise that calling
 * "moveForward", for instance, moves the robot forward. In a sense, when I write this code, I
 * expect this to be the gateway from the mind of the robot to the body of the robot. It is up to
 * other parts of the code to decide what to do; it is up to implementations of RobotController to
 * know how to do it.
 *
 * The importance of refactoring the code in this way is that, since we have many autonomous
 * OpMode classes, we do not want to implement this code in all of them, and potentially
 * differently.
 *
 * Note that this may not be the best way to move the robot. Specifying the distance to move forward
 * isn't a very good way to control the bot in manual mode. It may be that this interface is useful
 * for autonomous modes, another interface is useful for manual modes, and the same class may
 * implement all of those methods in ways that interrelate in the object itself.
 */
public interface RobotController {
    public void moveForward(double centimeters);
    public void turn(double radius);
}
