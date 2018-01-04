package com.clarity.test;

import com.clarity.test.go.GoLangParseTest;
import com.clarity.test.go.GoLangSmokeTest;
import com.clarity.test.go.i74RegressionTest;
import com.clarity.test.java.AccessModifiersTest;
import com.clarity.test.java.AnnotationInvocationTest;
import com.clarity.test.java.ChildComponentsTest;
import com.clarity.test.java.CommentsParsingTest;
import com.clarity.test.java.ComponentExistTest;
import com.clarity.test.java.ComponentSourceFilePathTest;
import com.clarity.test.java.ComponentTypeTest;
import com.clarity.test.java.InvocationInheritanceTest;
import com.clarity.test.java.JavaDocInvocationTest;
import com.clarity.test.java.PackageAttributeTest;
import com.clarity.test.java.TypeDeclarationTest;
import com.clarity.test.java.TypeExtensionTest;
import com.clarity.test.java.TypeImplementationTest;
import com.clarity.test.javascript.JavaScriptSmokeTest;
import com.clarity.test.javascript.JavascriptParseTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Clarpse's main test suite.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ ComponentExistTest.class, PackageAttributeTest.class, ComponentTypeTest.class,
        RawFileComparisonTest.class, ComponentSourceFilePathTest.class, AnnotationInvocationTest.class,
        TypeExtensionTest.class, TypeImplementationTest.class, TypeDeclarationTest.class, ChildComponentsTest.class,
        AccessModifiersTest.class, CommentsParsingTest.class, InvocationInheritanceTest.class,
        JavascriptParseTest.class, JavaScriptSmokeTest.class, GoLangParseTest.class, UtilsTest.class,
        JavaDocInvocationTest.class, GoLangSmokeTest.class, i74RegressionTest.class
})
public class ClarpseTest {

}