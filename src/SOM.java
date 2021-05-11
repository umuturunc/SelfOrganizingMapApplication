import java.util.Random;

public class SOM {
    float[][][] neurons;
    Random random;
    float[][] inputs;
    float learningRate = 0.5f;
    int[][] heatMapArray;
    float[][] neuronDistances;

    public SOM(float[][] inputs) {
        neurons = new float[10][10][8];
        random = new Random();
        this.inputs = inputs;
        heatMapArray = new int[10][10];
        neuronDistances = new float[10][10];
        initializeWeights();
    }

    public void updateLearningRate() {
        learningRate *= 0.5f;
    }


    public void iterateAllInputs() {        //1 epoch
        for (int i = 0; i < inputs.length; i++) {
            int[] nearesNeuronIndex = nearestNeuronForInput(inputs[i], neurons);
            updateNeuronWeights(inputs[i], nearesNeuronIndex);
        }
    }

    private float euclidDistanceBetweenInputAndNeuron(float[] array1, float[] array2) {      //verilen input ve nöron ağırlık vektörünün birbirine uzaklığını hesapla

        float distance = 0.0f;
        for (int i = 0; i < 8; i++) {
            float difference = array1[i] - array2[2];
            distance += difference * difference;
        }
        return distance;
    }

    private int[] nearestNeuronForInput(float[] input, float[][][] neurons) {   //verilen input'a en yakın nöronun indis numaralarını dizi içerisinde döndür
        int[] nearestNeuronIndexes = new int[2];
        float minDistance = Float.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                float distance = euclidDistanceBetweenInputAndNeuron(neurons[i][j], input);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestNeuronIndexes[0] = i;
                    nearestNeuronIndexes[1] = j;
                }
            }
        }
        return nearestNeuronIndexes;
    }


    private void updateNeuronWeights(float[] input, int[] neuronIndexes) {
        int i = neuronIndexes[0];
        int j = neuronIndexes[1];
        for (int k = 0; k < 8; k++) {
            float weight = neurons[i][j][k];
            weight = weight + learningRate * (input[k] - weight);
            neurons[i][j][k] = weight;
        }
    }

    private void distanceBetweenWinningNeuronAndAllNeurons(int[] winningNeuronIndexes) {    //nöronlar arasındaki uzaklık matristeki koordinatlarına göre hesaplanıyor
        int winningI = winningNeuronIndexes[0];
        int winningJ = winningNeuronIndexes[1];
        neuronDistances = new float[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                neuronDistances[i][j] = (float) Math.sqrt((i - winningI) * (i - winningI) + (j - winningJ) * (j - winningJ));
            }
        }
    }

    public void printInputValues() {
        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0; j < inputs[i].length; j++) {
                System.out.print(inputs[i][j] + " ");
            }
            System.out.println();
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
//        System.out.println();
    }

    public int[][] createHeatMap() {
        heatMapArray = new int[10][10];
        for (int i = 0; i < inputs.length; i++) {
            int[] nearesNeuronIndex = nearestNeuronForInput(inputs[i], neurons);
            int row = nearesNeuronIndex[0];
            int column = nearesNeuronIndex[1];
            heatMapArray[row][column]++;
        }
        return heatMapArray;
    }

    private float getRandom() {
        return random.nextFloat();
    }
}
