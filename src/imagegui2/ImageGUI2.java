/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagegui2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author lordstorm
 */
public class ImageGUI2 extends Application 
{
    public static int index = 0;
    static Color[] color_list = {Color.BLACK, Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE};
    public static BufferedImage[] images = new BufferedImage[20];
    final FileChooser fileChooser = new FileChooser();
    @Override
    public void start(Stage primaryStage) 
    {
        Button btn = new Button();
        btn.setText("LOAD IMAGE");
        btn.setOnAction(new EventHandler<ActionEvent>() 
        {
            
            @Override
            public void handle(ActionEvent event) 
            {
                File file = fileChooser.showOpenDialog(primaryStage);
                    if (file != null) 
                    {
                      try 
                      {
                      images[0] = ImageIO.read(new File(file.getAbsolutePath()));
                      
                      } 
                      catch (IOException ex) 
                      {
                            
                      }
                      IM.display(images[0], file.getAbsolutePath());
                    }
            }
        });
        
        Button choose_file_save = new Button();
        choose_file_save.setText("SAVE IMAGE");
        choose_file_save.setOnAction(
            new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(final ActionEvent e) 
                {
                    File file = fileChooser.showOpenDialog(primaryStage);
                    if (file != null) 
                    {
                        
                        try 
                        {
                            ImageIO.write(images[index], "png", file);
                        } 
                        catch(Exception s)
                        {
                        }
                    }
                }
            });
        
        Button undo = new Button();
        undo.setText("UNDO");
        undo.setOnAction(new EventHandler<ActionEvent>() 
        {
            
            @Override
            public void handle(ActionEvent event) 
            {
               
                    if(index == 0)
                    {
                        
                    }
                    else
                    {
                        if(images[index-1] != null)
                        {
                        index--;
                        IM.update(images[index]);
                        }
                    }
                
            }
        });
        
        Button redo = new Button();
        redo.setText("REDO");
        redo.setOnAction(new EventHandler<ActionEvent>() 
        {
            
            @Override
            public void handle(ActionEvent event) 
            {
              
                    if(index == 20)
                    {
                        
                    }
                    else
                    {
                        if(images[index+1] != null)
                        {
                        index++;
                        IM.update(images[index]);
                        }
                    }
                
            }
        });
        
        Button reset = new Button();
        reset.setText("RESET");
        reset.setOnAction(new EventHandler<ActionEvent>() 
        {
            
            @Override
            public void handle(ActionEvent event) 
            {
                index = 0;
                for(int i = 1; i < 20; i++)
                {
                    images[i] = null;
                }
                IM.update(images[index]);
            }
        });
        
        Button noise = new Button();
        noise.setText("NOISE");
        noise.setOnAction(new EventHandler<ActionEvent>() 
        {
            
            @Override
            public void handle(ActionEvent event) 
            {
                int n = NoiseGUI.display();
                noise(n);
                IM.update(images[index]);
            }
        });
        
        Button add_rectangles = new Button();
        add_rectangles.setText("ADD RECTANGLES");
        add_rectangles.setOnAction(new EventHandler<ActionEvent>() 
        {
            
            @Override
            public void handle(ActionEvent event) 
            {
                int n[] = RectangleGUI.display();
                add_rectangles(n);
                IM.update(images[index]);
            }
        });
        
        Button add_circles = new Button();
        add_circles.setText("ADD CIRCLES");
        add_circles.setOnAction(new EventHandler<ActionEvent>() 
        {
            
            @Override
            public void handle(ActionEvent event) 
            {
                int n[] = CircleGUI.display();
                add_circles(n);
                IM.update(images[index]);
            }
        });
        
        VBox root = new VBox();
        root.getChildren().addAll(btn, choose_file_save, reset, undo, redo, noise, add_rectangles, add_circles);
        
        Scene scene = new Scene(root, 200, 220);
        
        primaryStage.setTitle("Image application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static BufferedImage deepCopy(BufferedImage bi)
    {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
    
    public static void noise(int foreach)
    {
        if(index < 20)
        {
            index++;
        }
        else
        {
            for(int i = 1; i < 20; i++)
            {
                images[i] = images[i+1];
            }
        }
        images[index] = deepCopy(images[index-1]);
        
        int n = 0;
        int m = 0;
        Random r = new Random();
        for(int i = 0; i < images[index].getWidth(); i++)
        {
            for(int j = 0; j < images[index].getHeight(); j++)
            {
                n = r.nextInt();
                m = color_list[r.nextInt(5)].getRGB();
                if(n % foreach == 0)
                {
                    images[index].setRGB(i, j, m);
                }
            }
        }
        
    }
    
    public static void add_rectangles(int[] m)
    {
        if(index < 20)
        {
            index++;
        }
        else
        {
            for(int i = 1; i < 20; i++)
            {
                images[i] = images[i+1];
            }
        }
        images[index] = deepCopy(images[index-1]);
        
        Graphics g = images[index].getGraphics();
        Random r = new Random();
        for(int n = 0; n < m[0]; n++)
        {
            int width = m[1] + r.nextInt(m[2] - m[1]);
            int height = m[3] + r.nextInt(m[4] - m[3]);
            int x = r.nextInt(images[index].getWidth() - width);
            int y = r.nextInt(images[index].getHeight() - height);
            g.setColor(color_list[r.nextInt(5)]);
            g.fillRect(x, y, width, height);
        }
        g.dispose();
        
    }
    
    public static void add_circles(int[] m)
    {
        if(index < 20)
        {
            index++;
        }
        else
        {
            for(int i = 1; i < 20; i++)
            {
                images[i] = images[i+1];
            }
        }
        images[index] = deepCopy(images[index-1]);
        
        Graphics g = images[index].getGraphics();
        Random r = new Random();
        for(int n = 0; n < m[0]; n++)
        {
            int width = m[1] + r.nextInt(m[2] - m[1]);
            int height = m[3] + r.nextInt(m[4] - m[3]);
            int x = r.nextInt(images[index].getWidth() - width);
            int y = r.nextInt(images[index].getHeight() - height);
            g.setColor(color_list[r.nextInt(5)]);
            g.fillOval(x, y, width, height);
            
        }
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
