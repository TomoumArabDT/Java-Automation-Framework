package Utilities.ClassGenerator;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class DynamicClassLoader {
    public static void main(String[] args) {
        try {
            URL[] urls = new URL[]{new URL("file:./")};
            URLClassLoader classLoader = new URLClassLoader(urls);
            Class<?> generatedClass = classLoader.loadClass("MyGeneratedClass");

            Object instance = generatedClass.getConstructor(String.class, int.class).newInstance("Hello", 42);

            Method myFunction = generatedClass.getMethod("myFunction");
            myFunction.invoke(instance);

            Method anotherFunction = generatedClass.getMethod("anotherFunction");
            anotherFunction.invoke(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

