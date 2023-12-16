/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package osproject;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class gui implements ActionListener {

    private JLabel pathLabel = null;
    private JButton browseButton = null;
    private JButton processButton = null;
    private JLabel resultLabel = null;
    private final JTable jTable = new JTable();
    private NewClass newClassInstance ;
    JCheckBox checkbox=null;
    File file_path = new File("");
    JTextArea tsh =null;
    JTextArea tlg =null;

    public gui() {
        newClassInstance = new NewClass(this); // Pass the reference to the gui instance
    }

    public gui(String txt) {
        newClassInstance = new NewClass(this); // Pass the reference to the gui instance
        JFrame frame = new JFrame();
        JLabel label = new JLabel("Please select a directory:");
        pathLabel = new JLabel("Selected Directory: ");
        browseButton = new JButton("Browse");
        browseButton.addActionListener(this);
        processButton = new JButton("Process");
        processButton.addActionListener(this);
        resultLabel = new JLabel("");

        checkbox = new JCheckBox("Include SubDirectories");
        checkbox.setBounds(100, 100, 50, 50);

        Color c1 = new Color(51, 204, 255);
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(150, 150, 150, 150));
        panel.setLayout(new FlowLayout());
        panel.add(label);
        panel.add(browseButton);
        panel.add(processButton);
        panel.add(checkbox);
        panel.add(resultLabel);
        panel.setBackground(c1);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(pathLabel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Word_statistics");
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == browseButton) {
            JFileChooser file_upload = new JFileChooser();
            file_upload.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int res = file_upload.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                file_path = file_upload.getSelectedFile();
                pathLabel.setText("Selected Directory: " + file_path.getAbsolutePath());

            }
        } else if (evt.getSource() == processButton  ) {
            boolean includeSubDirectories = checkbox.isSelected();
            System.out.print(file_path);
            tf(file_path,includeSubDirectories);
        }
    }

    public synchronized void addRowToTable(String lastFileName, int i_exested, int you_exested, int are_exested, int number_of_word, String subLongest, String subShorts) {
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        Object[] rowData = {lastFileName, number_of_word, you_exested, i_exested, are_exested, subLongest, subShorts};
        model.addRow(rowData);


    }

    public void tf(File file_path, Boolean ischeck) {
        JFrame jFrame = new JFrame("data table");
        jFrame.setSize(new Dimension(700, 500));
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel sh = new JLabel("Shortest word");
        JLabel lg = new JLabel("Longest word");
        tsh = new JTextArea(1, 10);
        tlg = new JTextArea(1, 10);
        Color c1 = new Color(51, 204, 255);

        final Object[] columns = new Object[]{"files", "#word", "#you", "is", "are", "longest word", "shortest word"};
        Object[][] data = new Object[][]{};

        jFrame.add(new JScrollPane(jTable), BorderLayout.CENTER);

        JPanel jPanel = new JPanel();
        JButton jButton = new JButton("process");
        System.out.print(data);
        jTable.setModel(new DefaultTableModel(data, columns));
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();

        jButton.addActionListener((ActionEvent e) -> {
            System.out.println("'Add' button pressed.");
            String filePathString = file_path.getAbsolutePath();
            newClassInstance.runProject(filePathString,ischeck);
        });

        /*  pass shortest longest words values  */
        String textsh = "";
        tsh.append(textsh);
        String textlg = "";
        tlg.append(textlg);

        jPanel.add(sh);
        jPanel.add(tsh);
        jPanel.add(lg);
        jPanel.add(tlg);
        jPanel.add(jButton);
        jFrame.add(jPanel, BorderLayout.SOUTH);
        jFrame.setVisible(true);
    }
    public void updateLongestWordInFile (String textlg){
        tlg.setText("");
        tlg.append(textlg);
    }
    public void updateShortsWordInFile (String textsh){
        tsh.setText("");
        tsh.append(textsh);
    }

    public static void main(String[] args) {
        String txt = null;
        new gui(txt);
    }
}
