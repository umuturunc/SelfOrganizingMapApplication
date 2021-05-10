import java.util.Random;

public class Main {

    public static void main(String[] args) {
        float[][] inputs = new float[500][8];   //500 adet veri, 8 attribute
        DataReader dataReader = new DataReader();   //Verileri okmak için ayrı oluşturulan ayrı class'ın nesnesi yaratılıyor
        inputs = dataReader.getInputs();
        //dataReader.printValues();   //normalize edilmiş veriler ekrana yazdırılıyor

//        float[][] inputFloats = new float[500][8];
//        Random random = new Random();
//        for (int i = 0; i < 500; i++) {
//            for (int j = 0; j < 8; j++) {
//                inputFloats[i][j] =(float) random.nextFloat();
//            }
//        }



        MyGUI myGUI = new MyGUI();
        //myGUI.showHeatMap(null);
        SOM som = new SOM(inputs);
        int epoch = 1;
        for (int i = 0; i < epoch; i++) {
            som.iterateAllInputs();
            som.updateLearningRate();
        }


////        som.printValues();
//        int[] neuronIndex = {0,0};
//        som.printValue(neuronIndex);
//        som.iterateAllInputs();
//        som.printValue(neuronIndex);

//        myGUI.showHeatMap(som.createHeatMap(),"deneme");



//        int[][] deneme = new int[10][10];
//        Random random = new Random();
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                deneme[i][j] = random.nextInt(500);
//            }
//        }

//        float[][] inputsFloat = new float[10][10];
//


//        som.printNeuronWeights();

//        som.printInputValues();

//        myGUI.showHeatMap(deneme, "All attributes");


    }
}
