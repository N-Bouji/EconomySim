import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
  
public class MyFrame extends Frame {
    public MyFrame()
    {
        setVisible(true);
        setSize(600, 400);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
    }
    public void paint(Graphics g)
    {
        g.drawRect(100, 100, 100, 50);
    }
  
    public static void main(String[] args)
    {
        new MyFrame();
    }
}
