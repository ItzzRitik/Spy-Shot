import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.font.TextLayout;
import java.awt.Color.*;
import java.awt.geom.*;
import java.text.AttributedString;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
public class SpyShot
{
    static String path="Capture/";static int num=-1;
    public static void main()
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run() 
            {
                 try
                 {
                     Toolkit kit = Toolkit.getDefaultToolkit();final Dimension d = kit.getScreenSize();String im="SPY SHOT";
                     Robot robot = new Robot();Rectangle rect = new Rectangle(d);File file=new File((path+"SpyShot ("+num+").jpg"));
                     do
                     {
                         num++;if(num>0){file=new File((path+"SpyShot ("+num+").jpg"));}else{file=new File((path+"SpyShot.jpg"));}
                     }
                     while(file.exists());num=-1;
                     BufferedImage image = robot.createScreenCapture(rect);image.flush();Graphics2D g = image.createGraphics();
                     g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                     g.draw(rect);int w=(int)(d.getWidth()*1.79),h=(int)(d.getHeight()*4.7);FontRenderContext frc = g.getFontRenderContext();
                     //Font f = new Font("VDub", Font.PLAIN,40);
                     Font f = Font.createFont( Font.TRUETYPE_FONT, new FileInputStream("Resources/VDUB.TTF") );
                     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                     ge.registerFont(f);f=f.deriveFont(40.0f);
                     AttributedString as = new AttributedString(im);as.addAttribute(TextAttribute.FONT, f, 0,im.length());
                     AttributedCharacterIterator aci = as.getIterator();TextLayout tl = new TextLayout(aci, frc);
                     float sw = (float) tl.getBounds().getWidth();float sh = (float) tl.getBounds().getHeight();
                     Shape sha = tl.getOutline(AffineTransform.getTranslateInstance((w / 2 - sw / 2)-50, h * 0.2 + sh / 2));
                     g.setColor(new Color(200,200,200));g.setStroke(new BasicStroke(6));g.draw(sha);g.setColor(Color.black);g.fill(sha);
                     g.setColor(Color.white);g.drawRect(0,0,(int)d.getWidth(),(int)d.getHeight());g.setStroke(new BasicStroke(2));g.setColor(Color.black);
                     g.drawRect(2,2,(int)d.getWidth()-4,(int)d.getHeight()-4);
                     ImageIO.write(image, "JPEG",file);Thread.sleep(5000);
                     SwingUtilities.invokeLater(this);
                 }catch (Exception e){}
            }
        });
    }
}