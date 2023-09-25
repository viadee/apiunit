package de.viadee.apiunit.rules;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaParameter;
import de.viadee.apiunit.ApiRule;
import de.viadee.apiunit.ApiRuleCheck;
import de.viadee.apiunit.ApiUnitTest;
import de.viadee.apiunit.ApiUnitTestResultObject;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HttpPatchMethodsMustHaveRequestBody implements ApiRule {
    private final List<ApiRuleCheck> violations = new ArrayList<>();
    private final List<ApiRuleCheck> compliances = new ArrayList<>();

    @Override
    public ApiUnitTestResultObject check(JavaClasses importedClasses) {
        for (JavaClass importedClass : importedClasses) {
            checkMethodsForPatchMethodsForRequestBody(importedClass);
        }
        return new ApiUnitTestResultObject(violations, compliances);
    }

    public ApiUnitTestResultObject check(String packagePath) {
        ApiUnitTest myTest = new ApiUnitTest(packagePath);
        myTest.addRule(this);
        return myTest.check();
    }

    private void checkMethodsForPatchMethodsForRequestBody(JavaClass javaClass) {
        javaClass.getAllMethods().forEach(javaMethod -> {
            if (Utils.isPatchMethod(javaMethod)) {
                checkForExistingRequestBody(javaMethod);
            }
        });
    }

    private void checkForExistingRequestBody(JavaMethod method) {
        boolean hasExistingAndRequiredRequestBody = false;
        List<JavaParameter> methodParameters = method.getParameters();
        for (JavaParameter methodParameter : methodParameters) {
            Optional<RequestBody> optionalRequestBody = methodParameter.tryGetAnnotationOfType(RequestBody.class);
            if (optionalRequestBody.isPresent()) {
                if (optionalRequestBody.get().required()) {
                    hasExistingAndRequiredRequestBody = true;
                }
            }
        }
        if (!hasExistingAndRequiredRequestBody) {
            this.violations.add(new ApiRuleCheck(this, method.getFullName(), "method has no Request Body or the Request Body is set to be not required"));
        } else {
            this.compliances.add(new ApiRuleCheck(this, method.getFullName(), "method has a required Request Body"));
        }
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }
}
