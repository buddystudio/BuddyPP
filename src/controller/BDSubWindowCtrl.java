package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import model.BDGUIModel;
import view.BDSubWindow;

public class BDSubWindowCtrl
{
	public BDGUIModel guiModel = new BDGUIModel();
	
	private double xOffset = 0;
    private double yOffset = 0;
    
	public BDSubWindowCtrl(BDSubWindow subWindow)
	{
		// 点击关闭按钮
		subWindow.titlePanel.closeBtn.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
            	// Close the window.
            	subWindow.close();
            }
        });
		
		// 点击开始拖动窗口
		subWindow.titlePanel.setOnMousePressed(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event) 
            {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();

                // 记录窗体当前的位置
                guiModel.preX = subWindow.getX();
                guiModel.preY = subWindow.getY();
            }
        });
		
		// 拖动窗口
		subWindow.titlePanel.setOnMouseDragged(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event) 
            {
            	subWindow.setX(event.getScreenX() - xOffset);
            	subWindow.setY(event.getScreenY() - yOffset);
            }
        });
	}

}
