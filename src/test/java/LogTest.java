import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Slf4j
public class LogTest {
    private static final Logger logger = LoggerFactory.getLogger(LogTest.class);
    @Test
    public void test(){
        logger.info("test");
        logger.debug("debug");
    }
}
