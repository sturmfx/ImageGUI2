/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagegui2;

import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author lordstorm
 */
public class IM 
{
    public static boolean is_visible = false;
    public static Stage window = new Stage();
    public static Canvas can;
    public static VBox layout = new VBox();
    public static Scene scene = new Scene(layout);
    public static GraphicsContext gc;
    public static Image image;
    public static void display(BufferedImage bi, String path)
    {
        if(!is_visible)
        {
        window.initModality(Modality.NONE);
        is_visible = true;
        window.setTitle(path);
        window.setMaxWidth(bi.getWidth());
        window.setMaxHeight(bi.getHeight());
        
        can = new Canvas(bi.getWidth(), bi.getHeight());
        
        layout.getChildren().add(can);
        
        window.setScene(scene);
        window.show();
        
        gc = can.getGraphicsContext2D();
        image = SwingFXUtils.toFXImage(bi, null);
        gc.drawImage(image, 0, 0);
        }
        else
        {
        window.setTitle(path);
        window.setMaxWidth(bi.getWidth());
        window.setMaxHeight(bi.getHeight());
        
        can.setWidth(bi.getWidth());
        can.setHeight(bi.getHeight());
        gc = can.getGraphicsContext2D();
        image = SwingFXUtils.toFXImage(bi, null);
        gc.drawImage(image, 0, 0);
        }
        
       
        
        
        
        
    }
    public static void update(BufferedImage bi)
    {
        gc = can.getGraphicsContext2D();
        image = SwingFXUtils.toFXImage(bi, null);
        gc.drawImage(image, 0, 0);
    }
}
