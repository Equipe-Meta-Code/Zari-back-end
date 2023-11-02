package view;
 
import javax.swing.*;

import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
 
public class RoundButton extends JButton {
    private boolean hover = false;
 
    public RoundButton(String label) {
        super(label);
        setOpaque(false); // Torna o botão transparente para que a cor de fundo padrão seja exibida
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false); // Remove a borda padrão para evitar problemas visuais
        setUI(new BasicButtonUI()); // Utiliza as configurações padrão do Look and Feel
 
        // Define a cor de fundo do botão
        setBackground(UIManager.getColor("Button.background")); // Por exemplo, azul
 
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }
 
            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                repaint();
            }
        });
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }
 
        if (hover) {
            g.setColor(Color.gray);
        }
 
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 
        int width = getWidth();
        int height = getHeight();
        g2.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, 15, 15));
 
        super.paintComponent(g);
    }
 
    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.draw(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, 15, 15));
    }
}
 
