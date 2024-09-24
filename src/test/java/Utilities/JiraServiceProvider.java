package Utilities;

import net.rcarz.jiraclient.*;
import net.rcarz.jiraclient.Issue.FluentCreate;

import java.io.File;
import java.util.List;

public class JiraServiceProvider {

    public JiraClient jira;
    public String project;
    private final String JiraUrl;

    public JiraServiceProvider(String JiraUrl, String username, String password, String project) {
        this.JiraUrl = JiraUrl;

        BasicCredentials creds = new BasicCredentials(username, password);

        jira = new JiraClient(JiraUrl, creds);

        this.project = project;
    }


    public Issue createJiraTicket(String issueType, String summary, String description, String reporter) {
        try {
            FluentCreate fluentCreate = jira.createIssue(project, issueType);
            fluentCreate.field(Field.SUMMARY, summary);
            fluentCreate.field(Field.DESCRIPTION, description);
            //fluentCreate.field(Field.ATTACHMENT,attachement);

            Issue newIssue = fluentCreate.execute();

//            newIssue.addAttachment(attachment);

            System.out.println("********************************************");

            System.out.println("New issue created in Jira with ID: " + newIssue);

            System.out.println("New issue URL is :" + JiraUrl + "/browse/" + newIssue);

            System.out.println("********************************************");
            return newIssue;
        } catch (JiraException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addAttachmentsToIssue(Issue issue, List<File> files) {
//        try {
//            jira.getRestClient().post(JiraUrl + "rest/api/3/issue/" + issue.getId() + "/attachments", );
//        } catch (RestException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
    }
}
