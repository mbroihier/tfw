# tfw - Test Framework

This repository contains a test framework that I use for testing software projects.  This work is still in progress.

In the doc directory, I've got a discussion of test automation and an example of using the framework against a real project called RTNV.  Following the framework, I wrote requirements for an Android app that allows a user to build a portfolio and then monitor the net value of that portfolio.  From the requirements document, my tools extracted the requirements and helped me write the test procedure for testing the application.  The application is stored in the rtnv repository under my account.  https://tfw/herokuapp/com has a staged version of my tools that display the test procedure and, when hosted on a Mac, pulls up an emacs session to edit the procedure.  I am currently in the process of editing the tools to be platform independent when using the Node.js based "editor-server".

By the way, most of the tools have been written is Java, Python, and C++.  At the moment, the HeroKu demonstration is using python2 tools knitted together in a Node.js Express
framework.

Thanks to Arseny Kapoulkine.  I'm using pugixml version 1.8 from http://pugixml.org/ to parse and write HTML files.  Please see the pugixml licence included in this repositiory.