package com.chalwk.util;

import com.chalwk.EventListeners.NicknameUpdate;
import com.chalwk.EventListeners.GuildReady;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import java.io.IOException;

public class Builder {

    /**
     * Builds the bot using shard manager:
     *
     * @throws IOException if the bot token is invalid.
     */
    public static void build() throws IOException {
        String token = Auth.getToken();

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("YOU"));
        builder.setMemberCachePolicy(MemberCachePolicy.ALL); // really important otherwise the bot won't be able to get the nicknames of the members.
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_PRESENCES,
                GatewayIntent.MESSAGE_CONTENT);

        ShardManager shardManager = builder.build();
        shardManager.addEventListener(new GuildReady());
        shardManager.addEventListener(new NicknameUpdate());
    }
}
