package org.cilantro.data;

import static java.lang.System.getProperty;
import static java.lang.System.getenv;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.nio.file.Path;

import org.apache.logging.log4j.Logger;
import org.cilantro.data.admin.props.SimulationProps;
import org.cilantro.data.admin.props.cbsMasterProps;
import org.cilantro.utils.JsonUtil;

public final class DataReader {
    private static final Logger LOGGER = getLogger ();

    public static cbsMasterProps loadCbsMasterProps () {
        cbsMasterProps cbsMasterProps = null;
        LOGGER.traceEntry ();
        final var defaultPath = Path.of (getProperty ("user.dir"), "src/test/resources/data.json/admin/props/")
            .toString ();
        final var configDirectory = ofNullable (getenv ("PROPERTIES_PROPS_PATH")).orElse (
            ofNullable (getProperty ("login.props.path")).orElse (defaultPath));
        final var configPath = Path.of (configDirectory, "cbsMaster-props.json")
            .toString ();
        cbsMasterProps = JsonUtil.fromFile (configPath, cbsMasterProps.class);
        return LOGGER.traceExit (cbsMasterProps);
    }

    public static SimulationProps loadSimulationProps () {
        SimulationProps simulationProps = null;
        LOGGER.traceEntry ();
        final var defaultPath = Path.of (getProperty ("user.dir"), "src/test/resources/data.json/admin/props/")
                .toString ();
        final var configDirectory = ofNullable (getenv ("PROPERTIES_PROPS_PATH")).orElse (
                ofNullable (getProperty ("login.props.path")).orElse (defaultPath));
        final var configPath = Path.of (configDirectory, "simulation-props.json")
                .toString ();
        simulationProps = JsonUtil.fromFile (configPath, SimulationProps.class);
        return LOGGER.traceExit (simulationProps);
    }

}
