package com.goodworkalan.synapse.mix;

import com.goodworkalan.go.go.Artifact;
import com.goodworkalan.mix.ProjectModule;
import com.goodworkalan.mix.builder.Builder;
import com.goodworkalan.mix.builder.JavaProject;

public class SynapseProject extends ProjectModule {
    @Override
    public void build(Builder builder) {
        builder
            .cookbook(JavaProject.class)
                .produces(new Artifact("com.goodworkalan/synapse/0.1"))
                .main()
                    .depends()
                        .artifact(new Artifact("com.goodworkalan/litany/0.7"))
                        .artifact(new Artifact("org.jibx/jibx-extras/1.1.6a"))
                        .artifact(new Artifact("org.jibx/jibx-bind/1.1.6a"))
                        .artifact(new Artifact("org.jibx/jibx-run/1.1.6a"))
                        .artifact(new Artifact("commons-collections/commons-collections/3.2"))
                        .artifact(new Artifact("org.slf4j/slf4j-log4j12/1.4.2"))
                        .artifact(new Artifact("log4j/log4j/1.2.14"))
                        .artifact(new Artifact("org.mortbay.jetty/jetty/7.0.0pre3"))
                        .artifact(new Artifact("org.mortbay.jetty/jetty-util/7.0.0pre3"))
                        .artifact(new Artifact("org.mortbay.jetty/jetty-wadi-session-manager/7.0.0pre3"))
                        .artifact(new Artifact("org.mortbay.jetty/jetty-servlet/7.0.0pre3"))
                        .artifact(new Artifact("org.mortbay.jetty/jetty-plus/7.0.0pre3"))
                        .artifact(new Artifact("org.mortbay.jetty/jsp-2.1-jetty/7.0.0pre3"))
                        .end()
                    .end()
                .test()
                    .depends()
                        .artifact(new Artifact("org.testng/testng/5.10/jdk15"))
                        .end()
                    .end()
                .end()
            .end();
    }
}
