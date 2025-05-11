package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JTextField urlField;
    private JComboBox<String> formatSelector;
    private JButton downloadButton;
    private JProgressBar progressBar;
    private JTextArea logArea;
    private JTextField downloadPathField;
    private JButton browseButton;
    private JCheckBox playlistCheckbox;

    public MainFrame() {
        setTitle("Baixador Youtube");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centralizar a janelinha

        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        //topPanel.setBackground(new Color(100,100,100)); //teste

        //URL, Linha 1
        //Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(new JLabel("URL do Vídeo"), gbc);

        //Campo pra digitar o URL
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        urlField = new JTextField(50);
        topPanel.add(urlField, gbc);

        //Seletor de Pasta, Linha 2
        //Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        topPanel.add(new JLabel("Salvar em:"), gbc);

        //Campo do caminho do download
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        downloadPathField = new JTextField(System.getProperty("user.home"));
        topPanel.add(downloadPathField, gbc);

        //Botão para procurar a pasta
        gbc.gridx = 2;
        gbc.weightx = 0;
        browseButton = new JButton("Procurar...");
        topPanel.add(browseButton, gbc);

        //Seletor de formato (comboBox), Linha 3
        //Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        topPanel.add(new JLabel("Formato:"), gbc);

        //ComboBox pra selecionar formato
        gbc.gridx = 1;
        formatSelector = new JComboBox<>(new String[]{"Vídeo (MP4)", "Áudio (MP3)"});
        topPanel.add(formatSelector, gbc);
        add(topPanel, BorderLayout.NORTH);

        //Mostrador de Logs
        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane, BorderLayout.CENTER);

        //Botão e Barra de Progresso
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        downloadButton = new JButton("Baixar");
        progressBar = new JProgressBar();
        progressBar.setBorderPainted(true);

        //CheckBox para Baixar Playlists
        gbc.gridx = 2;
        playlistCheckbox = new JCheckBox("Baixar Playlist");
        topPanel.add(playlistCheckbox, gbc);

        //Adicionar aqui...

        bottomPanel.add(downloadButton, BorderLayout.WEST);
        bottomPanel.add(progressBar, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public JButton getDownloadButton() {
        return downloadButton;
    }

    public JTextField getUrlField() {
        return urlField;
    }

    public JComboBox<String> getFormatSelector() {
        return formatSelector;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void appendLog(String text) {
        logArea.append(text + "\n");
    }

    public JTextField getDownloadPathField() {
        return downloadPathField;
    }

    public JButton getBrowseButton() {
        return browseButton;
    }

    public boolean isPlaylistSelected(){
        return playlistCheckbox.isSelected();
    }
}
