package br.com.mariojp.figureeditor;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            JFrame frame = new JFrame("Figure Editor — Clique para inserir figuras");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            DrawingPanel panel = new DrawingPanel();

            // botão "Cor..."
            JButton colorButton = new JButton("Cor...");
            colorButton.addActionListener(e -> {
                Color chosen = JColorChooser.showDialog(frame, "Escolher Cor", Color.BLUE);
                if (chosen != null) {
                    panel.setCurrentColor(chosen);
                }
            });

            JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
            topBar.add(colorButton);

            frame.setLayout(new BorderLayout());
            frame.add(topBar, BorderLayout.NORTH);
            frame.add(panel, BorderLayout.CENTER);

            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
