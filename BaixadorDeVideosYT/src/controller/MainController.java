package controller;

import tasks.DownloadTask;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    private final MainFrame mainFrame;

    public MainController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initController();
    }

    private void initController() {
        mainFrame.getDownloadButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = mainFrame.getUrlField().getText();
                String format = (String) mainFrame.getFormatSelector().getSelectedItem();

                if (url.isEmpty()) {
                    mainFrame.appendLog("Por favor, insira uma URL válida!\n");
                    return;
                }

                mainFrame.getProgressBar().setValue(0);
                mainFrame.appendLog("Iniciando o download...\n");

                // Cria uma nova task para fazer o download em segundo plano
                String path = mainFrame.getDownloadPathField().getText();
                DownloadTask task = new DownloadTask(mainFrame, url, format, path);
                task.addPropertyChangeListener(evt -> {
                    if ("progress".equals(evt.getPropertyName())) {
                        int progress = (Integer) evt.getNewValue();
                        mainFrame.getProgressBar().setValue(progress);
                    }
                });
                task.execute();
            }
        });

        mainFrame.getBrowseButton().addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = chooser.showOpenDialog(mainFrame);

            if (result == JFileChooser.APPROVE_OPTION) {
                String selectedPath = chooser.getSelectedFile().getAbsolutePath();
                mainFrame.getDownloadPathField().setText(selectedPath);
            }
        });

    }
}
