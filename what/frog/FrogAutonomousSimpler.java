package org.firstinspires.ftc.teamcode.what.frog;

/**
 * Created by FTC2 on 07.02.2017.
 */
public class FrogAutonomousSimpler extends FrogAutonomous {

    public void resetGyros(){
        addAction(new FrogAction() {
            @Override
            public void action() {
                resetHeading();
                nextAction();
            }
        });
        robotWait(500);
        addAction(new FrogAction() {
            @Override
            public void action() {
                topGyro.reactivateRead();
                bottomGyro.reactivateRead();
                nextAction();
            }
        });
        robotWait(500);
    }

    public void addChangeFront(final short front){
        addAction(new FrogAction() {
            @Override
            public void action() {
                changeDirection(front);
                nextAction();
            }
        });
    }
    public void addDriveToPosition(int x, int y){
        int[] vector;
        vector = FrogMath.calculateVector(x, y, robotX, robotY);
        double[] polar = FrogMath.xyToPolar(vector[0], vector[1]);
        robotX = x;
        robotY = y;
        int heading = headingAfterFront((int)polar[1]);
        addDriveToHeading(heading);
        addDriveDistance((int)polar[0]);
    }
    public void addDriveToHeading(int heading){
        final int heading_final = heading;
        addAction(new FrogAction() {
            @Override
            public void action() {
                initDriveToHeading(heading_final);
                nextAction();
            }
        });
        addAction(new FrogAction() {
            @Override
            public void action() {
                telemetry.addData("Status", "driving to heading " + Integer.toString(heading_final));
                driveToHeading();
                if(!drivingToHeading()){
                    stopDrive();
                    nextAction();
                }
            }
        });
    }

    public void addDriveDistance(int distance){
        final int distance_final = distance;
        addAction(new FrogAction() {
            @Override
            public void action() {
                resetDrive();
                initDriveDistance(distance_final);
                nextAction();
            }
        });

        addAction(new FrogAction() {
            @Override
            public void action() {
                telemetry.addData("Status", "driving to distance " + Integer.toString(distance_final));
                driveDistance();
                if(!drivingToPosition()){
                    stopDrive();
                    nextAction();
                }
            }
        });
    }

    public void addDriveToWall(){
        addAction(new FrogAction() {
            @Override
            public void action() {
                changeDirection(BEACON_FRONT);
                nextAction();
            }
        });
        addAction(new FrogAction() {
            @Override
            public void action() {
                driveToWall(5);
                telemetry.addData("Status", "driving to wall");
                if(!drivingToWall()){
                    resetDrive();
                    nextAction();
                }
            }
        });
    }
    public void addBlueBeacon(){
        addAction(new FrogAction() {
            @Override
            public void action() {
                telemetry.addData("Status", "pushing blue beacon");
                pushBlueBeacon();
                nextAction();
            }
        });
        robotWait(2000);
        addAction(new FrogAction() {
            @Override
            public void action() {
                resetServo();
                nextAction();
            }
        });
    }

    public void addRedBeacon(){
        addAction(new FrogAction() {
            @Override
            public void action() {
                telemetry.addData("Status", "pushing red beacon");
                pushRedBeacon();
                nextAction();
            }
        });
        robotWait(2000);
        addAction(new FrogAction() {
            @Override
            public void action() {
                resetServo();
                nextAction();
            }
        });
    }

    public void robotWait(final int milliSecs){
        addAction(new FrogAction() {
            @Override
            public void action() {
                initWait(milliSecs);
                nextAction();
            }
        });
        addAction(new FrogAction() {
            @Override
            public void action() {
                if(!waiting()){
                    nextAction();
                }
            }
        });
    }

    public void addShoot(){
        addAction(new FrogAction() {
            @Override
            public void action() {
                initShootBall();
                nextAction();
            }
        });
        addAction(new FrogAction() {
            @Override
            public void action() {
                shootBall();
                telemetry.addData("Status", "shooting");
                if(!shooting()){
                    nextAction();
                }
            }
        });
    }

    public void addNextBall(){
        addAction(new FrogAction() {
            @Override
            public void action() {
                steffiMotor.setPower(1);
                nextAction();
            }
        });
        robotWait(2000);
    }
}
