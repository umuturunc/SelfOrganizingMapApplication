import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class DataReader {

    private float[][] inputs;
    private float[] maxValues;
    private HashMap<String, Float> dummyTable;
    private FileInputStream fis;
    private String datasetPath;
    private Scanner sc;

    private void initializeVariables() {
        inputs = new float[500][8];
        maxValues = new float[8];

        dummyTable = new HashMap<>();
        initializeDummyTable(); //dummy coding

        datasetPath = System.getProperty("user.dir") + "\\\\emlak-veri.txt";

        try {
            fis = new FileInputStream(datasetPath);
            sc = new Scanner(fis);  //attribute isimlerini içeren satır atlanıyor
            System.out.println(sc.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

//    public void setInputs(float[][] inputs)
//    {
//        this.inputs = inputs;
//        normalizeValues();
//    }

    private void initializeDummyTable() {   //integer encoding
        dummyTable.put("TT", 0f);
        dummyTable.put("A", 0.125f);
        dummyTable.put("B", 0.25f);
        dummyTable.put("C", 0.375f);
        dummyTable.put("D", 0.5f);
        dummyTable.put("E", 0.625f);
        dummyTable.put("F", 0.75f);
        dummyTable.put("G", 0.875f);
        dummyTable.put("H", 1f);
    }

    public DataReader() {
        initializeVariables();
        readData();
        normalizeValues();
    }

    public float[][] getInputs() {
        return inputs;
    }

    private void readData() {
        int count = 0;
        while (sc.hasNextLine()) {
            float[] vector = new float[8];
            String[] strVector = new String[8];
            strVector = sc.nextLine().split(",");
            strVector[4] = String.valueOf(dummyTable.get(strVector[4]));
            copyArray(strVector, vector);
            for (int i = 0; i < 8; i++) {
                if (maxValues[i] < vector[i])
                    maxValues[i] = vector[i];
            }
            inputs[count++] = vector;
        }

    }

    private void copyArray(String[] strArray, float[] floatArray) {
        for (int i = 0; i < 8; i++) {
            floatArray[i] = Float.valueOf(strArray[i]);
        }
    }

    //min-max normalization
    private void normalizeValues() {    //veriler [0,1] aralığına çekiliyor
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 8; j++) {
                inputs[i][j] /= maxValues[j];
            }
        }
    }

    public void printValues()
    {
        Arrays.stream(inputs).forEach(v -> {
            for (int i = 0; i < 8; i++) {
                System.out.print(v[i] + " ");
            }
            System.out.println();
        });
    }
}
