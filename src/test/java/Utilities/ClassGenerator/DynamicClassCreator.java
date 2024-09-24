package Utilities.ClassGenerator;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DynamicClassCreator {
    public static void createClass(String packageName, String className, String classContent) throws IOException {
        // Define the path where the generated class will be placed
        String packagePath = packageName.replace(".", File.separator);
        String directoryPath = "src/test/java/" + packagePath;

        // Create the directory structure based on the package name
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Create the file with the appropriate path
        String fileName = directoryPath + "/" + className + ".java";
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(classContent);
        }

        // Compile the generated class
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, fileName);
    }

    public static void main(String[] args) {
        String packageName = "org.example";
        String className = "MyGeneratedClass";
        String superClass = "TestBase";  // Specify that the generated class should inherit from TestBase

        // Define interfaces (optional)
        List<String> interfaces = new ArrayList<>();

        // Define imports
        List<String> imports = new ArrayList<>();
        imports.add("java.util.List");
        imports.add("java.util.ArrayList");

        // Define variables
        List<ClassTemplate.Variable> variables = new ArrayList<>();
        variables.add(new ClassTemplate.Variable("String", "myVar", "\"Hello World\""));
        variables.add(new ClassTemplate.Variable("int", "myInt", "42"));

        // Define methods with parameters
        List<ClassTemplate.Method> methods = new ArrayList<>();

        // Method 1: No parameters, void return type
        methods.add(new ClassTemplate.Method("void", "help", "System.out.println(\"myVar: \" + myVar + \", myInt: \" + myInt);", new ArrayList<>()));

        // Method 2: One parameter, void return type
        List<ClassTemplate.Parameter> killParams = new ArrayList<>();
        killParams.add(new ClassTemplate.Parameter("String", "message"));
        methods.add(new ClassTemplate.Method("void", "kill", "System.out.println(message);", killParams));

        // Generate the class content
        String classContent = ClassTemplate.generateClass(className, superClass, interfaces, imports, variables, methods, packageName);

        // Create the class
        try {
            createClass(packageName, className, classContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
