package de.viadee.apiunit.rules;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaEnumConstant;
import de.viadee.apiunit.ApiRule;
import de.viadee.apiunit.ApiRuleCheck;
import de.viadee.apiunit.ApiUnitTest;
import de.viadee.apiunit.ApiUnitTestResultObject;

import java.util.ArrayList;
import java.util.List;

public class EnumerationValuesMustBeCapitalizedNamesWithUnderscores implements ApiRule {
    private final List<ApiRuleCheck> violations = new ArrayList<>();
    private final List<ApiRuleCheck> compliances = new ArrayList<>();

    @Override
    public ApiUnitTestResultObject check(JavaClasses importedClasses) {
        for (JavaClass importedClass : importedClasses) {
            if (importedClass.isEnum()) {
                for (JavaEnumConstant enumConstant : importedClass.getEnumConstants()) {
                    if (!valueIsCapitalizedNamesWithUnderscores(enumConstant.name())) {
                        this.violations.add(new ApiRuleCheck(this, importedClass.getFullName(), enumConstant.name() + " is not capitalized Name with Underscores"));
                    }else{
                        this.compliances.add(new ApiRuleCheck(this, importedClass.getFullName(), enumConstant.name() + " is capitalized Name with Underscores"));
                    }
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

    private boolean valueIsCapitalizedNamesWithUnderscores(String value) {
        return value.matches("[A-Z][A-Z_]*");
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }
}
