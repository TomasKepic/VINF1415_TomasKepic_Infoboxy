package infoboxNames;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

public class TestOutput {


		@Test
		public void compareInfoboxNames() throws IOException {
		    final File expected = new File("C:/Temp/sample_output_infobox_name_enwiki-latest-pages-articles3.xml-p000025001p000055000");
		    final File output = new File("C:/Temp/sample_output_name.xml");
		    Assert.assertEquals(FileUtils.readLines(expected), FileUtils.readLines(output));
		}
		
		@Test
		public void compareInfoboxProperties() throws IOException {
		    final File expected = new File("C:/Temp/sample_output_infobox-information_enwiki-latest-pages-articles3.xml-p000025001p000055000");
		    final File output = new File("C:/Temp/sample_output_information.xml");
		    Assert.assertEquals(FileUtils.readLines(expected), FileUtils.readLines(output));
		}
	
}
