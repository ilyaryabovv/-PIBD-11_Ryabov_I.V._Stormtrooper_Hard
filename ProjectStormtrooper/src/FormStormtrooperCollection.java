import CollectionGenericObjects.AbstractCompany;
import CollectionGenericObjects.MassiveGenericObjects;
import CollectionGenericObjects.StormtrooperSharingService;
import Drawnings.DrawingBaseStormtrooper;
import Drawnings.DrawingStormtrooper;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class FormStormtrooperCollection extends JFrame{
    private String title;
    private Dimension dimension;
    public static CanvasFormStormtrooperCollection<DrawingBaseStormtrooper> _canvasStormtrooper = new CanvasFormStormtrooperCollection<DrawingBaseStormtrooper>();
    private static AbstractCompany _company = null;
    private JButton CreateButton = new JButton("Создать бомбардировщик");;
    private JButton CreateShipButton = new JButton("Создать базовый бомбардировщик");
    private JButton RemoveButton = new JButton("Удалить");
    private JButton GoToCheckButton = new JButton("Check");
    private JButton RandomButton = new JButton("RandomShip");
    private JButton RefreshButton = new JButton("Refresh");
    private JComboBox ComboBoxCollections = new JComboBox(new String[]{"", "Хранилище"});
    private JFormattedTextField MaskedTextField;
    public FormStormtrooperCollection(String title, Dimension dimension) {
        this.title = title;
        this.dimension = dimension;
    }
    public static void canvasShow() {
        _company.SetPosition();
        _canvasStormtrooper.SetCollectionToCanvas(_company);
        _canvasStormtrooper.repaint();
    }
    private void CreateObject(String typeOfClass){
        if (_company == null) return;
        int speed = (int)(Math.random() * 300 + 100);
        float weight = (float)(Math.random() * 3000 + 1000);
        Color bodyColor = getColor();
        DrawingBaseStormtrooper drawingBaseStormtrooper;
        switch (typeOfClass) {
            case "DrawingBaseStormtrooper":
                drawingBaseStormtrooper = new DrawingBaseStormtrooper(speed, weight, bodyColor);
                break;
            case "DrawingStormtrooper":
                Color additionalColor = getColor();
                boolean rockets = new Random().nextBoolean();
                boolean bombs = new Random().nextBoolean();
                boolean engines = new Random().nextBoolean();
                int typeOfEngine = ((int)((Math.random()*3)+1));
                drawingBaseStormtrooper = new DrawingStormtrooper(speed, weight, bodyColor, additionalColor, rockets, bombs,engines,typeOfEngine);
                break;
            default: return;
        }
        if (_company._collection.Insert(drawingBaseStormtrooper, 0) != -1) {
            JOptionPane.showMessageDialog(null, "Объект добавлен");
            canvasShow();
        }
        else {
            JOptionPane.showMessageDialog(null, "Объект не удалось добавить");
        }
    }
    public Color getColor() {
        Color initializator = new Color((int)(Math.random() * 255 + 0),(int)(Math.random() * 255 + 0),(int)(Math.random() * 255 + 0));
        Color color = JColorChooser.showDialog(this, "Цвет", initializator);
        return color;
    }
    public void Init() {
        setTitle(title);
        setMinimumSize(dimension);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MaskFormatter mask = null;
        try {
            mask = new MaskFormatter("##");
            mask.setPlaceholder("00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        MaskedTextField = new JFormattedTextField(mask);

        ComboBoxCollections.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (ComboBoxCollections.getSelectedItem().toString()) {
                    case "Хранилище":
                        _company = new StormtrooperSharingService(getWidth()-200, getHeight()-110, new MassiveGenericObjects<DrawingBaseStormtrooper>());
                        break;
                }
            }
        });

        CreateShipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateObject("DrawingBaseStormtrooper");
            }
        });
        CreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateObject("DrawingStormtrooper");
            }
        });

        RemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (_company == null || MaskedTextField.getText() == null) {
                    return;
                }
                int pos = parseInt(MaskedTextField.getText());
                int resultConfirmDialog = JOptionPane.showConfirmDialog(null, "Удалить", "Удаление", JOptionPane.YES_NO_OPTION);
                if (resultConfirmDialog == JOptionPane.NO_OPTION) return;
                if (_company._collection.Remove(pos) != null) {
                    System.out.println(pos);
                    JOptionPane.showMessageDialog(null, "Объект удален");
                    canvasShow();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Не удалось удалить объект");
                }
            }
        });

        GoToCheckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (_company == null)
                {
                    return;
                }
                DrawingBaseStormtrooper stormtrooper = null;
                int counter = 100;
                while (stormtrooper == null)
                {
                    stormtrooper = _company.GetRandomObject();
                    counter--;
                    if (counter <= 0)
                    {
                        break;
                    }
                }
                if (stormtrooper == null)
                {
                    return;
                }
                FormStormtrooper form = new FormStormtrooper("Бомбардировщик", new Dimension(1000,750));
                form.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        canvasShow();
                        super.windowClosing(e);

                    }
                });
                form.Init(stormtrooper);
            }
        });

        RefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (_company == null)
                {
                    return;
                }
                canvasShow();
            }
        });
        RandomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(_company==null){
                    return;
                }
                FormAdditionalCollection form = new FormAdditionalCollection();
                form.setCompany(_company);
            }
        });
        _canvasStormtrooper.setBounds(0, 0, getWidth()-200, getHeight());
        ComboBoxCollections.setBounds(getWidth()-190, 10, 150, 20);
        CreateShipButton.setBounds(getWidth()-190, 60, 150, 30);
        CreateButton.setBounds(getWidth()-190, 100, 150, 30);
        MaskedTextField.setBounds(getWidth()-190,200,150,30);
        RemoveButton.setBounds(getWidth()-190, 240, 150, 30);
        GoToCheckButton.setBounds(getWidth()-190, 280, 150, 30);
        RandomButton.setBounds(getWidth()-190, 320, 150, 30);
        RefreshButton.setBounds(getWidth()-190, getHeight()-90, 150, 30);

        setSize(dimension.width,dimension.height);
        setLayout(null);
        add(_canvasStormtrooper);
        add(ComboBoxCollections);
        add(CreateShipButton);
        add(CreateButton);
        add(MaskedTextField);
        add(RemoveButton);
        add(GoToCheckButton);
        add(RandomButton);
        add(RefreshButton);
        setVisible(true);

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                _canvasStormtrooper.setBounds(0, 0, getWidth()-200, getHeight()-70);
                ComboBoxCollections.setBounds(getWidth()-190, 10, 150, 20);
                CreateShipButton.setBounds(getWidth()-190, 60, 150, 30);
                CreateButton.setBounds(getWidth()-190, 100, 150, 30);
                MaskedTextField.setBounds(getWidth()-190,200,150,30);
                RemoveButton.setBounds(getWidth()-190, 240, 150, 30);
                GoToCheckButton.setBounds(getWidth()-190, 280, 150, 30);
                RandomButton.setBounds(getWidth()-190, 320, 150, 30);
                RefreshButton.setBounds(getWidth()-190, getHeight()-90, 150, 30);
            }
        });
    }
}