package util.json.play;

import org.junit.Assert;
import org.junit.Test;

public class JSONHelperTest
{
    private JSONHelperBuilder builder = new JSONHelperBuilder();

    @Test
    public void testCreateValidObjectFromJSON()
    {
        JSONHelper jsonHelper = new JSONHelper();

        JSONHelperTestClass testObj = jsonHelper.getObjectForJSON(JSONHelperBuilder.VALID_TEST_CLASS_JSON,
                JSONHelperTestClass.class);

        Assert.assertNotNull(testObj);

        Assert.assertEquals(testObj.getTestProp1(), JSONHelperBuilder.PROP1);

        Assert.assertEquals(testObj.getTestProp2(), JSONHelperBuilder.PROP2);
    }

    @Test
    public void testCreateJSONFromValidObject()
    {
        JSONHelper jsonHelper = new JSONHelper();

        String jsonString = jsonHelper.getJSONForObject(builder.getValidTestClassObject());

        Assert.assertNotNull(jsonString);

        Assert.assertEquals(jsonString, JSONHelperBuilder.VALID_TEST_CLASS_JSON);
    }

    @Test
    public void testCreateJSONErrorMessageWithNull()
    {
        JSONHelper jsonHelper = new JSONHelper();
        try
        {
            jsonHelper.createJSONErrorMessage(null, 1, "", "");

            Assert.fail("Null pointer Exception should have been thrown");
        }
        catch (NullPointerException npe)
        {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testCreateJSONErrorMessageWithPreviousMessage()
    {
        JSONHelper jsonHelper = new JSONHelper();
        String previousJSONObject = "{\"test\": \"testvalue\"}";
        String testObject = "{\"test\": \"testvalue\", \"returnCode\": \"1\", \"errorReason\": \"value\", \"errorMessage\": \"value\"}";
        String returnString = jsonHelper.createJSONErrorMessage(previousJSONObject, 1, "value", "value");
        Assert.assertEquals(testObject, returnString);
    }
}
