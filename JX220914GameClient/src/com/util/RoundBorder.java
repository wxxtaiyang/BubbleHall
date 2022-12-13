package com.util;

import javax.swing.border.Border;
import java.awt.*;

public class RoundBorder implements Border {
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.BLUE);
        g.drawRoundRect(0, 0, c.getWidth()-1, c.getHeight()-1, 30, 30);

    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}
