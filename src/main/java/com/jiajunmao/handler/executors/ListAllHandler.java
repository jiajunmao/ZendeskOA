package com.jiajunmao.handler.executors;

import com.jiajunmao.handler.tmpl.Handler;
import com.jiajunmao.processors.Nice;
import com.jiajunmao.processors.Requester;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ListAllHandler extends Handler {

    private static final int PAGE_LEN = 5;

    public ListAllHandler() {
        super();
    }

    public ListAllHandler(String description) {
        super(description);
    }


    @Override
    public boolean execute(int argc, String[] argv) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<Object> future = executorService.submit(() -> {
            JSONObject object = null;
            try {
                object = Requester.parse(Requester.request("https://zccjiajunmao.zendesk.com/api/v2/tickets.json", "GET", null));
            } catch (IOException e) {
                System.out.printf("\nERROR: %s\n", e.getMessage());
                return true;
            }

            AsciiTable tb = new AsciiTable();
            tb.getRenderer().setCWC(new CWC_LongestLine());
            tb.addRule();
            tb.addRow("ID", "Created", "Updated", "Prio.", "Requester", "Submitter", "Subject", "Description");
            tb.addRule();

            JSONArray array = object.getJSONArray("tickets");
            int totalPage = array.length() / PAGE_LEN + (array.length() % PAGE_LEN == 0 ? 0 : 1);
            int currPage = Integer.parseInt(argv[0]) - 1;
            int pageLeft = currPage * PAGE_LEN;
            int pageRight = pageLeft + PAGE_LEN;

            if (currPage < 0 || currPage >= totalPage) {
                if (argc != 2) System.out.printf("\nThe page number you entered is invalid!\n");
                return null;
            }

            for (int i = pageLeft; i < array.length() && i < pageRight; i++) {
                JSONObject json = array.getJSONObject(i);
                tb.addRow(String.valueOf(json.getInt("id")),
                        Nice.niceDate(json.getString("created_at")),
                        Nice.niceDate(json.getString("updated_at")),
                        Nice.nicePriority(json.getString("priority")),
                        Requester.getUsernameById(json.get("requester_id").toString()),
                        Requester.getUsernameById(json.get("submitter_id").toString()),
                        Nice.niceString(json.getString("subject"), 15),
                        Nice.niceString(json.getString("description"), 20));
                tb.addRule();
            }

            String rightArrow = currPage < totalPage ? ">" : "";
            String leftArrow = currPage > 0 ? "<" : "";
            tb.addRow(null, null, null, null, null, null, null, leftArrow + " " + (currPage + 1) + " of " + (totalPage) + " " + rightArrow).setTextAlignment(TextAlignment.CENTER);
            tb.addRule();

            String result = tb.render();
            System.out.println();
            System.out.println(result);
            return null;
        });

        System.out.print("Generating report");
        while (!future.isDone()) {
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                //
            }

            if (future.isDone()) break;
            System.out.print(".");
        }

        executorService.shutdown();
        return true;
    }
}
