package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

/*A generator for simulating alert data for patients*/

public class AlertGenerator implements PatientDataGenerator {

    public static final Random randomGenerator = new Random();

    //changed all the alerstate to camelcase
    private boolean[] alertStates; // false = resolved, true = pressed

    /*
      Constructs an AlertGenerator with the specified number of patients.

      @param patientCount The number of patients for whom to generate alert data.
     */

    public AlertGenerator(int patientCount) {

        /*
          Generates alert data for the specified patient and outputs it using the provided output strategy.

          @param patientId      The ID of the patient for whom to generate the data.
          @param outputStrategy The strategy used to output the generated data.
         */
        alertStates = new boolean[patientCount + 1];
    }

    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            if (alertStates[patientId]) {
                if (randomGenerator.nextDouble() < 0.9) { // 90% chance to resolve
                    alertStates[patientId] = false;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
                }
            } else {
                // changed the lambda to camelcase
                double lambda = 0.1; // Average rate (alerts per period), adjust based on desired frequency
                double p = -Math.expm1(-lambda); // Probability of at least one alert in the period
                boolean alertTriggered = randomGenerator.nextDouble() < p;

                if (alertTriggered) {
                    alertStates[patientId] = true;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "triggered");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }
}
