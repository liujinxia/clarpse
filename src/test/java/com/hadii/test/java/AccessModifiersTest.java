package com.hadii.test.java;

import com.hadii.clarpse.compiler.ClarpseProject;
import com.hadii.clarpse.compiler.File;
import com.hadii.clarpse.compiler.Lang;
import com.hadii.clarpse.compiler.SourceFiles;
import com.hadii.clarpse.sourcemodel.OOPSourceCodeModel;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AccessModifiersTest {

    @Test
    public void testClassLevelModifier() throws Exception {

        final String code = " public class Test { }";
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file2.java", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        OOPSourceCodeModel generatedSourceModel = parseService.result();
        assertTrue(((String) generatedSourceModel.getComponent("Test").get().modifiers().toArray()[0])
                .equalsIgnoreCase("public"));
    }

    @Test
    public void testInterfaceLevelModifier() throws Exception {

        final String code = " public interface Test { }";
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file2.java", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        OOPSourceCodeModel generatedSourceModel = parseService.result();
        assertTrue(((String) generatedSourceModel.getComponent("Test").get().modifiers().toArray()[0])
                .equalsIgnoreCase("public"));
    }

    @Test
    public void testEnumLevelModifier() throws Exception {

        final String code = "class Tester {private enum Test { }}";
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file2.java", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        OOPSourceCodeModel generatedSourceModel = parseService.result();
        assertTrue(((String) generatedSourceModel.getComponent("Tester.Test").get().modifiers().toArray()[0])
                .equalsIgnoreCase("private"));
    }

    @Test
    public void testClassMethodLevelModifier() throws Exception {

        final String code = "class Tester { private class Test { static boolean lolcakes(){} }}";
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file2.java", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        OOPSourceCodeModel generatedSourceModel = parseService.result();
        assertTrue(((String) generatedSourceModel.getComponent("Tester.Test.lolcakes()").get().modifiers().toArray()[0])
                .equalsIgnoreCase("static"));
    }

    @Test
    public void testClassConstructorLevelModifier() throws Exception {

        final String code = "class Tester { private class Test { test(){} }}";
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file2.java", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        OOPSourceCodeModel generatedSourceModel = parseService.result();
        assertTrue(generatedSourceModel.getComponent("Tester.Test.test()").get().modifiers().isEmpty());
    }

    @Test
    public void testInterfaceMethodLevelModifier() throws Exception {

        final String code = "class Tester { private interface Test { abstract boolean lolcakes(); }}";
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file2.java", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        OOPSourceCodeModel generatedSourceModel = parseService.result();
        assertTrue(((String) generatedSourceModel.getComponent("Tester.Test.lolcakes()").get().modifiers().toArray()[0])
                .equalsIgnoreCase("abstract"));
    }

    @Test
    public void testFieldVarLevelModifier() throws Exception {

        final String code = "public class Test { public static int fieldVar; }";
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file2.java", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        OOPSourceCodeModel generatedSourceModel = parseService.result();
        assertTrue(((String) generatedSourceModel.getComponent("Test.fieldVar").get().modifiers().toArray()[0])
                .equalsIgnoreCase("public"));
        assertTrue(((String) generatedSourceModel.getComponent("Test.fieldVar").get().modifiers().toArray()[1])
                .equalsIgnoreCase("static"));
    }

    @Test
    public void testMethodParamLevelModifier() throws Exception {

        final String code = "public class Test { Test(final String str){} }";
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file2.java", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        OOPSourceCodeModel generatedSourceModel = parseService.result();
        assertTrue(
                ((String) generatedSourceModel.getComponent("Test.Test(String).str").get().modifiers().toArray()[0])
                        .equalsIgnoreCase("final"));
    }

    @Test
    public void testMethodLocalVarLevelModifier() throws Exception {

        final String code = "public class Test { Test(){ final String str;} }";
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file2.java", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        OOPSourceCodeModel generatedSourceModel = parseService.result();
        assertTrue(
                generatedSourceModel.getComponent("Test.Test().str").get().modifiers().size() == 1);
    }

    @Test
    public void testMethodLocalVarLevelNoModifier() throws Exception {

        final String code = "public class Test { Test(){ String str;} }";
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file2.java", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        OOPSourceCodeModel generatedSourceModel = parseService.result();
        assertTrue(
                generatedSourceModel.getComponent("Test.Test().str").get().modifiers().isEmpty());
    }
}
