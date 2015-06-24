package random;


import java.awt.Graphics;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.RenderingHints;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import java.awt.*;


public class ImageCropper
{

    public static void main(String[] args) throws IOException
    {
        final String source = "/Users/machaval/labs/repos/random/resources/AJAX-Endpoint-48x32.png";
        final File sourceFile = new File(source);

        final BufferedImage read = ImageIO.read(sourceFile);


        final int x = 18;
        final int y = 0;
        final int w = read.getHeight();
        final int h = read.getHeight();

        System.out.println("h = " + h);
        final BufferedImage chooped = read.getSubimage(x, y, w, h);

        BufferedImage out = new BufferedImage(w, h, read.getType());
        Graphics2D g = (Graphics2D) out.getGraphics();
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        g.drawImage(chooped, 0, 0, chooped.getHeight(), chooped.getHeight(), null);
        g.setBackground(Color.WHITE);
        Area cutOutArea = new Area(new Rectangle(0, 0, chooped.getWidth(), chooped.getHeight()));
        cutOutArea.subtract(new Area(new Ellipse2D.Float(0, 0, w, w)));
        // Set the fill color to an opaque color
        g.setColor(Color.WHITE);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Clear the cut out area
        g.fill(cutOutArea);
        g.dispose();
        ImageIO.write(out, "png", new File("/Users/machaval/labs/repos/random/resources/AJAX-Endpoint-48x32-out.png"));

    }


}
