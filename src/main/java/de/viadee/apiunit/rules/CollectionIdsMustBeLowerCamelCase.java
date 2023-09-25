package de.viadee.apiunit.rules;

public class CollectionIdsMustBeLowerCamelCase extends CollectionIdsMustMatchExpression {

    public CollectionIdsMustBeLowerCamelCase() {
        super("([a-z]+[A-Z]?)*");
        this.sucessMsg = " is lower camel case";
        this.failMsg = " is not lower camel case";
    }

}
