package com.goodworkalan.synapse;

import com.goodworkalan.cafe.ProjectModule;
import com.goodworkalan.cafe.builder.Builder;
import com.goodworkalan.cafe.outline.JavaProject;

/**
 * Builds the project definition for Synapse.
 *
 * @author Alan Gutierrez
 */
public class SynapseProject implements ProjectModule {
    /**
     * Build the project definition for Synapse.
     *
     * @param builder
     *          The project builder.
     */
    public void build(Builder builder) {
        builder
            .cookbook(JavaProject.class)
                .produces("com.github.bigeasy.synapse/synapse/0.1")
                .depends()
                    .production("com.github.bigeasy.litany/litany/0.+7")
                    .production("org.jibx/jibx-extras/1.1.6a")
                    .production("org.jibx/jibx-bind/1.1.6a")
                    .production("org.jibx/jibx-run/1.1.6a")
                    .production("commons-collections/commons-collections/3.2")
                    .production("org.slf4j/slf4j-log4j12/1.4.2")
                    .production("log4j/log4j/1.2.14")
                    .production("org.mortbay.jetty/jetty/7.0.0pre3")
                    .production("org.mortbay.jetty/jetty-util/7.0.0pre3")
                    .production("org.mortbay.jetty/jetty-wadi-session-manager/7.0.0pre3")
                    .production("org.mortbay.jetty/jetty-servlet/7.0.0pre3")
                    .production("org.mortbay.jetty/jetty-plus/7.0.0pre3")
                    .production("org.mortbay.jetty/jsp-2.1-jetty/7.0.0pre3")
                    .development("org.testng/testng-jdk15/5.10")
                    .end()
                .end()
            .end();
    }
}
