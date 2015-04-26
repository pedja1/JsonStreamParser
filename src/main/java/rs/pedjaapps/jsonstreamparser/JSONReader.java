package rs.pedjaapps.jsonstreamparser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by pedja on 25.4.15..
 */
public class JSONReader
{
    private final JSONHandler jsonHandler;
    private final JsonFactory jFactory;

    public JSONReader(JSONHandler jsonHandler)
    {
        this.jsonHandler = jsonHandler;
        if(jsonHandler == null)
        {
            throw new IllegalArgumentException("JSONHandler cannot be null");
        }
        jFactory = new JsonFactory();
    }

    public void parse(InputStream stream) throws JSONException
    {
        try
        {
            JsonParser jParser = jFactory.createParser(stream);
            JsonToken currentToken;
            while ((currentToken = jParser.nextToken()) != null)
            {
                switch (currentToken)
                {
                    case NOT_AVAILABLE:
                        break;
                    case START_OBJECT:
                        jsonHandler.objectStart(jParser.getCurrentName());
                        break;
                    case END_OBJECT:
                        jsonHandler.objectEnd(jParser.getCurrentName());
                        break;
                    case START_ARRAY:
                        jsonHandler.arrayStart(jParser.getCurrentName());
                        break;
                    case END_ARRAY:
                        jsonHandler.arrayEnd(jParser.getCurrentName());
                        break;
                    case VALUE_STRING:
                    case VALUE_NUMBER_INT:
                    case VALUE_NUMBER_FLOAT:
                    case VALUE_TRUE:
                    case VALUE_FALSE:
                    case VALUE_NULL:
                        jsonHandler.value(jParser.getText());
                        break;
                }
            }
        }
        catch (IOException e)
        {
            throw new JSONException(e);
        }
    }
}
