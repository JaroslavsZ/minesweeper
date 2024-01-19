package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Main {

    static ImageIcon icoMine = createIcon("minesweeper/mine.png");
    static ImageIcon icoEmpty = createIcon("minesweeper/empty.png");
    static ImageIcon icoOpen = createIcon("minesweeper/empty.png");
    static ImageIcon icoFlag = createIcon("minesweeper/flag.png");
    static ImageIcon icoWrongFlag = createIcon("minesweeper/wrong-flag.png");
    static ImageIcon icoSmile = createIcon("minesweeper/smile.png");

    static ImageIcon createIcon(String resourceName) {
        return new ImageIcon(Objects.requireNonNull(Main.class.getClassLoader().getResource(resourceName)));
    }

    static class FieldButtonListener extends MouseAdapter {

        private int x;
        private int y;
        private final JLabel label;

        FieldButtonListener(int x, int y, JLabel label) {
            this.x = x;
            this.y = y;
            this.label = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(SwingUtilities.isLeftMouseButton(e)) {
                System.out.println("Mouse 1 clicked: " + x + " " + y);
            }
            if(SwingUtilities.isRightMouseButton(e)) {
                System.out.println("Mouse 2 clicked: " + x + " " + y);
            }
            label.setIcon(icoFlag);
        }
    }

    static class ButtonSmileAction extends AbstractAction {
        ButtonSmileAction() {
            super("", icoSmile);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button smile");
        }
    }

    public static void main(String[] args) {


        var frm = new JFrame("Minesweeper");
        frm.setLayout(new BorderLayout());

        var pnlNorth = new JPanel();
        var btnSmile = new JButton();
        btnSmile.setAction(new ButtonSmileAction());


        btnSmile.setBorder(null);
        pnlNorth.add(btnSmile);
        frm.add(pnlNorth, BorderLayout.NORTH);

        var pnlField = new JPanel(new GridLayout(10, 10));
        pnlField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frm.add(pnlField, BorderLayout.CENTER);

        for(int y = 0; y < 10; y ++) {
            for(int x = 0; x < 10; x ++) {

                JLabel lbl = new JLabel();
                lbl.addMouseListener(new FieldButtonListener(x, y, lbl));

                var rand = Math.random();
                if(rand < 0.1) {
                    lbl.setIcon(icoMine);
                } else if(rand < 0.3){
                    lbl.setIcon(icoEmpty);
                } else if(rand < 0.8) {
                    lbl.setIcon(icoOpen);
                } else if(rand < 0.95 ){
                    lbl.setIcon(icoFlag);
                }  else {
                    lbl.setIcon(icoWrongFlag);
                }
                pnlField.add(lbl);
            }
        }

        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setBounds(100, 100, 800, 800);
        frm.pack();
        frm.setVisible(true);
    }
}
