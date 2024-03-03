import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Random;

public class FormStormtrooper extends JFrame {
    private String title;
    private Dimension dimension;
    private int Width;
    private int Height;
    private CanvasStormtrooper canvasStormtrooper = new CanvasStormtrooper();
    private JButton CreateButton = new JButton("Создать");;
    private JButton UpButton = new JButton();
    private JButton DownButton = new JButton();;
    private JButton LeftButton = new JButton();;
    private JButton RightButton = new JButton();
    public FormStormtrooper(String title, Dimension dimension) {
        this.title = title;
        this.dimension = dimension;
    }
    public void Init() {
        setTitle(title);
        setMinimumSize(dimension);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Width = getWidth() - 15;
        Height = getHeight() - 35;

        CreateButton.setName("createButton");
        Icon iconUp = new ImageIcon("src\\Resources\\arrowUp.png");
        UpButton.setIcon(iconUp);
        UpButton.setName("UP");
        DownButton.setName("DOWN");
        Icon iconDown = new ImageIcon("src\\Resources\\arrowDown.png");
        DownButton.setIcon(iconDown);
        LeftButton.setName("LEFT");
        Icon iconLeft = new ImageIcon("src\\Resources\\arrowLeft.png");
        LeftButton.setIcon(iconLeft);
        RightButton.setName("RIGHT");
        Icon iconRight = new ImageIcon("arrowRight.png");
        RightButton.setIcon(iconRight);

        CreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int StartPositionX = (int)(Math.random() * 90 + 10);
                int StartPositionY = (int)(Math.random() * 90 + 10);
                int speed = (int)(Math.random() * 300 + 100);
                float weight = (float) (Math.random() * 3000 + 1000);
                Color bodyColor = new Color((int)(Math.random() * 255 + 0),(int)(Math.random() * 255 + 0),(int)(Math.random() * 255 + 0));
                Color additionalColor = new Color((int)(Math.random() * 255 + 0),(int)(Math.random() * 255 + 0),(int)(Math.random() * 255 + 0));;
                boolean rockets = new Random().nextBoolean();
                boolean boombs = new Random().nextBoolean();;
                boolean engines = new Random().nextBoolean();;
                canvasStormtrooper._drawingStormtrooper = new DrawingStormtrooper();
                canvasStormtrooper._drawingStormtrooper.Init(speed, weight, bodyColor, additionalColor,rockets, boombs, engines);
                canvasStormtrooper._drawingStormtrooper.SetPictureSize(Width, Height);
                canvasStormtrooper._drawingStormtrooper.SetPosition( StartPositionX, StartPositionY);
                canvasStormtrooper.repaint();
            }
        });

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (canvasStormtrooper._drawingStormtrooper == null) return;
                boolean result = false;
                switch ((((JButton)(event.getSource())).getName())) {
                    case "UP":
                        result = canvasStormtrooper._drawingStormtrooper.MoveTransport(DirectionType.Up);
                        break;
                    case "DOWN":
                        result = canvasStormtrooper._drawingStormtrooper.MoveTransport(DirectionType.Down);
                        break;
                    case "LEFT":
                        result = canvasStormtrooper._drawingStormtrooper.MoveTransport(DirectionType.Left);
                        break;
                    case "RIGHT":
                        result = canvasStormtrooper._drawingStormtrooper.MoveTransport(DirectionType.Right);
                        break;
                }
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
        CreateButton.setBounds(10, getHeight() - 90, 100, 40);
        UpButton.setBounds(getWidth() - 140, getHeight() - 160, 50, 50);
        DownButton.setBounds(getWidth() - 140, getHeight() - 100, 50, 50);
        RightButton.setBounds(getWidth() - 80, getHeight() - 100, 50, 50);
        LeftButton.setBounds(getWidth() - 200, getHeight() - 100, 50, 50);
        add(CreateButton);
        add(UpButton);
        add(DownButton);
        add(RightButton);
        add(LeftButton);
        add(canvasStormtrooper);
        setVisible(true);
        //обработка события изменения размеров окна
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Width = getWidth() - 15;
                Height = getHeight() - 35;
                if (canvasStormtrooper._drawingStormtrooper != null)
                    canvasStormtrooper._drawingStormtrooper.SetPictureSize(Width, Height);
                canvasStormtrooper.setBounds(0,0, getWidth(), getHeight());
                CreateButton.setBounds(10, getHeight() - 90, 100, 40);
                UpButton.setBounds(getWidth() - 140, getHeight() - 160, 50, 50);
                DownButton.setBounds(getWidth() - 140, getHeight() - 100, 50, 50);
                RightButton.setBounds(getWidth() - 80, getHeight() - 100, 50, 50);
                LeftButton.setBounds(getWidth() - 200, getHeight() - 100, 50, 50);
            }
        });
    }
}
