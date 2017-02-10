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
                driveDistance();
                if(!drivingToPosition()){
                    stopDrive();
                    nextAction();
                }
            }
        });
    }

    public void addBlueBeacon(){
        addAction(new FrogAction() {
            @Override
            public void action() {
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
                if(!shooting()){
                    nextAction();
                }
            }
        });
    }
}
