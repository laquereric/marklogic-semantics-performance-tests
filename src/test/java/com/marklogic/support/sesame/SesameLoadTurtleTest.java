package com.marklogic.support.sesame;

import com.marklogic.semantics.sesame.MarkLogicRepositoryConnection;
import com.marklogic.support.SPARQLUtils;
import com.marklogic.support.Utils;
import com.marklogic.support.annotations.Benchmark;
import com.marklogic.support.annotations.MarkLogicSesame;
import com.marklogic.support.providers.MarkLogicSesameRepositoryProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;

import java.io.IOException;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * Created by ableasdale on 28/05/2017.
 */

@MarkLogicSesame
public class SesameLoadTurtleTest {

    //private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 596Kb x-turtle file (charging-stations-export-20170530-095533.ttl)")
    public void testLoadingSmallXTurtleFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofSeconds(2), () -> {
            conn.add(Utils.getFileReader("turtle/charging-stations-export-20170530-095533.ttl"), "", RDFFormat.TURTLE);
        });

        assertEquals(8900, SPARQLUtils.countAllTriples(conn));
    }

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 779K Turtle file (units.ttl)")
    public void testLoadingSmallTurtleFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofSeconds(2), () -> {
            conn.add(Utils.getFileReader("turtle/units.ttl"), "", RDFFormat.TURTLE);
        });

        assertEquals(23485, SPARQLUtils.countAllTriples(conn));
    }

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 3.3MB Turtle file (unescothes.ttl)")
    public void testLoadingMediumSizeTurtleFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofSeconds(10), () -> {
            conn.add(Utils.getFileReader("turtle/unescothes.ttl"), "", RDFFormat.TURTLE);
        });

        assertEquals(75202, SPARQLUtils.countAllTriples(conn));
    }

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 51MB Turtle file (history.ttl)")
    public void testLoadingLargeTurtleFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofSeconds(50), () -> {
            conn.add(Utils.getFileReader("turtle/history.ttl"), "", RDFFormat.TURTLE);
        });

        assertEquals(391551, SPARQLUtils.countAllTriples(conn));
        //TODO :: 595673??
    }

    @Benchmark
    @Test
    @RepeatedTest(2)
    @DisplayName("Using the MarkLogic Sesame API to load a 130MB Turtle file (fulldump.ttl)")
    public void testLoadingAnotherLargeTurtleFile() throws RepositoryException, IOException, RDFParseException {

        MarkLogicRepositoryConnection conn = MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection();

        assertTimeoutPreemptively(ofSeconds(95), () -> {
            conn.add(Utils.getFileReader("turtle/fulldump.ttl"), "", RDFFormat.TURTLE);
        });

        assertEquals(204122, SPARQLUtils.countAllTriples(conn));
    }
}
