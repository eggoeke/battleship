import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class BattleshipGrid {

    public static void main(String[] args) {
        Random rand = new Random();
        // Create the frame
        JFrame frame = new JFrame("Battleship Grid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        // Create the panel that will hold the grid
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Create labels for the rows (A-G)
        char[] rows = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        for (int i = 0; i < rows.length; i++) {
            c.gridx = 0;
            c.gridy = i + 1; // Start from 1 to leave space for column labels
            panel.add(new JLabel(String.valueOf(rows[i])), c);
        }

        // Create labels for the columns (1-7)
        for (int i = 1; i <= 10; i++) {
            c.gridx = i;
            c.gridy = 0;
            panel.add(new JLabel(String.valueOf(i)), c);
        }

        // Create the grid of buttons with action listeners
        JButton[][] buttons = new JButton[10][10];

        ArrayList<Ship> ships = new ArrayList<Ship>();
        // Counter for number of ships!! We want 5 ships
        for(int i = 0; i < 5; i++){
            int[][] coord = new int[3][2];
            int isVertical = rand.nextInt(2);
            if(isVertical == 1){
                //iterating over the rows of the coordinates
                int y = rand.nextInt(10);
                int x = rand.nextInt(10);
                for(int j = 0; j < 3; j++){
                    if(x+2 > 10){
                        x= x-2;

                    } else if(x+1 > 10){
                        x = x-1;
                    }
                    coord[j][0] = x+j;
                    coord[j][1] = y;
                }
            } else{
                //iterating over the rows of the coordinates
                int y = rand.nextInt(10);
                int x = rand.nextInt(10);
                for(int j = 0; j < 3; j++){
                    if(y+2 > 10){
                        y= y-2;

                    } else if(y+1 > 10){
                        y = y-1;
                    }
                    coord[j][0] = x;
                    coord[j][1] = y+j;
                }
            }
            int length = i;

            boolean isDuplicate = false;
                for(int k = 0; k < length; k++){
                    int[][] coordinates = ships.get(k).getCoordinates();
                    for(int h = 0; h < 3; h++){
                        boolean isXDuplicate = (coord[h][0] == coordinates[h][0]);
                        boolean isYDuplicate = (coord[h][1] == coordinates[h][1]);    
                        isDuplicate = ((isXDuplicate)&&(isYDuplicate));
                        if(isDuplicate){
                            break;
                        }
                    }
                    if(isDuplicate){
                        break;
                    }
                }
            if(isDuplicate){
                i = i-1;
            } else {
                Ship ship = new Ship(coord);
                ships.add(ship);
            }
        }

        Grid board = new Grid(ships, 10, 10);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setPreferredSize(new Dimension(80, 80)); // Increase button size
                buttons[i][j].setBackground(Color.BLUE);
                buttons[i][j].setOpaque(true);
                c.gridx = j + 1; // Start from 1 to leave space for row labels
                c.gridy = i + 1; // Start from 1 to leave space for column labels
                panel.add(buttons[i][j], c);

                // Add action listener to each button
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Find the button that was clicked
                        for (int row = 0; row < 10; row++) {
                            for (int col = 0; col < 10; col++) {
                                if (e.getSource() == buttons[row][col]) {
                                    // Print coordinates of the clicked button
                                    // See if ship is there
                                    // If ship there, turn red, otherwise light-green
                                    boolean isHit = board.checkShip(row, col);
                                    if(isHit){
                                        buttons[row][col].setBackground(Color.RED);
                                    }else{
                                        buttons[row][col].setBackground(Color.YELLOW);

                                    }
                                    System.out.println("Button clicked at: " + rows[row] + (col + 1));
                                }
                            }
                        }
                    }
                });
            }
        }

        // Add the panel to the frame
        frame.add(panel);
        frame.setVisible(true);
    }
}
