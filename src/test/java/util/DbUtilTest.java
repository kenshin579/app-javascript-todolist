package util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class DbUtilTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetConnection() throws Exception {
        DbUtil dbUtil = DbUtil.getInstance();

        assertNotNull(dbUtil.getConnection());
    }
}
