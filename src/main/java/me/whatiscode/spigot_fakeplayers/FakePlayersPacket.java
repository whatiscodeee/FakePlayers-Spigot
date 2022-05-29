package me.whatiscode.spigot_fakeplayers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedServerPing;
import org.bukkit.plugin.Plugin;

import java.util.function.Consumer;

public class FakePlayersPacket extends PacketAdapter {

    private Consumer<WrappedServerPing> callback = PConsumer();

    public FakePlayersPacket(Plugin plugin) {
        super(plugin, PacketType.Status.Server.SERVER_INFO);
    }

    @Override
    public void onPacketSending(PacketEvent e) {
        callback.accept(e.getPacket().getServerPings().read(0));
    }

    public void setCallback(Consumer<WrappedServerPing> callback) {
        this.callback = callback;
    }

    public void clearCallback() {
        this.callback = PConsumer();
    }

    private static Consumer<WrappedServerPing> PConsumer() {
        return wrappedServerPing -> { };
    }
}
