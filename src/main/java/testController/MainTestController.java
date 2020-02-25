package testController;

import com.intellij.openapi.project.Project;
import ide.IDEA;
import ide.PsiHandler;
import ide.Tester;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.java.PsiMethodCallExpressionImpl;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import test.Test;
import test.UnitTests;

import java.io.File;
import java.net.URL;
import java.util.*;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectMethod;

public class MainTestController {

    //https://github.com/rastocny/mock-management/blob/master/src/mock/manager/data/psiparser/PsiParser.java


    public void runAllTests(String path, Project project) throws InterruptedException {
        File root = new File(path);
//        for (File file : Objects.requireNonNull(root.listFiles())) {
//            IDEA methodGather = new IDEA();
//            Tester tester = new Tester(methodGather.getAllMethodsByClass(file.getClass()));
//            tester.runTests();
//        }
        PsiHandler psiHandler = new PsiHandler();
        List<PsiMethod> tests = psiHandler.getAllTests(project);
        List<Test> x = new ArrayList<>();
        for (PsiMethod test : tests) {
            HashSet<PsiMethod> a = psiHandler.traverseBodyToFindAllMethodUsages(test.getBody());
            x.add(new Test(test, Objects.requireNonNull(test.getContainingClass()),a));
            System.out.println("-------");
        }
        for (Test t : x){
            t.printTestParams();
        }
    }
}
