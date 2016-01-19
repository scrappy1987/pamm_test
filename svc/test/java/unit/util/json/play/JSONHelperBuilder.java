package util.json.play;

public class JSONHelperBuilder
{
    static final String VALID_TEST_CLASS_JSON = "{\"testProp1\":\"prop1val\",\"testProp2\":\"prop2val\"}";

    static final String PROP1 = "prop1val";

    static final String PROP2 = "prop2val";

    String getJSONForValidTestClass()
    {
        return VALID_TEST_CLASS_JSON;
    }

    JSONHelperTestClass getValidTestClassObject()
    {
        return new JSONHelperTestClass(PROP1, PROP2);
    }
}
