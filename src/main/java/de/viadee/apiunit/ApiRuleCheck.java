package de.viadee.apiunit;

public class ApiRuleCheck {

    ApiRule rule;
    String location;
    String description;

    public ApiRuleCheck(ApiRule rule, String location, String description) {
        this.rule = rule;
        this.location = location;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ApiRuleCheck{ rule='" + rule + '\'' + ", location='" + location + '\'' + ", description='" + description + "'}";
    }
}
