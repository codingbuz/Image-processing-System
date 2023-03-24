import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.*;
import java.awt.color.ColorSpace;
import javax.swing.border.TitledBorder;
//import java.awt.image.CropImageFilter;
//import java.awt.image.FilteredImageSource;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
//---------------------- Set a canvas Class ----------------------------------------
class imageLoad1 extends Canvas
{
static int imgheight, imgwidth;
String imgadd1 = ScrolledPane.add;
int x=350, y=0;
static int ip;
Float Flag =0f;
int imgFlag =1;
Image img;
 Insets insets;
imageLoad1()
	{
	addMouseMotionListener(new MouseMotionHandler());
	}

 public void paint (Graphics g)
 {
 if (imgFlag != 0){g.drawImage(img,x,y,imgwidth,imgheight, this);}
if(Flag >0)
{
Graphics2D g2d=(Graphics2D)g;
g2d.translate(450, 0); // Translate the center of our coordinates.
g2d.rotate(Flag);  // Rotate the image by  radian.  (1 radian = 1degree * ^/180)
g2d.drawImage(img, 0, 0, imgwidth, imgheight,this);
}
}
 void setImage (Image img2)
	{//-------- Load a Image-----
this.img = img2;
	Toolkit tool = Toolkit.getDefaultToolkit();
	img = tool.getImage(imgadd1);
		fitImage(img);
	}

void rotate(Float dgree)
{ // ----- Rotate-------
Flag = dgree;
imgFlag = 0;
repaint();
}
//----------------------------------Fit to the Screen----------------------
	 void fitImage (Image img2)
	{
	img = null;
	this.img = img2;
	if (ScrolledPane.width<500 && ScrolledPane.height<500)
	repaint();
	else {if (ScrolledPane.height>500 && ScrolledPane.height>ScrolledPane.width)
		{
		 ip = (500 *100)/ ScrolledPane.height;
		  imgheight= ip* ScrolledPane.height/100;
		  imgwidth = ip*ScrolledPane.width/100;
			}
	else if (ScrolledPane.width>500 && ScrolledPane.width>ScrolledPane.height)
			{	 ip = (500 *100)/ ScrolledPane.width;
			  imgheight= ip* ScrolledPane.height/100;
			  imgwidth = ip*ScrolledPane.width/100;
			}
repaint();
	}}
class MouseMotionHandler extends MouseMotionAdapter {
public void mouseDragged(MouseEvent e) {
x = e.getX();
y = e.getY();

repaint();
}
public void mouseMoved(MouseEvent e) {}
}
//-------Zoom--------
void zoom(int cc)
{
imgheight = imgheight+ cc * imgheight/100;
imgwidth = imgwidth+ cc * imgwidth/100;
	repaint();
}
}// canvas close
//--------------- --------     Main Frame -----------------------
 class Frame1 extends JFrame
{
	int scrwidth, scrheight, value;
	JSlider slider;    // jslider declare
	JTextField  tb;
	String str;
Image ii;				// tempray local image obj;
imageLoad1 canvas1[]; // object array
static int fobjcounter =0;
	Label l1=new Label("-.-.-.-.-.-.-.-.-.-.-.-.-.-..-.-.-.--.-.-WELCOME! AV IMAGE EDITING & PROCESS IS EASSY WAY OF IMAGE EDITING WITH EFFECTS.-.-.-.-.-.-.-.-.-.-.--.-.-.-.-.-.-.-.-.-.-.-");

	JToolBar jt = new JToolBar("Toolbar", JToolBar.HORIZONTAL);
	JButton b1=new JButton(new ImageIcon("open.jpg"));JButton b2=new JButton(new ImageIcon("save.jpg"));
	JButton b3=new JButton(new ImageIcon("cut.jpg"));JButton b4=new JButton(new ImageIcon("copy.jpg"));
	JButton b5= new JButton(new ImageIcon("zoom.jpg"));

		MenuBar mb=new MenuBar();

		Menu file=new Menu("File");
			MenuItem open=new MenuItem("Open");MenuItem save=new MenuItem("Save");
			MenuItem saveas=new MenuItem("Save As");MenuItem print=new MenuItem("Print");MenuItem end=new MenuItem("Exit");

		Menu edit=new Menu("Edit");
			MenuItem undo =new MenuItem("Undo");MenuItem reundo=new MenuItem("Reundo");
			MenuItem cut=new MenuItem("Cut");MenuItem copy=new MenuItem("Copy");MenuItem paste=new MenuItem("Paste");

		Menu image=new Menu("Image");
			MenuItem crop = new MenuItem("Crop");MenuItem duplicate = new MenuItem("Duplicate");MenuItem rename = new MenuItem("Rename");
			MenuItem scale = new MenuItem("Scale");MenuItem translate = new MenuItem("Translate");MenuItem rotate = new MenuItem("Rotate");

	//		MenuItem rotate90 = new MenuItem("Rotate90");//MenuItem zoomout = new MenuItem("Zoom--");

		Menu adjestment = new Menu("Adjestment");
			MenuItem brightness = new MenuItem("Brightness");MenuItem contrast = new MenuItem("Contrast");MenuItem windowlevel = new MenuItem("Window/level");
			MenuItem colorbalance = new MenuItem("Color Balance");MenuItem size = new MenuItem("Size");MenuItem convertsize = new MenuItem("Convert Size");

		Menu process=new Menu("Process");
			MenuItem grayscale = new MenuItem("Grayscale");
		Menu plugins=new Menu("Plugins");
			MenuItem filter = new MenuItem("Filter");
	Frame1( )
	{		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		canvas1=new imageLoad1[500];
		setVisible(true);

		jt.add(b1);jt.add(b2);jt.add(b3);jt.add(b4);
		this.add(jt,BorderLayout.NORTH);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		slider = new JSlider(0,200);
		slider.setValue(0);
		tb = new JTextField("000");
		slider.addChangeListener(new ChangeListener(){public void stateChanged(ChangeEvent e){
		int value = slider.getValue();String str = Integer.toString(value);tb.setText(str);
		zooming(value);}});
		tb.setBounds(380,100,100,30);this.add(tb);
		slider.setBounds(20,500,300,30);this.add(slider);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize= kit.getScreenSize();
		 scrwidth = screenSize.width;
		scrheight= screenSize.height;
		this.setSize(scrwidth,scrheight);
			System.out.println("Value of h="+scrheight);System.out.println("Value of w="+scrwidth);

		setTitle("AV IMAGE EDITING & PROCESS"+"@"+imageLoad1.ip);
		System.out.println(imageLoad1.ip);
		this.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){System.exit(0);}});

	this.addKeyListener(new KeyListener(){public void keyPressed(KeyEvent e){}
	public void keyReleased(KeyEvent e){}public void keyTyped(KeyEvent e){}  });

		l1.setBounds(10,50,600,30);this.add(l1);
		l1.setFont(new Font("Arial",1,18));
		Thread1 t1=new Thread1();
		t1.start();

		mb.add(file);
			// -*-*-*-*-**-*-*-*-*-*-*-*-*for File Menu-*-*-*-*-*-**-*-*-*-*-*-*-**-*-*-*-*-**-
		file.add(open);file.addSeparator();file.add(save);file.add(saveas);file.addSeparator();file.add(print);file.addSeparator();file.add(end);
		end.setShortcut(new MenuShortcut('X'));
		open.setShortcut(new MenuShortcut('O'));
		save.setShortcut(new MenuShortcut('S'));
		this.setMenuBar(mb);
		end.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){end_Click();}});
		open.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){open_Click();}});
		save.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){save_Click();}});
		b1.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){open_Click();}});
		b2.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){save_Click();}});

			//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- For Edit Menu *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
		mb.add(edit);
		edit.add(undo);edit.add(reundo);edit.addSeparator();edit.add(cut);edit.add(copy);edit.add(paste);
		  undo.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){undo_Click();}});
		  // for Image Menu
		mb.add(image);
		image.add(crop);image.add(duplicate);image.add(rename);image.add(scale);image.add(translate);image.add(rotate);//rotate.add(rotate90);
		crop.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){crop_Click();}});
		rotate.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){rotate_Click();}});

		  //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- For Ajestment *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
		mb.add(adjestment);	adjestment.add(brightness);	adjestment.add(contrast);adjestment.add(windowlevel);adjestment.add(colorbalance);adjestment.add(size);adjestment.add(convertsize);
brightness.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){colorapp_Click();}});
contrast.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){colorapp_Click();}});
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- for Process *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
		mb.add(process); process.add(grayscale);process.add(filter);
		filter.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){filter_Click();}});
		grayscale.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){gray_Click();}});

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- for Plugins *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
		mb.add(plugins);
}// frame1 cnst. Close

		class Thread1 extends Thread
		{
			public void run()
			{
			while(true)
				{String s=l1.getText();String s1=s.substring(0,1);String s2=s.substring(1);String s3=s2+s1;
				l1.setText(s3); try{Thread.sleep(200);}catch(Exception eee){}
				}
			}
	}
//.-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-    EXIT   -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
		void end_Click(){System.exit(0);}
		void exchange()
		{
			ii = ScrolledPane.image1;
			System.out.println("Avinash");
				canvas1[ScrolledPane.objcnt]=new imageLoad1();
				canvas1[ScrolledPane.objcnt].setBounds(0,0,scrwidth,scrheight);
				this.add(canvas1[ScrolledPane.objcnt]);
				canvas1[ScrolledPane.objcnt].setBackground(Color.RED);
				//this.add(canvas1);
				canvas1[ScrolledPane.objcnt].fitImage(ii);
				setTitle("AV IMAGE EDITING & PROCESS-"+"@");
		}
//.-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-OPEN A IMAGE-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

				void open_Click()
					{
						ScrolledPane mainFrame	= new ScrolledPane();
						setVisible(false);
					}

void save_Click()
{
				FileDialog fd=new FileDialog(this,"Save As...",1);
				fd.setVisible(true);
			String	addloc = (fd.getDirectory() + fd.getFile());
			BufferedImage bi = new BufferedImage(ii.getWidth(this), ii
			        .getHeight(this), BufferedImage.TYPE_INT_RGB);
			  Graphics2D   big = bi.createGraphics();
    big.drawImage(ii, 0, 0, this);
 writeImage(bi, addloc, "jpg");
}
 void writeImage( BufferedImage imgsave, String fileLocation,String extension) {
try {
BufferedImage bi = imgsave;
File outputfile = new File(fileLocation);
ImageIO.write(imgsave, extension, outputfile);
} catch (IOException e) {
e.printStackTrace();
}
}

//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*ZOOM-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-**-*-*-*--*-*
void zooming (int oo)
{
int x = ScrolledPane.objcnt;
ScrolledPane.objcnt++;
int ee = imageLoad1.imgheight;
int ff = imageLoad1.imgwidth;
System.out.println(oo);
imageLoad1.imgheight = imageLoad1.imgheight+oo * ee/100;
imageLoad1.imgwidth = imageLoad1.imgwidth+oo * ff/100;

canvas1[ScrolledPane.objcnt]=new imageLoad1();
canvas1[ScrolledPane.objcnt].setBounds(0,80,scrwidth,scrheight);
this.add(canvas1[ScrolledPane.objcnt]);
canvas1[ScrolledPane.objcnt].setBackground(Color.WHITE);
canvas1[ScrolledPane.objcnt].setImage(ii);
canvas1[ScrolledPane.objcnt].zoom(oo);
canvas1[x].hide();
}
//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* ROTATE -*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-**-*-*-*--*-*
void undo_Click()
{
int x = ScrolledPane.objcnt;
ScrolledPane.objcnt--;
canvas1[ScrolledPane.objcnt].show();
canvas1[x].hide();
}

//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* ROTATE -*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-**-*-*-*--*-*
void rotate_Click()
{int x = ScrolledPane.objcnt;
ScrolledPane.objcnt++;
Float dpass = 1.5714f;
				canvas1[ScrolledPane.objcnt]=new imageLoad1();
				canvas1[ScrolledPane.objcnt].setBounds(0,80,scrwidth,scrheight);
				this.add(canvas1[ScrolledPane.objcnt]);
canvas1[ScrolledPane.objcnt].setBackground(Color.WHITE);
canvas1[ScrolledPane.objcnt].setImage(ii);
canvas1[ScrolledPane.objcnt].rotate(dpass);
canvas1[x].hide();
}
//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* SET EFFECTS -*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-**-*-*-*--*-*
void seteffect(Image xy)
{ ii = xy;
ScrolledPane.objcnt++;
				 canvas1[ScrolledPane.objcnt]=new imageLoad1();
				canvas1[ScrolledPane.objcnt].setBounds(0,80,scrwidth,scrheight);
				this.add(canvas1[ScrolledPane.objcnt]);
canvas1[ScrolledPane.objcnt].setBackground(Color.WHITE);
canvas1[ScrolledPane.objcnt].fitImage(ii);
}

//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*  Brightness and Cantrast   -*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-**-*-*-*--*-*

void colorapp_Click()
{
ColorApp brtness = new ColorApp();
brtness.show();
}
//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*  Crop   -*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-**-*-*-*--*-*
void crop_Click ()
{System.out.println("Avin");
  Insets insets = null;
}
//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*  GRAYSCALE -*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-**-*-*-*--*-*
void gray_Click()
{
ColorConvertDemo ccobj = new ColorConvertDemo();
ccobj.show(true);
}

//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*  Filters -*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-**-*-*-*--*-*
void filter_Click()
{
ConvolveApp objj=new ConvolveApp();
objj.show();
}


}// main class close


class ScrolledPane extends 	JFrame
{
	public static Image image1;
		public static int height, width;
		public static String add;
		static int objcnt =1;
	private		JScrollPane scrollPane;

Frame1 bewajaha;

void key_Click()
{

	Frame1 fobj = new Frame1();
	fobj.show();
	fobj.exchange();
	bewajaha.hide();
}
ScrolledPane()// cnstr of class
{
		this.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){scrollPane.hide();}});

	this.addKeyListener(new KeyListener(){public void keyPressed(KeyEvent e){}
public void keyReleased(KeyEvent e){}public void keyTyped(KeyEvent e){ key_Click();}  });

	setBounds(200,150, 400, 500 );
		//setBackground( Color.RED );
		JPanel topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );
				FileDialog fd=new FileDialog(this,"Open As...",0);
				fd.setVisible(true);
				Toolkit tool = Toolkit.getDefaultToolkit();
				add = (fd.getDirectory() + fd.getFile());
				System.out.println(add);
				image1 = tool.getImage(add);
						MediaTracker mTracker = new MediaTracker(this);
						mTracker.addImage(image1,1);
				try{	mTracker.waitForID(1); }catch(InterruptedException e){System.out.println("Exception ="+e);}
				width = image1.getWidth(null);height = image1.getHeight(null);
				System.out.println("The width of image: " + width);System.out.println("The height of image: " + height);
	//			imageLoad1 faltu = new imageLoad1();

		Icon image = new ImageIcon( add );
		JLabel label = new JLabel( image );
		setTitle( add );

		// Create a tabbed pane
		scrollPane = new JScrollPane();
		scrollPane.getViewport().add( label );
		topPanel.add( scrollPane, BorderLayout.CENTER );
			bewajaha = new Frame1();
		setVisible( true );
		}
}


//-------------------------------------- Brigtness and contrace-----------------------------

class ColorApp extends JFrame {

 DisplayPanel displayPanel;

 JButton brightenButton, darkenButton, contrastIncButton, contrastDecButton,
      setButton, resetButton;

  public ColorApp() {
    super("Brightness and Cantrast");
    Container container = getContentPane();

    displayPanel = new DisplayPanel();

    container.add(displayPanel);

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(3, 2));
    panel.setBorder(new TitledBorder("Click a Button to Perform the Associated Operation and Reset..."));

    brightenButton = new JButton("Brightness >>");
    brightenButton.addActionListener(new ButtonListener());
    darkenButton = new JButton("Darkness >>");
    darkenButton.addActionListener(new ButtonListener());
    contrastIncButton = new JButton("Contrast >>");
    contrastIncButton.addActionListener(new ButtonListener());
    contrastDecButton = new JButton("Contrast <<");
    contrastDecButton.addActionListener(new ButtonListener());
    setButton = new JButton("Set");
    setButton.addActionListener(new ButtonListener());
    resetButton = new JButton("Reset");
    resetButton.addActionListener(new ButtonListener());

    panel.add(brightenButton);
    panel.add(darkenButton);
    panel.add(contrastIncButton);
    panel.add(contrastDecButton);
    panel.add(setButton);
    panel.add(resetButton);

    container.add(BorderLayout.SOUTH, panel);

    setSize(displayPanel.getWidth(), displayPanel.getHeight() + 25);
    show();
  }

  class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      JButton button = (JButton) e.getSource();

      if (button.equals(brightenButton)) {
        displayPanel.brightenLUT();
        displayPanel.applyFilter();
        displayPanel.repaint();
      } else if (button.equals(darkenButton)) {
        displayPanel.darkenLUT();
        displayPanel.applyFilter();
        displayPanel.repaint();
      } else if (button.equals(contrastIncButton)) {
        displayPanel.contrastIncLUT();
        displayPanel.applyFilter();
        displayPanel.repaint();
      } else if (button.equals(contrastDecButton)) {
        displayPanel.contrastDecLUT();
        displayPanel.applyFilter();
        displayPanel.repaint();
      } else if (button.equals(setButton)) {
		displayPanel.returnimg();

      } else if (button.equals(resetButton)) {
        displayPanel.reset();
        displayPanel.repaint();
      }
    }
  }
}

class DisplayPanel extends JPanel {
  Image displayImage;

  BufferedImage bi;

  Graphics2D big;

  LookupTable lookupTable;

  DisplayPanel() {
    setBackground(Color.black); // panel background color
    loadImage();
    setSize(500,500);
    createBufferedImage();
  }

  public void loadImage() {
    displayImage = Toolkit.getDefaultToolkit().getImage(ScrolledPane.add);
    if (displayImage.getWidth(this) == -1) {
      System.out.println("No jpg file");
      System.exit(0);
    }
  }

  public void createBufferedImage() {
    bi = new BufferedImage(displayImage.getWidth(this), displayImage
        .getHeight(this), BufferedImage.TYPE_INT_ARGB);
    big = bi.createGraphics();
    big.drawImage(displayImage, 0, 0,500,500, this);
}

 void returnimg(){
Frame1 bewajaha1 = new Frame1();
setVisible( true );
try {
File outputfile = new File("bon.jpg");
ImageIO.write(bi, "JPG", outputfile);
} catch (IOException e) {e.printStackTrace();}
	Image it;
	Toolkit tool = Toolkit.getDefaultToolkit();
	it = tool.getImage("bon.jpg");
bewajaha1.seteffect(it);
}

 public void brightenLUT() {
    short brighten[] = new short[256];
    for (int i = 0; i < 256; i++) {
      short pixelValue = (short) (i + 10);
      if (pixelValue > 255)
        pixelValue = 255;
      else if (pixelValue < 0)
        pixelValue = 0;
      brighten[i] = pixelValue;
    }
    lookupTable = new ShortLookupTable(0, brighten);
  }

  public void darkenLUT() {
    short brighten[] = new short[256];
    for (int i = 0; i < 256; i++) {
      short pixelValue = (short) (i - 10);
      if (pixelValue > 255)
        pixelValue = 255;
      else if (pixelValue < 0)
        pixelValue = 0;
      brighten[i] = pixelValue;
    }
    lookupTable = new ShortLookupTable(0, brighten);
  }

  public void contrastIncLUT() {
    short brighten[] = new short[256];
    for (int i = 0; i < 256; i++) {
      short pixelValue = (short) (i * 1.2);
      if (pixelValue > 255)
        pixelValue = 255;
      else if (pixelValue < 0)
        pixelValue = 0;
      brighten[i] = pixelValue;
    }
    lookupTable = new ShortLookupTable(0, brighten);
  }

  public void contrastDecLUT() {
    short brighten[] = new short[256];
    for (int i = 0; i < 256; i++) {
      short pixelValue = (short) (i / 1.2);
      if (pixelValue > 255)
        pixelValue = 255;
      else if (pixelValue < 0)
        pixelValue = 0;
      brighten[i] = pixelValue;
    }
    lookupTable = new ShortLookupTable(0, brighten);
  }

  public void reverseLUT() {
    byte reverse[] = new byte[256];
    for (int i = 0; i < 256; i++) {
      reverse[i] = (byte) (255 - i);
    }
    lookupTable = new ByteLookupTable(0, reverse);
  }

  public void reset() {
    big.setColor(Color.black);
    big.clearRect(0, 0, 500, 500);
    big.drawImage(displayImage, 0, 0,500,500, this);
  }

    public void applyFilter() {
    LookupOp lop = new LookupOp(lookupTable, null);
    lop.filter(bi, bi);
  }

  public void update(Graphics g) {
    g.clearRect(0, 0, getWidth(), getHeight());
    paintComponent(g);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    g2D.drawImage(bi, 0, 0, this);
  }
}

//------------------------Filters Class-------------------------------

class ColorConvertDemo extends JFrame {
  ColorPanel displayPanel;

  JButton grayButton, resetButton, setButton, negButton;

  public ColorConvertDemo() {
    super();
    Container container = getContentPane();

    displayPanel = new ColorPanel();
    container.add(displayPanel);

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(2, 2));
    panel.setBorder(new TitledBorder("Click the Gray Scale Button to Create Gray Scale Image..."));

    grayButton = new JButton("Gray Scale");
    grayButton.addActionListener(new ButtonListener());
    resetButton = new JButton("Reset");
    resetButton.addActionListener(new ButtonListener());
	setButton = new JButton("Set");
	setButton.addActionListener(new ButtonListener());
    negButton = new JButton("Neg");
	negButton.addActionListener(new ButtonListener());
	panel.add(negButton);
    panel.add(grayButton);
    panel.add(resetButton);
	panel.add(setButton);

    container.add(BorderLayout.SOUTH, panel);

    addWindowListener(new WindowEventHandler());

    setSize(displayPanel.getWidth(), displayPanel.getHeight());
    setVisible(true);
  }

  class WindowEventHandler extends WindowAdapter {
    public void windowClosing(WindowEvent e) {}
  }


  class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      JButton button = (JButton) e.getSource();

      if (button.equals(grayButton))
      {
        displayPanel.grayOut();
        displayPanel.repaint();
      }
      else if (button.equals(resetButton))
      {
        displayPanel.reset();
        displayPanel.repaint();
      }
      else if(button.equals(negButton))
      {
		 displayPanel.reverseLUT();
        displayPanel.applyFilter();
        displayPanel.repaint();
		  }
      else if(button.equals(setButton))
      {
		displayPanel.returnimg();
   //     displayPanel.repaint();
		}
    }
  }
}

class ColorPanel extends JLabel {
  Image displayImage;

  BufferedImage bi;

  Graphics2D big;
String outputFileLocation ="bon.jpg";
//String inputFileLocation="J:/kaku.jpg";
LookupTable lookupTable;
  ColorPanel() {
    setBackground(Color.black);
    loadImage();
    setSize(displayImage.getWidth(this), displayImage.getWidth(this));

    createBufferedImage();
  }

  public void loadImage() {
    displayImage = Toolkit.getDefaultToolkit().getImage(ScrolledPane.add);
    MediaTracker mt = new MediaTracker(this);
    mt.addImage(displayImage, 1);
    try {
      mt.waitForAll();
    } catch (Exception e) {
      System.out.println("Exception while loading.");
    }
  }

  public void createBufferedImage() {
    bi = new BufferedImage(displayImage.getWidth(this), displayImage
        .getHeight(this), BufferedImage.TYPE_INT_RGB);
    big = bi.createGraphics();
    big.drawImage(displayImage, 0,0, this);
  }

 void returnimg(){
Frame1 bewajaha1 = new Frame1();
setVisible( true );
try {
File outputfile = new File("bon.jpg");
ImageIO.write(bi, "JPG", outputfile);
} catch (IOException e) {e.printStackTrace();}
	Image it;
	Toolkit tool = Toolkit.getDefaultToolkit();
	it = tool.getImage("bon.jpg");
bewajaha1.seteffect(it);
}
  public void grayOut() {
    ColorConvertOp colorConvert = new ColorConvertOp(ColorSpace
        .getInstance(ColorSpace.CS_GRAY), null);
    colorConvert.filter(bi, bi);
  }

  public void reset() {
    big.setColor(Color.black);
    big.clearRect(0, 0, bi.getWidth(this), bi.getHeight(this));
    big.drawImage(displayImage, 0, 0, this);
  }
  public void set(){
try {
//BufferedImage bi = img;
File outputfile = new File(outputFileLocation);
ImageIO.write(bi, "jpg", outputfile);
} catch (IOException e) {e.printStackTrace();}
//  writeImage(bi, outputFileLocation, "jpg");
  }
 void writeImage(BufferedImage img, String fileLocation,String extension) {
try {
BufferedImage bi = img;
File outputfile = new File(fileLocation);
ImageIO.write(bi, extension, outputfile);
} catch (IOException e) {e.printStackTrace();}
}

  public void reverseLUT() {
    byte reverse[] = new byte[256];
    for (int i = 0; i < 256; i++) {
      reverse[i] = (byte) (255 - i);
    }
    lookupTable = new ByteLookupTable(0, reverse);
  }
    public void applyFilter() {
    LookupOp lop = new LookupOp(lookupTable, null);
    lop.filter(bi, bi);
  }

public static BufferedImage readImage(String fileLocation) {
BufferedImage img = null;
try {
img = ImageIO.read(new File(fileLocation));
} catch (IOException e) {
e.printStackTrace();
}
return img;
}

  public void update(Graphics g) {
    g.clearRect(0, 0, getWidth(), getHeight());
    paintComponent(g);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;

    g2D.drawImage(bi, 0, 0, this);
  }
}

//----------------------------------------Filters-----------------------------------

 class ConvolveApp extends JFrame {
  CPanel displayPanel;

  JButton sharpenButton,sharpen2Button, blurringButton,blurring2Button, edButton,ed2Button, resetButton,setButton;

  public ConvolveApp() {
    super();
    Container container = getContentPane();

    displayPanel = new CPanel();
    container.add(displayPanel);

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(4, 4));
    panel
        .setBorder(new TitledBorder(
            "Click a Button to Perform the Associated Operation and Reset..."));

    sharpenButton = new JButton("Sharpen>>");
    sharpenButton.addActionListener(new ButtonListener());
    blurringButton = new JButton("Blur>>");
    blurringButton.addActionListener(new ButtonListener());
    edButton = new JButton("Edge Detect>>");
    edButton.addActionListener(new ButtonListener());
    resetButton = new JButton("Reset");
    resetButton.addActionListener(new ButtonListener());

    sharpen2Button = new JButton("Sharpen<<");
    sharpen2Button.addActionListener(new ButtonListener());
    blurring2Button = new JButton("Blur<<");
    blurring2Button.addActionListener(new ButtonListener());
    ed2Button = new JButton("Edge Detect<<");
    ed2Button.addActionListener(new ButtonListener());
    setButton = new JButton("Set");
    setButton.addActionListener(new ButtonListener());

    panel.add(sharpenButton);
    panel.add(sharpen2Button);
    panel.add(blurringButton);
    panel.add(blurring2Button);
    panel.add(edButton);
    panel.add(ed2Button);
    panel.add(setButton);
    panel.add(resetButton);

    container.add(BorderLayout.SOUTH, panel);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    setSize(displayPanel.getWidth(), displayPanel.getHeight() + 10);
    setVisible(true);
  }

  class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      JButton button = (JButton) e.getSource();

      if (button.equals(sharpenButton)) {
        displayPanel.sharpen();
        displayPanel.repaint();
      } if (button.equals(sharpen2Button)) {
        displayPanel.sharpen2();
        displayPanel.repaint();
      } else if (button.equals(blurringButton)) {
        displayPanel.blur();
        displayPanel.repaint();
      } else if (button.equals(blurring2Button)) {
        displayPanel.blur2();
        displayPanel.repaint();
      } else if (button.equals(edButton)) {
        displayPanel.edgeDetect();
        displayPanel.repaint();
      } else if (button.equals(ed2Button)) {
        displayPanel.edgeDetect2();
        displayPanel.repaint();
      } else if (button.equals(setButton)) {
        displayPanel.returnimg();
        //displayPanel.repaint();
      } else if (button.equals(resetButton)) {
        displayPanel.reset();
        displayPanel.repaint();
      }
    }
  }
}

class CPanel extends JLabel {
  Image displayImage;

  BufferedImage biSrc;

  BufferedImage biDest; // Destination image is mandetory.

  BufferedImage bi; // Only an additional reference.

  Graphics2D big;
float bff= 0.0625f;
float sff=-1.0f;
float eff= 1.0f;
  CPanel() {
    setBackground(Color.black);
    loadImage();
    setSize(displayImage.getWidth(this), displayImage.getWidth(this));
    createBufferedImages();
    bi = biSrc;
  }

  public void loadImage() {
    displayImage = Toolkit.getDefaultToolkit().getImage(ScrolledPane.add);
    MediaTracker mt = new MediaTracker(this);
    mt.addImage(displayImage, 1);
    try {
      mt.waitForAll();
    } catch (Exception e) {
      System.out.println("Exception while loading.");
    }
    if (displayImage.getWidth(this) == -1) {
      System.out.println("No jpg file");
    }
  }

  public void createBufferedImages() {
    biSrc = new BufferedImage(displayImage.getWidth(this), displayImage
        .getHeight(this), BufferedImage.TYPE_INT_RGB);

    big = biSrc.createGraphics();
    big.drawImage(displayImage, 0, 0, this);

    biDest = new BufferedImage(displayImage.getWidth(this), displayImage
        .getHeight(this), BufferedImage.TYPE_INT_RGB);
  }

  public void sharpen() {
    //float data[] = { -1.0f, -1.0f, -1.0f, -1.0f, 9.0f, -1.0f, -1.0f, -1.0f, -1.0f };
    float data[] = { sff, sff, sff, sff, 9.0f, sff, sff, sff, sff };
    Kernel kernel = new Kernel(3, 3, data);
    ConvolveOp convolve = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP,null);
    convolve.filter(biSrc, biDest);
    bi = biDest;
    sff = sff +0.03f;
  }
public void sharpen2() {
    //float data[] = { -1.01f, -1.01f, -1.01f, -1.01f, sff, -1.01f, -1.01f, -1.01f, -1.01f };
        float data[] = { sff, sff, sff, sff, 9.0f, sff, sff, sff, sff };
    Kernel kernel = new Kernel(3, 3, data);
    ConvolveOp convolve = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP,null);
    convolve.filter(biSrc, biDest);
    bi = biDest;
  sff = sff -0.03f;}


  public void blur() {
    //float data[] = { 0.0625f, 0.125f, 0.0625f, 0.125f, 0.25f, 0.125f,0.0625f, 0.125f, 0.0625f };
//float ff = 0.0625f;
    float data[] = { bff , 0.125f,  bff, 0.125f, 0.25f, 0.125f, bff , 0.125f, bff };
    Kernel kernel = new Kernel(3, 3, data);
    ConvolveOp convolve = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP,
        null);
    convolve.filter(biSrc, biDest);
    bi = biDest;
bff = bff+0.0200f;
  }
 public void blur2() {
    //float data[] = { 0.0625f, 0.125f, 0.0625f, 0.125f, 0.25f, 0.125f,0.0625f, 0.125f, 0.0625f };
//float ff = 0.0625f;
    float data[] = { bff , 0.125f,  bff, 0.125f, 0.25f, 0.125f, bff , 0.125f, bff };
    Kernel kernel = new Kernel(3, 3, data);
    ConvolveOp convolve = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP,
        null);
    convolve.filter(biSrc, biDest);
    bi = biDest;
bff = bff-0.0200f;
  }


    public void edgeDetect() {
   // float data[] = { 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f,-1.0f };
float data[] = { eff, 0.0f, -1.0f, eff, 0.0f, -1.0f, eff, 0.0f,-1.0f };
    Kernel kernel = new Kernel(3, 3, data);
    ConvolveOp convolve = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP,
        null);
    convolve.filter(biSrc, biDest);

    bi = biDest;
   eff = eff+0.02f;
  }
    public void edgeDetect2() {
    //float data[] = { 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f,-1.0f };
float data[] = { eff, 0.0f, -1.0f, eff, 0.0f, -1.0f, eff, 0.0f,-1.0f };
    Kernel kernel = new Kernel(3, 3, data);
    ConvolveOp convolve = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP,
        null);
    convolve.filter(biSrc, biDest);
    bi = biDest;
    eff = eff-0.02f;
  }
 void returnimg(){
Frame1 bewajaha1 = new Frame1();
setVisible( true );
try {
File outputfile = new File("bon.jpg");
ImageIO.write(bi, "JPG", outputfile);
} catch (IOException e) {e.printStackTrace();}
	Image it;
	Toolkit tool = Toolkit.getDefaultToolkit();
	it = tool.getImage("bon.jpg");
bewajaha1.seteffect(it);
}


  public void reset() {
    big.setColor(Color.black);
    big.clearRect(0, 0, bi.getWidth(this), bi.getHeight(this));
    big.drawImage(displayImage, 0, 0, this);
    bi = biSrc;
  }

  public void update(Graphics g) {
    g.clearRect(0, 0, getWidth(), getHeight());
    paintComponent(g);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    g2D.drawImage(bi, 0, 0, this);
  }
}

//------------------------Main Class-------------------------------

class av
{
	JWindow swind;
	JPanel panel1;
	ImageIcon img1;
	JLabel label1;
	av()
	{
			swind=new JWindow();
			panel1=new JPanel();
			panel1.setBackground(Color.BLACK);
			panel1.setLayout(null);
			ImageIcon i1=new ImageIcon("logo.jpg");
			label1=new JLabel(" ",i1,JLabel.CENTER);
			label1.setBounds(0,0,460,300);
			panel1.add(label1);
			swind.add(panel1);
			swind.setAlwaysOnTop(true);
			swind.setSize(454,301);
			swind.setLocationRelativeTo(null);
			swind.setVisible(true);
			try
			 {
	           Thread.sleep(3000);
			 }
			 catch(InterruptedException e)
			 {}

			 swind.setVisible(false);
	             //EventQueue.invokeLater((Runnable) new Login());
	         swind.dispose();

	}

	public static void main(String[] ar)
	{
		      new av();
		Frame1 f=new Frame1();

	}
}



