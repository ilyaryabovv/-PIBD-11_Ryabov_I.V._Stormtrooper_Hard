import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Random;

public class FormStormtrooper extends JFrame {
    private final String title;
    private final Dimension dimension;
    private int Width;
    private int Height;
    private final CanvasStormtrooper canvasStormtrooper = new CanvasStormtrooper();
    private final JButton CreateButton = new JButton("Создать");
    private final JButton UpButton = new JButton();
    private final JButton DownButton = new JButton();
    private final JButton LeftButton = new JButton();
    private final JButton RightButton = new JButton();
    public FormStormtrooper(String title, Dimension dimension) {
        this.title = title;
        this.dimension = dimension;
    }
    public void Init() {
        setTitle(title);
        setMinimumSize(dimension);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Width = getWidth() - 13;
        Height = getHeight() - 30;
        CreateButton.setName("createButton");
        Icon iconUp = new ImageIcon("ProjectStormtrooper\\Resources\\arrowUp.jpg");
        UpButton.setIcon(iconUp);
        UpButton.setName("UP");
        DownButton.setName("DOWN");
        Icon iconDown = new ImageIcon("ProjectStormtrooper\\Resources\\arrowDown.jpg");
        DownButton.setIcon(iconDown);
        LeftButton.setName("LEFT");
        Icon iconLeft = new ImageIcon("ProjectStormtrooper\\Resources\\arrowLeft.jpg");
        LeftButton.setIcon(iconLeft);
        RightButton.setName("RIGHT");
        Icon iconRight = new ImageIcon("ProjectStormtrooper\\Resources\\arrowRight.jpg");
        RightButton.setIcon(iconRight);
        CreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int StartPositionX = (int)(Math.random() * 90 + 10);
                int StartPositionY = (int)(Math.random() * 90 + 10);
                int speed = (int)(Math.random() * 300 + 100);
                float weight = (float) (Math.random() * 3000 + 1000);
                Color bodyColor = new Color((int)(Math.random() * 255 + 0),(int)(Math.random() * 255 + 0),(int)(Math.random() * 255 + 0));
                Color additionalColor = new Color((int)(Math.random() * 255 + 0),(int)(Math.random() * 255 + 0),(int)(Math.random() * 255 + 0));
                boolean rockets = new Random().nextBoolean();
                boolean bombs = new Random().nextBoolean();
                boolean engines = new Random().nextBoolean();
                canvasStormtrooper._drawingStormtrooper = new DrawingStormtrooper();
                canvasStormtrooper._drawingStormtrooper.Init(speed, weight, bodyColor, additionalColor,rockets, bombs, engines);
                canvasStormtrooper._drawingStormtrooper.SetPictureSize(Width, Height);
                canvasStormtrooper._drawingStormtrooper.SetPosition( StartPositionX, StartPositionY);
                canvasStormtrooper.repaint();
            }
        });

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (canvasStormtrooper._drawingStormtrooper == null) return;
                boolean result = switch ((((JButton) (event.getSource())).getName())) {
                    case "UP" -> canvasStormtrooper._drawingStormtrooper.MoveTransport(DirectionType.Up);
                    case "DOWN" -> canvasStormtrooper._drawingStormtrooper.MoveTransport(DirectionType.Down);
                    case "LEFT" -> canvasStormtrooper._drawingStormtrooper.MoveTransport(DirectionType.Left);
                    case "RIGHT" -> canvasStormtrooper._drawingStormtrooper.MoveTransport(DirectionType.Right);
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
        CreateButton.setBounds(10, getHeight() - 90, 130, 40);
        UpButton.setBounds(getWidth() - 180, getHeight() - 210, 70, 70);
        DownButton.setBounds(getWidth() - 180, getHeight() - 140, 70, 70);
        RightButton.setBounds(getWidth() - 110, getHeight() - 140, 70, 70);
        LeftButton.setBounds(getWidth() - 250, getHeight() - 140, 70, 70);
        add(CreateButton);
        add(UpButton);
        add(DownButton);
        add(RightButton);
        add(LeftButton);
        add(canvasStormtrooper);
        setVisible(true);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Width = getWidth() - 13;
                Height = getHeight() - 30;
                if (canvasStormtrooper._drawingStormtrooper != null)canvasStormtrooper._drawingStormtrooper.SetPictureSize(Width, Height);
                canvasStormtrooper.setBounds(0,0, getWidth(), getHeight());
                CreateButton.setBounds(10, getHeight() - 90, 130, 40);
                UpButton.setBounds(getWidth() - 180, getHeight() - 210, 70, 70);
                DownButton.setBounds(getWidth() - 180, getHeight() - 140, 70, 70);
                RightButton.setBounds(getWidth() - 110, getHeight() - 140, 70, 70);
                LeftButton.setBounds(getWidth() - 250, getHeight() - 140, 70, 70);
            }
        });
    }
}
