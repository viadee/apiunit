package de.viadee.apiunit;

import java.util.ArrayList;
import java.util.List;

public class ApiUnitTestResultObject {
    List<ApiRuleCheck> violations = new ArrayList<>();
    List<ApiRuleCheck> compliances = new ArrayList<>();

    public ApiUnitTestResultObject() {

    }

    public ApiUnitTestResultObject(List<ApiRuleCheck> violations, List<ApiRuleCheck> compliances) {
        this.violations = violations;
        this.compliances = compliances;
    }

    public int getViolationCounter() {
        return violations.size();
    }

    public int getComplianceCounter() {
        return compliances.size();
    }

    public void addViolations(List<ApiRuleCheck> violations) {
        this.violations.addAll(violations);
    }

    public void addCompliances(List<ApiRuleCheck> compliances) {
        this.compliances.addAll(compliances);
    }

    public List<ApiRuleCheck> getViolations() {
        return violations;
    }

    public List<ApiRuleCheck> getCompliances() {
        return compliances;
    }

    public void concat(ApiUnitTestResultObject tmp) {
        this.addViolations(tmp.getViolations());
        this.addCompliances(tmp.getCompliances());
    }

    @Override
    public String toString() {
        return "ApiUnitTestResultObject{ \r\n \tviolations (n="+getViolationCounter()+")= [\r\n" + buildViolationsString() + " \t] \r\n \tcompliances (n="+getComplianceCounter()+")= [\r\n" + buildCompliancesString() + " \t] \r\n}";
    }

    private String buildViolationsString(){
        StringBuilder violationsString = new StringBuilder();
        for (int i = 0; i < violations.size(); i++) {
            violationsString.append("\t \t");
            violationsString.append(violations.get(i).toString());
            if(i != (violations.size()-1)){
                violationsString.append(",");
            }
            violationsString.append("\r\n");
        }
        return violationsString.toString();
    }

    private String buildCompliancesString(){
        StringBuilder compliancesString = new StringBuilder();
        for (int i = 0; i < compliances.size(); i++) {
            compliancesString.append("\t \t");
            compliancesString.append(compliances.get(i).toString());
            if(i != (compliances.size()-1)){
                compliancesString.append(",");
            }
            compliancesString.append("\r\n");
        }
        return compliancesString.toString();
    }
}
