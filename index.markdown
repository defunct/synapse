---
layout: default
title: Synapse
---

# Class Concerns and Decisions

The first concern was what to use to build it, but I decided on distributing the
V8 engine behind an HTTP server, then using JSONP to invoke the local server.

## Reproducable Build

Since we are deploying V8, anyone wanting to build Synapse will want to build
V8. If they build V8, they will have a C compiler, Python and SCons installed,
so we'll use a C complier, Pythong and SCons as our build environment for now.
Rather than pull in Boost for unit testing, we can use the unit testing
framework in V8. Same goes for the threading library.
