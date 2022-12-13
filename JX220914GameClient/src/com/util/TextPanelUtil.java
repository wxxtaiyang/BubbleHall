package com.util;

import com.commen.ImageData;

import javax.swing.*;
import javax.swing.text.*;

public class TextPanelUtil extends JTextPane {

    @Override
    public void insertIcon(Icon g) {
        MutableAttributeSet inputAttributes = getInputAttributes();
        inputAttributes.removeAttributes(inputAttributes);
        StyleConstants.setIcon(inputAttributes, g);
        ImageIcon icon = (ImageIcon) g;
        String name = icon.toString().split("/")[icon.toString().split("/").length-1].split("\\.")[0];
        replaceSelection("&^"+name+"&^", false);
        inputAttributes.removeAttributes(inputAttributes);
    }

    private void replaceSelection(String content, boolean checkEditable) {
        if (checkEditable && !isEditable()) {
            UIManager.getLookAndFeel().provideErrorFeedback(TextPanelUtil.this);
            return;
        }
        Document doc = getStyledDocument();
        if (doc != null) {
            try {
                Caret caret = getCaret();
                boolean composedTextSaved = saveComposedText(caret.getDot());
                int p0 = Math.min(caret.getDot(), caret.getMark());
                int p1 = Math.max(caret.getDot(), caret.getMark());
                AttributeSet attr = getInputAttributes().copyAttributes();
                if (doc instanceof AbstractDocument) {
                    ((AbstractDocument)doc).replace(p0, p1 - p0, content,attr);
                }
                else {
                    if (p0 != p1) {
                        doc.remove(p0, p1 - p0);
                    }
                    if (content != null && content.length() > 0) {
                        doc.insertString(p0, content, attr);
                    }
                }
                if (composedTextSaved) {
                    restoreComposedText();
                }
            } catch (BadLocationException e) {
                UIManager.getLookAndFeel().provideErrorFeedback(TextPanelUtil.this);
            }
        }
    }
}
