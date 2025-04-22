package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingUI extends JFrame implements GameUI {
    private JTextField[][] cells = new JTextField[9][9];
    private GameController controller;

    public SwingUI() {
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new BorderLayout());
        
        JPanel gridPanel = new JPanel(new GridLayout(9, 9));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        Font font = new Font("SansSerif", Font.BOLD, 20);
        
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                cells[row][col].setFont(font);
                
                // Adicionar bordas para subgrades 3x3
                if (row % 3 == 0) cells[row][col].setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
                if (col % 3 == 0) cells[row][col].setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK));
                
                gridPanel.add(cells[row][col]);
            }
        }
        
        JButton checkButton = new JButton("Verificar Solução");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.checkSolution();
            }
        });
        
        add(gridPanel, BorderLayout.CENTER);
        add(checkButton, BorderLayout.SOUTH);
    }

    @Override
    public void setController(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void updateCell(int row, int col, int value, boolean isFixed) {
        cells[row][col].setText(value == 0 ? "" : String.valueOf(value));
        cells[row][col].setEditable(!isFixed);
        cells[row][col].setBackground(isFixed ? Color.LIGHT_GRAY : Color.WHITE);
    }

    @Override
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}