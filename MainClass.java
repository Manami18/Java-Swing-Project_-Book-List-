package guiapplicationpack;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class MainPanel extends JPanel implements ActionListener
{
    private JLabel lblListCaption;
    private JTextField txtBookName;
    private JComboBox cmbBookCategory;
    private JList lstBookList;
    private JScrollPane spnBookList;
    private JButton btnAddNew,btnRemoveItem,btnClearItems,btnExit;
    private String[] category = {"Classics","Romance","Comic Book","Detective","Fantasy","Historical Fiction","Horror","Adventure","Literary Fiction","Science Fiction","Short Stories","Women's Fiction","Thillers","Biographies","Cook Books","Essays"};
    private Vector<String> bookList = new Vector<String>();
    
    private JLabel makeLabel(String cap,int x,int y,int w,int h,int mode)
    {
        JLabel temp = new JLabel(cap);
        if(mode == 1)
        {
            temp.setFont(new Font("Verdana", 1, 25));
            temp.setOpaque(true);
            temp.setBackground(Color.RED);
            temp.setForeground(Color.WHITE);
            temp.setHorizontalAlignment(JLabel.CENTER);
            temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));            
        }
        else
            temp.setFont(new Font("Courier New", 1, 16));
        temp.setBounds(x,y,w,h);
        super.add(temp);
        return temp;
    }
    private JTextField makeTextField(int x,int y,int w,int h)
    {
        JTextField temp = new JTextField();
        temp.setFont(new Font("Courier New",1,16));
        temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        temp.setBounds(x,y,w,h);
        temp.setHorizontalAlignment(JTextField.CENTER);
        temp.addKeyListener(new KeyListener() 
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                if(txtBookName.getText().length()==0)
                    btnAddNew.setEnabled(false);
                else
                    btnAddNew.setEnabled(true);
                
                if(e.getKeyChar() == KeyEvent.VK_ENTER) bookRecordTracking();
                if(e.getKeyChar() == KeyEvent.VK_ESCAPE) System.exit(0);
            }
            @Override
            public void keyPressed(KeyEvent e){}
            @Override
            public void keyReleased(KeyEvent e){}
        });
        add(temp);
        return temp;
    }
    private JComboBox makeComboBox(int x,int y,int w,int h,String[] items)
    {
        JComboBox temp = new JComboBox(items);
        temp.setFont(new Font("Verdana", 1, 12));
        temp.setBounds(x,y,w,h);
        add(temp);
        return temp;
    }
    private JButton makeButton(String caption,int x,int y,int w,int h)
    {
        JButton temp = new JButton(caption);
        temp.setBounds(x,y,w,h);
        temp.setFont(new Font("Verdana", 1, 12));
        temp.setMargin(new Insets(0,0,0,0));
        temp.addActionListener(this);
        super.add(temp);
        return temp;
    }
    public MainPanel()
    {
        makeLabel("Bibliography",                10, 10,470,50,1);
        makeLabel("Enter Book Name",             10, 70,200,30,2);
        txtBookName     = makeTextField(            220, 70,260,30);
        makeLabel("Select Book Category",        10,110,200,30,2);
        cmbBookCategory = makeComboBox(             220,110,260,30, category);
        makeLabel("List of Books with Category", 10,150,300,30,2);
        lstBookList     = new JList(bookList);
        lstBookList.setFont(new Font("Courier New", 1, 14));
        lstBookList.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                btnRemoveItem.setEnabled(true);
            }
        });
        
        lblListCaption = new JLabel("Name of Book        |Book Category");
        lblListCaption.setFont(new Font("Courier New", 1, 14));
        spnBookList     = new JScrollPane(lstBookList);
        spnBookList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        spnBookList.setBounds(10,190,300,170);
        spnBookList.setColumnHeaderView(lblListCaption);
        add(spnBookList);
        
        btnAddNew       = makeButton("Add New",     325,190,150,30);
        btnAddNew.setEnabled(false);
        btnRemoveItem   = makeButton("Remove Item", 325,235,150,30);
        btnRemoveItem.setEnabled(false);
        btnClearItems   = makeButton("Clear Items", 325,280,150,30);
        btnClearItems.setEnabled(false);
        btnExit         = makeButton("Exit",        325,325,150,30);
    }
    private void bookRecordTracking()
    {
        String msg = String.format("%-20s|%s", txtBookName.getText(),cmbBookCategory.getSelectedItem());
        bookList.add(msg);
        lstBookList.setListData(bookList);
        txtBookName.grabFocus();
        txtBookName.selectAll();
        btnClearItems.setEnabled(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object ob = e.getSource();
        if(ob == btnAddNew)
        {
            bookRecordTracking();
        }
        else if(ob == btnRemoveItem)
        {
            int index = lstBookList.getSelectedIndex();
            bookList.remove(index);
            lstBookList.setListData(bookList);
            btnRemoveItem.setEnabled(false);
        }
        else if(ob == btnClearItems)
        {
            int confirm = JOptionPane.showConfirmDialog(null, "Want to clear all the list items?","Confirm",JOptionPane.YES_NO_OPTION);
            if(confirm == 0)
            {
                bookList.clear();
                lstBookList.setListData(bookList);
                btnRemoveItem.setEnabled(false);
                btnClearItems.setEnabled(false);
            }
        }
        else if(ob == btnExit)
        {
            System.exit(0);
        }
    }
}
class MainFrame extends JFrame
{
    private MainPanel panel;
    public MainFrame()
    {
        panel = new MainPanel();
        panel.setBackground(new Color(225,250,160));
        panel.setLayout(new BorderLayout());
        super.add(panel);
    }
}
public class MainClass
{
    public static void main(String[] args)
    {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(510, 410);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Book List");
        frame.setResizable(false);
    }
}