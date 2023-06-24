package com.chalwk.EventListeners;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.JSONObject;

import static com.chalwk.util.LoadWhitelist.getWhitelist;

public class NicknameUpdate extends ListenerAdapter {

    JSONObject whitelist = getWhitelist();

    private static void privateMessage(User user, String str) {
        user.openPrivateChannel().queue((channel) ->
                channel.sendMessage(str).queue()
        );
    }

    private static void sout(String str) {
        System.out.println(str);
    }

    @Override
    public void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent event) {

        User member = event.getMember().getUser();
        String old_nick = member.getName();
        String new_nick = event.getNewNickname();
        String guild_name = event.getGuild().getName();

        if (!member.isBot() && whitelist.has(new_nick)) {

            String admin_id = whitelist.getString(new_nick);
            User admin = event.getJDA().getUserById(admin_id);

            if (admin == null) {
                sout("User [" + old_nick + "] tried to impersonate [" + new_nick + "] in " + guild_name + " but the admin [" + admin_id + "] was not found!");
                return;
            }

            // Reset nickname:
            event.getGuild().modifyNickname(event.getMember(), old_nick).queue();

            // Notify user:
            privateMessage(member, "You are not allowed to use that nickname [" + admin.getName() + "] in " + guild_name + "!\n" +
                    "Your nickname has been reset to " + old_nick + "!");

            // Notify admin:
            privateMessage(admin, "User " + old_nick + " in " + guild_name + " attempted to impersonate you, but was thwarted by my awesomeness!");

            // Log to console:
            sout("User [" + old_nick + "] tried to impersonate [" + new_nick + "] in " + guild_name + "!");
        }
    }
}
