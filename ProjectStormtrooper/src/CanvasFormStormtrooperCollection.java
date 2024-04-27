import javax.swing.*;
import CollectionGenericObjects.AbstractCompany;
import Drawnings.DrawingBaseStormtrooper;

import java.awt.*;

public class CanvasFormStormtrooperCollection<T> extends JComponent {
    public AbstractCompany company = null;
    public void SetCollectionToCanvas(AbstractCompany company) {
        this.company = company;
    }
    public CanvasFormStormtrooperCollection() {};
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        if (company == null || company._collection == null) {
            return;
        }
        company.DrawBackgound(g);
        for (int i = 0; i < company._collection.getCount(); i++) {

            Graphics2D g2d = (Graphics2D) g;
            T obj = (T) company._collection.Get(i);
            if (obj instanceof DrawingBaseStormtrooper) {
                ((DrawingBaseStormtrooper) obj).DrawTransport(g2d);
            }
        }
        super.repaint();
    }
}
