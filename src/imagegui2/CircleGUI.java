/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagegui2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author lordstorm
 */
public class CircleGUI 
{
    public static int min_x = 100;
    public static int max_x = 200;
    
    public static int min_y = 100;
    public static int max_y = 200;
    
    public static int n = 3;
    
    public static int[] nn = new int[5];
    public static int[] display()
    {
        
        Stage window = new Stage();
        TextField min_x_field = new TextField();
        TextField max_x_field = new TextField();
        TextField min_y_field = new TextField();
        TextField max_y_field = new TextField();
        TextField n_field = new TextField();
        
        n_field.setPromptText("AMOUNT OF CIRCLES");
        min_x_field.setPromptText("MINUMUM WIDTH OF CIRCLES");
        max_x_field.setPromptText("MAXIMUM WIDTH OF CIRCLES");
        min_y_field.setPromptText("MINIMUM HEIGHT OF CIRCLES");
        max_y_field.setPromptText("MAXIMUM HEIGHT OF CIRCLES");
        Button ok = new Button();
        VBox box = new VBox();
        
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SETTINGS FOR RANDOM CIRCLES");
        window.setMinWidth(250);
        
        ok.setText("OK");
        ok.setOnAction(new EventHandler<ActionEvent>() 
        {
            
            @Override
            public void handle(ActionEvent event) 
            {
                if(isInteger(n_field.getText()))
                {
                   if(isInteger(min_x_field.getText()))
                   {
                       if(isInteger(min_x_field.getText()))
                       {
                           if(isInteger(min_x_field.getText()))
                           {
                               if(isInteger(min_x_field.getText()))
                               {
                                n = Integer.parseInt(n_field.getText());
                                min_x = Integer.parseInt(min_x_field.getText());
                                max_x = Integer.parseInt(max_x_field.getText());
                                min_y = Integer.parseInt(min_y_field.getText());
                                max_y = Integer.parseInt(max_y_field.getText());
                                
                                nn[0] = n;
                                nn[1] = min_x;
                                nn[2] = max_x;
                                nn[3] = min_y;
                                nn[4] = max_y;
                                window.close();
                               }
                           }
                       }
                   }
                }
            }
        });
        
        box.getChildren().addAll(n_field, min_x_field, max_x_field, min_y_field, max_y_field, ok);
        box.setAlignment(Pos.CENTER);
        Scene scene = new Scene(box);
        window.setScene(scene);
        window.showAndWait();
        return nn;
    }
    
    public static boolean isInteger(String s) 
    {
    try 
    { 
        Integer.parseInt(s); 
    } catch(NumberFormatException e) 
    { 
        return false; 
    } catch(NullPointerException e) 
    {
        return false;
    }
    
    return true;
}
}
