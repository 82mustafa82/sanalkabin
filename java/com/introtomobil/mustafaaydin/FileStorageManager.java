package com.introtomobil.mustafaaydin;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileStorageManager {

    public static java.util.List<Clothes> readClothes(Context context, int pos){

        java.util.List<Wardrop> wardrops = readWardrops(context);
        return wardrops.get(pos).getClothesList();
    }
    public static void addClothes(Context context, int wno, Clothes clothes){
        java.util.List<Wardrop> wList = readWardrops(context);
        Wardrop targetWardop = null;
        int idx=0, i=0;
        for(Wardrop w:wList){
            if (w.getId()==wno){
                targetWardop = w;
                idx = i;
            }
            i++;
        }
        targetWardop.addToClothesList(clothes);
        wList.set(idx,targetWardop);
        writeWardrops(context, wList);
    }

    public static void removeClothes(Context context, int wno, int cno){
        java.util.List<Wardrop> wList = readWardrops(context);
        Wardrop targetWardop = null;
        int idx=0, i=0;
        for(Wardrop w:wList){
            if (w.getId()==wno){
                targetWardop = w;
                idx = i;
            }
            i++;
        }
        targetWardop.deleteFromClothesList(cno);
        wList.set(idx, targetWardop);
        writeWardrops(context, wList);
    }
    public static void removeWardrop(Context context, int wno){
        java.util.List<Wardrop> wList = readWardrops(context);
        Wardrop targetWardop = null;
        for(Wardrop w:wList){
            if (w.getId()==wno){
                targetWardop = w;
            }
        }
        wList.remove(targetWardop);
        writeWardrops(context, wList);
    }



    public static java.util.List<Wardrop> readWardrops(Context context){
        File StorageDir = new File(Environment.getExternalStorageDirectory()+ "/Android/data/"+ context.getPackageName());
        File fileDir = new File(StorageDir.getPath(), "WardropList" );
        ObjectInputStream input = null;
        java.util.List<Wardrop> myWardrops = null;
        try {
            input = new ObjectInputStream(new FileInputStream(fileDir));
            myWardrops = (java.util.List<Wardrop>) input.readObject();
            input.close();
            return myWardrops;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Wardrop>();
    }

    public static void addWardrops(Context context, Wardrop newWardop){
        java.util.List<Wardrop> WardropList = readWardrops(context);
        WardropList.add(newWardop);
        writeWardrops(context, WardropList);
    }
    public static void writeWardrops(Context context, java.util.List<Wardrop> WardropList){
        File StorageDir = new File(Environment.getExternalStorageDirectory()+ "/Android/data/"+ context.getPackageName());
        File fileDir = new File(StorageDir.getPath() , "WardropList");
        try {
            FileOutputStream fileout = new FileOutputStream(fileDir);
            ObjectOutputStream out = new ObjectOutputStream(fileout);
            out.writeObject(WardropList);
            out.close();
            Toast.makeText(context,"Wardrop Created",Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
