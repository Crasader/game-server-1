package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by veininguo on 2017/1/30.
 */
public class SimpleUniqueGeneratorTest {
    static final Logger logger = LoggerFactory.getLogger(SimpleUniqueGeneratorTest.class);

    private UniqueIDGeneratorService uniqueIDGeneratorService;

    @org.junit.Before
    public void init() {
        this.uniqueIDGeneratorService = new SimpleUniqueGenerator();
    }

    @org.junit.Test
    public void generate() throws Exception {
        for (int i  = 0; i < 10; i++) {
            logger.info("generate unique id : {}", uniqueIDGeneratorService.generate());
        }
    }

    @org.junit.Test
    public void generateFor() throws Exception {
        for (int i  = 0; i < 10; i++) {
            logger.info("generate unique id : {}", uniqueIDGeneratorService.generateFor(SimpleUniqueGeneratorTest.class));
        }
    }
}