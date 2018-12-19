package edu.sla;

class Rocket implements Runnable {
    private int id;
    private int launchTime;
    private boolean canLaunch;
    private boolean launched;

    private boolean recursiveMode;

    Rocket(int rocketId, boolean mode) {
        id = rocketId;
        // launchTime is random amount between 2 and 10 seconds
        launchTime = 2 + (int)(Math.random() * 8);
        canLaunch = false;
        launched = false;
        recursiveMode = mode;
    }

    boolean launched() {
        return launched;
    }

    private void countdownIterative() {
        for (int i = launchTime; i > 0; i--) {
            System.out.println("Rocket " + id + " launching in " + i + " seconds.");
            try {
                // wait 1 second (which is same as 1000 milliseconds)
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        canLaunch = true;
    }

    private void countdownRecursive(int count) {
        if (count > 0) {
            System.out.println("Rocket " + id + " launching in " + count + " seconds.");
            try {
                // wait 1 second (which is same as 1000 milliseconds)
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            countdownRecursive(count - 1);
        } else {
            canLaunch = true;
        }
    }

    private void launch() {
        if (canLaunch) {
            System.out.println("Rocket " + id + " launching!!!!!!");
            launched = true;
        } else {
            System.out.println("ERROR: Rocket " + id + " can't launch BEFORE countdown");
        }
        System.out.println("");
    }

    public void run() {
        System.out.println("Rocket " + id + " countdown commence.");
        if (recursiveMode) {
            countdownRecursive(launchTime);
        } else {
            countdownIterative();
        }
        launch();
    }
}
