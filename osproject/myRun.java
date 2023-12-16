/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package osproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 20120
 */
    public class myRun implements Runnable {
    private final My_project myProject;
    private final String filePath;
    private final gui guiInstance;

    public myRun(My_project myProject, String filePath, gui guiInstance) {
        this.myProject = myProject;
        this.filePath = filePath;
        this.guiInstance = guiInstance;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line + Thread.currentThread().getName());
                
                myProject.Word_statistics(line,filePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
