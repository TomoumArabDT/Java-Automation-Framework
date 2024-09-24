package Utilities.ClassGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ClassGeneratorUI {

    private JFrame frame;
    private JTextField packageNameField;
    private JTextField classNameField;
    private JTextField superClassField;
    private JTextField interfaceField;
    private JTextField variableNameField;
    private JTextField variableTypeField;
    private JTextField variableInitialValueField;
    private JTextField methodNameField;
    private JTextField methodBodyField;
    private JTextField methodReturnTypeField;
    private JTextArea methodParametersArea;
    private JTextField importField;
    private DefaultListModel<String> importsListModel;
    private DefaultListModel<String> variablesListModel;
    private DefaultListModel<String> methodsListModel;
    private DefaultListModel<String> interfacesListModel;
    private List<ClassTemplate.Variable> variables;
    private List<ClassTemplate.Method> methods;
    private List<String> imports;
    private List<String> interfaces;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ClassGeneratorUI window = new ClassGeneratorUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ClassGeneratorUI() {
        variables = new ArrayList<>();
        methods = new ArrayList<>();
        imports = new ArrayList<>();
        interfaces = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Package Name
        JLabel lblPackageName = new JLabel("Package Name:");
        lblPackageName.setBounds(10, 10, 100, 20);
        frame.getContentPane().add(lblPackageName);

        packageNameField = new JTextField();
        packageNameField.setBounds(120, 10, 200, 20);
        frame.getContentPane().add(packageNameField);
        packageNameField.setColumns(10);

        // Class Name
        JLabel lblClassName = new JLabel("Class Name:");
        lblClassName.setBounds(10, 40, 100, 20);
        frame.getContentPane().add(lblClassName);

        classNameField = new JTextField();
        classNameField.setBounds(120, 40, 200, 20);
        frame.getContentPane().add(classNameField);
        classNameField.setColumns(10);

        // Super Class
        JLabel lblSuperClass = new JLabel("Super Class:");
        lblSuperClass.setBounds(10, 70, 100, 20);
        frame.getContentPane().add(lblSuperClass);

        superClassField = new JTextField();
        superClassField.setBounds(120, 70, 200, 20);
        frame.getContentPane().add(superClassField);
        superClassField.setColumns(10);

        // Interfaces
        JLabel lblInterfaces = new JLabel("Interfaces:");
        lblInterfaces.setBounds(10, 100, 100, 20);
        frame.getContentPane().add(lblInterfaces);

        interfaceField = new JTextField();
        interfaceField.setBounds(120, 100, 200, 20);
        frame.getContentPane().add(interfaceField);
        interfaceField.setColumns(10);

        JButton btnAddInterface = new JButton("Add Interface");
        btnAddInterface.setBounds(330, 100, 120, 20);
        frame.getContentPane().add(btnAddInterface);

        interfacesListModel = new DefaultListModel<>();
        JList<String> interfacesList = new JList<>(interfacesListModel);
        JScrollPane interfacesScrollPane = new JScrollPane(interfacesList);
        interfacesScrollPane.setBounds(10, 130, 440, 80);
        frame.getContentPane().add(interfacesScrollPane);

        // Imports
        JLabel lblImport = new JLabel("Import:");
        lblImport.setBounds(10, 220, 100, 20);
        frame.getContentPane().add(lblImport);

        importField = new JTextField();
        importField.setBounds(120, 220, 200, 20);
        frame.getContentPane().add(importField);
        importField.setColumns(10);

        JButton btnAddImport = new JButton("Add Import");
        btnAddImport.setBounds(330, 220, 120, 20);
        frame.getContentPane().add(btnAddImport);

        importsListModel = new DefaultListModel<>();
        JList<String> importsList = new JList<>(importsListModel);
        JScrollPane importsScrollPane = new JScrollPane(importsList);
        importsScrollPane.setBounds(10, 250, 440, 80);
        frame.getContentPane().add(importsScrollPane);

        // Variables
        JLabel lblVariableName = new JLabel("Variable Name:");
        lblVariableName.setBounds(10, 340, 100, 20);
        frame.getContentPane().add(lblVariableName);

        variableNameField = new JTextField();
        variableNameField.setBounds(120, 340, 200, 20);
        frame.getContentPane().add(variableNameField);
        variableNameField.setColumns(10);

        JLabel lblVariableType = new JLabel("Variable Type:");
        lblVariableType.setBounds(10, 370, 100, 20);
        frame.getContentPane().add(lblVariableType);

        variableTypeField = new JTextField();
        variableTypeField.setBounds(120, 370, 200, 20);
        frame.getContentPane().add(variableTypeField);
        variableTypeField.setColumns(10);

        JLabel lblInitialValue = new JLabel("Initial Value:");
        lblInitialValue.setBounds(10, 400, 100, 20);
        frame.getContentPane().add(lblInitialValue);

        variableInitialValueField = new JTextField();
        variableInitialValueField.setBounds(120, 400, 200, 20);
        frame.getContentPane().add(variableInitialValueField);
        variableInitialValueField.setColumns(10);

        JButton btnAddVariable = new JButton("Add Variable");
        btnAddVariable.setBounds(330, 340, 120, 80);
        frame.getContentPane().add(btnAddVariable);

        variablesListModel = new DefaultListModel<>();
        JList<String> variablesList = new JList<>(variablesListModel);
        JScrollPane variablesScrollPane = new JScrollPane(variablesList);
        variablesScrollPane.setBounds(10, 430, 440, 80);
        frame.getContentPane().add(variablesScrollPane);

        // Methods
        JLabel lblMethodName = new JLabel("Method Name:");
        lblMethodName.setBounds(10, 520, 100, 20);
        frame.getContentPane().add(lblMethodName);

        methodNameField = new JTextField();
        methodNameField.setBounds(120, 520, 200, 20);
        frame.getContentPane().add(methodNameField);
        methodNameField.setColumns(10);

        JLabel lblMethodReturnType = new JLabel("Return Type:");
        lblMethodReturnType.setBounds(10, 550, 100, 20);
        frame.getContentPane().add(lblMethodReturnType);

        methodReturnTypeField = new JTextField();
        methodReturnTypeField.setBounds(120, 550, 200, 20);
        frame.getContentPane().add(methodReturnTypeField);
        methodReturnTypeField.setColumns(10);

        JLabel lblMethodParameters = new JLabel("Parameters (type name, type name):");
        lblMethodParameters.setBounds(10, 580, 300, 20);
        frame.getContentPane().add(lblMethodParameters);

        methodParametersArea = new JTextArea();
        methodParametersArea.setBounds(10, 610, 310, 40);
        frame.getContentPane().add(methodParametersArea);

        JLabel lblMethodBody = new JLabel("Method Body:");
        lblMethodBody.setBounds(10, 660, 100, 20);
        frame.getContentPane().add(lblMethodBody);

        methodBodyField = new JTextField();
        methodBodyField.setBounds(120, 660, 200, 20);
        frame.getContentPane().add(methodBodyField);
        methodBodyField.setColumns(10);

        JButton btnAddMethod = new JButton("Add Method");
        btnAddMethod.setBounds(330, 620, 120, 60);
        frame.getContentPane().add(btnAddMethod);

        methodsListModel = new DefaultListModel<>();
        JList<String> methodsList = new JList<>(methodsListModel);
        JScrollPane methodsScrollPane = new JScrollPane(methodsList);
        methodsScrollPane.setBounds(10, 710, 440, 80);
        frame.getContentPane().add(methodsScrollPane);

        // Generate Class Button
        JButton btnGenerateClass = new JButton("Generate Class");
        btnGenerateClass.setBounds(460, 670, 200, 50);
        frame.getContentPane().add(btnGenerateClass);

        // Preview Code Button
        JButton btnPreviewCode = new JButton("Preview Code");
        btnPreviewCode.setBounds(460, 600, 200, 50);
        frame.getContentPane().add(btnPreviewCode);

        // Event Listeners
        btnAddInterface.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String interfaceName = interfaceField.getText();
                if (!interfaceName.isEmpty()) {
                    interfaces.add(interfaceName);
                    interfacesListModel.addElement(interfaceName);
                    interfaceField.setText("");
                }
            }
        });

        btnAddImport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String importName = importField.getText();
                if (!importName.isEmpty()) {
                    imports.add(importName);
                    importsListModel.addElement(importName);
                    importField.setText("");
                }
            }
        });

        btnAddVariable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String varName = variableNameField.getText();
                String varType = variableTypeField.getText();
                String initialValue = variableInitialValueField.getText();
                if (isValidJavaIdentifier(varName) && !varType.isEmpty()) {
                    variables.add(new ClassTemplate.Variable(varType, varName, initialValue));
                    variablesListModel.addElement(varType + " " + varName + " = " + initialValue);
                    variableNameField.setText("");
                    variableTypeField.setText("");
                    variableInitialValueField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid variable name or type.");
                }
            }
        });

        btnAddMethod.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String methodName = methodNameField.getText();
                String returnType = methodReturnTypeField.getText();
                String methodBody = methodBodyField.getText();
                String[] params = methodParametersArea.getText().split(",\\s*");
                List<ClassTemplate.Parameter> parameters = new ArrayList<>();


                    for (String param : params) {
                        String[] parts = param.split("\\s+");
                        if (parts.length == 2) {
                            parameters.add(new ClassTemplate.Parameter(parts[0], parts[1]));
                        }
                    }


                if (isValidJavaIdentifier(methodName) && !returnType.isEmpty()) {
                    methods.add(new ClassTemplate.Method(returnType, methodName, methodBody, parameters));
                    methodsListModel.addElement(returnType + " " + methodName + "(" + methodParametersArea.getText() + ")");
                    methodNameField.setText("");
                    methodReturnTypeField.setText("");
                    methodParametersArea.setText("");
                    methodBodyField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid method name or return type.");
                }
            }
        });

        btnPreviewCode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String className = classNameField.getText();
                String superClass = superClassField.getText();
                String packageName = packageNameField.getText();

                if (packageName.isEmpty() || className.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Package name and class name are required.");
                    return;
                }

                String classContent = ClassTemplate.generateClass(
                        className,
                        superClass,
                        interfaces,
                        imports,
                        variables,
                        methods,
                        packageName
                );

                JTextArea codePreviewArea = new JTextArea(classContent);
                codePreviewArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
                codePreviewArea.setEditable(false);

                JScrollPane codeScrollPane = new JScrollPane(codePreviewArea);

                JOptionPane.showMessageDialog(frame, codeScrollPane, "Code Preview", JOptionPane.PLAIN_MESSAGE);
            }
        });

        btnGenerateClass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String className = classNameField.getText();
                String superClass = superClassField.getText();
                String packageName = packageNameField.getText();

                if (packageName.isEmpty() || className.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Package name and class name are required.");
                    return;
                }

                String classContent = ClassTemplate.generateClass(
                        className,
                        superClass,
                        interfaces,
                        imports,
                        variables,
                        methods,
                        packageName
                );

                try {
                    DynamicClassCreator.createClass(packageName, className, classContent);
                    JOptionPane.showMessageDialog(frame, "Class generated successfully!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Failed to generate class!");
                }
            }
        });
    }

    private boolean isValidJavaIdentifier(String identifier) {
        return Pattern.matches("\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierPart}*", identifier);
    }
}
