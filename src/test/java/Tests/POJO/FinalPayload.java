package Tests.POJO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FinalPayload {

    public static Fields FinalBody() throws JsonProcessingException {
        IssueType issuetype = new IssueType("Bug");
        Projects project = new Projects("RES");
        Payload p = new Payload("Some summary", "Some description", issuetype, project);
        Fields f = new Fields(p);

        com.fasterxml.jackson.databind.ObjectMapper objMap = new ObjectMapper();
        String mydata = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(f);

        System.out.println(mydata);

        return f;



    }

}
