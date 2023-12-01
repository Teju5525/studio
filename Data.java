import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
  public class OJDBC extends JFrame implements ActionListener
  {
    GridBagConstraits gc;
    JButton b1,b2,b3,b4,b5,b6,b7,b8;
    JLabel l1,l2,l3;
    JTextField id,name,dept;
    Statement st;
    ResultSet r;
    JComboBox jcb;
    OJDBC(){
        setSize(600,500);
        setLocation(370,150);
        setVisible(true);
        b1=new JButton("First");
        b2=new JButton("Next");
        b3=new JButton("Previous");
        b4=new JButton("Last");
        b5=new JButton("Insert");
        b6=new JButton("Modify");
        b7=new JButton("Delete");
        b8=new JButton("Exit");
                   id=new JTextField(10);
                   name=new JTextField(10);
                   dept=new JTextField(10);
            l1=new JLabel("Id:");
            l2=new JLabel("Name:");
            l3=new JLabel("Dept:");
            jcb=new JComboBox();
                setLayout(new GridBagLayout());
                gc=new GridBagConstraints();
                addGrid(l1,0,0,2,1);
                addGrid(id,0,2,2,1);
                addGrid(l2,1,0,2,1);
                addGrid(name,1,2,2,1);
                addGrid(l3,2,0,2,1);
                addGrid(dept,2,2,2,1);
                addGrid(b1,3,0,1,1);
                addGrid(b1,3,1,1,1);
                addGrid(b1,3,2,1,1);
                addGrid(b1,3,3,1,1);
                addGrid(b1,4,0,1,1);
                addGrid(b1,4,1,1,1);
                addGrid(b1,4,2,1,1);
                addGrid(b1,4,3,1,1);
                id.setEditable(false);
                try{
                    st=DataCon.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE.ResultSet.CONCUR_UPDATABLE);
                 r=st.executeQuery("select * from emp");   
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
                b1.addActionListener(this);
                b2.addActionListener(this);               
                b3.addActionListener(this);
                b4.addActionListener(this);
                b5.addActionListener(this);
                b6.addActionListener(this);
                b7.addActionListener(this);
                b8.addActionListener(this);
                    }
                    public void addGrid(Component c,int x,int y,int w,int h){
                        gc.gridx=y;
                        gc.gridy=x;
                        gc.gridwidth=w;
                        gc.gridheight=h;
                        gc.fill=gc.BOTH;
                        add(c,gc);
                    }
                    public void clearTextFields(){
                        name.setText("");
                        dept.sertText("");
                    }
                         public static void main(String[] args){
                            new OJDBC();
                         }
        public void setData() throws Exception{
            id.setText(Integer.toString(r.getInt(1)));
            name.setText(r.getString(2));
            dept.setText(r.grtString(3));
        }
        public void editable(bollean b){
            name.setEditable(b);
            dept.setEditable(b);
        }
            public void btEnabled(boolean b){
                b1.setEnabled(b);
                b2.setEnabled(b);
                b3.setEnabled(b);
                b4.setEnabled(b);
                b5.setEnabled(b);
                b6.setEnabled(b);
                b7.setEnabled(b);
            }
    public void actionPerformed(ActionEvent e){
        try{
            if(e.getSource()==b1)
            {
                editable(false);
                r.first();
                setData();
            }
            if(e.getSource()==b2)
            {
                editable(false);
                r.next();
                setData();
            }
            if(e.getSource()==b3)
            {
                editable(false);
                r.previous();
                setData();
            }
            if(e.getSource()==b4)
            {
                editable(false);
                r.last();
                setData();
            }
            if(e.getActionCommand().equals("Insert")){
                PreparedStatement ps=DataCon.con.prepareStatement("select max(EID)from emp");
                ResultSet r1=ps.executeQuery();
                r1.next();
                int maxEID=r1.getInt(1);
                maxEID++;
        id.setText(Integer.toString(maxEID));
        clearTextFields();
        b5.setText("save");
        bt.enabled(false);
        editable(true);
        remove(dept);
        addGrid(jcb,2,2,2,1);
        PreparedStatement ps1=DataCon.con.prepareStatement("select dept from emp");
        ResultSet r2=ps1.executeQuery();
        while(r2.next()){
            jcb.addItem(r2.getString(1));
        }        
        b8.setText("Go Back");
            }
            if(e.getActionCommand().equals("save")){
            preparedStatement ps3=DataCon.con.prepareStatement("insert into emp (?,?,?,?)");
            ps3.setInt(1,Integer.parseInt(id.getText()));
            ps3.setString(2,name.getText());
            ps3.setInt(3,jcb.getSelectedIndex()+1);
            ResultSet r2=ps3.executeQuery();
            b5.setText("Insert");
            remove(jcb);
            addGrid(dept,2,2,2,1);
            bt.enabled(true);
            clearTextFields();
            jcb.removeAllItems();
            r=st.executeQuery("select*from emp");
            r.last();
            setData();
            b8.setText("Exit");
            }
            if(e.getActionCommand().equals("go back")){
                if(b5.getInt().equals("Save"));
                b5.setText("Insert");
                remove(jcb);
                addGrid(dept,2,2,2,1);
                btEnabled(true);
                clearTextFields();
                jcb.removeAllItems();
                r=st.executeQuery("select*from emp");
                r.last();
                setData();
                b8.setData("Exit");
            }
        }
        if(e.getActionCommad().equals("Exit"))
            System.exit(0);
    }
    catch(Exception ee)
    {
        ee.printStackTrace();
        JOptionPane.showMessageDialog(this,"NO MORE DATA IN DATABASE","Alert",JOptionOane.ERROR_MESSAGE);
    }
}
  }