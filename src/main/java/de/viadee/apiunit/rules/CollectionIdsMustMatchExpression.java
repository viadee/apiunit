package de.viadee.apiunit.rules;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import de.viadee.apiunit.ApiRule;
import de.viadee.apiunit.ApiRuleCheck;
import de.viadee.apiunit.ApiUnitTest;
import de.viadee.apiunit.ApiUnitTestResultObject;

import java.util.ArrayList;
import java.util.List;

public class CollectionIdsMustMatchExpression implements ApiRule {
    private final String regEx;
    String sucessMsg;
    String failMsg;
    private final List<ApiRuleCheck> violations = new ArrayList<>();
    private final List<ApiRuleCheck> compliances = new ArrayList<>();

    public CollectionIdsMustMatchExpression(String regEx) {
        this.regEx = regEx;
        sucessMsg = " does match " + this.regEx;
        failMsg = " does not match " + this.regEx;
    }

    @Override
    public ApiUnitTestResultObject check(JavaClasses importedClasses) {
        for (JavaClass importedClass : importedClasses) {
            checkMethodsForValidIdentifier(importedClass);
        }
        return new ApiUnitTestResultObject(violations, compliances);
    }

    public ApiUnitTestResultObject check(String packagePath) {
        ApiUnitTest myTest = new ApiUnitTest(packagePath);
        myTest.addRule(this);
        return myTest.check();
    }

    private void checkMethodsForValidIdentifier(JavaClass javaClass) {
        javaClass.getAllMethods().forEach(javaMethod -> {
            if (Utils.isHttpMethod(javaMethod)) {
                String collectionId = Utils.getCollectionId(javaMethod);
                if (collectionId.matches(this.regEx)) {
                    this.compliances.add(new ApiRuleCheck(this, javaMethod.getFullName(), collectionId + sucessMsg));
                } else {
                    this.violations.add(new ApiRuleCheck(this, javaMethod.getFullName(), collectionId + failMsg));
                }
            }
        });
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }
}
