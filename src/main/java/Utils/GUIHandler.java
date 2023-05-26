/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.util.HashMap;
import javax.swing.JFrame;

/**
 *
 * @author franc
 */
public class GUIHandler {
    public final static String menu = "menu",boxDeleter= "boxdeleter",arithmeticDisplayer= "arithmeticdisplayer",objectAdder="objectadder",DOTP="DOTP",OiBTP="OIBTP",tableDisplayer="tableDisplayer";
    
    
    private HashMap<String,JFrame> frames;
    private JFrame currentFrame;

    public GUIHandler() {
        this.frames= new HashMap();
        //TODO cargar los Frames al hashmap, crear variables estaticas para acceder los hashmaps, asignar como currentFrame al frame del menu.
       
    }
    
    
    
    
    public void changeFrame(String nextFrame){
        this.currentFrame.setVisible(false);
        this.currentFrame= frames.get(nextFrame);
        this.currentFrame.setVisible(true);
        this.currentFrame.repaint();
        this.currentFrame.revalidate();
    }
    
    public JFrame getFrame(String frameName){
        JFrame ret = this.frames.get(frameName);
        return ret;
    }
    
    
}
