package ut.net.daragh.jira.mailparser;

import org.junit.Test;
import net.daragh.jira.mailparser.MyPluginComponent;
import net.daragh.jira.mailparser.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}