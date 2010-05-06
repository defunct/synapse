package com.goodworkalan.synapse.mix;

import com.goodworkalan.mix.ProjectModule;
import com.goodworkalan.mix.builder.Builder;
import com.goodworkalan.mix.builder.JavaProject;

public class SynapseProject extends ProjectModule {
    @Override
    public void build(Builder builder) {
        builder
            .cookbook(JavaProject.class)
                .produces("com.github.bigeasy.synapse/synapse/0.1")
                .main()
                    .depends()
                        .include("com.github.bigeasy.litany/litany/0.+7")
                        .include("org.jibx/jibx-extras/1.1.6a")
                        .include("org.jibx/jibx-bind/1.1.6a")
                        .include("org.jibx/jibx-run/1.1.6a")
                        .include("commons-collections/commons-collections/3.2")
                        .include("org.slf4j/slf4j-log4j12/1.4.2")
                        .include("log4j/log4j/1.2.14")
                        .include("org.mortbay.jetty/jetty/7.0.0pre3")
                        .include("org.mortbay.jetty/jetty-util/7.0.0pre3")
                        .include("org.mortbay.jetty/jetty-wadi-session-manager/7.0.0pre3")
                        .include("org.mortbay.jetty/jetty-servlet/7.0.0pre3")
                        .include("org.mortbay.jetty/jetty-plus/7.0.0pre3")
                        .include("org.mortbay.jetty/jsp-2.1-jetty/7.0.0pre3")
                        .end()
                    .end()
                .test()
                    .depends()
                        .include("org.testng/testng-jdk15/5.10")
                        .end()
                    .end()
                .end()
            .end();
    }
}
