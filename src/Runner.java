import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Collections;

class GUI extends JFrame {
    private static int numberOfStudent = 0;
    private final int TEXTFIELD_LENGTH = 13;
    JScrollPane scrollPane;
    private JButton addBtn;
    private JButton editBtn;
    private JButton displayBtn;
    private JButton searchBtn;
    private JTextField rollNumTextField;
    private JTextField fullNameTextField;
    private JTextField semesterTextField;
    private static DefaultListModel<Student> studentDefaultList;
    private JList studentList;

    public GUI() {
        super("STUDENT MANAGEMENT SYSTEM");

        setLayout(new GridLayout(3, 1, 1, 3));

        Container container = getContentPane();


        // creating the menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // JMenu object
        JMenu menu = new JMenu();

        // JMenuItem objects
        JMenuItem addItem = new JMenuItem("Add Student");
        JMenuItem editItem = new JMenuItem("Edit Student");
        JMenuItem displayItem = new JMenuItem("Display All Students");
        JMenuItem searchItem = new JMenuItem("Search Student");

        // menu item listener
        addItem.addActionListener(
                (event) -> {
                    if (rollNumTextField.getText().length() == 0 || fullNameTextField.getText().length() == 0
                            || semesterTextField.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null,"Please Enter All Fields!","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String rollNum = rollNumTextField.getText();
                    String fullName = fullNameTextField.getText();
                    int semester = Integer.parseInt(semesterTextField.getText());

                    for (int i=0; i<numberOfStudent; i++){
                        if (studentDefaultList.get(i).getRollNumber().equals(rollNum)) {
                            JOptionPane.showMessageDialog(null,"Roll number already assigned!","Error",JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    studentDefaultList.add(numberOfStudent++, new Student(rollNum, fullName, semester)); // adding student to the list
                    rollNumTextField.setText("");
                    fullNameTextField.setText("");
                    semesterTextField.setText("");
                    scrollPane.setVisible(false);
                    JOptionPane.showMessageDialog(null,"Student Added Successfully!","Success",JOptionPane.PLAIN_MESSAGE);
                }
        );

        displayItem.addActionListener((e -> {
            scrollPane.setVisible(true);
        }));

        // Adding items to menu
        menu.add(addItem);
        menu.add(editItem);
        menu.add(displayItem);
        menu.add(searchItem);

        // adding menu to the menuBar
        menuBar.add(menu);

        // initializing textFields
        rollNumTextField = new JTextField(TEXTFIELD_LENGTH);
        fullNameTextField = new JTextField(TEXTFIELD_LENGTH);
        semesterTextField = new JTextField(TEXTFIELD_LENGTH);

        // creating the studentDefaultList list
        studentDefaultList = new DefaultListModel<>();

        add(createPanel("STUDENT DETAILS", rollNumTextField, fullNameTextField, semesterTextField, "Roll Number:", "Full Name:", "Semester"));

        // Operations panel
        JPanel operationsPanel = new JPanel(new GridLayout(1, 3));
        operationsPanel.setBorder(BorderFactory.createTitledBorder("OPERATIONS"));

        addBtn = new JButton("Add Student");
        addBtn.addActionListener((event) -> {
            if (rollNumTextField.getText().length() == 0 || fullNameTextField.getText().length() == 0
                || semesterTextField.getText().length() == 0) {
                JOptionPane.showMessageDialog(null,"Please Enter All Fields!","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }

            String rollNum = rollNumTextField.getText();
            String fullName = fullNameTextField.getText();
            int semester = Integer.parseInt(semesterTextField.getText());

            for (int i=0; i<numberOfStudent; i++){
                if (studentDefaultList.get(i).getRollNumber().equals(rollNum)) {
                    JOptionPane.showMessageDialog(null,"Roll number already assigned!","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            studentDefaultList.add(numberOfStudent++, new Student(rollNum, fullName, semester)); // adding student to the list
            rollNumTextField.setText("");
            fullNameTextField.setText("");
            semesterTextField.setText("");
            scrollPane.setVisible(false);
            JOptionPane.showMessageDialog(null,"Student Added Successfully!","Success",JOptionPane.PLAIN_MESSAGE);
        });

        editBtn = new JButton("Edit Student");
        editBtn.addActionListener((event) -> {
            if (rollNumTextField.getText().length() == 0 || fullNameTextField.getText().length() == 0
                    || semesterTextField.getText().length() == 0) {
                JOptionPane.showMessageDialog(null,"Please Enter All Fields!","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }

            String rollNum = rollNumTextField.getText();
            String fullName = fullNameTextField.getText();
            int semester = Integer.parseInt(semesterTextField.getText());

            Student studentToEdit = null;
            int i;
            for (i=0; i<numberOfStudent; i++){
                if (studentDefaultList.get(i).getRollNumber().equals(rollNum)) {
                    studentToEdit = studentDefaultList.get(i);
                    break;
                }
            }

            if(studentToEdit == null) {
                JOptionPane.showMessageDialog(null,"No student found with this roll number!","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }

            studentToEdit.setName(fullName);
            studentToEdit.setSemester(semester);

            studentDefaultList.set(i, studentToEdit); // adding student to the list
            rollNumTextField.setText("");
            fullNameTextField.setText("");
            semesterTextField.setText("");
            scrollPane.setVisible(false);
            JOptionPane.showMessageDialog(null,"Student Details Edited Successfully!","Success",JOptionPane.PLAIN_MESSAGE);
        });

        displayBtn = new JButton("Display Student");
        displayBtn.addActionListener((e -> {
            scrollPane.setVisible(true);
        }));

        searchBtn = new JButton("Search Student");
        searchBtn.addActionListener((event) -> {
            if (rollNumTextField.getText().length() == 0) {
                JOptionPane.showMessageDialog(null,"Please Enter Roll Number!","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }

            String rollNum = rollNumTextField.getText();

            Student studentToFind = null;
            int i;
            for (i=0; i<numberOfStudent; i++){
                if (studentDefaultList.get(i).getRollNumber().equals(rollNum)) {
                    studentToFind = studentDefaultList.get(i);
                    break;
                }
            }

            if(studentToFind == null) {
                JOptionPane.showMessageDialog(null,"No student found with this roll number!","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }

            rollNumTextField.setText("");
            fullNameTextField.setText("");
            semesterTextField.setText("");
            scrollPane.setVisible(false);
            JOptionPane.showMessageDialog(null,"Roll Number: "+studentToFind.getRollNumber()+"\nFull Name: "+studentToFind.getName()+"\nSemester: "+studentToFind.getSemester(),"Student Details",JOptionPane.INFORMATION_MESSAGE);
        });

        // adding components to the frame
        operationsPanel.add(addBtn);
        operationsPanel.add(editBtn);
        operationsPanel.add(displayBtn);
        operationsPanel.add(searchBtn);
        add(operationsPanel);

        // preparing JList to be displayed
        studentList = new JList(studentDefaultList);
        studentList.setVisibleRowCount(4);
        scrollPane = new JScrollPane(studentList);
        add(scrollPane);
        scrollPane.setVisible(false);
    }

    private JPanel createPanel(String legend, JComponent jComponent1, JComponent jComponent2, JComponent jComponent3, String strLabel1, String strLabel2, String strLabel3) {
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 3, 1));

        // setting border of mainPanel
        TitledBorder titledBorder = new TitledBorder(legend);
        mainPanel.setBorder(titledBorder);

        // first sub-panel
        JPanel panel1 = new JPanel(new GridLayout(1, 2, 1, 1));

        JLabel label1 = new JLabel(strLabel1);

        panel1.add(label1);
        panel1.add(jComponent1);

        // second sub-panel
        JPanel panel2 = new JPanel(new GridLayout(1, 2, 1, 1));

        JLabel label2 = new JLabel(strLabel2);

        panel2.add(label2);
        panel2.add(jComponent2);

        // third sub-panel
        JPanel panel3 = new JPanel(new GridLayout(1, 2, 1, 1));

        JLabel label3 = new JLabel(strLabel3);

        panel3.add(label3);
        panel3.add(jComponent3);

        // adding sub-panels to mainPanel
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);

        return mainPanel;
    }

    public void createAndShowFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 420);
        this.setVisible(true);
    }
}

public class Runner {
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.createAndShowFrame();
    }
}