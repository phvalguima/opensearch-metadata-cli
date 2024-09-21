package org.opensearch.cli.main;

import java.io.IOException;
import java.nio.file.Path;

import org.opensearch.cli.Terminal;
import org.opensearch.cli.UserException;
import org.opensearch.cluster.ClusterState;
import org.opensearch.cluster.coordination.OpenSearchNodeCommand;
import org.opensearch.common.collect.Tuple;
import org.opensearch.env.Environment;
import org.opensearch.gateway.PersistedClusterStateService;

import joptsimple.OptionSet;
import joptsimple.OptionSpec;

public class ListMetadataCommand extends OpenSearchNodeCommand {

    private final OptionSpec<String> arguments;

    public ListMetadataCommand() {
        super("List metadata details from the cluster state");
        arguments = parser.nonOptions("custom metadata names");
    }

    @Override
    protected void processNodePaths(Terminal terminal, Path[] dataPaths, int nodeLockId, OptionSet options, Environment env)
        throws IOException, UserException {

        final PersistedClusterStateService persistedClusterStateService = createPersistedClusterStateService(env.settings(), dataPaths);

        terminal.println(Terminal.Verbosity.VERBOSE, "Loading cluster state");
        final Tuple<Long, ClusterState> termAndClusterState = loadTermAndClusterState(persistedClusterStateService, env);
        final ClusterState currentClusterState = termAndClusterState.v2();
        terminal.println(
            Terminal.Verbosity.VERBOSE,
            "[current cluster state = " + currentClusterState + "]"
        );
    }
}
