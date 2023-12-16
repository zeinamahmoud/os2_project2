/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package osproject;


import java.io.File;
import java.util.ArrayList;

public class NewClass {
    private final gui guiInstance;

    // Modify the constructor to accept a gui instance
    public NewClass(gui guiInstance) {
        this.guiInstance = guiInstance;
    }
    public static void main(String[] args) {
        

    }
    public void runProject(String directorFile,Boolean subDirector){
        try {
            ArrayList<String> textFilePaths = new ArrayList<>();
            
            findTextFiles(subDirector,directorFile, textFilePaths);

            // Print the results
            for (String filePath : textFilePaths) {
                System.out.println(filePath);
            }

            My_project myProject = new My_project(guiInstance);

            // Assuming you have a list of file paths

            for (String filePath : textFilePaths) {
                myRun myRunnable = new myRun(myProject, filePath, guiInstance);
                Thread thread = new Thread(myRunnable);
                thread.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void findTextFiles(Boolean subDirector,String directoryPath, ArrayList<String> textFilePaths) {
        try {
            File directory = new File(directoryPath);

            if (!directory.exists()) {
                throw new Exception("Directory does not exist: " + directoryPath);
            }

            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory() && subDirector == true) {
                        // Recursively search subdirectories
                        findTextFiles(subDirector,file.getAbsolutePath(), textFilePaths);
                    } else if (file.isFile() && file.getName().toLowerCase().endsWith(".txt")) {
                        // Check if the path is already in the array before adding it
                        String filePath = file.getAbsolutePath();
                        if (!textFilePaths.contains(filePath)) {
                            // If the path is not in the array, add it
                            textFilePaths.add(filePath);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}