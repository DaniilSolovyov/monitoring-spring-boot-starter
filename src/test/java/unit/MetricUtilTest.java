package unit;

import com.gmail.solovyov.daniil.util.MetricUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class MetricUtilTest {

    @Parameter
    public Object[] args;

    @Parameter(1)
    public String expected;

    @Parameters(name = "{index}: expected string - {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new Object[]{null, null}, "null,null"},
                {new Object[]{"Valera", 129L}, "Valera,129"}
        });
    }

    @Test
    public void argsToString() {
        String actual = MetricUtil.argsToString(args);
        Assert.assertEquals(expected, actual);
    }
}