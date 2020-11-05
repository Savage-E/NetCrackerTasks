package com.netcracker;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

public class DigitalTVContract extends Contract {
    private @NotNull String channelPack;

    public DigitalTVContract(int id, @NotNull LocalDate startDate, @NotNull LocalDate endDate, @NotNull Client client, @NotNull String channelPack) {
        super(id, startDate, endDate, client);
        this.channelPack = channelPack;
    }

    public @NotNull String getChannelPack() {
        return channelPack;
    }

    public void setChannelPack(@NotNull String channelPack) {
        this.channelPack = channelPack;
    }
}
