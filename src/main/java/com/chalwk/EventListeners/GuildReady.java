package com.chalwk.EventListeners;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildReady extends ListenerAdapter {

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        System.out.println("Guild ready: " + event.getGuild().getName());
        System.out.println("Bot name: " + event.getJDA().getSelfUser().getName());
    }
}
