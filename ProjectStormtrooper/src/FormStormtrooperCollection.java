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

    private Queue<DrawingBaseStormtrooper> _collectionRemovedObjects = new LinkedList<>();
    private StorageCollection<DrawingBaseStormtrooper> _storageCollection = new StorageCollection<DrawingBaseStormtrooper>();
    private JTextField textBoxCollection = new JTextField();
    private JRadioButton radioButtonMassive = new JRadioButton("Массив");
    private JRadioButton radioButtonList = new JRadioButton("Список");
    private JButton buttonAddCollection = new JButton("Добавить");
    private JList listBoxCollection = new JList();
    private JButton buttonRemoveCollection = new JButton("Удалить");
    private JButton buttonCreateCompany = new JButton("Создать компанию");
    private JButton createButton = new JButton("Создать бомбардировщик");
    private JButton createShipButton = new JButton("Создать базовый бомбардировщик");
    private JButton removeButton = new JButton("Удалить");
    private JButton removeObjectsButton = new JButton("Удаленные объекты");
    private JButton GoToCheckButton = new JButton("На проверку");
    private JButton RandomButton = new JButton("Случайные");
    private JButton RefreshButton = new JButton("Обновить");
    private JComboBox ComboBoxCollections = new JComboBox(new String[]{"", "Хранилище"});
    private JFormattedTextField TextField;
    public FormStormtrooperCollection(String title, Dimension dimension) {
        this.title = title;
        this.dimension = dimension;
    }
    public static void canvasShow() {
        _company.SetPosition();
        _canvasStormtrooper.SetCollectionToCanvas(_company);
        _canvasStormtrooper.repaint();
    }
    private void CreateObject(String typeOfClass) {
        if (_company == null) return;
        int speed = (int) (Math.random() * 300 + 100);
        float weight = (float) (Math.random() * 3000 + 1000);
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

                int typeOfEngine = ((int) ((Math.random() * 3) + 1));
                drawingBaseStormtrooper = new DrawingStormtrooper(speed, weight, bodyColor, additionalColor, rockets, bombs, engines, typeOfEngine);
                break;
            default:
                return;
        }
        if (_company._collection.Insert(drawingBaseStormtrooper) != -1) {
            JOptionPane.showMessageDialog(null, "Объект добавлен");
            canvasShow();
        } else {
            JOptionPane.showMessageDialog(null, "Объект не удалось добавить");
        }
    }

    public Color getColor() {
        Color initializator = new Color((int) (Math.random() * 255 + 0), (int) (Math.random() * 255 + 0), (int) (Math.random() * 255 + 0));
        Color color = JColorChooser.showDialog(this, "Цвет", initializator);
        return color;
    }

    public void Init() {
        setTitle(title);
        setMinimumSize(dimension);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        TextField = new JFormattedTextField();
    

        createShipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateObject("DrawingBaseStormtrooper");
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateObject("DrawingStormtrooper");
            }
        });

        RemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (_company == null || TextField.getText() == null) {
                    return;
                }
                int pos = parseInt(TextField.getText());
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
        RandomButton.setBounds(getWidth()-190, 140, 150, 30);
        TextField.setBounds(getWidth()-190,200,150,30);
        RemoveButton.setBounds(getWidth()-190, 240, 150, 30);
        GoToCheckButton.setBounds(getWidth()-190, 280, 150, 30);
        RefreshButton.setBounds(getWidth()-190, getHeight()-90, 150, 30);


        buttonAddCollection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textBoxCollection.getText().isEmpty() || (!radioButtonMassive.isSelected()
                        && !radioButtonList.isSelected())) {
                    JOptionPane.showMessageDialog(null, "Не все данные заполнены");
                    return;
                }
                CollectionType collectionType = CollectionType.None;
                if (radioButtonMassive.isSelected()) {
                    collectionType = CollectionType.Massive;
                } else if (radioButtonList.isSelected()) {
                    collectionType = CollectionType.List;
                }
                _storageCollection.AddCollection(textBoxCollection.getText(), collectionType);
                RerfreshListBoxItems();
            }
        });
        buttonRemoveCollection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listBoxCollection.getSelectedIndex() < 0 || listBoxCollection.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(null, "Коллекция не выбрана");
                    return;
                }
                int resultConfirmDialog = JOptionPane.showConfirmDialog(null,
                        "Удалить", "Удаление",
                        JOptionPane.YES_NO_OPTION);
                if (resultConfirmDialog == JOptionPane.NO_OPTION) return;
                _storageCollection.DelCollection(listBoxCollection.getSelectedValue().toString());
                RerfreshListBoxItems();
            }
        });
        buttonCreateCompany.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listBoxCollection.getSelectedIndex() < 0 || listBoxCollection.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(null, "Коллекция не выбрана");
                    return;
                }
                ICollectionGenericObjects<DrawingBaseStormtrooper> collection =
                        _storageCollection.getCollectionObject(listBoxCollection.getSelectedValue().toString());
                if (collection == null) {
                    JOptionPane.showMessageDialog(null, "Коллекция не проинициализирована");
                    return;
                }
                switch (ComboBoxCollections.getSelectedItem().toString()) {
                    case "Хранилище":
                        _company = new StormtrooperSharingService(getWidth() - 200, getHeight() - 110,
                                collection);
                        break;
                }
                RerfreshListBoxItems();
            }
        });
        removeObjectsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (_collectionRemovedObjects.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Коллекция  пуста");
                    return;
                }
                DrawingBaseStormtrooper stormtrooper = null;
                stormtrooper = _collectionRemovedObjects.remove();
                if (stormtrooper == null) {
                    return;
                }
                FormStormtrooper form = new FormStormtrooper("Бомбардировщик", new Dimension(1000, 750));
                form.Init(stormtrooper);
            }
        });
        ButtonGroup radioButtonsGroup = new ButtonGroup();
        JLabel labelCollectionName = new JLabel("Название коллекции");
        radioButtonsGroup.add(radioButtonMassive);
        radioButtonsGroup.add(radioButtonList);
        _canvasStormtrooper.setBounds(0, 0, getWidth() - 200, getHeight());
        labelCollectionName.setBounds(getWidth()-190, 10, 150, 20);
        textBoxCollection.setBounds(getWidth()-190,35,150,25);
        radioButtonMassive.setBounds(getWidth()-190, 60, 75, 20);
        radioButtonList.setBounds(getWidth()-105, 60, 75, 20);
        buttonAddCollection.setBounds(getWidth()-190, 85, 150, 20);
        listBoxCollection.setBounds(getWidth()-190, 115, 150, 70);
        buttonRemoveCollection.setBounds(getWidth()-190, 195, 150, 20);
        ComboBoxCollections.setBounds(getWidth() - 190, 225, 150, 20);
        buttonCreateCompany.setBounds(getWidth()-190, 255, 150, 20);
        createShipButton.setBounds(getWidth() - 190, 285, 150, 30);
        createButton.setBounds(getWidth() - 190, 325, 150, 30);
        RandomButton.setBounds(getWidth() - 190, 365, 150, 30);
        removeObjectsButton.setBounds(getWidth()-190, 505, 150, 30);
        TextField.setBounds(getWidth() - 190, 545, 150, 30);
        removeButton.setBounds(getWidth() - 190, 585, 150, 30);
        GoToCheckButton.setBounds(getWidth() - 190, 625, 150, 30);
        RefreshButton.setBounds(getWidth() - 190, 665, 150, 30);
        setSize(dimension.width, dimension.height);
        setLayout(null);
        add(_canvasStormtrooper);
        add(ComboBoxCollections);
        add(createShipButton);
        add(createButton);
        add(TextField);
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
                TextField.setBounds(getWidth()-190,200,150,30);
                RemoveButton.setBounds(getWidth()-190, 240, 150, 30);
                GoToCheckButton.setBounds(getWidth()-190, 280, 150, 30);
                RandomButton.setBounds(getWidth()-190, 140, 150, 30);
                RefreshButton.setBounds(getWidth()-190, getHeight()-90, 150, 30);
            }
        });
    }
}