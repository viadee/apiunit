package de.viadee.apiunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ApiUnitTest {

    Collection<String> packages = new ArrayList<>();
    List<ApiRule> rules = new ArrayList<>();

    public ApiUnitTest(String[] packages) {
        this.packages = new ArrayList<>(Arrays.asList(packages));
    }

    public ApiUnitTest(String packagePath) {
        packages.add(packagePath);
    }

    public void addRule(ApiRule rule){
        this.rules.add(rule);
    }

    public ApiUnitTestResultObject check(){
        JavaClasses importedClasses = new ClassFileImporter().importPackages(packages);
        ApiUnitTestResultObject result = new ApiUnitTestResultObject();
        for (ApiRule rule : rules) {
            result.concat(rule.check(importedClasses));
        }
        return result;
    }
}
