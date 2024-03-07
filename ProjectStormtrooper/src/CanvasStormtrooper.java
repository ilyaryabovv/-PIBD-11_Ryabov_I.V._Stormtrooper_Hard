import javax.swing.*;
import java.awt.*;

public class CanvasStormtrooper extends JComponent {
    public DrawingStormtrooper _drawingStormtrooper;
    public CanvasStormtrooper(){}
    public void paintComponent(Graphics g) {
        if (_drawingStormtrooper == null) {
            return;
        }
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        _drawingStormtrooper.DrawTransport(g2d);
        super.repaint();
    }
}
