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
public class NoiseGUI 
{
    public static int n = 10;
    public static int display()
    {
        
        Stage window = new Stage();
        TextField field = new TextField();
        Button ok = new Button();
        VBox box = new VBox();
        
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Set rate of noise");
        
        ok.setText("OK");
        ok.setOnAction(new EventHandler<ActionEvent>() 
        {
            
            @Override
            public void handle(ActionEvent event) 
            {
                if(isInteger(field.getText()))
                {
                    n = Integer.parseInt(field.getText());
                    window.close();
                }
            }
        });
        
        box.getChildren().addAll(field, ok);
        box.setAlignment(Pos.CENTER);
        Scene scene = new Scene(box);
        window.setScene(scene);
        window.showAndWait();
        return n;
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
