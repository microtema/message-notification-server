package de.seven.fate.sse;

import de.seven.fate.event.MessageEventData;
import de.seven.fate.message.enums.MessageType;
import lombok.extern.log4j.Log4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Log4j
@Component
@WebServlet(value = "/sse", asyncSupported = true)
public class SSEServlet extends HttpServlet {

    private static final Map<String, PrintWriter> BROADCAST = Collections.synchronizedMap(new HashMap<>());

    private static void sentComment(PrintWriter writer, String command, Object data) {

        writer.write("event: " + command + "\n");
        writer.write("data: " + data + "\n\n");

        writer.flush();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        AsyncContext context = req.startAsync();
        context.setTimeout(TimeUnit.HOURS.toMillis(1));

        ServletResponse actxResponse = context.getResponse();

        actxResponse.setContentType("text/event-stream");
        actxResponse.setCharacterEncoding("UTF-8");

        PrintWriter writer = actxResponse.getWriter();

        sentComment(actxResponse.getWriter(), "connect", Boolean.TRUE);

        BROADCAST.put(getUserName(req), writer);
    }

    /**
     * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * Message Events
     * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     *
     * @param eventData
     */
    @EventListener
    protected void onMessageChangeEvent(MessageEventData eventData) {

        String ldapId = eventData.getLdapId();

        broadcastMessage(ldapId);
    }

    private void broadcastMessage(String ldapId) {
        log.debug("broadcast outbound event on data: " + ldapId);

        PrintWriter writer = BROADCAST.get(ldapId);

        if (writer == null) {
            return;
        }

        sentComment(writer, "message", MessageType.UNREAD);
    }

    @Override
    public void destroy() {
        BROADCAST.clear();
    }

    private String getUserName(ServletRequest req) { //NOSONAR

        return "mtema"; // Authentication auth = SecurityContextHolder.getContext().getAuthentication(); //NOSONAR
    }
}
