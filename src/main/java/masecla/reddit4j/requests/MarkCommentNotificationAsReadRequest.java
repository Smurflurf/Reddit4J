package masecla.reddit4j.requests;

import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import masecla.reddit4j.client.Reddit4J;
import masecla.reddit4j.objects.RedditComment;

/**
 * A request to mark a message as read.
 * This is typically used for comment replies or private messages that appear in the user's inbox.
 */
public class MarkCommentNotificationAsReadRequest {

    private final Reddit4J client;
    private final String commentFullname;

    /**
     * Prepares a request to mark a specific comment or message as read.
     *
     * @param client  The authenticated Reddit4J client.
     * @param comment The comment or message to be marked as read. Its fullname will be used.
     */
    public MarkCommentNotificationAsReadRequest(Reddit4J client, RedditComment comment) {
        this.client = client;
        this.commentFullname = comment.getName();
    }
    
    /**
     * Executes the request to mark the message as read.
     *
     * @return {@code true} if the request was likely successful, {@code false} if an exception occurred.
     */
    public boolean execute() {
        Connection conn = client.useEndpoint("/api/read_message").method(Method.POST);
        
        conn.data("id", this.commentFullname);

        try {
            client.getHttpClient().execute(conn);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}