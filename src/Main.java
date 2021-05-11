public class Main {

    public static void main(String[] args) {
        float[][] inputs = new float[500][8];   //500 adet veri, 8 attribute
        DataReader dataReader = new DataReader();   //Verileri okmak için
        inputs = dataReader.getInputs();

        SOM som = new SOM(inputs);
        MyGUI myGUI = new MyGUI();


        int epoch = 1;


        for (int i = 0; i < epoch; i++) {
            som.iterateAllInputs();
            som.updateLearningRate();
        }


        myGUI.showHeatMap(som.createHeatMap(), "");

    }
}
