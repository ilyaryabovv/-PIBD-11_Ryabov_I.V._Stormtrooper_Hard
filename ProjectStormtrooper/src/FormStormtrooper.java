import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Random;

import Drawnings.DirectionType;
import Drawnings.DrawingBaseStormtrooper;
import Drawnings.DrawingStormtrooper;
import MovementStrategy.*;

public class FormStormtrooper extends JFrame {
    private AbstractStrategy _strategy;
    private final String title;
    private final Dimension dimension;
    private int Width;
    private int Height;
    private final CanvasStormtrooper canvasStormtrooper = new CanvasStormtrooper();
    private JComboBox ComboBoxStrategy = new JComboBox(new String []{"К центру", "К краю"});
    private JButton ButtonStrategy = new JButton("Шаг");
    private  final JButton CreateBaseStormtrooper = new JButton("Создать базовый бомбардировщик");
    private  final JButton CreateStormtrooper = new JButton("Создать бомбардировщик");
    private final JButton UpButton = new JButton();
    private final JButton DownButton = new JButton();
    private final JButton LeftButton = new JButton();
    private final JButton RightButton = new JButton();
    public FormStormtrooper(String title, Dimension dimension) {
        this.title = title;
        this.dimension = dimension;
    }
    private void CreateObject(String typeOfClass){
        int StartPositionX = (int)(Math.random() * 90 + 10);
        int StartPositionY = (int)(Math.random() * 90 + 10);
        int speed = (int)(Math.random() * 300 + 100);
        float weight = (float) (Math.random() * 3000 + 1000);
        Color bodyColor = new Color((int)(Math.random() * 255 + 0),(int)(Math.random() * 255 + 0),(int)(Math.random() * 255 + 0));
        switch (typeOfClass){
            case "Drawnings.DrawingBaseStormtrooper":
                canvasStormtrooper._drawingBaseStormtrooper = new DrawingBaseStormtrooper(speed, weight, bodyColor);
                canvasStormtrooper._drawingBaseStormtrooper.SetPictureSize(Width, Height);
                canvasStormtrooper._drawingBaseStormtrooper.SetPosition( StartPositionX, StartPositionY);
                canvasStormtrooper.repaint();
                break;
            case "Drawnings.DrawingStormtrooper":
                Color additionalColor = new Color((int)(Math.random() * 255 + 0),(int)(Math.random() * 255 + 0),(int)(Math.random() * 255 + 0));
                boolean rockets = new Random().nextBoolean();
                boolean bombs = new Random().nextBoolean();
                boolean engines = new Random().nextBoolean();
                int typeOfEngine = ((int)((Math.random()*3)+1));
                canvasStormtrooper._drawingBaseStormtrooper = new DrawingStormtrooper(speed, weight, bodyColor, additionalColor,rockets, bombs, engines, typeOfEngine);
                canvasStormtrooper._drawingBaseStormtrooper.SetPictureSize(Width, Height);
                canvasStormtrooper._drawingBaseStormtrooper.SetPosition( StartPositionX, StartPositionY);
                canvasStormtrooper.repaint();
                break;
            default:
                return;
        }
        _strategy=null;
        ComboBoxStrategy.setEnabled(true);
    }
    public void Init() {
        setTitle(title);
        setMinimumSize(dimension);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Width = getWidth()-10;
        Height = getHeight() - 34;
        _strategy=null;
        CreateStormtrooper.setName("createStormtrooperButton");
        CreateBaseStormtrooper.setName("createBaseStormtrooperButton");
        Icon iconUp = new ImageIcon("Resources\\arrowUp.jpg");
        UpButton.setIcon(iconUp);
        UpButton.setName("UP");
        DownButton.setName("DOWN");
        Icon iconDown = new ImageIcon("Resources\\arrowDown.jpg");
        DownButton.setIcon(iconDown);
        LeftButton.setName("LEFT");
        Icon iconLeft = new ImageIcon("Resources\\arrowLeft.jpg");
        LeftButton.setIcon(iconLeft);
        RightButton.setName("RIGHT");
        Icon iconRight = new ImageIcon("Resources\\arrowRight.jpg");
        RightButton.setIcon(iconRight);
        ButtonStrategy.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (canvasStormtrooper._drawingBaseStormtrooper == null) return;
                if (ComboBoxStrategy.isEnabled())
                {
                    int index = ComboBoxStrategy.getSelectedIndex();
                    switch(index)
                    {
                        case 1:
                            _strategy = new MoveToBorder();
                            break;
                        case 0:
                            _strategy = new MoveToCenter();
                            break;
                        default:
                            _strategy = null;
                            break;
                    };
                    if (_strategy == null)
                    {
                        return;
                    }
                    _strategy.SetData(new MoveableStormtrooper(canvasStormtrooper._drawingBaseStormtrooper), Width, Height);
                }
                if (_strategy == null)
                {
                    return;
                }
                ComboBoxStrategy.setEnabled(false);
                _strategy.MakeStep();
                if (_strategy.GetStatus() == StrategyStatus.Finish)
                {
                    ComboBoxStrategy.setEnabled(true);
                    _strategy = null;
                }
            }
        });
        CreateBaseStormtrooper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateObject("Drawnings.DrawingBaseStormtrooper");
            }
        });
        CreateStormtrooper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateObject("Drawnings.DrawingStormtrooper");
            }
        });


        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (canvasStormtrooper._drawingBaseStormtrooper == null) return;
                boolean result = switch ((((JButton) (event.getSource())).getName())) {
                    case "UP" -> canvasStormtrooper._drawingBaseStormtrooper.MoveTransport(DirectionType.Up);
                    case "DOWN" -> canvasStormtrooper._drawingBaseStormtrooper.MoveTransport(DirectionType.Down);
                    case "LEFT" -> canvasStormtrooper._drawingBaseStormtrooper.MoveTransport(DirectionType.Left);
                    case "RIGHT" -> canvasStormtrooper._drawingBaseStormtrooper.MoveTransport(DirectionType.Right);
                    default -> false;
                };
                if (result) {
                    canvasStormtrooper.repaint();
                }
            }
        };
        UpButton.addActionListener(actionListener);
        DownButton.addActionListener(actionListener);
        LeftButton.addActionListener(actionListener);
        RightButton.addActionListener(actionListener);
        setSize(dimension.width,dimension.height);
        setLayout(null);
        canvasStormtrooper.setBounds(0,0, getWidth(), getHeight());
        CreateBaseStormtrooper.setBounds(10, getHeight() - 90, 130, 40);
        CreateStormtrooper.setBounds(160,getHeight()-90,130,40);
        UpButton.setBounds(getWidth() - 180, getHeight() - 210, 70, 70);
        DownButton.setBounds(getWidth() - 180, getHeight() - 140, 70, 70);
        RightButton.setBounds(getWidth() - 110, getHeight() - 140, 70, 70);
        LeftButton.setBounds(getWidth() - 250, getHeight() - 140, 70, 70);
        ComboBoxStrategy.setBounds(getWidth() - 170, 10, 140, 35);
        ButtonStrategy.setBounds(getWidth() - 130, 55, 100, 25);
        add(CreateBaseStormtrooper);
        add(CreateStormtrooper);
        add(ComboBoxStrategy);
        add(ButtonStrategy);
        add(UpButton);
        add(DownButton);
        add(RightButton);
        add(LeftButton);
        add(canvasStormtrooper);
        setVisible(true);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Width = getWidth() -10;
                Height = getHeight() - 34;
                if (canvasStormtrooper._drawingBaseStormtrooper != null)canvasStormtrooper._drawingBaseStormtrooper.SetPictureSize(Width, Height);
                canvasStormtrooper.setBounds(0,0, getWidth(), getHeight());
                CreateBaseStormtrooper.setBounds(10, getHeight() - 90, 130, 40);
                CreateStormtrooper.setBounds(160,getHeight()-90,130,40);
                UpButton.setBounds(getWidth() - 180, getHeight() - 210, 70, 70);
                DownButton.setBounds(getWidth() - 180, getHeight() - 140, 70, 70);
                RightButton.setBounds(getWidth() - 110, getHeight() - 140, 70, 70);
                LeftButton.setBounds(getWidth() - 250, getHeight() - 140, 70, 70);
                ComboBoxStrategy.setBounds(getWidth() - 170, 10, 140, 35);
                ButtonStrategy.setBounds(getWidth() - 130, 55, 100, 25);
            }
        });
    }
}
