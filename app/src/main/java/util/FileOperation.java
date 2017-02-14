package util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darryl on 6/1/2017.
 */

public class FileOperation {

    Context fileContext;

    File path;

    public FileOperation(Context context) {
        this.fileContext = context;
        path = this.fileContext.getFilesDir();
    }

    public void writeFile(String fileName, String strData){
        try{
            File file = new File(this.path, fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream stream = fileContext.getApplicationContext().openFileOutput(fileName, Context.MODE_APPEND);
            stream.write(strData.getBytes()); // writes the bytes
            stream.close();
            Log.e("FOwriteFile: ", "Created File");
        }catch(IOException e){
            Log.e("FOwriteFile: ", e.toString());
        }
    }

    public List<String> readFile(String fileName) {
        List<String> listStr = new ArrayList<>();

        File file = new File(path, fileName);

        try {
            FileInputStream in = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String strLine = bufferedReader.readLine();
            while (strLine != null && !strLine.trim().isEmpty()) {
                listStr.add(strLine);
                strLine = bufferedReader.readLine();
            }
            isr.close();
            in.close();
        } catch (FileNotFoundException e) {
            Log.e("FOreadFile: ", e.toString());
        } catch (IOException e) {
            Log.e("FOreadFile: ", e.toString());
        }
        return listStr;
    }
}
