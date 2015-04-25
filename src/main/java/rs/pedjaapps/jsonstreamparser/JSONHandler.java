package rs.pedjaapps.jsonstreamparser;

/**
 * Created by pedja on 25.4.15..
 */
public abstract class JSONHandler
{
    /**
     * Called when json object start tag '{' is encountered while reading stream.<br>
     * This is a good place to instantiate your java object that should represent this json object
     * @param objectName this is the name (key) of the object. Can be null if for example this object belongs to an array
     * @see #objectEnd(String)*/
    public abstract void objectStart(String objectName);

    /**
     * Called when json object end tag '}' is encountered while reading stream.<br>
     * @param objectName this is the name (key) of the object. Can be null if for example this object belongs to an array
     * @see #objectStart(String)*/
    public abstract void objectEnd(String objectName);

    /**
     * Called when json array start tag '[' is encountered while reading stream.<br>
     * This is a good place to for example instantiate your java array/list that should represent this json array
     * @param arrayName this is the name (key) of the object. Can be null if for example this array is top level element of this json
     * @see #arrayEnd(String) */
    public abstract void arrayStart(String arrayName);

    /**
     * Called when json array end tag ']' is encountered while reading stream.<br>
     * @param arrayName this is the name (key) of the object. Can be null if for example this array is top level element of this json
     * @see #arrayStart(String) */
    public abstract void arrayEnd(String arrayName);

    /**
     * This will be called for every value encountered while reading stream.*/
    public abstract void value(String value);
}
