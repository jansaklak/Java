import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SinPlot extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private JSlider slider;
    private PlotPanel plotPanel;

    public SinPlot() {
        setSize(WIDTH, HEIGHT);
        setTitle("Sin(x) Plot");
        setLayout(new BorderLayout());

        slider = new JSlider(JSlider.HORIZONTAL, 0, 360, 90);
        slider.setMajorTickSpacing(90);
        slider.setMinorTickSpacing(15);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(new SliderChangeListener());

        plotPanel = new PlotPanel();

        add(slider, BorderLayout.SOUTH);
        add(plotPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SinPlot sp = new SinPlot();
        sp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sp.setVisible(true);
    }

    private class SliderChangeListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            plotPanel.setRange(slider.getValue());
            plotPanel.repaint();
        }
    }

    private class PlotPanel extends JPanel {
        private int range;

        public PlotPanel() {
            range = 90;
        }

        public void setRange(int range) {
            this.range = range;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            g.drawLine(0, centerY, getWidth(), centerY);
            g.drawLine(centerX, 0, centerX, getHeight());
            g.setColor(Color.RED);
            for (int x = -range; x <= range; x++) {
                int y = (int)(50 * Math.sin((x / 180.0) * Math.PI));
                g.drawLine(centerX + x, centerY - y, centerX + x, centerY - y);
            }
        }
    }
}