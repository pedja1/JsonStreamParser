package rs.pedjaapps.jsonstreamparser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by pedja on 25.4.15..
 */
public class Main
{
    public static void main(String[] args) throws IOException, JSONException
    {
        testJsonStreamParser();
        testJsonBindParser();
    }

    private static void testJsonBindParser() throws IOException
    {
        long start = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("/home/pedja/json/test_json.json")));
        String line;
        while((line = reader.readLine()) != null)
        {
            builder.append(line);
        }
        JSONObject jsonObject = new JSONObject(builder.toString());
        System.out.println();
        System.out.println("parsing took: " + (System.currentTimeMillis() - start));
    }

    private static void testJsonStreamParser() throws FileNotFoundException, JSONException
    {
        long start = System.currentTimeMillis();
        FileInputStream stream = new FileInputStream(new File("/home/pedja/json/test_json.json"));
        JSONReader reader = new JSONReader(new JSONHandler()
        {
            @Override
            public void objectStart(String objectName)
            {
                //System.out.println("objectStart: " + objectName);
            }

            @Override
            public void objectEnd(String objectName)
            {
                //System.out.println("objectEnd: " + objectName);
            }

            @Override
            public void arrayStart(String arrayName)
            {
                //System.out.println("arrayStart: " + arrayName);
            }

            @Override
            public void arrayEnd(String arrayName)
            {
                //System.out.println("arrayEnd: " + arrayName);
            }

            @Override
            public void value(String value)
            {
                //System.out.println("value: " + value);
            }
        });
        reader.parse(stream);
        System.out.println();
        System.out.println("parsing took: " + (System.currentTimeMillis() - start));
    }
}
