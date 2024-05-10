package gal.sinhote.adventofcode._2021.day3;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

class DiagnosticReportTest {

	@Test
	void testUnity() {
		DiagnosticReport report = new DiagnosticReport();

		report.addDiagnosticNumber(2L);

		assertThat(report.getGammaRate(), is(2L));
		assertThat(report.getEpsilonRate(), is(1L));
	}

	@Test
	void testMultiple() {
		DiagnosticReport report = new DiagnosticReport();

		report.addDiagnosticNumber(2L);

		assertThat(report.getGammaRate(), is(2L));
		assertThat(report.getEpsilonRate(), is(1L));

		report.addDiagnosticNumber(8L);

		assertThat(report.getGammaRate(), is(10L));
		assertThat(report.getEpsilonRate(), is(5L));
	}

	@Test
	void testRealInput() throws IOException, URISyntaxException {
		DiagnosticReport report = new DiagnosticReport();
		Path path = Paths.get(DiagnosticReportTest.class.getResource("/testInput.txt").toURI());
		System.out.println(path);
		report.addAllDiagnosticNumbers(path);

		assertThat(report.getGammaRate(), is(22L));
		assertThat(report.getEpsilonRate(), is(9L));

		assertThat(report.getO2GeneratorRating(), is(23L));
		assertThat(report.getCO2ScrubberRating(), is(10L));


	}
}