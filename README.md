# ApiUnit

ApiUnit is a free and simple library for checking REST API guidelines of your Java code.
It is based on [ArchUnit](https://www.archunit.org/) and uses whose deliverd Java code structure.
ApiUnit's focus is to automatically check compliance with guidelines in projects that are developed with Spring. The analysis takes into account common Spring annotations.

## An Example

### Add the Maven Central dependency to your project

##### Maven

```
<dependency>
    <groupId>de.viadee</groupId>
    <artifactId>apiunit</artifactId>
    <version>0.1.0</version>
</dependency>
```

### Create a test

#### Check a couple of Guidelines/Rules

```java
import de.viadee.apiunit.ApiUnitTest;
import de.viadee.apiunit.ApiUnitTestResultObject;
import de.viadee.apiunit.rules.EnumerationValuesMustBeCapitalizedNamesWithUnderscores;
import de.viadee.apiunit.rules.HttpGetMethodsMustNotHaveRequestBody;
import de.viadee.apiunit.rules.HttpPatchMethodsMustHaveRequestBody;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyApiGuidelineTest {

    @Test
    public void checkApiTests() {
        ApiUnitTest myTest = new ApiUnitTest("de.viadee.project");
        myTest.addRule(new EnumerationValuesMustBeCapitalizedNamesWithUnderscores());
        myTest.addRule(new HttpGetMethodsMustNotHaveRequestBody());
        myTest.addRule(new HttpPatchMethodsMustHaveRequestBody());
        myTest.addRule(...);
        ApiUnitTestResultObject testResult = myTest.check();
        System.out.println(testResult);
        assertEquals(0, testResult.getViolationCounter());
    }
}
```

Currently, the following rules are available for consideration:
- HttpPutMethodsMustHaveRequestBody
- HttpPostMethodsMustHaveRequestBody
- HttpPatchMethodsMustHaveRequestBody
- HttpGetMethodsMustNotHaveRequestBody
- HttpDeleteMethodsMustNotHaveRequestBody
- EnumerationValuesMustBeCapitalizedNamesWithUnderscores
- EnumerationTypesMustBeUpperCamelCase
- CollectionIdsMustMatchExpression
- CollectionIdsMustBeValidCIdentifier

These rules are based on Google´s [API design guide](https://cloud.google.com/apis/design).

#### Check a single of Guideline/Rule

```java
import de.viadee.apiunit.ApiUnitTestResultObject;
import de.viadee.apiunit.rules.CollectionIdsMustBeValidCIdentifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyApiGuidelineTest {

    @Test
    public void collectionIds_Must_Be_Valid_C_Identifier() {
        ApiUnitTestResultObject testResult = new CollectionIdsMustBeValidCIdentifier().check("de.viadee.project");
        assertEquals(0, testResult.getViolationCounter());
    }
}
```

## License

ApiUnit is published under the Apache License 2.0, see http://www.apache.org/licenses/LICENSE-2.0 for details.

It redistributes some third party libraries:

* ArchUnit (https://www.archunit.org/), under Apache License 2.0
