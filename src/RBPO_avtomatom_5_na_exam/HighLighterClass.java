package RBPO_avtomatom_5_na_exam;

import java.awt.Color;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

public class HighLighterClass {
    static MyHighlightPainter findHighlightPainter = new MyHighlightPainter(
      Color.yellow);

    public static void highlight(JTextComponent textComp, String pattern,MyHighlightPainter myHighlightPainter)
        throws Exception {
        if (myHighlightPainter.getColor().equals(Color.yellow)) {
            removeHighlights(textComp);
        }
        Highlighter hilite = textComp.getHighlighter();
        Document doc = textComp.getDocument();
        String text = doc.getText(0, doc.getLength());
        int pos = 0;
        while ((pos = text.indexOf(pattern, pos)) >= 0) {
            hilite.addHighlight(pos, pos + pattern.length(), myHighlightPainter);
            pos += pattern.length();
        }
    }

    public static void removeHighlights(JTextComponent textComp) {
        Highlighter hilite = textComp.getHighlighter();
        Highlighter.Highlight[] hilites = hilite.getHighlights();
        for (int i = 0; i < hilites.length; i++) {
            if (hilites[i].getPainter() instanceof MyHighlightPainter) {

                hilite.removeHighlight(hilites[i]);
            }
        }
    }
}

class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {

  public MyHighlightPainter(Color color) {
    super(color);
  }
}


