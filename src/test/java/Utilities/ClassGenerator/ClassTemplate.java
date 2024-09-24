package Utilities.ClassGenerator;

import java.util.List;

public class ClassTemplate {
    public static String generateClass(String className, String superClass, List<String> interfaces, List<String> imports, List<Variable> variables, List<Method> methods, String packageName) {
        StringBuilder classBuilder = new StringBuilder();

        // Package declaration
        classBuilder.append("package ").append(packageName).append(";\n");

        // Add imports
        for (String importStatement : imports) {
            classBuilder.append("import ").append(importStatement).append(";\n");
        }
        classBuilder.append("\n");

        // Class declaration
        classBuilder.append("public class ").append(className);

        if (superClass != null && !superClass.isEmpty()) {
            classBuilder.append(" extends ").append(superClass);
        }

        if (interfaces != null && !interfaces.isEmpty()) {
            classBuilder.append(" implements ");
            for (int i = 0; i < interfaces.size(); i++) {
                classBuilder.append(interfaces.get(i));
                if (i < interfaces.size() - 1) {
                    classBuilder.append(", ");
                }
            }
        }

        classBuilder.append(" {\n");

        // Add variables
        for (Variable variable : variables) {
            classBuilder.append("    private ").append(variable.type).append(" ").append(variable.name)
                    .append(variable.initialValue != null ? " = " + variable.initialValue : "")
                    .append(";\n");
        }
        classBuilder.append("\n");

        // Constructor
        classBuilder.append("    public ").append(className).append("(WebDriver driver");
//        for (int i = 0; i < variables.size(); i++) {
//            Variable variable = variables.get(i);
//            classBuilder.append(variable.type).append(" ").append(variable.name);
//            if (i < variables.size() - 1) {
//                classBuilder.append(", ");
//            }
//        }
        classBuilder.append(") {\n");

        if (superClass != null && !superClass.isEmpty()) {
            classBuilder.append("        super(driver);\n");
        }

//        for (Variable variable : variables) {
//            classBuilder.append("        this.").append(variable.name).append(" = ").append(variable.name).append(";\n");
//        }
        classBuilder.append("    }\n\n");

        // Add methods
        for (Method method : methods) {
            classBuilder.append("    public ").append(method.returnType).append(" ").append(method.name).append("(");
            for (int i = 0; i < method.parameters.size(); i++) {
                Parameter parameter = method.parameters.get(i);
                classBuilder.append(parameter.type).append(" ").append(parameter.name);
                if (i < method.parameters.size() - 1) {
                    classBuilder.append(", ");
                }
            }
            classBuilder.append(") {\n");
            classBuilder.append("        ").append(method.body).append("\n");
            classBuilder.append("    }\n\n");
        }

        classBuilder.append("}\n");
        return classBuilder.toString();
    }

    public static class Variable {
        String type;
        String name;
        String initialValue;

        public Variable(String type, String name, String initialValue) {
            this.type = type;
            this.name = name;
            this.initialValue = initialValue;
        }
    }

    public static class Method {
        String returnType;
        String name;
        String body;
        List<Parameter> parameters;

        public Method(String returnType, String name, String body, List<Parameter> parameters) {
            this.returnType = returnType;
            this.name = name;
            this.body = body;
            this.parameters = parameters;
        }
    }

    public static class Parameter {
        String type;
        String name;

        public Parameter(String type, String name) {
            this.type = type;
            this.name = name;
        }
    }
}
