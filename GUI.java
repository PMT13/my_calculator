import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    JLabel label = new JLabel("0");
    String firstNum = "";
    String operator = "";
    String secondNum = "";
    int firstDecimalCnt = 0;                     // makes sure there's only one decimal point per number
    int secondDecimalCnt = 0;
    double total = 0.0;
    boolean operatorDetected = false;   // makes sure there's an operator

    public GUI(){
        JFrame frame = new JFrame("My Calculator");
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        label.setFont(new Font("Courier", Font.PLAIN, 120));
        frame.setPreferredSize(new Dimension(390,390));
        frame.setLocation(500,300);

        GridLayout insideGrid = new GridLayout(2,1);
        GridLayout grid = new GridLayout(5,4);
        panel.setLayout(insideGrid);
        panel2.setLayout(grid);
        frame.add(panel);

        JButton button7 = new JButton("7");
        button7.addActionListener(this);

        JButton button8 = new JButton("8");
        button8.addActionListener(this);

        JButton button9 = new JButton("9");
        button9.addActionListener(this);

        JButton buttonDiv = new JButton("/");
        buttonDiv.addActionListener(this);

        JButton button4 = new JButton("4");
        button4.addActionListener(this);

        JButton button5 = new JButton("5");
        button5.addActionListener(this);

        JButton button6 = new JButton("6");
        button6.addActionListener(this);

        JButton buttonMul = new JButton("*");
        buttonMul.addActionListener(this);

        JButton button1 = new JButton("1");
        button1.addActionListener(this);

        JButton button2 = new JButton("2");
        button2.addActionListener(this);

        JButton button3 = new JButton("3");
        button3.addActionListener(this);

        JButton buttonAdd = new JButton("+");
        buttonAdd.addActionListener(this);

        JButton button0 = new JButton("0");
        button0.addActionListener(this);

        JButton buttonDot = new JButton(".");
        buttonDot.addActionListener(this);

        JButton buttonNeg = new JButton("(-)");
        buttonNeg.addActionListener(this);

        JButton buttonMinus = new JButton("-");
        buttonMinus.addActionListener(this);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        JButton emptyButton2 = new JButton("");
        JButton emptyButton3 = new JButton("");
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(this);

        panel2.add(button7);
        panel2.add(button8);
        panel2.add(button9);
        panel2.add(buttonDiv);
        panel2.add(button4);
        panel2.add(button5);
        panel2.add(button6);
        panel2.add(buttonMul);
        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);
        panel2.add(buttonAdd);
        panel2.add(button0);
        panel2.add(buttonDot);
        panel2.add(buttonNeg);
        panel2.add(buttonMinus);
        panel2.add(clearButton);
        panel2.add(emptyButton2);
        panel2.add(emptyButton3);
        panel2.add(enterButton);
        panel.add(label);
        panel.add(panel2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
    public static void main(String[] args){
        GUI myGui = new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();                 // retrieves the information of the button that was clicked
        if(!clicked.getText().equals("/") && !clicked.getText().equals("*") && !clicked.getText().equals("+") && !clicked.getText().equals("-") &&
                !clicked.getText().equals("Clear") && !clicked.getText().equals("Enter") && !clicked.getText().equals("(-)")){     // either the first number or second one
            if(total != 0.0 && operator.equals("")){               // if the user still wants to use the calculated total from previous calculation
                firstNum = clicked.getText();
                secondNum = "";
                operator = "";
                firstDecimalCnt = 0;
                secondDecimalCnt = 0;
                total = 0.0;
                operatorDetected = false;
                label.setText(firstNum);
            }else {
                if (operatorDetected == false) {                   // if calculator is on the first number
                    firstNum += clicked.getText();
                    label.setText(firstNum);
                    if (clicked.getText().equals(".")) {
                        firstDecimalCnt++;
                    }
                } else {                                      // if calculator is on the second number
                    secondNum += clicked.getText();
                    label.setText(secondNum);
                    if (clicked.getText().equals(".")) {
                        secondDecimalCnt++;
                    }
                }
            }
        }else{                                          // if the user is not entering in a number
            if((firstNum.equals("") || firstDecimalCnt > 1 || firstNum.equals(".")) || (operatorDetected == true && (secondNum.equals("") || secondDecimalCnt > 1 || secondNum.equals(".")))
            || (secondNum.equals("0") && operator.equals("/"))){          // if there is no first number inputted yet or too many decimals in the first number then ERROR
                label.setText("ERROR");
                firstNum = "";
                secondNum = "";
                operator = "";
                firstDecimalCnt = 0;
                secondDecimalCnt = 0;
                operatorDetected = false;
            }else {                                     // if user is clicking an operator
                if (!clicked.getText().equals("Clear") && !clicked.getText().equals("Enter") && !clicked.getText().equals("(-)")) {
                    if(!secondNum.equals("")){
                        if(operator.equals("/")){
                            total = Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
                        }
                        if(operator.equals("*")){
                            total = Double.parseDouble(firstNum) * Double.parseDouble(secondNum);
                        }
                        if(operator.equals("+")){
                            total = Double.parseDouble(firstNum) + Double.parseDouble(secondNum);
                        }
                        if(operator.equals("-")){
                            total = Double.parseDouble(firstNum) - Double.parseDouble(secondNum);
                        }
                        label.setText(String.valueOf(total));
                        firstNum = String.valueOf(total);
                        secondNum = "";
                        operator = "";
                        secondDecimalCnt = 0;
                        operatorDetected = false;
                    }else {
                        operator = clicked.getText();
                        operatorDetected = true;
                    }
                }else{                                  // if user is clicking Clear button
                    if(clicked.getText().equals("Clear")){
                        label.setText("0");
                        firstNum = "";
                        secondNum = "";
                        operator = "";
                        firstDecimalCnt = 0;
                        secondDecimalCnt = 0;
                        operatorDetected = false;
                    }                                   // if user is clicking Enter button
                    if(clicked.getText().equals("Enter")){
                        if(operator.equals("/")){
                            total = Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
                        }
                        if(operator.equals("*")){
                            total = Double.parseDouble(firstNum) * Double.parseDouble(secondNum);
                        }
                        if(operator.equals("+")){
                            total = Double.parseDouble(firstNum) + Double.parseDouble(secondNum);
                        }
                        if(operator.equals("-")){
                            total = Double.parseDouble(firstNum) - Double.parseDouble(secondNum);
                        }
                        label.setText(String.valueOf(total));
                        firstNum = String.valueOf(total);
                        secondNum = "";
                        operator = "";
                        secondDecimalCnt = 0;
                        operatorDetected = false;
                    }
                    if(clicked.getText().equals("(-)")){         // if user is clicking negative button (Note: only works when user clicks it
                        if(operatorDetected == false){           // after already typing in the number that they want to become negative
                            firstNum = "-" + firstNum;
                            label.setText(firstNum);
                        }else{
                            secondNum = "-" + secondNum;
                            label.setText(secondNum);
                        }
                    }
                }
            }
        }
    }
}
