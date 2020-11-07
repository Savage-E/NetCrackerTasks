package com.netcracker;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

/**
 * Represents the Digital TV contract.
 * <p>
 * Please see the {@link com.netcracker.Contract} class for true identity
 *
 * @author Vlad Kotov
 */
public class DigitalTVContract extends Contract {
    private @NotNull String channelPack;

    /**
     * Creates a Digital TV Contract.
     *
     * @param id          the contract id
     * @param startDate   the date of the beginning of the contract
     * @param endDate     the end date of the contract
     * @param person      person entity
     * @param channelPack available channels in the contract
     */
    public DigitalTVContract(int id, @NotNull LocalDate startDate, @NotNull LocalDate endDate, @NotNull Person person, @NotNull String channelPack) {
        super(id, startDate, endDate, person);
        this.channelPack = channelPack;
    }

    public @NotNull String getChannelPack() {
        return channelPack;
    }

    public void setChannelPack(@NotNull String channelPack) {
        this.channelPack = channelPack;
    }
}
