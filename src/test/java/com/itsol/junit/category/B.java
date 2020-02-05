package com.itsol.junit.category;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category({ FastTests.class })
public class B {
    @Test
    public void c() {
        Assert.assertEquals("B.c", "true", "true");
    }
}
