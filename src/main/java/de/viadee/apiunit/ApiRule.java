package de.viadee.apiunit;

import com.tngtech.archunit.core.domain.JavaClasses;

public interface ApiRule {

    ApiUnitTestResultObject check(JavaClasses importedClasses);

    ApiUnitTestResultObject check(String packagePath);

    @Override
    String toString();
}
