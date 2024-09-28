package model;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class Print implements Printable {

    final Component COMPONENT;

    public Print(Component component) {
        COMPONENT = component;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

        if (pageIndex > 0) {
            
            return Printable.NO_SUCH_PAGE;
            
        } else {
            
            Dimension dimension = COMPONENT.getSize();
            double componentHeight = dimension.getHeight();
            double componentWidth = dimension.getWidth();

            double pageHeight = pageFormat.getImageableHeight();
            double pageWidth = pageFormat.getImageableWidth();

            double pageXStart = pageFormat.getImageableX();
            double pageYStart = pageFormat.getImageableY();

            double xRatio = pageWidth / componentWidth;
            double yRatio = pageHeight / componentHeight;

            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.translate(pageXStart, pageYStart);
            graphics2D.scale(xRatio, yRatio);
            COMPONENT.paint(graphics2D);

            return Printable.PAGE_EXISTS;
        }
    }

}
