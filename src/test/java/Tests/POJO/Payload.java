package Tests.POJO;

public class Payload {

    String summary;
    String description;
    Projects project;
    IssueType issuetype;

    public Payload(String summary, String description, IssueType issuetype, Projects project) {
        this.summary = summary;
        this.description = description;
        this.project = project;
        this.issuetype = issuetype;
    }


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IssueType getIssuetype() {
        return issuetype;
    }

    public void setIssuetype(IssueType issuetype) {
        this.issuetype = issuetype;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

}
