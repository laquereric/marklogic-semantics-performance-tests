package com.marklogic.support.extensions;

import com.marklogic.semantics.sesame.MarkLogicRepository;
import com.marklogic.support.Configuration;
import com.marklogic.support.MarkLogicSesameRepositoryProvider;
import org.junit.jupiter.api.extension.*;
import org.openrdf.repository.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by ableasdale on 28/05/2017.
 */
public class MarkLogicSesameExtension implements BeforeAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback, Extension {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    @Override
    public void beforeAll(ContainerExtensionContext context) throws Exception {
        LOG.info("MARKLOGIC Sesame: BEFORE ALL :)" +  MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection().isActive());
        assertTrue(MarkLogicSesameRepositoryProvider.getMarkLogicRepositoryConnection().isOpen());
    }

    @Override
    public void afterAll(ContainerExtensionContext context) throws Exception {
        LOG.info("MARKLOGIC Sesame: AFTER ALL :)");
    }

    @Override
    public void afterTestExecution(TestExtensionContext context) throws Exception {
        LOG.info("MARKLOGIC Sesame: AFTER TEST :)");
    }

    @Override
    public void beforeTestExecution(TestExtensionContext context) throws Exception {
        LOG.info("MARKLOGIC Sesame: BEFORE TEST :)");
    }
}
