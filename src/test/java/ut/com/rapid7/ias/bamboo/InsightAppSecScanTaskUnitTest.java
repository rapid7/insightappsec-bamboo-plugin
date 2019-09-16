package ut.com.rapid7.ias.bamboo;

import org.junit.Test;
import com.rapid7.ias.bamboo.impl.InsightAppSecScanTask;

import static org.junit.Assert.assertEquals;

public class InsightAppSecScanTaskUnitTest
{
    @Test
    public void testMyName()
    {
        InsightAppSecScanTask component = new InsightAppSecScanTask(null, null);
        assertEquals("names do not match!", "Rapid7 InsightAppSec",component.getName());
    }
}