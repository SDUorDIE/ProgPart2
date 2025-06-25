import com.mycompany.progpart2.Handler;
import com.mycompany.progpart2.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class HandlerTest {
    private Handler handler;

    @BeforeEach
    public void setUp() {
        handler = new Handler();
    }

    @Test
    public void testGetLongestMessage() {
        handler.processMessagesFromTestData(new String[][] {
            {"12345", "Short"},
            {"12345", "This is a much longer message for testing"}
        });

        Message longest = handler.getLongestMessage();
        assertNotNull(longest);
        assertTrue(longest.getText().length() > 10);
    }

    @Test
    public void testSearchByRecipient() {
        handler.processMessagesFromTestData(new String[][] {
            {"111", "Hello"}, {"222", "Hi"}, {"111", "Another"}
        });

        ArrayList<Message> result = handler.searchByRecipient("111");
        assertEquals(2, result.size());
    }

    @Test
    public void testDeleteByHash() {
        handler.processMessagesFromTestData(new String[][] {
            {"111", "To delete"}, {"222", "Keep this"}
        });

        String hashToDelete = handler.getLongestMessage().getHash();
        boolean deleted = handler.deleteByHash(hashToDelete);
        assertTrue(deleted);
    }

    @Test
    public void testStoreAndReadMessagesToJson() {
        handler.processMessagesFromTestData(new String[][] {
            {"789", "JSON Test 1"}, {"456", "JSON Test 2"}
        });

        handler.storeMessagesToJson();
        handler.readMessagesFromJson();

        assertEquals(2, handler.searchByRecipient("789").size() + handler.searchByRecipient("456").size());
    }
}
