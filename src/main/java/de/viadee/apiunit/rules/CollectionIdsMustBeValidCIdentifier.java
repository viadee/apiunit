package de.viadee.apiunit.rules;

public class CollectionIdsMustBeValidCIdentifier extends CollectionIdsMustMatchExpression {
    public CollectionIdsMustBeValidCIdentifier() {
        super("[_a-zA-Z][_a-zA-Z0-9]*");
        this.sucessMsg = " is valid c identifier";
        this.failMsg = " is no valid c identifier";
    }

}
