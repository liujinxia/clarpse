package com.hadii.test.java;

import com.hadii.clarpse.compiler.ClarpseProject;
import com.hadii.clarpse.compiler.File;
import com.hadii.clarpse.compiler.Lang;
import com.hadii.clarpse.compiler.SourceFiles;
import com.hadii.clarpse.reference.ComponentReference;
import com.hadii.clarpse.sourcemodel.OOPSourceCodeModel;
import org.junit.Assert;
import org.junit.Test;

/**
 * Ensure component type extensions invocations are accurate.
 */
public class TypeExtensionReferenceTest {

    @Test
    public void testAccurateExtendedTypes() throws Exception {
        final String code = "package com; \n public class ClassA extends ClassD<?> { }";
        OOPSourceCodeModel generatedSourceModel;
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file1", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        generatedSourceModel = parseService.result();
        Assert.assertTrue(((ComponentReference) generatedSourceModel.getComponent("com.ClassA").get().references()
                .toArray()[0]).invokedComponent().equals("com.ClassD"));
    }

    @Test
    public void testAccurateExtendedTypesSize() throws Exception {
        final String code = "package com; \n public class ClassA extends ClassD { }";
        OOPSourceCodeModel generatedSourceModel;
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file1", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        generatedSourceModel = parseService.result();
        Assert.assertTrue(generatedSourceModel.getComponent("com.ClassA").get().references().size() == 1);
    }

    @Test
    public void testAccurateExtendedTypesForNestedClass() throws Exception {
        final String code = "package com; \n public class ClassA { public class ClassB extends ClassD{} }";
        OOPSourceCodeModel generatedSourceModel;
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file1", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        generatedSourceModel = parseService.result();
        Assert.assertTrue(((ComponentReference) generatedSourceModel.getComponent("com.ClassA.ClassB")
                .get().references().toArray()[0]).invokedComponent().equals("com.ClassD"));

        Assert.assertTrue(generatedSourceModel.getComponent("com.ClassA.ClassB").get().references().size() == 1);
    }

    @Test
    public void testAccurateExtendedTypesSizeForNestedClass() throws Exception {
        final String code = "package com; \n public class ClassA { public class ClassB extends ClassD{} }";
        OOPSourceCodeModel generatedSourceModel;
        final SourceFiles rawData = new SourceFiles(Lang.JAVA);
        rawData.insertFile(new File("file1", code));
        final ClarpseProject parseService = new ClarpseProject(rawData);
        generatedSourceModel = parseService.result();
        Assert.assertTrue(generatedSourceModel.getComponent("com.ClassA.ClassB").get().references().size() == 1);
    }
}
