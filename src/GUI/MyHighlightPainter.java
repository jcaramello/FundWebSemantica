package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;

import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.text.JTextComponent;

public class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {

	  public MyHighlightPainter(Color color) {
	        super(color);
	    }

}
