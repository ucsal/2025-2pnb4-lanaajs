package br.com.mariojp.figureeditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

class DrawingPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_SIZE = 60;
    private final List<ColoredShape> shapes = new ArrayList<>();
    private Point startDrag = null;
    private Color currentColor = new Color(30,144,255); // cor padrão (azul DodgerBlue)

    DrawingPanel() {
        setBackground(Color.WHITE);
        setOpaque(true);
        setDoubleBuffered(true);

        var mouse = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && startDrag == null) {
                    int size = Math.max(Math.min(DEFAULT_SIZE, DEFAULT_SIZE), 10);
                    Shape s = new Ellipse2D.Double(e.getPoint().x, e.getPoint().y, size, size);
                    shapes.add(new ColoredShape(s, currentColor));
                    repaint();
                }
            }
        };
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    // permite mudar a cor atual
    void setCurrentColor(Color color) {
        if (color != null) {
            this.currentColor = color;
        }
    }

    void clear() {
        shapes.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (ColoredShape cs : shapes) {
            g2.setColor(cs.color);
            g2.fill(cs.shape);
            g2.setColor(new Color(0, 0, 0, 70));
            g2.setStroke(new BasicStroke(1.2f));
            g2.draw(cs.shape);
        }

        g2.dispose();
    }

    // classe interna para armazenar figura + cor
    private static class ColoredShape {
        final Shape shape;
        final Color color;

        ColoredShape(Shape shape, Color color) {
            this.shape = shape;
            this.color = color;
        }
    }
}
