package de.viadee.apiunit;
import de.viadee.apiunit.rules.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleUsages {

    @Test
    public void checkHttpGetMethodsMustNotHaveRequestBody() {
        ApiUnitTestResultObject testResult = new HttpGetMethodsMustNotHaveRequestBody().check("de.viadee.apiunit.demo");
        System.out.println(testResult);
        assertEquals(5, testResult.getComplianceCounter());
        assertEquals(1, testResult.getViolationCounter());
    }

    @Test
    public void checkHttpPostMethodsMustHaveRequestBody() {
        ApiUnitTestResultObject testResult = new HttpPostMethodsMustHaveRequestBody().check("de.viadee.apiunit.demo");
        System.out.println(testResult);
        assertEquals(1, testResult.getComplianceCounter());
        assertEquals(2, testResult.getViolationCounter());
    }

    @Test
    public void checkHttpPutMethodsMustHaveRequestBody() {
        ApiUnitTestResultObject testResult = new HttpPutMethodsMustHaveRequestBody().check("de.viadee.apiunit.demo");
        System.out.println(testResult);
        assertEquals(1, testResult.getComplianceCounter());
        assertEquals(2, testResult.getViolationCounter());
    }

    @Test
    public void checkHttpPatchMethodsMustHaveRequestBody() {
        ApiUnitTestResultObject testResult = new HttpPatchMethodsMustHaveRequestBody().check("de.viadee.apiunit.demo");
        System.out.println(testResult);
        assertEquals(1, testResult.getComplianceCounter());
        assertEquals(2, testResult.getViolationCounter());
    }

    @Test
    public void checkHttpDeleteMethodsMustNotHaveRequestBody() {
        ApiUnitTestResultObject testResult = new HttpDeleteMethodsMustNotHaveRequestBody().check("de.viadee.apiunit.demo");
        System.out.println(testResult);
        assertEquals(1, testResult.getComplianceCounter());
        assertEquals(2, testResult.getViolationCounter());
    }

    @Test
    public void checkCollectionIdsMustBeValidCIdentifier() {
        ApiUnitTestResultObject testResult = new CollectionIdsMustBeValidCIdentifier().check("de.viadee.apiunit.demo");
        System.out.println(testResult);
        assertEquals(12, testResult.getComplianceCounter());
        assertEquals(6, testResult.getViolationCounter());
    }

    @Test
    public void checkCollectionIdsMustBeLowerCamelCase() {
        ApiUnitTestResultObject testResult = new CollectionIdsMustBeLowerCamelCase().check("de.viadee.apiunit.demo");
        System.out.println(testResult);
        assertEquals(12, testResult.getComplianceCounter());
        assertEquals(6, testResult.getViolationCounter());
    }

    @Test
    public void checkCollectionIdsMustMatchExpression() {
        ApiUnitTestResultObject testResult = new CollectionIdsMustMatchExpression("([a-z]+-)*[a-z]+").check("de.viadee.apiunit.demo");
        System.out.println(testResult);
        assertEquals(12, testResult.getComplianceCounter());
        assertEquals(6, testResult.getViolationCounter());
    }

    @Test
    public void checkEnumerationValuesMustBeCapitalizedNamesWithUnderscores() {
        ApiUnitTestResultObject testResult = new EnumerationValuesMustBeCapitalizedNamesWithUnderscores().check("de.viadee.apiunit.demo");
        System.out.println(testResult);
        assertEquals(3, testResult.getComplianceCounter());
        assertEquals(1, testResult.getViolationCounter());
    }

    @Test
    public void checkEnumerationTypesMustBeUpperCamelCase() {
        ApiUnitTestResultObject testResult = new EnumerationTypesMustBeUpperCamelCase().check("de.viadee.apiunit.demo");
        System.out.println(testResult);
        assertEquals(1, testResult.getComplianceCounter());
        assertEquals(0, testResult.getViolationCounter());
    }

    @Test
    public void checkMultipleRules() {
        ApiUnitTest myTest = new ApiUnitTest("de.viadee.apiunit.demo");
        myTest.addRule(new CollectionIdsMustBeValidCIdentifier());
        myTest.addRule(new EnumerationValuesMustBeCapitalizedNamesWithUnderscores());
        myTest.addRule(new HttpGetMethodsMustNotHaveRequestBody());
        myTest.addRule(new HttpPatchMethodsMustHaveRequestBody());
        ApiUnitTestResultObject testResult = myTest.check();
        System.out.println(testResult);
        //assertEquals(0, testResult.getViolationCounter());
        assertEquals(10, testResult.getViolationCounter());
    }
}
