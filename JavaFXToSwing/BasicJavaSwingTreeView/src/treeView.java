import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
public class treeView {
    JFrame f;
    treeView(){
        f=new JFrame();
        DefaultMutableTreeNode style=new DefaultMutableTreeNode("Style");
        DefaultMutableTreeNode color=new DefaultMutableTreeNode("color");
        DefaultMutableTreeNode font=new DefaultMutableTreeNode("font");
        style.add(color);
        style.add(font);

        // Library for the Font file
        DefaultMutableTreeNode times = new DefaultMutableTreeNode("New Times Roman");
        DefaultMutableTreeNode helv = new DefaultMutableTreeNode("Helvetica");
        DefaultMutableTreeNode bask = new DefaultMutableTreeNode("Baskerville");
        DefaultMutableTreeNode goth = new DefaultMutableTreeNode("Gotham");
        DefaultMutableTreeNode didot = new DefaultMutableTreeNode("Didot");

        // Library for the Color file
        DefaultMutableTreeNode red = new DefaultMutableTreeNode("red");
        DefaultMutableTreeNode blue = new DefaultMutableTreeNode("blue");
        DefaultMutableTreeNode black = new DefaultMutableTreeNode("black");
        DefaultMutableTreeNode green = new DefaultMutableTreeNode("green");
        color.add(red);
        color.add(blue);
        color.add(black);
        color.add(green);
        font.add(times);
        font.add(helv);
        font.add(bask);
        font.add(goth);
        font.add(didot);
        JTree jt=new JTree(style);
        f.add(jt);
        f.setBounds(600,300,200, 260);
        f.setVisible(true);
    }
    public static void main(String[] args) {
        new treeView();
    }}