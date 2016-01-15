/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user_interface;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Ethan Stewart
 */
public class ExtraWindow extends javax.swing.JFrame {
    // MEMBER VARIABLES
    private String callingFrame;
    
    // DEFAULT CONSTRUCTOR
    public ExtraWindow() {
        // Make sure main window is re-enabled on close
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                closeSecondaryFrame();
            }
        });
    }
    
    // CUSTOM CONSTRUCTOR
    public ExtraWindow(String c) {
        // Set callingFrame to parameter passed in
        callingFrame = c;
        
        // Make sure main window is re-enabled on close
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                closeSecondaryFrame();
            }
        });
    }
    
    // GETTERS
    public String getCallingFrame() {
        return callingFrame;
    }
    
    // SETTERS
    public void setCallingFrame(String c) {
        if (c != null && c.length() > 0) {
            callingFrame = c;
        }
    }
    
    // METHODS
    protected void closeSecondaryFrame() {
        for (Frame frame : Frame.getFrames()) {
            if (frame.getTitle().equals(callingFrame)) {
                frame.setEnabled(true);
                break;
            }
        }
        
        this.dispose();
    }
}
