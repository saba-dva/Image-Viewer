
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.awt.image.RescaleOp;

public class ImageViewerGUI extends JFrame implements ActionListener{
    JButton selectFileButton;
    JButton showImageButton;
    JButton resizeButton;
    JButton grayscaleButton;
    JButton brightnessButton;
    JButton closeButton;
    JButton showResizeButton;
    JButton showBrightnessButton;
    JButton backButton;
    JTextField widthTextField;
    JTextField heightTextField;
    JTextField brightnessTextField;
    String filePath = "/home/...";
    File file;
    JFileChooser fileChooser = new JFileChooser(filePath);
    int h = 900;
    int w = 1200;
    float brightenFactor = 1;

    ImageViewerGUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Image Viewer");
        this.setSize(700, 300);
        this.setResizable(true);
        mainPanel();
    }

    public void mainPanel(){
        // Create main panel for adding to Frame

        JPanel mainPanel = new JPanel();


        // Create Grid panel for adding buttons to it, then add it all to main panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 2));
        selectFileButton = new JButton("Choose Image");
        selectFileButton.addActionListener(this);
        showImageButton = new JButton("Show Image");
        showImageButton.addActionListener(this);
        brightnessButton = new JButton("Brightness");
        brightnessButton.addActionListener(this);
        grayscaleButton = new JButton("Gray scale");
        grayscaleButton.addActionListener(this);
        resizeButton = new JButton("Resize");
        resizeButton.addActionListener(this);
        closeButton = new JButton("Exit");
        closeButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.addActionListener(this);

        // Adding all buttons to Grid panel
        buttonsPanel.add(this.selectFileButton);
        buttonsPanel.add(this.showImageButton);
        buttonsPanel.add(this.brightnessButton);
        buttonsPanel.add(this.grayscaleButton);
        buttonsPanel.add(this.resizeButton);
        buttonsPanel.add(this.closeButton);

        // add Grid panel that contains 6 buttons to main panel
        mainPanel.add(buttonsPanel);

        // add main panel to our frame
        this.add(mainPanel);
        this.setVisible(true);
    }

    public void resizePanel(){
        JPanel resizePanel = new JPanel();
        resizePanel.setLayout(null);
        showResizeButton = new JButton("Show Result");
        showResizeButton.addActionListener(this);
        widthTextField = new JTextField();
        widthTextField.addActionListener(this);
        heightTextField = new JTextField();
        heightTextField.addActionListener(this);
        JLabel widthLabel = new JLabel("Width");
        widthLabel.setBounds(150,120,60,40);
        JLabel heightLabel = new JLabel("Height");
        heightLabel.setBounds(150,220,60,40);
        JLabel resize_SectionLabel = new JLabel("Resize Section");
        resize_SectionLabel.setBounds(260,50,200,40);
        backButton.setBounds(100,320,120,50);
        showResizeButton.setBounds(400,320,120,50);
        widthTextField.setBounds(240,120,150,60);
        heightTextField.setBounds(240,220,150,60);
        resizePanel.add(this.backButton);
        resizePanel.add(this.showResizeButton);
        resizePanel.add(this.widthTextField);
        resizePanel.add(this.heightTextField);
        resizePanel.add(widthLabel);
        resizePanel.add(heightLabel);
        resizePanel.add(resize_SectionLabel);
        this.add(resizePanel);
    }
    public void brightnessPanel(){
        JPanel brightnessPanel = new JPanel();
        brightnessPanel.setLayout(null);
        showBrightnessButton = new JButton("Result");
        showBrightnessButton.addActionListener(this);
        showBrightnessButton.setBounds(400,250,120,50);
        backButton.setBounds(100,250,120,50);
        backButton.addActionListener(this);
        brightnessTextField = new JTextField();
        brightnessTextField.setBounds(350,90,200,50);
        JLabel brightness1TextField = new JLabel("Enter f");
        brightness1TextField.setBounds(100,80,100,60);
        JLabel brightness2TextField = new JLabel("(must be between 0 and 1)");
        brightness2TextField.setBounds(100,110,200,60);
        brightnessPanel.add(brightness1TextField);
        brightnessPanel.add(brightness2TextField);
        brightnessPanel.add(this.showBrightnessButton);
        brightnessPanel.add(this.backButton);
        brightnessPanel.add(this.brightnessTextField);
        this.add(brightnessPanel);
    }

    public void chooseFileImage(){

       fileChooser.setAcceptAllFileFilterUsed(false);
       int option = fileChooser.showOpenDialog(ImageViewerGUI.this);
       if(option == JFileChooser.APPROVE_OPTION){
           file = fileChooser.getSelectedFile();
       }

    }
    public void showOriginalImage(){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(String.valueOf(file)).getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT));
        JLabel pictureLabel = new JLabel();
        pictureLabel.setIcon(imageIcon);
        tempPanel.add(pictureLabel);
        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }
    public void grayScaleImage(){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        try{
            BufferedImage image = ImageIO.read(file);
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            ColorConvertOp op = new ColorConvertOp(cs, null);
            ImageIcon imageIcon = new ImageIcon( op.filter(image,null));
            JLabel label = new JLabel();
            label.setIcon(imageIcon);
            tempPanel.add(label);
        }catch (Exception ex)
        {

        }
        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }
    public void showResizeImage(int w, int h){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        try {
            BufferedImage image = ImageIO.read(file);
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(w, h, Image.SCALE_DEFAULT));
            JLabel label = new JLabel();
            label.setIcon(imageIcon);
            tempPanel.add(label);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }
    public void showBrightnessImage(float f){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        try{
            BufferedImage image = ImageIO.read(file);
            RescaleOp op = new RescaleOp(f, 0, null);
            BufferedImage image1 = op.filter(image,null);
            ImageIcon imageIcon = new ImageIcon(image1 );
            JLabel label = new JLabel();
            label.setIcon(imageIcon);
            tempPanel.add(label);
        }catch (Exception ex)
        {

        }
        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==resizeButton){

            this.getContentPane().removeAll();
            this.resizePanel();
            this.revalidate();
            this.repaint();

        }else if(e.getSource()== showImageButton){

            this.showOriginalImage();

        }else if(e.getSource()==grayscaleButton){

            this.grayScaleImage();

        }else if(e.getSource()== showResizeButton){

        try{
            w = Integer.parseInt(widthTextField.getText());
            h = Integer.parseInt(heightTextField.getText());
        }catch (Exception ex){
            System.out.println("Something went wrong.");
        }

           this.showResizeImage(w,h);

        }else if(e.getSource()==brightnessButton){

            this.getContentPane().removeAll();
            this.brightnessPanel();
            this.revalidate();
            this.repaint();

        }else if(e.getSource()== showBrightnessButton){
            try{
                brightenFactor = Float.parseFloat(brightnessTextField.getText());
            }catch (Exception ex){
                System.out.println("Something went wrong.");
            }

            this.showBrightnessImage(brightenFactor);

        }else if(e.getSource()== selectFileButton){

            this.chooseFileImage();

        }else if(e.getSource()==closeButton){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        else if(e.getSource()==backButton){
            this.getContentPane().removeAll();
            this.mainPanel();
            this.revalidate();
            this.repaint();
        }
    }
}
