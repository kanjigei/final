/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nea.projects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import static nea.projects.editor.hsb;
import static nea.projects.editor.mysa;
import static nea.projects.editor.sdf;

/**
 *
 * @author 15choasi
 */
public class window extends JComponent implements MouseWheelListener, MouseMotionListener, ActionListener {

    private static int Max_iter = 400;
    private static float Scale = 250;
    private double zoomfactor = 1230;
    private double prevzoomfactor = 1;
    static BufferedImage Image;
    int mf = 35;
    public static int WIDTH = 1100;
    public static int HEIGHT = 600;
    
    private double xOffset;
    private double yOffset;
    private int xDiff;
    private int yDiff;
    private Point startPoint;
    public static float hueoffset = 0.1f;
    private static Timer timer;
    private boolean newImage = true;
    public static JButton J;
    public static JFrame frame;
    private static double top = -1.0;
    private static double left = -2.3;
    private static double zoom = 1.0 / 280.0;
    private static JLabel sdfs;
    public static float brightness;
    public static float saturation;
    public static Color c = Color.decode("0xf90300");
    public static int counter = 0;
    
    public static float hue;

    private boolean zoomer, dragger, released;

    public window() {

        Image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        timer = new Timer(1, this);
        

        J = new JButton("show Pallete editor");
        J.setActionCommand("editor");
        ActionListener l = (ActionEvent ae) -> {
            String action = ae.getActionCommand();
            if (action.equals("editor")) {
                new editor().setVisible(true);
            }
        };
        J.addActionListener(l);
        J.addKeyListener(keyListener());
        

//        JFrame frame = new JFrame("mandelbrot set");
//        
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);
//        frame.setContentPane(new window());
//        frame.getContentPane().add(ls);
//        frame.getContentPane().add(J,BorderLayout.PAGE_END);
//       
//        Component add = frame.getContentPane().add(this);
//        frame.getContentPane().add(this);
//        
//        
//        
//        frame.pack();
//        frame.setResizable(true);
//        frame.setVisible(true);
        // Image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        /* for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                zx = zy = 0;
                cX = (x - 500) / zoom;
                cY = (y - 300) / zoom;
                int iter = Max_iter;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                    
                }
                Image.setRGB(x, y, iter | (iter << mf));
            } 
        }*/
    }
//    public static void main(String[] args) {
//       
//        EventQueue.invokeLater(() -> {  
//        JFrame frame = new JFrame("mandelbrot set");
//        
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);
//        frame.getContentPane().add(new window());
//        frame.getContentPane().add(ls);
//        frame.getContentPane().add(J,BorderLayout.PAGE_END);
//       
//        Component add = frame.getContentPane().add(this);
//        frame.getContentPane().add(this);
//        
//        
//        
//        frame.pack();
//        frame.setResizable(true);
//        frame.setVisible(true);
//        
//        
//        });
//      }

    public static MouseListener mouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON1) {
                    
                    
                    left = (event.getX() - WIDTH / 8.0) * zoom + left;
                    top = (event.getY() - HEIGHT / 8.0) * zoom + top;
                    zoom = zoom / 4.0;
                    counter = counter+1;
                    
                    switch (counter) {
                        case 25 -> Max_iter = 500000;
                        case 24 -> Max_iter = 370000;
                        case 23 -> Max_iter = 270000;
                        case 22 -> Max_iter = 200000;
                        case 21 -> Max_iter = 150000;
                        case 20 -> Max_iter = 125000;
                        case 19 -> Max_iter = 110000;
                        case 18 -> Max_iter = 96000;
                        case 17 -> Max_iter = 83000;
                        case 16 -> Max_iter = 71000;
                        case 15 -> Max_iter = 60000;
                        case 14 -> Max_iter = 50000;
                        case 13 -> Max_iter = 41000;
                        case 12 -> Max_iter = 33000;
                        case 11 -> Max_iter = 26000;
                        case 10 -> Max_iter = 20000;
                        case 9 -> Max_iter = 15000;
                        case 8 -> Max_iter = 9500;
                        case 6 -> Max_iter = 7000;
                        case 5 -> Max_iter = 5000;
                        case 3 -> Max_iter = 3000;
                        case 2 -> Max_iter = 2000;
                        case 1 -> Max_iter = 1025;
                        default -> {
                        }
                    }
                }else if(event.getButton() == MouseEvent.BUTTON3){
                    left = (event.getX() - WIDTH) * zoom + left;
                    top = (event.getY() - HEIGHT) * zoom + top;
                    zoom = zoom * 2.0;
                    counter = counter-1;
                    
                    switch (counter) {
                        case 25 -> Max_iter = 500000;
                        case 24 -> Max_iter = 370000;
                        case 23 -> Max_iter = 270000;
                        case 22 -> Max_iter = 200000;
                        case 21 -> Max_iter = 150000;
                        case 20 -> Max_iter = 125000;
                        case 19 -> Max_iter = 110000;
                        case 18 -> Max_iter = 96000;
                        case 17 -> Max_iter = 83000;
                        case 16 -> Max_iter = 71000;
                        case 15 -> Max_iter = 60000;
                        case 14 -> Max_iter = 50000;
                        case 13 -> Max_iter = 41000;
                        case 12 -> Max_iter = 33000;
                        case 11 -> Max_iter = 26000;
                        case 10 -> Max_iter = 20000;
                        case 9 -> Max_iter = 15000;
                        case 8 -> Max_iter = 9500;
                        case 6 -> Max_iter = 7000;
                        case 5 -> Max_iter = 5000;
                        case 3 -> Max_iter = 3000;
                        case 2 -> Max_iter = 2000;
                        case 1 -> Max_iter = 1025;
                        default -> {
                        }
                    }
                }
                
                renderMandelbrotset();
                System.out.println(counter);
                System.out.println("max iter: "+ Max_iter);
            }
        };
    }
    
    public static KeyListener keyListener(){
        return new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent event){
                if(event.getKeyCode() == KeyEvent.VK_R){
                    top = -1.0;
                    left = -2.3;
                    zoom = 1/280.0;
                    counter = 0;
                    Max_iter = 400;
                }else if(event.getKeyCode() == KeyEvent.VK_I){
                    Max_iter = Max_iter + 5000;
                }else if(event.getKeyCode() == KeyEvent.VK_X){
                    Max_iter = Max_iter + 400000;
                    
                }
                System.out.println(Max_iter);
                renderMandelbrotset();
            }
        };
    }
    

    @Override
    public void addNotify() {
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        timer.start();
    }

    private void repaint(boolean newImage) {
        this.newImage = newImage;
        repaint();
    }

    public int rint(double d) {
        return (int) Math.rint(d); // half even
    }

    private static Executor executor = Executors.newSingleThreadExecutor();

    public static void renderMandelbrotset() {

        executor.execute(() -> {
        
            printThreadInfo();
            IntStream.range(0, HEIGHT).parallel().forEach((int y) -> {
                double ci = y * zoom + top;
                
//                if (mysa != null) {

                    for (int x = 0; x < WIDTH; ++x) {
                        double cr = x * zoom + left;

                        double zr = 0.0;
                        double zi = 0.0;
                        int colour = 0x000000; // paint The Mandelbrot Set black
                        int i;
                        for (i = 0; i < Max_iter; ++i) {
                            double zrzr = zr * zr;
                            double zizi = zi * zi;
                            if (zrzr + zizi >= 4) {
                                
                            try{
                                hsb = Color.RGBtoHSB(sdf.getRed(), sdf.getGreen(), sdf.getBlue(), null);
                
                                hue = hsb[0];
                                saturation = hsb[1];
                                brightness = hsb[2];

                                colour = Color.HSBtoRGB((float)i/Max_iter+ hue,  saturation, brightness);
                                
                            }catch(Exception e){
                                hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
                
                                hue = hsb[0];
                                saturation = hsb[1];
                                brightness = hsb[2];

                                colour = Color.HSBtoRGB((float)i/Max_iter+ hue,  saturation, brightness);
                                
                            }
                            
                            break;
                            
                            }
                            zi = 2.0 * zr * zi + ci;
                            zr = zrzr - zizi + cr;
                        }
                        Image.setRGB(x, y, colour);
                    }
//                } else {
//                    for (int x = 0; x < WIDTH; ++x) {
//                        double cr = x * zoom + left;
//
//                        double zr = 0.0;
//                        double zi = 0.0;
//                        int colour = 0x000000; // paint The Mandelbrot Set black
//                        int i;
//                        for (i = 0; i < Max_iter; ++i) {
//                            double zrzr = zr * zr;
//                            double zizi = zi * zi;
//                            if (zrzr + zizi >= 4) {
//                               
//                            hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
//                
//                            hue = hsb[0];
//                            saturation = hsb[1];
//                            brightness = hsb[2];
//                   
//                            colour = Color.HSBtoRGB((float)i/Max_iter+ hue,  saturation, brightness);
////                              colour = Color.HSBtoRGB(((float)i / Max_iter + hueoffset)%1f, 0.7f, 1);
//                            }
//                            zi = 2.0 * zr * zi + ci;
//                            zr = zrzr - zizi + cr;
//                        }
//                        Image.setRGB(x, y, colour);
//                    }
//                }
                frame.repaint();
            });
        });
    }

//    public static int calculatePoint(float x , float y){
//        
//        float cx = x;
//        float cy = y;
//        
//        int i = 0;
//        
//       
//        
//        for(;i<Max_iter;i++){
//            
//            float nx = x*x - y*y + cx;
//            float ny = 2 * x* y +cy;
//            
//            x = nx;
//            y = ny;
//            
//            if(x*x + y*y > 4) break;
//        }
//        
//        if(i == Max_iter) return 0x000000;
//        return Color.HSBtoRGB(((float)i / Max_iter + hueoffset)%1f, 0.7f, 1);
//        
//    }
    private static void printThreadInfo() {
        System.out.println(Thread.currentThread());
            Arrays.stream(Thread.currentThread().getStackTrace())
                .skip(2)
                .limit(4)
                .forEach(System.out::println);
        System.out.println();
    }

//    private static int PALETTE() {
//     
//       
//    };
    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        Graphics2D g2 = (Graphics2D) g;
//        if (zoomer) {
//            AffineTransform at = new AffineTransform();
//
//            double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
//            double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();
//
//            double zoomDiv = zoomfactor / prevzoomfactor;
//
//            xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
//            yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;
//
//            at.scale(zoomfactor, zoomfactor);
//            prevzoomfactor = zoomfactor;
//            g2.transform(at);
//            zoomer = false;
//        }
//        if (dragger) {
//            AffineTransform at = new AffineTransform();
//            at.translate(xOffset + xDiff, yOffset + yDiff);
//            at.scale(zoomfactor, zoomfactor);
//            g2.transform(at);
//
//            if (released) {
//                xOffset += xDiff;
//                yOffset += yDiff;
//                dragger = false;
//            }
//
//        }

        g.drawImage(Image, 0, 0, null);

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
//        zoomer = true;
//
//        //Zoom in
//        if (e.getWheelRotation() < 0) {
//            zoomfactor *= 1.1;
//            repaint();
//        }
//        if (e.getWheelRotation() > 0) {
//            zoomfactor /= 1.1;
//            repaint();
//        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {
//        Point curPoint = e.getLocationOnScreen();
//        xDiff = curPoint.x - startPoint.x;
//        yDiff = curPoint.y - startPoint.y;
//
//        dragger = true;
//
//        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // hueoffset += 0.5f;
        //       renderMandelbrotset();
        repaint();
        timer.stop();

    }

}
