package org.opensearch.cli.main;


import org.opensearch.cli.CommandLoggingConfigurator;
import org.opensearch.cli.MultiCommand;
import org.opensearch.cli.Terminal;

public class Node extends MultiCommand {

    public Node() {
        super("A CLI tool to list metadata from OpenSearch", () -> {});
        CommandLoggingConfigurator.configureLoggingWithoutConfig();
        subcommands.put("list-metadata", new ListMetadataCommand());
    }

    public static void main(String[] args) throws Exception {
        exit(new Node().main(args, Terminal.DEFAULT));
    }

}
