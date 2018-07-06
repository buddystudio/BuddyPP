/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import java.util.Map;

import util.base.Base;
import util.debug.BDSerial;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
import model.BoardMap;

/**
 *
 * @author gsh
 */
public class BDPreSettingWindow extends BDWindow 
{
	public ComboBox<String> combPin = new ComboBox<>();
	public ComboBox<BoardMap> combMode = new ComboBox<BoardMap>();

	public Button btnSubmit = new Button("确定");

	public BDPreSettingWindow() 
	{
		// 窗口初始化
		super.init(500, 50);

		// 总在最前方
		this.setAlwaysOnTop(true);

		// 只有关闭按钮的窗口
		this.initStyle(StageStyle.UTILITY);
		this.setResizable(false);

		this.setTitle("  请选择板型和串口");
		this.setScene(scene);

		HBox contain = new HBox();
		Label lbMode = new Label("板型");
		Label lbPin = new Label("串口");

		combPin.setPrefWidth(100);
		combMode.setPrefWidth(200);
		combMode.getItems().clear();

		btnSubmit.setStyle("-fx-background-radius: 0, 0;");
		combPin.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");
		combMode.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");
		
		combMode.getItems().add(new BoardMap("Buddy LEO", "leonardo"));
		combMode.getItems().add(new BoardMap("Buddy UNO", "uno"));
		
		Map<String, Map<String, String>> mapFile = Base.getMapFile();
		
		try
		{
			for (String key : mapFile.keySet()) 
			{
				combMode.getItems().add(new BoardMap(mapFile.get(key).get("name"),key));		
			}
		}
		catch(Exception e){}

		combMode.getSelectionModel().selectFirst();

		combPin.getItems().clear();
		combPin.getItems().addAll(BDSerial.list());
		combPin.getSelectionModel().select(0);

		btnSubmit.setPrefSize(70, 30);
		
		contain.setPadding(new Insets(15, 15, 15, 15)); // 设置边距
		contain.setSpacing(10); 						// 设置间距
		contain.setAlignment(Pos.CENTER); 				// 居中排列

		contain.getChildren().add(lbMode);
		contain.getChildren().add(combMode);
		contain.getChildren().add(lbPin);
		contain.getChildren().add(combPin);
		contain.getChildren().add(btnSubmit);

		rootPanel.getChildren().add(contain);
	}

	public void ReflashPort() 
	{
		combPin.getItems().clear();
		
		List<String> ports = BDSerial.list();
		
		if (ports.size() > 0) 
		{
			combPin.getItems().addAll(ports);
			combPin.getSelectionModel().select(0);
		}
	}
}
