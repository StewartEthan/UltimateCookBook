/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatecookbook;

// Imports for GUI
import javax.swing.*;

// Import for custom UI code
import user_interface.*;

/**
 *
 * @author Ethan Stewart
 */
public class UltimateCookBook {
    
    // Default constructor
    // Very basic, just create the new window, the UI classes do the rest
    UltimateCookBook() {
        // Create the window
        new UltimateCookBookUI().setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UltimateCookBook();
            }
        });
    }
    
}
