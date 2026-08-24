package flik;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestFlik {
    @Test
    public void TestOrigin() {
        assertTrue(Flik.isSameNumber(3, 3));
        assertTrue(Flik.isSameNumber(129, 129));
    }
    @Test
    public void TestSteve() {
    }
}
