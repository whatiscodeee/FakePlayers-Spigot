package me.whatiscode.spigot_fakeplayers;

import com.comphenix.protocol.ProtocolLibrary;
import me.whatiscode.spigot_fakeplayers.command.impl.SetFakePlayers;
import org.bukkit.plugin.java.JavaPlugin;

public class FakeplayersPlugin extends JavaPlugin {

    private FakePlayersPacket FPacket;

    @Override
    public void onEnable() {
        // Потребитель
        FakePlayersPacket packet = new FakePlayersPacket(this);
        ProtocolLibrary.getProtocolManager().addPacketListener(packet);
        this.FPacket = packet;

        // Регистрация команды
        getCommand("setfakeplayers").setExecutor(new SetFakePlayers(this));
    }

    public FakePlayersPacket getFPacket() {
        return FPacket;
    }
}
