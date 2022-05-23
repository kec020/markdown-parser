import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.*;
public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }
    @Test
    public void getLinks() throws IOException {
        String contents = Files.readString(Path.of("test-file.md"));
        assertEquals(List.of("https://something.com", "some-thing.html"), MarkdownParse.getLinks(contents));
    }

    @Test
    public void testSnippet1() throws IOException {
        String contents = Files.readString(Path.of("Snippet 1.md"));
        List<String> expect = List.of("`google.com", "google.com", "ucsd.edu");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
    
    @Test
    public void testSnippet2() throws IOException {
        String contents = Files.readString(Path.of("Snippet 2.md"));
        List<String> expect = List.of("a.com", "a.com(())", "example.com");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test
    public void testSnippet3() throws IOException {
        String contents = Files.readString(Path.of("Snippet 3.md"));
        List<String> expect = List.of("https://www.twitter.com", "https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule", "https://cse.ucsd.edu/");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
}
