import CollectionGenericObjects.AbstractCompany;
import Drawnings.*;
import Drawnings.Engines.*;
import Entities.EntityStormtrooper;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class FormStormtrooperConfig extends JFrame {
    private String title;
    private Dimension dimension;
    private DrawingBaseStormtrooper _stormtrooper;
    private AbstractCompany company = null;
    private JLabel labelSpeed = new JLabel("Скорость");
    private JLabel labelWeight = new JLabel("Вес");
    private JLabel labelNumberOfEngines = new JLabel("Двигатели");

    private JLabel labelBaseStormtrooper = new JLabel("Самолет", SwingConstants.CENTER);
    private JLabel labelStormtrooper = new JLabel("Бомбардировщик", SwingConstants.CENTER);
    private JLabel labelColor = new JLabel("Цвета");
    private JLabel labelBodyColor = new JLabel("Основной цвет", SwingConstants.CENTER);
    private JLabel labelAdditionalColor = new JLabel("Дополнительный цвет", SwingConstants.CENTER);
    private JLabel labelEngines = new JLabel("Тип двигателей",SwingConstants.CENTER);
    private JLabel labelDefaultEngines = new JLabel("Классические",SwingConstants.CENTER);
    private JLabel labelOvalEngines = new JLabel("Овальные", SwingConstants.CENTER);
    private JLabel labelTriangleEngines = new JLabel("Треугольные", SwingConstants.CENTER);

    private JSpinner spinnerSpeed = new JSpinner();
    private JSpinner spinnerWeight = new JSpinner();
    private JSpinner spinnerNumberOfEngines = new JSpinner();
    private JCheckBox checkBoxBombs = new JCheckBox("Имеет бомбы");
    private JCheckBox checkBoxRockets = new JCheckBox("Имеет ракеты");
    private JCheckBox checkBoxEngines = new JCheckBox("Имеет двигатели");
    private JComponent panelObject = new JPanel();
    private JPanel panelColorRed = new JPanel();
    private JPanel panelColorGreen = new JPanel();
    private JPanel panelColorBlue = new JPanel();
    private JPanel panelColorYellow = new JPanel();
    private JPanel panelColorBlack = new JPanel();
    private JPanel panelColorWhite = new JPanel();
    private JPanel panelColorGray = new JPanel();
    private JPanel panelColorCyan = new JPanel();
    private JButton buttonAdd = new JButton("Добавить");
    private JButton buttonCancel = new JButton("Отмена");

    public FormStormtrooperConfig(String title, Dimension dimension) {
        this.title = title;
        this.dimension = dimension;
    }
    public void Init() {
        SpinnerModel numSpeed = new SpinnerNumberModel(100, 100, 1000, 1);
        SpinnerModel numWeight = new SpinnerNumberModel(100, 100, 1000, 1);
        spinnerSpeed.setModel(numSpeed);
        spinnerWeight.setModel(numWeight);
        SpinnerModel numEngines = new SpinnerNumberModel(2, 2, 6, 2);
        spinnerNumberOfEngines.setModel(numEngines);
        panelObject = new Canvas();
        panelObject.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        spinnerSpeed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (_stormtrooper == null) return;
                _stormtrooper.EntityBaseStormtrooper.setSpeed((int)spinnerSpeed.getValue());
            }
        });
        spinnerWeight.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (_stormtrooper == null) return;
                _stormtrooper.EntityBaseStormtrooper.setWeight((int)spinnerWeight.getValue());
            }
        });

        spinnerNumberOfEngines.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(_stormtrooper ==null)return;
                if(_stormtrooper.EntityBaseStormtrooper instanceof EntityStormtrooper stormtrooper){
                    _stormtrooper.drawingEngines.setAmountOfEngines((int) spinnerNumberOfEngines.getValue());
                    panelObject.repaint();
                }
            }
        });
        checkBoxBombs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (_stormtrooper == null) return;
                if (_stormtrooper.EntityBaseStormtrooper instanceof EntityStormtrooper stormtrooper) {
                    stormtrooper.setBombs(checkBoxBombs.isSelected());
                    panelObject.repaint();
                }
            }
        });
        checkBoxRockets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (_stormtrooper == null) return;
                if (_stormtrooper.EntityBaseStormtrooper instanceof EntityStormtrooper stormtrooper) {
                    stormtrooper.setRockets(checkBoxRockets.isSelected());
                    panelObject.repaint();
                }
            }
        });

        checkBoxEngines.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(_stormtrooper == null)return;
                if(_stormtrooper.EntityBaseStormtrooper instanceof EntityStormtrooper stormtrooper){
                    stormtrooper.setEngines(checkBoxEngines.isSelected());
                    if(_stormtrooper instanceof DrawingStormtrooper stormtrooper1 && stormtrooper1.drawingEngines==null){
                        stormtrooper1.drawingEngines=new DrawingEngines();
                        stormtrooper1.drawingEngines.setAmountOfEngines((int) spinnerNumberOfEngines.getValue());
                    }
                    panelObject.repaint();
                }
            }
        });
        labelBaseStormtrooper.setBackground(Color.WHITE);
        labelStormtrooper.setBackground(Color.WHITE);
        labelBaseStormtrooper.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        labelStormtrooper.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        labelBodyColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        labelAdditionalColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        labelDefaultEngines.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        labelOvalEngines.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        labelTriangleEngines.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        MouseAdapter labelObjectsMouseDown = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ((JLabel) e.getComponent()).getTransferHandler().exportAsDrag(((JLabel) e.getComponent()), e, TransferHandler.COPY);
            }
        };
        TransferHandler labelObjectsTransferHandler = new TransferHandler() {
            @Override
            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY;
            }
            @Override
            protected Transferable createTransferable(JComponent c) {
                return new StringSelection(((JLabel) c).getText());
            }
        };

        labelBaseStormtrooper.addMouseListener(labelObjectsMouseDown);
        labelBaseStormtrooper.setTransferHandler(labelObjectsTransferHandler);
        labelStormtrooper.addMouseListener(labelObjectsMouseDown);
        labelStormtrooper.setTransferHandler(labelObjectsTransferHandler);

        MouseAdapter labelEnginesMouseDown = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ((JLabel) e.getComponent()).getTransferHandler().exportAsDrag(((JLabel) e.getComponent()), e, TransferHandler.COPY);
            }
        };

        labelDefaultEngines.addMouseListener(labelEnginesMouseDown);
        labelOvalEngines.addMouseListener(labelEnginesMouseDown);
        labelTriangleEngines.addMouseListener(labelEnginesMouseDown);
        labelDefaultEngines.setTransferHandler(new TransferHandler() {
            @Override
            public int getSourceActions(JComponent c) {return TransferHandler.COPY;}

            @Override
            protected Transferable createTransferable(JComponent c) {
                return new EnginesTransferable(new DrawingEngines());
            }
        });
        labelOvalEngines.setTransferHandler(new TransferHandler() {
            @Override
            public int getSourceActions(JComponent c) {return TransferHandler.COPY;}

            @Override
            protected Transferable createTransferable(JComponent c) {
                return new EnginesTransferable(new DrawingOvalEngines());
            }
        });
        labelTriangleEngines.setTransferHandler(new TransferHandler() {
            @Override
            public int getSourceActions(JComponent c) {return TransferHandler.COPY;}

            @Override
            protected Transferable createTransferable(JComponent c) {
                return new EnginesTransferable(new DrawingTriangleEngines());
            }
        });

        panelObject.setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                return support.isDataFlavorSupported(DataFlavor.stringFlavor)
                        || support.isDataFlavorSupported(EnginesTransferable.enginesDataFlavor);
            }
            @Override
            public boolean importData(TransferHandler.TransferSupport support) {
                if (canImport(support)) {
                    try {
                        String data = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
                        switch (data) {
                            case "Самолет":
                                _stormtrooper = new DrawingBaseStormtrooper((int) spinnerSpeed.getValue(), (int) spinnerWeight.getValue(),
                                        Color.WHITE);
                                break;
                            case "Бомбардировщик":
                                _stormtrooper = new DrawingStormtrooper((int) spinnerSpeed.getValue(), (int) spinnerWeight.getValue(),
                                        Color.WHITE, Color.BLACK, checkBoxBombs.isSelected(), checkBoxRockets.isSelected(),checkBoxEngines.isSelected());
                                _stormtrooper.drawingEngines=new DrawingEngines();
                                _stormtrooper.drawingEngines.setAmountOfEngines((int) spinnerNumberOfEngines.getValue());
                                break;
                        }
                        if (_stormtrooper != null) {
                            _stormtrooper.SetPictureSize(155,155);
                            _stormtrooper.SetPosition(5,10);
                        }
                        else return false;
                    }
                    catch (UnsupportedFlavorException | IOException e) {}
                    try {
                        IDrawingEngines engines =
                                (IDrawingEngines) support.getTransferable().getTransferData(EnginesTransferable.enginesDataFlavor);
                        _stormtrooper.drawingEngines = engines;
                        _stormtrooper.drawingEngines.setAmountOfEngines((int) spinnerNumberOfEngines.getValue());

                    }catch (UnsupportedFlavorException | IOException e) {}
                    panelObject.repaint();
                    return true;
                }
                return false;
            }
        });
        JPanel[] colorPanels = {
                panelColorRed,
                panelColorGreen,
                panelColorBlue,
                panelColorYellow,
                panelColorWhite,
                panelColorBlack,
                panelColorGray,
                panelColorCyan,
        };
        panelColorRed.setBackground(Color.RED);
        panelColorGreen.setBackground(Color.GREEN);
        panelColorBlue.setBackground(Color.BLUE);
        panelColorYellow.setBackground(Color.YELLOW);
        panelColorWhite.setBackground(Color.WHITE);
        panelColorBlack.setBackground(Color.BLACK);
        panelColorGray.setBackground(Color.GRAY);
        panelColorCyan.setBackground(Color.CYAN);
        MouseAdapter colorMouseDown = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ((JPanel) e.getComponent()).getTransferHandler().exportAsDrag(((JPanel) e.getComponent()), e, TransferHandler.COPY);
            }
        };
        for (var panelColor : colorPanels) {
            panelColor.addMouseListener(colorMouseDown);
            panelColor.setTransferHandler(new ColorTransferHandler());
        }
        labelBodyColor.setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                return support.isDataFlavorSupported(ColorTransferable.colorDataFlavor);
            }
            @Override
            public boolean importData(TransferSupport support) {
                try {
                    Color color = (Color) support.getTransferable().getTransferData(ColorTransferable.colorDataFlavor);
                    if (_stormtrooper == null) return false;
                    _stormtrooper.EntityBaseStormtrooper.setBodyColor(color);
                    return true;
                } catch (UnsupportedFlavorException | IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
        labelAdditionalColor.setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                if (!(_stormtrooper instanceof DrawingStormtrooper)) return false;
                return support.isDataFlavorSupported(ColorTransferable.colorDataFlavor);
            }
            @Override
            public boolean importData(TransferSupport support) {
                try {
                    Color color = (Color) support.getTransferable().getTransferData(ColorTransferable.colorDataFlavor);
                    if (_stormtrooper == null) return false;
                    if (_stormtrooper.EntityBaseStormtrooper instanceof EntityStormtrooper stormtrooper) {
                        stormtrooper.setAdditionalColor(color);
                        labelColor.setBackground(color);
                        return true;
                    }
                    return false;
                } catch (UnsupportedFlavorException | IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (_stormtrooper == null) return;
                DrawingBaseStormtrooper copyStormtrooper;
                if (_stormtrooper instanceof DrawingStormtrooper)
                    copyStormtrooper =  new DrawingStormtrooper((EntityStormtrooper) _stormtrooper.EntityBaseStormtrooper, _stormtrooper.drawingEngines);
                else
                    copyStormtrooper =  new DrawingBaseStormtrooper(_stormtrooper.EntityBaseStormtrooper, _stormtrooper.drawingEngines);
                company._collection.Insert(copyStormtrooper);
                FormStormtrooperCollection.canvasShow();
                dispose();
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        labelSpeed.setBounds(8, 17, 75, 15);
        labelWeight.setBounds(8,43, 75, 15);
        labelNumberOfEngines.setBounds(8, 68, 75, 15);
        labelBaseStormtrooper.setBounds(8,150,105,30);
        labelStormtrooper.setBounds(8,190,110,30);
        checkBoxBombs.setBounds(8,85,150,20);
        checkBoxRockets.setBounds(8,105,150,20);
        checkBoxEngines.setBounds(8,125,150,20);
        spinnerSpeed.setBounds(80,15, 60,20);
        spinnerWeight.setBounds(80,40, 60,20);
        spinnerNumberOfEngines.setBounds(80, 65,60,20);
        panelObject.setBounds(555,50,160,150);
        labelBodyColor.setBounds(500,5,100, 40);
        labelAdditionalColor.setBounds(605,5,170,40);
        labelEngines.setBounds(225,190,150,15);
        labelDefaultEngines.setBounds(140, 210, 100, 40);
        labelOvalEngines.setBounds(250, 210, 100,40);
        labelTriangleEngines.setBounds(360,210,100,40);
        labelColor.setBounds(200,10,50,15);
        panelColorRed.setBounds(200, 30, 40, 40);
        panelColorGreen.setBounds(250, 30, 40,40);
        panelColorBlue.setBounds(300,30,40,40);
        panelColorYellow.setBounds(350,30,40,40);
        panelColorWhite.setBounds(200, 80,40,40);
        panelColorBlack.setBounds(250,80,40,40);
        panelColorGray.setBounds(300,80,40,40);
        panelColorCyan.setBounds(350,80,40,40);
        buttonAdd.setBounds(515, 210, 110, 40);
        buttonCancel.setBounds(640, 210, 110, 40);
        setSize(dimension.width, dimension.height);
        setLayout(null);
        add(labelSpeed);
        add(labelWeight);
        add(labelNumberOfEngines);
        add(labelBaseStormtrooper);
        add(labelStormtrooper);
        add(labelColor);
        add(labelBodyColor);
        add(labelAdditionalColor);
        add(labelDefaultEngines);
        add(labelDefaultEngines);
        add(labelOvalEngines);
        add(labelTriangleEngines);
        add(spinnerSpeed);
        add(spinnerWeight);
        add(spinnerNumberOfEngines);
        add(checkBoxBombs);
        add(checkBoxRockets);
        add(checkBoxEngines);
        add(panelObject);
        add(panelColorRed);
        add(panelColorGreen);
        add(panelColorBlue);
        add(panelColorYellow);
        add(panelColorWhite);
        add(labelEngines);
        add(panelColorBlack);
        add(panelColorGray);
        add(panelColorCyan);
        add(buttonAdd);
        add(buttonCancel);
        setVisible(true);
    }
    public void setCompany(AbstractCompany company) {
        this.company = company;
    }
    private class Canvas extends JComponent {
        public Canvas() {
        }
        public void paintComponent(Graphics g) {
            if (_stormtrooper == null) {
                return;
            }
            super.paintComponents(g);
            Graphics2D g2d = (Graphics2D) g;
            _stormtrooper.DrawTransport(g2d);
            super.repaint();
        }
    }
    private class ColorTransferable implements Transferable {
        private Color color;
        private static final DataFlavor colorDataFlavor = new DataFlavor(Color.class, "Color");
        public ColorTransferable(Color color) {
            this.color = color;
        }
        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{colorDataFlavor};
        }
        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return colorDataFlavor.equals(flavor);
        }
        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
            if (isDataFlavorSupported(flavor)) {
                return color;
            } else {
                throw new UnsupportedFlavorException(flavor);
            }
        }
    }
    private class ColorTransferHandler extends TransferHandler {
        @Override
        public int getSourceActions(JComponent c) {
            return TransferHandler.COPY;
        }
        @Override
        protected Transferable createTransferable(JComponent c) {
            return new ColorTransferable(c.getBackground());
        }
    }
    private class EnginesTransferable implements Transferable {
        private IDrawingEngines engines;
        private static final DataFlavor enginesDataFlavor = new DataFlavor(IDrawingEngines.class, "Engines");
        public EnginesTransferable(IDrawingEngines engines) {
            this.engines = engines;
        }
        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{enginesDataFlavor};
        }
        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor.equals(enginesDataFlavor);
        }
        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
            if (isDataFlavorSupported(flavor)) {
                return engines;
            } else {
                throw new UnsupportedFlavorException(flavor);
            }
        }
    }

}