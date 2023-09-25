package de.viadee.apiunit.rules;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import de.viadee.apiunit.ApiRule;
import de.viadee.apiunit.ApiRuleCheck;
import de.viadee.apiunit.ApiUnitTest;
import de.viadee.apiunit.ApiUnitTestResultObject;

import java.util.ArrayList;
import java.util.List;

public class EnumerationTypesMustBeUpperCamelCase implements ApiRule {
    private final List<ApiRuleCheck> violations = new ArrayList<>();
    private final List<ApiRuleCheck> compliances = new ArrayList<>();

    @Override
    public ApiUnitTestResultObject check(JavaClasses importedClasses) {
        for (JavaClass importedClass : importedClasses) {
            if (importedClass.isEnum()) {
                if (valueIsUpperCamelCase(importedClass.getSimpleName())) {
                    this.compliances.add(new ApiRuleCheck(this, importedClass.getFullName(), importedClass.getName() + " is Upper Camel Case"));
                }else{
                    this.violations.add(new ApiRuleCheck(this, importedClass.getFullName(), importedClass.getName() + " is not Upper Camel Case"));
                }
            }
        }
        return new ApiUnitTestResultObject(violations, compliances);
    }

    public ApiUnitTestResultObject check(String packagePath) {
        ApiUnitTest myTest = new ApiUnitTest(packagePath);
        myTest.addRule(this);
        return myTest.check();
    }

    private boolean valueIsUpperCamelCase(String value) {
        return value.matches("([A-Z][a-z]+)+");
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }
}
