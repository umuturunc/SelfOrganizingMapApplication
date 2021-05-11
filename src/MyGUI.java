import javax.swing.*;
import java.awt.*;

public class MyGUI {

    JFrame frame;
    JPanel panel;
    int[][] heatMap;

    public MyGUI() {
        frame = new JFrame("HeatMap");
        panel = new JPanel();
        panel.setLayout(new GridLayout(10, 10));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void showHeatMap(int[][] array, String title) {
        float[][] normalizedArray = normalizeValues(array);

        frame.setTitle("HeatMap - " + title);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton button = new JButton();
                button.setFocusable(false);
                button.removeMouseListener(button.getMouseListeners()[0]);
                button.setFont(new Font(null, Font.BOLD, 20));
                button.setText(String.valueOf(array[i][j]));
                button.setBackground(new Color(1f, 0f, 0f, normalizedArray[i][j]));
                panel.add(button);
            }
        }
        frame.add(panel);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);  //arayüzü ekranın ortasında göster
        frame.setVisible(true);
    }


    private float[][] normalizeValues(int[][] array) {
        int max = findMax(array);
        float[][] normalizedArray = new float[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                normalizedArray[i][j] = (array[i][j] / (float) max);
            }
        }
        return normalizedArray;
    }

    private int findMax(int[][] array) {
        int max = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (array[i][j] > max)
                    max = array[i][j];
            }
        }
        return max;
    }
}
