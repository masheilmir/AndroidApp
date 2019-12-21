package com.b07.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DatabaseSerializer {

    public static void serialize(Context context, Serializable obj, String fileName) throws IOException {
        try {
            ObjectOutputStream out =
                    new ObjectOutputStream(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            out.writeObject(obj);
            out.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Object deserialize(Context context, String fileName)
            throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream in =
                    new ObjectInputStream(context.openFileInput(fileName));
            Object obj = in.readObject();
            in.close();
            return obj;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
    }

}

