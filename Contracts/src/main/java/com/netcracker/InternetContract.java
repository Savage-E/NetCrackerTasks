package com.netcracker;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

/**
 * Represents the entity of Internet contracts.
 * <p>
 * Please see the {@link com.netcracker.Contract} class for true identity
 *
 * @author Vlad Kotov
 */
public final class InternetContract extends Contract {
    private int maxSpeed;

    /**
     * Creates an Internet Contract.
     *
     * @param id     the  contract id
     * @param startDate the date of the beginning of the contract
     * @param endDate   the end date of the contract
     * @param client    the client entity
     * @param maxSpeed  the max speed of the internet in the contract
     */
    public InternetContract(int id, @NotNull LocalDate startDate, @NotNull LocalDate endDate, @NotNull Client client, int maxSpeed) {
        super(id, startDate, endDate, client);
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
