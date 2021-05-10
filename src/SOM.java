import java.util.Random;

public class SOM {
    float[][][] neurons;
    Random random;
    float[][] inputs;
    float learningRate = 0.5f;
    int[][] heatMapArray;

    public SOM(float[][] inputs) {
        neurons = new float[10][10][8];
        random = new Random();
        this.inputs = inputs;
        heatMapArray = new int[10][10];

        initializeWeights();
    }

    public void updateLearningRate() {
        learningRate *= 0.8f;
    }


    public void iterateAllInputs() {        //1 epoch
//        System.out.println(inputs.length);
        for (int i = 0; i < inputs.length; i++) {
            int[] nearesNeuronIndex = nearestNeuron(inputs[i], neurons);
            updateNeuronWeights(inputs[i], nearesNeuronIndex);
        }
    }

    private float euclidDistance(float[] array1, float[] array2) {
        float distance = 0;
        for (int i = 0; i < 8; i++) {
            distance += (float) Math.sqrt(array1[i] - array2[i]);
        }
        System.out.println(distance);
        return distance;
    }

    private int[] nearestNeuron(float[] input, float[][][] neurons) {
        int[] nearestNeuronIndexes = new int[2];
        float minDistance = Float.MAX_VALUE;
//        System.out.println(minDistance);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 8; k++) {
                    float distance = euclidDistance(neurons[i][j], input);
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestNeuronIndexes[0] = i;
                        nearestNeuronIndexes[1] = j;
                    }
                }
            }
        }
        return nearestNeuronIndexes;
    }

    public int[][] createHeatMap() {
        heatMapArray = new int[10][10];
        for (int i = 0; i < inputs.length; i++) {
            int[] nearesNeuronIndex = nearestNeuron(inputs[i], neurons);
            int row = nearesNeuronIndex[0];
            int column = nearesNeuronIndex[1];
            heatMapArray[row][column]++;
        }
        return heatMapArray;
    }



//    public int[][] createHeatMapForAttribute(int index)
//    {
//
//    }


    private void updateNeuronWeights(float[] input, int[] neuronIndexes) {
        int i = neuronIndexes[0];
        int j = neuronIndexes[1];
        for (int k = 0; k < 8; k++) {
            float weight = neurons[i][j][k];
            weight = weight + learningRate * (input[k] - weight);
            neurons[i][j][k] = weight;
        }
    }


    private void initializeWeights() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 8; k++) {
                    neurons[i][j][k] = getRandom();
                }
            }
        }
    }

    public void printInputValues()
    {
        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0; j < inputs[i].length; j++) {
                System.out.print(inputs[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printNeuronWeights() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 8; k++) {
                    System.out.print(neurons[i][j][k] + " ");
                }
                System.out.println();
            }
        }
    }

    public void printSingleNeuronWeight(int[] neuronIdex) {
        int i = neuronIdex[0];
        int j = neuronIdex[1];
        for (int k = 0; k < 8; k++) {
            System.out.print(neurons[i][j][k] + " ");
        }
        System.out.println();

    }


    private float getRandom() {
        return random.nextFloat();
    }
}
