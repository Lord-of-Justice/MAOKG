package sample;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import javax.swing.*;

@SuppressWarnings("serial")
public class Main extends JPanel implements ActionListener {
    private static int maxWidth;
    private static int maxHeight;

    private int pictureWidth = 425;
    private int pictureHeight = 265;
    private int boredrForRotating = (pictureWidth - pictureHeight) / 2;

    Timer timer;

    private double angle;
    private double dx = 1;
    private double tx = 1;
    private double dy = 0;
    private double ty = 0;

    public Main() {
        timer = new Timer(10, this);
        timer.start();
    }

    // Всі дії, пов'язані з малюванням, виконуються в цьому методі
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // for better rendering
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);

        // set bg color
        g2d.setBackground(new Color(0, 128, 128));
        g2d.clearRect(0,0, maxWidth, maxHeight);

        drawBorder(g2d);
        g2d.translate(10, boredrForRotating);

        // Перетворення для анімації руху.
        g2d.translate(tx, ty);

        // draw many points
        g2d.setColor(Color.green);
        double points[][] = {
                { 65, 108 }, { 203, 2}, { 411, 96 }, { 279, 150 },
                { 321, 241 }, { 135, 253 }, { 65, 108 }
        };
        GeneralPath mainPolygon = new GeneralPath();
        mainPolygon.moveTo(points[0][0], points[0][1]);
        for (int k = 1; k < points.length; k++)
            mainPolygon.lineTo(points[k][0], points[k][1]);
        mainPolygon.closePath();

        //Animation of turn
        g2d.rotate(angle,  mainPolygon.getBounds2D().getCenterX(), mainPolygon.getBounds2D().getCenterY());
        g2d.fill(mainPolygon);

        // draw triangle
        GradientPaint gp = new GradientPaint(5, 10,
                new Color(255,255,0), 15, 5, new Color(255,0,0), true);
        g2d.setPaint(gp);
        Polygon triangle = new Polygon();
        triangle.addPoint(386, 133);
        triangle.addPoint(335, 216);
        triangle.addPoint(302, 152);
        g2d.fillPolygon(triangle);

        // draw rectangles
        gp = new GradientPaint(0, 10,
                new Color(0, 128, 0), 5, 5, new Color(0,0,0), true);
        g2d.setPaint(gp);
        g2d.fillRect(156, 79, 13, 12);
        g2d.fillRect(135, 155, 13, 12);

        // draw lines
        g2d.setColor(Color.black);
        drawLine(g2d, 5, BasicStroke.CAP_BUTT, new int[] {65, 108, 279, 150});
        drawLine(g2d, 12, BasicStroke.CAP_ROUND, new int[] {16, 8, 101, 82});
        drawLine(g2d, 12, BasicStroke.CAP_ROUND, new int[] {3, 220, 103, 182});
    }

    private void drawLine(Graphics2D g2d, int width, int bs_cap,  int[] points){
        BasicStroke bs = new BasicStroke(width, bs_cap, BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs);
        g2d.drawLine(points[0], points[1], points[2], points[3]);
    }
    private void drawBorder(Graphics2D graphics2D) {
        BasicStroke bs = new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
        graphics2D.setStroke(bs);
        GradientPaint gp = new GradientPaint(5, 20,
                new Color(33, 233, 223), 15, 5, Color.WHITE, true);
        graphics2D.setPaint(gp);
        graphics2D.drawRect(5, 5, maxWidth - 10, maxHeight - 10);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new Main());
        frame.setVisible(true);

        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (tx < 0) {
            dy = -1;
            dx = 0;
        } else if (tx > maxWidth - pictureWidth && maxWidth != 0) {
            dy = 1;
            dx = 0;
        }

        if (ty < 0) {
            dx = 1;
            dy = 0;
            ty += 2;
        } else if (ty > maxHeight - pictureHeight - boredrForRotating * 2  && maxHeight != 0) {
            dx = -1;
            dy = 0;
            ty -= 2;
        }

        angle += 0.01;
        tx += dx;
        ty += dy;

        repaint();
    }
}
