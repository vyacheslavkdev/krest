package ru.testexample.cs.client;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ClientInterface implements ActionListener {

    private JFrame frame;
    private JButton[] fieldButtons = new JButton[9];
    private JTextField textField;
    private JTextArea textArea;

    private JButton buttonSend;
    private ImageIcon crossIcon;
    private ImageIcon circleIcon;

    ClientController clientController;



    public ClientInterface(){
        loadResources();
        createForm();
    }

    private void loadResources() {
        crossIcon = new ImageIcon(this.getClass().getResource("/icon/cross.png"));
        circleIcon = new ImageIcon(this.getClass().getResource("/icon/circle.png"));
    }

    public void createForm() {
        frame = new JFrame("Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel btnPannel = new JPanel();
        btnPannel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnPannel.setPreferredSize(new Dimension(200,200));
        btnPannel.setLayout(new GridLayout(3,3,4,4));

        for (int i = 0; i < 9; i++){
            fieldButtons[i] = new JButton();
            fieldButtons[i].setActionCommand(String.valueOf(i));
            fieldButtons[i].addActionListener(this);
            btnPannel.add(fieldButtons[i]);
        }

        JPanel chatPannel = new JPanel();
        chatPannel.setLayout(new FlowLayout());
        chatPannel.setPreferredSize(new Dimension(200,200));
        chatPannel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textArea = new JTextArea(10,15);
        textArea.setEditable(false);
        textField = new JTextField(10);
        buttonSend = new JButton("Send");
        buttonSend.addActionListener(this);
        buttonSend.setMnemonic(KeyEvent.VK_ENTER);

        chatPannel.add(textArea);
        chatPannel.add(textField);
        chatPannel.add(buttonSend);

        frame.getContentPane().setLayout(new FlowLayout());
        frame.add(btnPannel);
        frame.add(chatPannel);
        frame.setSize(500,250);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Send"){
            clientController.sendMessage(textField.getText());
            textField.setText("");
        } else {
            clientController.sendField(e.getActionCommand());
        }
    }

    public void enableButtons(boolean isEnabled){
        for (int i = 0; i < 9; i++ ){
            fieldButtons[i].setEnabled(isEnabled);
        }
    }

    public void setButtonIcon(int index, boolean cross){
        if (cross){
            fieldButtons[index].setIcon(crossIcon);
        } else {
            fieldButtons[index].setIcon(circleIcon);
        }
    }

    public void restart(){
        for (int i = 0; i < 9; i++){
            fieldButtons[i].setIcon(null);
        }
    }
}
