package com.hadii.test.java;

import com.hadii.clarpse.compiler.ClarpseProject;
import com.hadii.clarpse.compiler.File;
import com.hadii.clarpse.compiler.Lang;
import com.hadii.clarpse.compiler.SourceFiles;
import com.hadii.clarpse.sourcemodel.OOPSourceCodeModel;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ChildComponentsTest {

    @Test
    public void testClassHasMethodChild() throws Exception {
        final String code = "class Test { void method(){} }";
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file2", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        final OOPSourceCodeModel generatedSourceModel = parseService.result();
        assertTrue(generatedSourceModel.getComponent("Test").get().children().toArray()[0].equals("Test.method()"));
    }

    @Test
    public void testClassHasFieldVarChild() throws Exception {
        final String code = "class Test { String fieldVar; }";
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file2", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        final OOPSourceCodeModel generatedSourceModel = parseService.result();
        assertTrue(generatedSourceModel.getComponent("Test").get().children().toArray()[0].equals("Test.fieldVar"));
    }

    @Test
    public void ignoreClassDeclaredWithinMethods() throws Exception {
        final String code = "class Test { void method() { class Tester {} } }";
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file2", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        final OOPSourceCodeModel generatedSourceModel = parseService.result();
        assertFalse(generatedSourceModel.getComponent("Test.method().Tester").isPresent());
        assertTrue(generatedSourceModel.size() == 2);
    }

    @Test
    public void testIntefaceHasMethodChild() throws Exception {
        final String code = "interface Test { void method(); }";
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file2", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        final OOPSourceCodeModel generatedSourceModel = parseService.result();
        assertTrue(generatedSourceModel.getComponent("Test").get().children().toArray()[0].equals("Test.method()"));
    }

    @Test
    public void testMethodHasMethodParamChild() throws Exception {
        final String code = "class Test { void method(String str); }";
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file2", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        final OOPSourceCodeModel generatedSourceModel = parseService.result();
        assertTrue(generatedSourceModel.getComponent("Test.method(String)").get().children().toArray()[0]
                .equals("Test.method(String).str"));
    }

    @Test
    public void testInterfaceHasConstantFieldChild() throws Exception {
        final String code = "interface Test { String NEAR_TO_QUERY; }";
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file2", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        final OOPSourceCodeModel generatedSourceModel = parseService.result();
        assertTrue(generatedSourceModel.getComponent("Test").get().children().toArray()[0].equals("Test.NEAR_TO_QUERY"));
    }

    @Test
    public void testClassHasNestedIntefaceChild() throws Exception {
        final String code = "class TestA { interface TestB { }}";
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file2", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        final OOPSourceCodeModel generatedSourceModel = parseService.result();
        assertTrue(generatedSourceModel.getComponent("TestA").get().children().toArray()[0].equals("TestA.TestB"));
    }

    @Test
    public void testClassHasNestedEnumChild() throws Exception {
        final String code = "class TestA { enum TestB { }}";
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file2", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        final OOPSourceCodeModel generatedSourceModel = parseService.result();
        assertTrue(generatedSourceModel.getComponent("TestA").get().children().toArray()[0].equals("TestA.TestB"));
    }

    @Test
    public void testEnumHasNestedConstantsChild() throws Exception {
        final String code = " enum TestA { A,B,C; }";
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file2", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        final OOPSourceCodeModel generatedSourceModel = parseService.result();
        assertTrue(generatedSourceModel.getComponent("TestA").get().children().toArray()[0].equals("TestA.A"));
        assertTrue(generatedSourceModel.getComponent("TestA").get().children().toArray()[1].equals("TestA.B"));
        assertTrue(generatedSourceModel.getComponent("TestA").get().children().toArray()[2].equals("TestA.C"));
    }

    @Test
    public void testClassWithMultipleChildren() throws Exception {
        final String code = " class TestA { String fieldVar; String method(){} interface TestB {}}";
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file2", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        final OOPSourceCodeModel generatedSourceModel = parseService.result();
        assertTrue(generatedSourceModel.getComponent("TestA").get().children().toArray()[0].equals("TestA.fieldVar"));
        assertTrue(generatedSourceModel.getComponent("TestA").get().children().toArray()[1].equals("TestA.method()"));
        assertTrue(generatedSourceModel.getComponent("TestA").get().children().toArray()[2].equals("TestA.TestB"));
    }
}
