package tasks;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import view.MainFrame;

public class DownloadTask extends SwingWorker<Void, String> {
    private final MainFrame mainFrame;
    private final String url;
    private final String format;
    private final String path;

    public DownloadTask(MainFrame mainFrame, String url, String format, String path) {
        this.mainFrame = mainFrame;
        this.url = url;
        this.format = format;
        this.path = path;
    }

    @Override
    protected Void doInBackground() {
        try {
            List<String> command = new ArrayList<>();
            command.add("bin/yt-dlp"); // Caminho do yt-dlp

            if (mainFrame.isPlaylistSelected()){
                command.add("--yes-playlist");
            }

            command.add(url);

            if (format.equalsIgnoreCase("Áudio (MP3)")) {
                command.add("-x");
                command.add("--audio-format");
                command.add("mp3");
            } else {
                command.add("-f");
                command.add("bestvideo[ext=mp4]+bestaudio[ext=mp4]/mp4");
            }

            command.add("--ffmpeg-location");
            command.add("bin");

            command.add("-P");
            command.add(path);

            ProcessBuilder builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true);
            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            int progressBar = 0;

            // Processar o progresso
            while ((line = reader.readLine()) != null) {
                publish(line); // Atualiza o logArea

                // Atualiza a barra
                progressBar += 1;
                if (progressBar <= 100) {
                    setProgress(progressBar);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                publish("Download concluído!");
            } else {
                publish("Erro durante o download!");
            }

        } catch (IOException | InterruptedException e) {
            publish(e.getMessage());
        }

        return null;
    }

    @Override
    protected void process(List<String> chunks) {
        for (String line : chunks) {
            mainFrame.appendLog(line + "\n");
        }
    }

    @Override
    protected void done() {
        mainFrame.getProgressBar().setValue(100);
    }
}
