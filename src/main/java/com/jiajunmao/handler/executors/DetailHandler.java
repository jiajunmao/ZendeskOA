package com.jiajunmao.handler.executors;

import com.jiajunmao.handler.tmpl.Handler;
import com.jiajunmao.processors.Nice;
import com.jiajunmao.processors.Requester;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import org.json.JSONArray;
import org.json.JSONObject;

public class DetailHandler extends Handler {

    public DetailHandler(String description) {
        super(description);
    }

    @Override
    public boolean execute(int argc, String[] argv) {
        JSONObject ticket = Requester.getTicketById(argv[0]);
        if (ticket == null) {
            System.out.println("The ticket ID you requested does not exists!");
            return true;
        }

        System.out.println(generateTable(ticket));
        return true;
    }

    public String generateTable(JSONObject ticket) {
        AsciiTable tb = new AsciiTable();
        tb.getRenderer().setCWC(new CWC_LongestLine());

        tb.addRule();
        tb.addRow(null, "Ticket Detail ID " + ticket.get("id").toString());
        tb.addRule();

        tb.addRow("Created at",
                ticket.isNull("created_at") ? "N/A" : Nice.niceDate(ticket.getString("created_at"), false));
        tb.addRow("Updated at",
                ticket.isNull("updated_at") ? "N/A" : Nice.niceDate(ticket.getString("updated_at"), false));
        tb.addRow("Priority",
                ticket.isNull("priority") ? "N/A" : ticket.getString("priority"));
        tb.addRow("Type",
                ticket.isNull("type") ? "N/A" : ticket.getString("type"));
        tb.addRule();

        tb.addRow("Requester",
                ticket.isNull("requester_id") ? "N/A" : Requester.getUsernameById(ticket.get("requester_id").toString()) + " (" + ticket.get("requester_id").toString() + ")");

        tb.addRow("Submitter",
                ticket.isNull("submitter_id") ? "N/A" : Requester.getUsernameById(ticket.get("submitter_id").toString()) + " (" + ticket.get("submitter_id").toString() + ")");
        tb.addRule();

        if (!ticket.isNull("tags") && ticket.getJSONArray("tags").length() > 0) {
            JSONArray arr = ticket.getJSONArray("tags");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr.length(); i++) {
                sb.append(arr.getString(i));
                sb.append(i != arr.length() - 1 ? ", " : "");
            }
            tb.addRow("Tags", sb.toString());
        } else {
            tb.addRow("Tags", "N/A");
        }
        tb.addRule();

        tb.addRow("Subject",
                ticket.isNull("subject") ? "N/A" : Nice.breakString(ticket.getString("subject"), 10)).setPaddingBottom(1);
        tb.addRow("Description",
                ticket.isNull("description") ? "N/A" : Nice.breakString(ticket.getString("description"), 10));

        tb.addRule();
        tb.setTextAlignment(TextAlignment.LEFT);
        return tb.render();
    }
}
