/**
 * Ports system
 * 
 * Note: theres multithreaded read to save ports state with
 * non concurrent/synchronized HashMap. Other thread only reads
 * to save state, no structural change to HashMap, so mostly ok
 * but may potentially cause mismatched save
 */

package phonon.ports

import org.bukkit.plugin.java.JavaPlugin
import phonon.xv.XV
import phonon.xv.XVPlugin

/*
 * Implement bukkit plugin interface
 */
public class PortPlugin : JavaPlugin() {

    // store reference to xv plugin instance
    var xv: XV? = null

    override fun onEnable() {
        // measure load time
        val timeStart = System.currentTimeMillis()

        val logger = this.getLogger()
        val pluginManager = this.getServer().getPluginManager()

        val xvPlugin = pluginManager.getPlugin("xv") as XVPlugin
        if ( xvPlugin === null || pluginManager.isPluginEnabled(xvPlugin) == false ) {
            logger.warning("xv plugin not found or enabled, will not warp vehicles")
        } else {
            this.xv = xvPlugin.xv
        }

        // load everything
        Ports.initialize(this)
        Ports.reload()

        // register listener
        pluginManager.registerEvents(PortsProtectionListener(), this)

        // register commands
        this.getCommand("port")?.setExecutor(PortCommand)
        this.getCommand("portadmin")?.setExecutor(PortAdminCommand)

        // print load time
        val timeEnd = System.currentTimeMillis()
        val timeLoad = timeEnd - timeStart
        logger.info("Enabled in ${timeLoad}ms")

        // print success message
        logger.info("now this is epic")
    }

    override fun onDisable() {
        Ports.onDisable()
        logger.info("wtf i hate xeth now")
    }
}
