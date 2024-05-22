package com.cardio_generator.outputs;

/*This code sets up a way to decide how to show health data.
 It creates different methods, like showing it on the screen,
 saving it to files, or sending it over a network.
These methods can be used depending on what's needed
 */

public interface OutputStrategy {

    /*
      Outputs health data for a specific patient.

      @param patientId The ID of the patient for which data is being outputted.
      @param timestamp The timestamp indicating when the data was generated.
      @param label     The label associated with the data.
      @param data      The actual health data being outputted.
     */
    void output(int patientId, long timestamp, String label, String data);
}
