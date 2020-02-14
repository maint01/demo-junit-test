package com.itsol.junit.category;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(value = {SlowTests.class, FastTests.class})
@Suite.SuiteClasses({A.class, B.class})
public class SlowTestSuite {
}
