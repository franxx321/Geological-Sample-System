/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils.EntityHandlers;

import Utils.DBConnector;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author franc
 */
public class GUIHandler {
    private HashMap<String,JFrame> frames;
    private JFrame currentFrame;

    public GUIHandler() {
        this.frames= new HashMap();
        //TODO cargar los Frames al hashmap, crear variables estaticas para acceder los hashmaps, asignar como currentFrame al frame del menu.
        
    }
    
    
    
    
    public void changeFrame(String nextFrame, DBConnector dBconnector, List<Object> atributes){
        this.currentFrame.setVisible(false);
        this.currentFrame= frames.get(nextFrame);
        this.currentFrame.setVisible(true);
        this.currentFrame.repaint();
        this.currentFrame.revalidate();
        
    }
    
}
