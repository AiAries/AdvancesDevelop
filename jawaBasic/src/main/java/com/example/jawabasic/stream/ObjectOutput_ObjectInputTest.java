package com.example.jawabasic.stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

class ObjectOutput_ObjectInputTest {
    static class Person implements Serializable {
        String name;

        public Person(String name) {
            this.name = name;
        }
    }
    public static void main(String[] args) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("E:\\aa.txt");
            ObjectOutput objectOutput = new ObjectOutputStream(outputStream);
            FileInputStream fileIn = new FileInputStream("E:\\aa.txt");
            ObjectInput in = new ObjectInputStream(fileIn);
            objectOutput.writeObject("str");
            objectOutput.writeObject(new Person("pza"));
            System.out.println(in.readObject());
            Person o = (Person) in.readObject();
            System.out.println(o.name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
