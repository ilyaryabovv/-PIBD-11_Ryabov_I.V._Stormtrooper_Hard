import CollectionGenericObjects.AdditionalCollection;
import CollectionGenericObjects.AbstractCompany;
import Drawnings.DrawingBaseStormtrooper;
import Drawnings.DrawingStormtrooper;
import Drawnings.Engines.DrawingEngines;
import Drawnings.Engines.DrawingOvalEngines;
import Drawnings.Engines.DrawingTriangleEngines;
import Drawnings.Engines.IDrawingEngines;
import Entities.EntityBaseStormtrooper;
import Entities.EntityStormtrooper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FormAdditionalCollection extends JFrame {
    public DrawingBaseStormtrooper _drawingBaseStormtrooper = null;
    private AbstractCompany _company = null;
    private CanvasStormtrooper _canvasStormtrooper = new CanvasStormtrooper();
    private AdditionalCollection<EntityBaseStormtrooper, IDrawingEngines> _additionalCollection = null;
    private Random random = new Random();
    private JButton buttonGenerate = new JButton("Создать");
    private JList<String> listEntity = new JList<String>();
    private JList<String> listEngines = new JList<String>();
    public FormAdditionalCollection() {
        setTitle("Random stormtrooper");
        setMinimumSize(new Dimension(650,310));
        _additionalCollection = new AdditionalCollection<EntityBaseStormtrooper,IDrawingEngines>(3,(Class)EntityBaseStormtrooper.class,(Class)IDrawingEngines.class);
        AddEntities();
        AddEngines();

            buttonGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                _drawingBaseStormtrooper = _additionalCollection.CreateAdditionalCollectionStormtrooper();
                _drawingBaseStormtrooper.SetPictureSize(getWidth(), getHeight());
                _drawingBaseStormtrooper.SetPosition(50,50);
                _canvasStormtrooper._drawingBaseStormtrooper = _drawingBaseStormtrooper;
                _canvasStormtrooper.repaint();
                DrawingBaseStormtrooper copyStormtrooper;
                if (_drawingBaseStormtrooper instanceof DrawingStormtrooper)
                    copyStormtrooper =  new DrawingStormtrooper((EntityStormtrooper) _drawingBaseStormtrooper.EntityBaseStormtrooper, _drawingBaseStormtrooper.drawingEngines);
                else
                    copyStormtrooper =  new DrawingBaseStormtrooper(_drawingBaseStormtrooper.EntityBaseStormtrooper, _drawingBaseStormtrooper.drawingEngines);
                _company._collection.Insert(copyStormtrooper);
                FormStormtrooperCollection.canvasShow();

                String[] data1 = new String[_additionalCollection.CountEntities];
                for (int i = 0; i < _additionalCollection.CountEntities; i++) {
                    EntityBaseStormtrooper entity = _additionalCollection._collectionEntity[i];
                    data1[i] = ToString(entity);
                }
                String[] data2 = new String[_additionalCollection.CountEngines];
                for (int i = 0; i < _additionalCollection.CountEngines; i++) {
                    IDrawingEngines engines = _additionalCollection._collectionEngines[i];
                    data2[i] = ToString(engines);
                }
                listEntity.setListData(data1);
                listEngines.setListData(data2);
            }
        });
        buttonGenerate.setBounds(450, 10, 100, 50);
        add(buttonGenerate);
        listEntity.setBounds(10,200,300,60);
        listEngines.setBounds(320,200,300,60);
        add(listEntity);
        add(listEngines);
        add(_canvasStormtrooper);
        setVisible(true);
    }
    private String ToString(EntityBaseStormtrooper entity) {
        String str = "";
        if (entity instanceof EntityStormtrooper) str += "EntityWarmlyShip ";
        else str += "EntityShip ";
        str += entity.getBodyColor().toString();
        return str;
    }
    private String ToString(IDrawingEngines engines) {
        if (engines == null || engines.getNumberOfEngines() == null)
            return "Не имеет двигателей";
        String str = ""+engines.getNumberOfEngines();
        str+=" двигателя";

        return str;
    }
    public void AddEntities() {
        for (int i = 0; i < _additionalCollection.CountEntities; i++) {
            random = new Random();
            int speed = random.nextInt(100, 300);
            float weight = random.nextInt(1000, 3000);
            Color bodycolor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            EntityBaseStormtrooper entityBaseStormtrooper;
            if (random.nextBoolean()) {
                entityBaseStormtrooper = new EntityBaseStormtrooper(speed, weight, bodycolor);
            }
            else {
                Color additionalcolor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
                boolean rockets = random.nextBoolean();
                boolean bombs = random.nextBoolean();
                boolean engines = random.nextBoolean();
                entityBaseStormtrooper = new EntityStormtrooper(speed, weight, bodycolor, additionalcolor, rockets, bombs,engines);
            }
            _additionalCollection.Insert(entityBaseStormtrooper);
        }
    }
    public void AddEngines() {
        for (int i = 0; i < _additionalCollection.CountEngines; i++) {
            random = new Random();
            EntityBaseStormtrooper entity = _additionalCollection._collectionEntity[i];
            IDrawingEngines drawingEngines = null;
            int numberOfEngines = ((int) ((Math.random() * 3) + 1) * 2);
            int typeOfEngines = (int) ((Math.random() * 3) + 1);
            if (entity instanceof EntityStormtrooper) {
                if (((EntityStormtrooper) entity).getEngines()) {
                        switch (typeOfEngines) {
                            case 1:
                                drawingEngines = new DrawingEngines();
                                drawingEngines.setAmountOfEngines((int) ((Math.random() * 3) + 1) * 2);
                                break;
                            case 2:
                                drawingEngines = new DrawingTriangleEngines();
                                drawingEngines.setAmountOfEngines((int) ((Math.random() * 3) + 1) * 2);
                                break;
                            case 3:
                                drawingEngines = new DrawingOvalEngines();
                                drawingEngines.setAmountOfEngines((int) ((Math.random() * 3) + 1) * 2);
                                break;
                        }
                }
            }
            if(drawingEngines!=null){
                _additionalCollection.Insert(drawingEngines);
            }
        }
    }
    void setCompany(AbstractCompany company) {
        this._company = company;
        for (int i = 0; i < _additionalCollection.CountEntities; i++){
            EntityBaseStormtrooper entity = _additionalCollection._collectionEntity[i];
            DrawingBaseStormtrooper _listStormtrooper;
            if(entity instanceof  EntityStormtrooper){
                _listStormtrooper = new DrawingStormtrooper((EntityStormtrooper) entity,_additionalCollection._collectionEngines[i]);
            }else{
                _listStormtrooper = new DrawingBaseStormtrooper(entity,_additionalCollection._collectionEngines[i]);
            }
            _company._collection.Insert(_listStormtrooper);


        }
    }
}