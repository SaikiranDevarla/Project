import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Human
{
String name;
int count,id;
Human(String name,int count,int id)
{
this.name=name;
this.count=count;
this.id=id;
}
}
//****************************************************************************************
class Goalkeeper extends Human
{
int total_stopping_shots;
int stopping_rate;
Goalkeeper(String name,int count,int id,int total_stopping_shots)
{
super(name,count,id);
this.total_stopping_shots=total_stopping_shots;
stopping_rate=(total_stopping_shots)/count;
}
public String toString()
{
return "Name: "+name+"\n"+
       "ID: "+id+"\n"+
	"Match count: "+count+"\n"+
	"Stopping rate: "+stopping_rate+"\n";
}
}
//****************************************************************************************
class Fieldplayer extends Human
{
int goal_count;
int goal_rate;
Fieldplayer(String name,int count,int id,int goal_count) 
{
super(name,count,id);
this.goal_count=goal_count;
goal_rate=(goal_count)/count;
}
public String toString()
{
return "Name: "+name+"\n"+
       "ID: "+id+"\n"+
	"Match count: "+count+"\n"+
	"Goal rate: "+goal_rate+"\n";
}
}
//*******************************Exception class************************************
class EmptyFieldException extends Exception
{
public String toString()
{
return "No field can be empty!!Please fill all the details.";
}
}
//*****************************Main class**********************************
public class FootBall1 extends JFrame
{
int one,index;
ArrayList<Fieldplayer> alfp=new ArrayList<>();
ArrayList<Goalkeeper> algk=new ArrayList<>();
JRadioButton fp,gk;
ButtonGroup g;
JLabel namel,idl,mcl,xl;
JTextField nametf,idtf,mctf,xtf;
TextArea ta;
JButton insert,search,bp,first,last,pre,next;
public FootBall1()
{
setTitle("FootBall Tournament-2023");
setLayout(null);
setSize(new Dimension(600,600));
g=new ButtonGroup();
fp=new JRadioButton("Field Player");
fp.setBounds(50,100,100,20);
g.add(fp);
gk=new JRadioButton("Goalie");
gk.setBounds(150,100,100,20);
namel=new JLabel("Name:");
namel.setBounds(50,150,100,20);
nametf=new JTextField();
nametf.setBounds(150,150,100,20);
idl=new JLabel("ID");
idl.setBounds(50,180,100,20);
idtf=new JTextField();
idtf.setBounds(150,180,100,20);
mcl=new JLabel("Match count");
mcl.setBounds(50,210,100,20);
mctf=new JTextField();
mctf.setBounds(150,210,100,20);
xl=new JLabel("xxxxxx");
xl.setBounds(50,240,100,20);
xtf=new JTextField();
xtf.setBounds(150,240,100,20);
ta=new TextArea();
ta.setBounds(280,150,260,200);
insert=new JButton("Insert");
insert.setBounds(75,270,120,20);
search=new JButton("Search");
search.setBounds(75,300,120,20);
bp=new JButton("Best Player");
bp.setBounds(75,330,120,20);
first=new JButton("<<");
first.setBounds(280,360,50,20);
pre=new JButton("<");
pre.setBounds(350,360,50,20);
next=new JButton(">");
next.setBounds(420,360,50,20);
last=new JButton(">>");
last.setBounds(490,360,50,20);
g.add(gk);
add(fp);
add(gk);
add(namel);
add(nametf);
add(idl);
add(idtf);
add(mcl);
add(mctf);
add(xl);
add(xtf);
add(ta);
add(insert);
add(search);
add(bp);
add(first);
add(pre);
add(next);
add(last);
//*****************************Action listeners****************************************
fp.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
xl.setText("Goals Count");
}
});
gk.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
xl.setText("Stopping Shots");
}
});
insert.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
if(fp.isSelected())
{
try 
{
if(nametf.getText().isEmpty() || idtf.getText().isEmpty() || mctf.getText().isEmpty() || xtf.getText().isEmpty())
{
throw new  EmptyFieldException();
}
String name=nametf.getText();
int count=Integer.parseInt(mctf.getText());
int id=Integer.parseInt(idtf.getText());
int goal_count=Integer.parseInt(xtf.getText());
Fieldplayer fd=new Fieldplayer(name,count,id,goal_count);
alfp.add(fd);
nametf.setText("");
idtf.setText("");
mctf.setText("");
xtf.setText("");
}
catch(EmptyFieldException e)
{
JOptionPane.showMessageDialog(null,e.toString(),"Error message",JOptionPane.ERROR_MESSAGE);
}
}

else if(gk.isSelected())
{
try 
{
if(nametf.getText().isEmpty() || idtf.getText().isEmpty() || mctf.getText().isEmpty() || xtf.getText().isEmpty())
{
throw new  EmptyFieldException();
}
String name=nametf.getText();
int count=Integer.parseInt(mctf.getText());
int id=Integer.parseInt(idtf.getText());
int total_stopping_goals=Integer.parseInt(xtf.getText());
Goalkeeper g=new Goalkeeper(name,count,id,total_stopping_goals);
algk.add(g);
nametf.setText("");
idtf.setText("");
mctf.setText("");
xtf.setText("");
}
catch(EmptyFieldException e)
{
JOptionPane.showMessageDialog(null,e.toString(),"Error message",JOptionPane.ERROR_MESSAGE);
}
}
}
});
search.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ae) {
        int flag = 0;

        if (fp.isSelected()) {
            int id = Integer.parseInt(idtf.getText());
            for (Fieldplayer i : alfp) {
                if (i.id == id) {
                    ta.append(i.toString() + "\n");
                    flag = 1;
                    one = alfp.indexOf(i);
                    break;
                }
            }
        } else if (gk.isSelected()) {
            int id = Integer.parseInt(idtf.getText());
            for (Goalkeeper i : algk) {
                if (i.id == id) {
                    ta.append(i.toString() + "\n");
                    flag = 1;
                    one = algk.indexOf(i);
                    break;
                }
            }
        }

        if (flag == 0) {
            JOptionPane.showMessageDialog(null, "Player not found", "Search", JOptionPane.INFORMATION_MESSAGE);
        }
    }
});

bp.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ae) {
        if (fp.isSelected()) {
            Fieldplayer bestplayer = alfp.get(0);
            for (Fieldplayer i : alfp) {
                if (i.goal_rate > bestplayer.goal_rate) {
                    bestplayer = i;
                    one = alfp.indexOf(i);
                }
            }
            ta.append(bestplayer.toString() + "\n");
        } else if (gk.isSelected()) {
            Goalkeeper bestplayer = algk.get(0);
            for (Goalkeeper i : algk) {
                if (i.stopping_rate > bestplayer.stopping_rate) {
                    bestplayer = i;
                    one = algk.indexOf(i);
                }
            }
            ta.append(bestplayer.toString() + "\n");
        }
    }
});

first.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ae) {
        if (fp.isSelected()) {
            Fieldplayer player = alfp.get(0);
            one = 0;
            ta.append(player.toString() + "\n");
        } else if (gk.isSelected()) {
            Goalkeeper player = algk.get(0);
            one = 0;
            ta.append(player.toString() + "\n");
        }
    }
});

last.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ae) {
        if (fp.isSelected()) {
            Fieldplayer player = alfp.get(alfp.size() - 1);
            one = alfp.size() - 1;
            ta.append(player.toString() + "\n");
        } else if (gk.isSelected()) {
            Goalkeeper player = algk.get(algk.size() - 1);
            one = algk.size() - 1;
            ta.append(player.toString() + "\n");
        }
    }
});

next.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ae) {
        if (fp.isSelected()) {
            index = one + 1;
            if (index < alfp.size()) {
                Fieldplayer player = alfp.get(index);
                ta.append("Next player: " + "\n" + player.toString() + "\n");
                one = index; // Update the current index
            } else {
                ta.append("No more players available.\n");
            }
        } else if (gk.isSelected()) {
            index = one + 1;
            if (index < algk.size()) {
                Goalkeeper player = algk.get(index);
                ta.append("Next player: " + "\n" + player.toString() + "\n");
                one = index; // Update the current index
            } else {
                ta.append("No more players available.\n");
            }
        }
    }
});

pre.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ae) {
        if (fp.isSelected()) {
            index = one - 1;
            if (index >= 0) {
                Fieldplayer player = alfp.get(index);
                ta.append("Previous player: " + "\n" + player.toString() + "\n");
                one = index;
            } else {
                ta.append("No previous players available.\n");
            }
        } else if (gk.isSelected()) {
            index = one - 1;
            if (index >= 0) {
                Goalkeeper player = algk.get(index);
                ta.append("Previous player: " + "\n" + player.toString() + "\n");
                one = index;
            } else {
                ta.append("No previous players available.\n");
            }
        }
    }
});


setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
public static void main(String args[])
{
SwingUtilities.invokeLater(new Runnable()
{
public void run()
{
new FootBall1();
}
});
}
} 