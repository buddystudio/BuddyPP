package util.base;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.BDParameters;
import view.BDWindow;

/**
 * 拉伸工具类
 * @author gsh
 */
public class BDDrawUtil 
{
    //窗体拉伸属性
    private static boolean isRight;					// 是否处于右边界调整窗口状态
    private static boolean isBottomRight;			// 是否处于右下角调整窗口状态
    private static boolean isBottom;				// 是否处于下边界调整窗口状态
    private final static int RESIZE_WIDTH = 10;		// 判定是否为调整窗口状态的范围与边界距离
    
    private final static double MIN_WIDTH = BDParameters.minWidth;		// 窗口最小宽度
    private final static double MIN_HEIGHT = BDParameters.minHeight;	// 窗口最小高
    
    public static void addDrawFunc(Stage stage, Pane root) 
    {
    	final ObjectProperty<Cursor> cursor_DEFAULT 	= new SimpleObjectProperty<>(Cursor.DEFAULT);
    	final ObjectProperty<Cursor> cursor_SE_RESIZE 	= new SimpleObjectProperty<>(Cursor.SE_RESIZE);
    	final ObjectProperty<Cursor> cursor_S_RESIZE 	= new SimpleObjectProperty<>(Cursor.S_RESIZE);
    	final ObjectProperty<Cursor> cursor_E_RESIZE 	= new SimpleObjectProperty<>(Cursor.E_RESIZE);
    	
        root.setOnMouseMoved((MouseEvent event) -> 
        {
            event.consume();

            double x = event.getSceneX();
            double y = event.getSceneY();
            double width = stage.getWidth();
            double height = stage.getHeight();

            root.cursorProperty().bind(cursor_DEFAULT);
            
            // 先将所有调整窗口状态重置
            isRight = isBottomRight = isBottom = false;
            
            if (y >= height - RESIZE_WIDTH) 
            {
                if (x <= RESIZE_WIDTH) 
                {// 左下角调整窗口状态

                } 
                else if (x >= width - RESIZE_WIDTH) 
                {
                	// 右下角调整窗口状态
                    isBottomRight = true;
                    root.cursorProperty().bind(cursor_SE_RESIZE);
                } 
                else
                {
                	// 下边界调整窗口状态
                    isBottom = true;
                    root.cursorProperty().bind(cursor_S_RESIZE);
                }
            } 
            else if (x >= width - RESIZE_WIDTH) 
            {
            	// 右边界调整窗口状态
                isRight = true;
                root.cursorProperty().bind(cursor_E_RESIZE);
            }
        });

        root.setOnMouseDragged((MouseEvent event) -> 
        {
            double x = event.getSceneX();
            double y = event.getSceneY();
           
            // 保存窗口改变后的x、y坐标和宽度、高度，用于预判是否会小于最小宽度、最小高度
            double nextX = stage.getX();
            double nextY = stage.getY();
            double nextWidth = stage.getWidth();
            double nextHeight = stage.getHeight();

            if (isRight || isBottomRight) 
            {
            	// 所有右边调整窗口状态
                nextWidth = x;
            }
            if (isBottomRight || isBottom) 
            {
            	// 所有下边调整窗口状态
                nextHeight = y;
            }
            if (nextWidth <= MIN_WIDTH) 
            {
            	// 如果窗口改变后的宽度小于最小宽度，则宽度调整到最小宽度
                nextWidth = MIN_WIDTH;
            }
            if (nextHeight <= MIN_HEIGHT) 
            {
            	// 如果窗口改变后的高度小于最小高度，则高度调整到最小高度
                nextHeight = MIN_HEIGHT;
            }
            
            // 最后统一改变窗口的x、y坐标和宽度、高度，可以防止刷新频繁出现的屏闪情况
            //stage.setX(nextX);
            //stage.setY(nextY);
            stage.setWidth(nextWidth);
            stage.setHeight(nextHeight);
        });
    }
    
    public static void addDrawSubFunc(Stage stage, Pane root) 
    {
    	final ObjectProperty<Cursor> cursor_DEFAULT 	= new SimpleObjectProperty<>(Cursor.DEFAULT);
    	final ObjectProperty<Cursor> cursor_SE_RESIZE 	= new SimpleObjectProperty<>(Cursor.SE_RESIZE);
    	final ObjectProperty<Cursor> cursor_S_RESIZE 	= new SimpleObjectProperty<>(Cursor.S_RESIZE);
    	final ObjectProperty<Cursor> cursor_E_RESIZE 	= new SimpleObjectProperty<>(Cursor.E_RESIZE);
    	
        root.setOnMouseMoved((MouseEvent event) -> 
        {
            event.consume();

            double x = event.getSceneX();
            double y = event.getSceneY();
            double width = stage.getWidth();
            double height = stage.getHeight();

            root.cursorProperty().bind(cursor_DEFAULT);
            
            // 先将所有调整窗口状态重置
            isRight = isBottomRight = isBottom = false;
            
            if (y >= height - RESIZE_WIDTH) 
            {
                if (x <= RESIZE_WIDTH) 
                {// 左下角调整窗口状态

                } 
                else if (x >= width - RESIZE_WIDTH) 
                {
                	// 右下角调整窗口状态
                    isBottomRight = true;
                    root.cursorProperty().bind(cursor_SE_RESIZE);
                } 
                else
                {
                	// 下边界调整窗口状态
                    isBottom = true;
                    root.cursorProperty().bind(cursor_S_RESIZE);
                }
            } 
            else if (x >= width - RESIZE_WIDTH) 
            {
            	// 右边界调整窗口状态
                isRight = true;
                root.cursorProperty().bind(cursor_E_RESIZE);
            }
        });

        root.setOnMouseDragged((MouseEvent event) -> 
        {
            double x = event.getSceneX();
            double y = event.getSceneY();
           
            // 保存窗口改变后的x、y坐标和宽度、高度，用于预判是否会小于最小宽度、最小高度
            double nextX = stage.getX();
            double nextY = stage.getY();
            double nextWidth = stage.getWidth();
            double nextHeight = stage.getHeight();

            if (isRight || isBottomRight) 
            {
            	// 所有右边调整窗口状态
                nextWidth = x;
            }
            if (isBottomRight || isBottom) 
            {
            	// 所有下边调整窗口状态
                nextHeight = y;
            }
            
            
            // 最后统一改变窗口的x、y坐标和宽度、高度，可以防止刷新频繁出现的屏闪情况
            //stage.setX(nextX);
            //stage.setY(nextY);
            stage.setWidth(nextWidth);
            stage.setHeight(nextHeight);
        });
    }
    
    // 实现子窗体居中于主窗体
  	static public void showInTheMiddle(Stage win)
  	{
  		if(BDParameters.os.equals("Mac OS X"))
		{
			return;
		}

  		if(BDParameters.gds.length == 1)
    	{
    		return;
    	}

  		double posX = BDParameters.primaryStage.getX() + (BDParameters.curWidth - win.getWidth()) / 2;
  		double posY = BDParameters.primaryStage.getY() + (BDParameters.curHeight - win.getHeight()) / 2;
  		
  		win.setX(posX);
  		win.setY(posY);
  	}
}
