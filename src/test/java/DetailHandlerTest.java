import com.jiajunmao.handler.executors.DetailHandler;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DetailHandlerTest {

    private final String dummyTicketJson = "{\"results\":[{\"url\":\"https://zccjiajunmao.zendesk.com/api/v2/tickets/1.json\",\"id\":1,\"external_id\":null,\"via\":{\"channel\":\"sample_ticket\",\"source\":{\"from\":{},\"to\":{},\"rel\":null}},\"created_at\":\"2021-11-27T20:26:32Z\",\"updated_at\":\"2021-11-27T20:26:33Z\",\"type\":\"incident\",\"subject\":\"Sample ticket: Meet the ticket\",\"raw_subject\":\"Sample ticket: Meet the ticket\",\"description\":\"Hi there,\\n\\nI’m sending an email because I’m having a problem setting up your new product. Can you help me troubleshoot?\\n\\nThanks,\\n The Customer\\n\\n\",\"priority\":\"normal\",\"status\":\"open\",\"recipient\":null,\"requester_id\":1524528323781,\"submitter_id\":1524528313341,\"assignee_id\":1524528313341,\"organization_id\":null,\"group_id\":4411411684883,\"collaborator_ids\":[],\"follower_ids\":[],\"email_cc_ids\":[],\"forum_topic_id\":null,\"problem_id\":null,\"has_incidents\":false,\"is_public\":true,\"due_at\":null,\"tags\":[\"sample\",\"support\",\"zendesk\"],\"custom_fields\":[],\"satisfaction_rating\":null,\"sharing_agreement_ids\":[],\"fields\":[],\"followup_ids\":[],\"ticket_form_id\":1500003319901,\"brand_id\":1500002352021,\"allow_channelback\":false,\"allow_attachments\":true,\"result_type\":\"ticket\"},{\"id\":1524528323781,\"url\":\"https://zccjiajunmao.zendesk.com/api/v2/users/1524528323781.json\",\"name\":\"The Customer\",\"email\":\"customer@example.com\",\"created_at\":\"2021-11-27T20:26:32Z\",\"updated_at\":\"2021-11-27T20:26:35Z\",\"time_zone\":\"America/Chicago\",\"iana_time_zone\":\"America/Chicago\",\"phone\":null,\"shared_phone_number\":null,\"photo\":{\"url\":\"https://zccjiajunmao.zendesk.com/api/v2/attachments/1501103627282.json\",\"id\":1501103627282,\"file_name\":\"profile_image_1524528323781_11747735.png\",\"content_url\":\"https://zccjiajunmao.zendesk.com/system/photos/1501103627282/profile_image_1524528323781_11747735.png\",\"mapped_content_url\":\"https://zccjiajunmao.zendesk.com/system/photos/1501103627282/profile_image_1524528323781_11747735.png\",\"content_type\":\"image/png\",\"size\":10254,\"width\":80,\"height\":80,\"inline\":false,\"deleted\":false,\"thumbnails\":[{\"url\":\"https://zccjiajunmao.zendesk.com/api/v2/attachments/1501103627342.json\",\"id\":1501103627342,\"file_name\":\"profile_image_1524528323781_11747735_thumb.png\",\"content_url\":\"https://zccjiajunmao.zendesk.com/system/photos/1501103627282/profile_image_1524528323781_11747735_thumb.png\",\"mapped_content_url\":\"https://zccjiajunmao.zendesk.com/system/photos/1501103627282/profile_image_1524528323781_11747735_thumb.png\",\"content_type\":\"image/png\",\"size\":2402,\"width\":32,\"height\":32,\"inline\":false,\"deleted\":false}]},\"locale_id\":1,\"locale\":\"en-US\",\"organization_id\":null,\"role\":\"end-user\",\"verified\":false,\"external_id\":null,\"tags\":[],\"alias\":null,\"active\":true,\"shared\":false,\"shared_agent\":false,\"last_login_at\":null,\"two_factor_auth_enabled\":false,\"signature\":null,\"details\":null,\"notes\":null,\"role_type\":null,\"custom_role_id\":null,\"moderator\":false,\"ticket_restriction\":\"requested\",\"only_private_comments\":false,\"restricted_agent\":true,\"suspended\":false,\"default_group_id\":null,\"report_csv\":false,\"user_fields\":{},\"result_type\":\"user\"},{\"id\":1524528313341,\"url\":\"https://zccjiajunmao.zendesk.com/api/v2/users/1524528313341.json\",\"name\":\"Jiajun Mao\",\"email\":\"jiajunm@uchicago.edu\",\"created_at\":\"2021-11-27T20:26:15Z\",\"updated_at\":\"2021-11-29T18:32:44Z\",\"time_zone\":\"America/Chicago\",\"iana_time_zone\":\"America/Chicago\",\"phone\":null,\"shared_phone_number\":null,\"photo\":null,\"locale_id\":1,\"locale\":\"en-US\",\"organization_id\":1500637950541,\"role\":\"admin\",\"verified\":true,\"external_id\":null,\"tags\":[],\"alias\":null,\"active\":true,\"shared\":false,\"shared_agent\":false,\"last_login_at\":\"2021-11-29T18:32:44Z\",\"two_factor_auth_enabled\":null,\"signature\":null,\"details\":null,\"notes\":null,\"role_type\":null,\"custom_role_id\":null,\"moderator\":true,\"ticket_restriction\":null,\"only_private_comments\":false,\"restricted_agent\":false,\"suspended\":false,\"default_group_id\":4411411684883,\"report_csv\":true,\"user_fields\":{},\"result_type\":\"user\"}],\"facets\":null,\"next_page\":null,\"previous_page\":null,\"count\":3}";
    private final String dummyTicketTable =
            "â”Œâ”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”�\n" +
            "â”‚Ticket Detail ID 1                      â”‚\n" +
            "â”œâ”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”¬â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”¤\n" +
            "â”‚Created at â”‚11-27 20:26:32              â”‚\n" +
            "â”‚Updated at â”‚11-27 20:26:33              â”‚\n" +
            "â”‚Priority   â”‚normal                      â”‚\n" +
            "â”‚Type       â”‚incident                    â”‚\n" +
            "â”œâ”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”¼â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”¤\n" +
            "â”‚Requester  â”‚The Customer (1524528323781)â”‚\n" +
            "â”‚Submitter  â”‚Jiajun Mao (1524528313341)  â”‚\n" +
            "â”œâ”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”¼â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”¤\n" +
            "â”‚Tags       â”‚sample, support, zendesk    â”‚\n" +
            "â”œâ”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”¼â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”¤\n" +
            "â”‚Subject    â”‚Sample ticket: Meet the     â”‚\n" +
            "â”‚           â”‚ticket                      â”‚\n" +
            "â”‚           â”‚                            â”‚\n" +
            "â”‚Descriptionâ”‚Hi there, Iâ€™m sending an    â”‚\n" +
            "â”‚           â”‚email because Iâ€™m having a  â”‚\n" +
            "â”‚           â”‚problem setting up your new â”‚\n" +
            "â”‚           â”‚product. Can you help me    â”‚\n" +
            "â”‚           â”‚troubleshoot? Thanks, The   â”‚\n" +
            "â”‚           â”‚Customer                    â”‚\n" +
            "â””â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”´â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”˜";

    @Test
    void detailhandler_generateTable() {
        JSONObject ticket = new JSONObject(dummyTicketJson).getJSONArray("results").getJSONObject(0);
        DetailHandler handler = new DetailHandler("description");

        String table = handler.generateTable(ticket);
        Assertions.assertEquals(table, dummyTicketTable);
    }
}
