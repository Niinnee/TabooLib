package me.skymc.taboolib.javascript;

import com.ilummc.tlib.logger.TLogger;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import org.bukkit.configuration.file.FileConfiguration;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Objects;

/**
 * @Author sky
 * @Since 2018-06-02 22:48
 */
public class ScriptHandler {

    private static ScriptEngine scriptEngine;
    private static ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    private static FileConfiguration scriptsFile;

    public static void inst() {
        try {
            NashornScriptEngineFactory factory = (NashornScriptEngineFactory) scriptEngineManager.getEngineFactories().stream().filter(factories -> "Oracle Nashorn".equalsIgnoreCase(factories.getEngineName())).findFirst().orElse(null);
            scriptEngine = Objects.requireNonNull(factory).getScriptEngine("-doe", "--global-per-engine");
        } catch (Exception ignored) {
            scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
        }
    }

    public static CompiledScript compile(String script) {
        try {
            Compilable compilable = (Compilable) scriptEngine;
            return compilable.compile(script);
        } catch (Exception e) {
            TLogger.getGlobalLogger().info("§4JavaScript §c" + script + "§4 Compile Failed: §c" + e.toString());
            return null;
        }
    }

    // *********************************
    //
    //        Getter and Setter
    //
    // *********************************

    public static ScriptEngine getScriptEngine() {
        return scriptEngine;
    }

    public static ScriptEngineManager getScriptEngineManager() {
        return scriptEngineManager;
    }
}
