import Drawnings.DrawingBaseStormtrooper;

import javax.swing.*;
import java.awt.*;

public class CanvasStormtrooper extends JComponent {
    public DrawingBaseStormtrooper _drawingBaseStormtrooper;
    public CanvasStormtrooper(){}
    public void paintComponent(Graphics g) {
        if (_drawingBaseStormtrooper == null) {
            return;
        }
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        _drawingBaseStormtrooper.DrawTransport(g2d);
        super.repaint();
    }
}
