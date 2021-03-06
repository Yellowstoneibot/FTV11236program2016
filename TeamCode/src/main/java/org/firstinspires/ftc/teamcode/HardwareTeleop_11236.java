package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwareK9bot;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a K9 robot.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Servo channel:  Servo to raise/lower arm: "arm"
 * Servo channel:  Servo to open/close claw: "claw"
 *
 * Note: the configuration of the servos is such that:
 *   As the arm servo approaches 0, the arm position moves up (away from the floor).
 *   As the claw servo approaches 0, the claw opens up (drops the game element).
 */
public class HardwareTeleop_11236
{
    /* Public OpMode members. */
    public DcMotor  leftMotor   = null;
    public DcMotor  rightMotor  = null;
    public DcMotor  rightFlyWheel = null;
    public DcMotor  leftFlyWheel  = null;
    public DcMotor  intake = null;
    public DcMotor  pulley = null;
    public Servo    arm = null;
    public final static double ARM_HOME = 0.2;
    public final static double ARM_MIN_RANGE  = 0.20;
    public final static double ARM_MAX_RANGE  = 0.90;


    /* Local OpMode members. */
    HardwareMap hwMap  = null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareTeleop_11236() {
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // save reference to HW Map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftMotor   = hwMap.dcMotor.get("left_drive");
        rightMotor  = hwMap.dcMotor.get("right_drive");
        intake = hwMap.dcMotor.get("intake");
        leftFlyWheel   = hwMap.dcMotor.get("left_fly");
        rightFlyWheel  = hwMap.dcMotor.get("right_fly");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        leftFlyWheel.setDirection(DcMotor.Direction.REVERSE);
        arm = hwMap.servo.get("arm");


        // Set all motors to zero power
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        leftFlyWheel.setPower(0);
        rightFlyWheel.setPower(0);
        intake.setPower(0);
        intake.setPower(0);
        arm.setPosition(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFlyWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFlyWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}
