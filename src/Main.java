package edu.sla;

public class Main {

    public static void main(String[] args) {
        boolean recursiveMode = false;
        int numRockets = 10;

        System.out.println("Will launch " + numRockets + " Rockets" + (recursiveMode ? " recursively." : " iteratively."));
        System.out.println("");

        // Let's launch some rockets CONCURRENTLY
        for (int i = 0; i < numRockets; i++) {
            Runnable rocket = new Rocket(i+1, recursiveMode);
            // create a new thread to launch this rocket concurrently as the other rockets
            Thread thread = new Thread(rocket);
            // tell that thread to launch the rocket
            thread.start();

            // DO continue launching more rockets while this rocket is counting down
            if (((Rocket)rocket).launched()) {
                System.out.println("ERROR: Rocket already launched?!");
            }
        }

        System.out.println("Rocket launcher LET all launches countdown concurrently.");
    }
}
