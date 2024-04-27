package CollectionGenericObjects;

import Drawnings.DrawingBaseStormtrooper;

import java.awt.*;

public class StormtrooperSharingService  extends AbstractCompany{
    public StormtrooperSharingService (int picWidth, int picHeight, ICollectionGenericObjects<DrawingBaseStormtrooper> collection) {
        super(picWidth, picHeight, collection);
    }
    @Override
    public void DrawBackgound(Graphics g) {
        int width = _pictureWidth / _placeSizeWidth;
        int height = _pictureHeight / _placeSizeHeight;
        g.setColor(Color.BLACK);
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height + 1; ++j)
            {
                g.drawLine(i * _placeSizeWidth+15, j * _placeSizeHeight, i * _placeSizeWidth+15 + _placeSizeWidth-55, j * _placeSizeHeight);
                g.drawLine(i * _placeSizeWidth+15, j * _placeSizeHeight, i * _placeSizeWidth+15, j * _placeSizeHeight -_placeSizeHeight);
            }
        }
    }
    @Override
    protected void SetObjectsPosition() {
        int width = _pictureWidth / _placeSizeWidth;
        int height = _pictureHeight / _placeSizeHeight;

        int curWidth = width - 4;
        int curHeight = 0;

        for (int i = 0; i < (_collection.getCount()); i++) {
            if (_collection.Get(i) != null) {
                _collection.Get(i).SetPictureSize(_pictureWidth, _pictureHeight);
                _collection.Get(i).SetPosition(_placeSizeWidth * curWidth + 16, curHeight * _placeSizeHeight + 3);
            }

            if (curWidth < width-1)
                curWidth++;
            else
            {
                curWidth = 0;
                curHeight++;
            }
            if (curHeight > height)
            {
                return;
            }
        }
    }
}