# Whistle Data Transformation Language

[TOC]

# Introduction

Whistle is a mapping language used for converting complex, nested data from one
schema to another.

Whistle is a terse, efficient syntax to describe transformations of
healthcare data, but is applicable to any domain.

In addition to the built-in functionality, the engine can be extended with
plugins which can provide native transformations, extra features, integration
with external services, and otherwise extend the engine functionality.

# Getting Started

1.  Take a look at the [Getting Started Tutorial](./doc/getting_started.md)
1.  Refer to the [Language Specification](./doc/spec.md) for details

# Coming Soon

In no particular order:

*   Visual Studio Code Extension
    *   Language Server
    *   Formatter
*   Example Plugin
*   HL7v2 to FHIR mappings
*   Unit testing plugin
*   Documentation generator

## Where is Whistle 1?

This repository contains what is technically Whistle 2, which is a from-scratch
rewrite of Whistle. This is now the current and actively maintained version of
Whistle.

The original version of Whistle is still available in the `wstl1` directory of
this repository for legacy purposes, however is not actively maintained, and we
encourage all users to migrate to Whistle 2.
